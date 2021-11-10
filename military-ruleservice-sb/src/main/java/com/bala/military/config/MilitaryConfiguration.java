package com.bala.military.config;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MilitaryConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(MilitaryConfiguration.class);

	@Qualifier("mrKieContainer")
	@Bean
	public KieContainer mrKieContainer() throws IOException {

		KieContainer kContainer = kieServices().newKieClasspathContainer();
		// check if the kiecontainer is loaded without any issues
		performSanityCheck(kContainer);
		return kContainer;
	}

	private KieServices kieServices() {
		return KieServices.Factory.get();
	}

	private void performSanityCheck(KieContainer kContainer) {
		// Check if all the resources are loaded correctly
		Results results = kContainer.verify();
		results.getMessages().stream().forEach((message) -> {
			LOGGER.info(">> Message ( {} ): {}", message.getLevel(), message.getText());
		});

		// If there is any Error, stop and correct it
		boolean hasError = results.hasMessages(Message.Level.ERROR);
		LOGGER.info("Any Error : {}", hasError);
		if (hasError) {
			throw new UnsupportedOperationException();
		}

		// Make sure that the expected KieBases and KieSessions are loaded.
		kContainer.getKieBaseNames().stream().map((kieBase) -> {
			LOGGER.info(">> Loading KieBase: {}", kieBase);
			return kieBase;
		}).forEach((kieBase) -> {
			kContainer.getKieSessionNamesInKieBase(kieBase).stream().forEach((kieSession) -> {
				LOGGER.info("\t >> Containing KieSession: {}", kieSession);
			});
		});
	}
}