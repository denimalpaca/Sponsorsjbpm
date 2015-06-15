package Sponsors;

import java.util.HashMap;
import java.util.Map;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
//import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.manager.RuntimeEnvironment;
//import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.drools.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.drools.builder.KnowledgeBuilder;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;

public class Main extends JbpmJUnitBaseTestCase {	
	@Test
	public void testMainProcess() throws Exception {
		//Newinfo newinfo = new Newinfo();
		MySQLAccess sqla = new MySQLAccess();
		
		/*
		KnowledgeBase kbase = readKnowledgeBase();
		//RuntimeManager manager = createRuntimeManager("sponsorflow.bpmn");
		RuntimeEngine engine = getRuntimeEngine(null);
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		TaskService taskService = engine.getTaskService();
		org.drools.runtime.process.ProcessInstance processInstance = ksession.startProcess("Sponsors.sponsorflow");
		*/
		
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("sponsorflow.bpmn"), ResourceType.BPMN2);
        KnowledgeBase kbase = kbuilder.newKnowledgeBase();
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        ProcessInstance processInstance = ksession.startProcess("Sponsors.sponsorflow");
		System.out.println("Process" + processInstance.getProcessName() + " started.");
		RuntimeManager manager = createRuntimeManager("sponsorflow.bpmn");
		RuntimeEngine engine = getRuntimeEngine(null);
		TaskService taskService = engine.getTaskService();

		//login
		Task task = taskService.getTaskById(4);
		System.out.println(task.getName());
		taskService.start(task.getId(), "user");
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("valid", sqla.loginSuccess);
		taskService.complete(task.getId(), "user", results);
		
		
		
		manager.disposeRuntimeEngine(engine);
		ksession.dispose();
		manager.close();
	}
	
	/*
    private static KnowledgeBase readKnowledgeBase() throws Exception {
        org.drools.builder.KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("sponsorflow.bpmn"), ResourceType.BPMN2);
        return kbuilder.newKnowledgeBase();
    }
	*/
	public Main() {
		super(true, true);
	}
}
