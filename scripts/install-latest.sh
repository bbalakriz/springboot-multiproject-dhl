#/bin/bash
while true
do
	# get the latest version number of the jar available in nexus - by sorting the version number
	NEW_VERSION=$(curl -s "http://admin:admin123@nexus-bala-test.apps.prototype-03.bbfy.p1.openshiftapps.com/repository/dhl-rules/com/bala/wrapper-ruleservice/maven-metadata.xml" | \
	grep "<version>.*</version>" | \
	sort --version-sort | uniq | tail -n1 | \
	sed -e "s#\(.*\)\(<version>\)\(.*\)\(</version>\)\(.*\)#\3#g")
	echo "Latest version of wrapper-ruleservice available in Nexus is: ${NEW_VERSION}"
	
	# get the version of jar that's currently running
	CURRENT_VERSION=$(ps -ef | grep -v grep | grep -Po 'wrapper-ruleservice-\K[^jar]*' | sed -e 's/\.$//')
	echo "Current version of wrapper-ruleservice runnning on this machine is: ${CURRENT_VERSION}"

	# check if the latest version is not the same as current version
	if [[ $CURRENT_VERSION != *"$NEW_VERSION"* ]]; then
		echo "New version is available - downloading......."
		# download the latest version 
		WGET_RESPONSE=$(wget http://nexus-bala-test.apps.prototype-03.bbfy.p1.openshiftapps.com/repository/dhl-rules/com/bala/wrapper-ruleservice/$NEW_VERSION/wrapper-ruleservice-$NEW_VERSION.jar)
		if [[ $? -eq 0 ]]; then
			# get the process id of the currently running jar and kill it
			PID=$(ps -ef | grep -v grep | grep "java -jar wrapper-ruleservice" | cut -d " " -f3)
			kill -9 $PID
			# execute the latest version of jar
			nohup java -jar wrapper-ruleservice-$NEW_VERSION.jar &
		fi
	fi
  # repeat this every 15 seconds
  sleep 30
done

# mvn deploy -DaltDeploymentRepository='nexus::default::http://admin:admin123@nexus-bala-test.apps.prototype-03.bbfy.p1.openshiftapps.com/repository/dhl-rules/'

