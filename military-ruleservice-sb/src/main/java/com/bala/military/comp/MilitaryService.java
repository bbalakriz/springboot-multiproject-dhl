package com.bala.military.comp;

import com.bala.military.comp.model.Item;

import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.DisposeCommand;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.GetObjectsCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
 
@Service
public class MilitaryService {
 
    @Qualifier("mrKieContainer")
    @Autowired
    private KieContainer mrKieContainer;
 
    public void checkMilitaryRules(Item item) {
        StatelessKieSession kieSession = mrKieContainer.newStatelessKieSession("ml-session");
        
        BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
		command.addCommand(new InsertObjectCommand(item));
		command.addCommand(new FireAllRulesCommand());
		command.addCommand(new GetObjectsCommand());
		command.addCommand(new DisposeCommand());
        
        kieSession.execute(command);
    }
}