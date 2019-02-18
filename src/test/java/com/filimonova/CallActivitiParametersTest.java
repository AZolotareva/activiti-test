package com.filimonova;

import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Egorov1-EA on 31.10.2018
 */
public class CallActivitiParametersTest extends PluggableActivitiTestCase {

    //Данный тесткейс запускает прцесс, в котором передает переменную в callActiviti, где она сначала меняется значение в UserTask,
    //а далее на ServiceTask. Значение на выходе должно быть равно измененному на userTask.
    @Deployment(resources = {"callActivitiTest/CallActivitiParametersTest.testCallActivitiParametersWithObjectChildTest.bpmn",
            "callActivitiTest/CallActivitiParametersTest.testCallActivitiParametersWithObjectTest.bpmn"})
    public void testCallActivitiParametersWithObjectTest(){
        ExecutionContext executionContext = new ExecutionContext();
        executionContext.setParameterByName("param1", "qwerty");
        executionContext.setParameterByName("param2", true);
        executionContext.setParameterByName("param3", 123);
//a799f4e2-5d2c-46da-b3df-a4e7f83cf99c
        //f289158d-a63d-40b3-a699-a50b243bcc62
        Map<String, Object> params = new HashMap<>();
        //params.put("serviceContext", executionContext);
        params.put("inVariable", 4L);

       ProcessInstance instance = runtimeService.startProcessInstanceByKey("f289158d-a63d-40b3-a699-a50b243bcc62");

        Task task = taskService.createTaskQuery().singleResult();

        params = new HashMap<>();
        executionContext.setParameterByName("param1", "asdfg");
        executionContext.setParameterByName("param2", false);
        executionContext.setParameterByName("param3", 321);
        //params.put("serviceContext", executionContext);
        params.put("inVariable", 5L);
        taskService.complete(task.getId(), params);
        System.out.println("sksks");

        List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery().list();
        //assertTrue(variableInstances.size()==2);
        Map<String, Object> processVariables = historyService.createHistoricProcessInstanceQuery().processInstanceId(instance.getId()).includeProcessVariables().singleResult().getProcessVariables();
        System.out.println("ksk");
        /*bpmApiService.completeUserTask(sbtTask.getId(), data);
        getTestCaseHelper().waitForProcessEnded(sbtProcessInstance.getId(), 15000);
        DataObjectWrapper processInstanceContext = processInstanceService.getProcessInstanceContext(sbtProcessInstance);
        ExecutionContext variable = (ExecutionContext) processInstanceContext.getObjectData().get("serviceContext");
        assertEquals("Значение переменной должно быть равно asdfg", "asdfg", variable.getParameterByName("param1"));
        assertEquals("Значение переменной должно быть равно false", new Boolean(false), variable.getParameterByName("param2"));
        assertEquals("Значение переменной должно быть равно 321", new Integer(321), variable.getParameterByName("param3"));
        assertEquals("Значение переменной должно быть равно 5", 5L, (long)processInstanceContext.getObjectData().get("inVariable"));*/
    }

    //Данный тесткейс запускает прцесс, в котором передает переменную в callActiviti, где она сначала меняется значение в UserTask,
    //а далее на ServiceTask. Значение на выходе должно быть равно измененному в сервис таск.
    /*@Deployment(resources = {"com/sbt/bpms/engine/usecases/callActivitiTest/CallActivitiParametersTest.testCallActivitiParametersWithObjectCorrectChildTest.bpmn",
            "com/sbt/bpms/engine/usecases/callActivitiTest/CallActivitiParametersTest.testCallActivitiParametersWithObjectCorrectTest.bpmn"})
    public void testCallActivitiParametersWithObjectCorrectTest(){
        IExecutionContext executionContext = new ExecutionContext();
        executionContext.setParameterByName("param1", "qwerty");
        executionContext.setParameterByName("param2", true);
        executionContext.setParameterByName("param3", 123);

        Map<String, Object> params = new HashMap<>();
        params.put("inVariable", 4L);
        params.put("serviceContext", executionContext);

        DataObjectWrapper data = new DataObjectWrapper();
        data.setObjectData(params);

        Message message = new Message("inputData");
        message.setData(data);

        SbtProcessDefinition sbtProcessDefinition = new SbtProcessDefinition("c54c6734-5425-421f-8998-658ca7d90e75");
        SbtProcessInstance sbtProcessInstance = processInstanceService.startProcessInstanceWithMessage(sbtProcessDefinition, message, null);
        getTestCaseHelper().waitForActiveTaskCount(1, null, 15000);
        Task task = taskService.createTaskQuery().singleResult();
        UserTask sbtTask = new UserTask();
        sbtTask.setId(task.getId());;
        userTaskService.assignUserTask(new SbtUser(clientSecurityService.getUser().getLogin()),sbtTask);
        params = new HashMap<>();
        executionContext.setParameterByName("param1", "asdfg");
        executionContext.setParameterByName("param2", false);
        executionContext.setParameterByName("param3", 321);
        params.put("inVariableLocal", 5L);
        params.put("serviceContextLocal", executionContext);
        data.setObjectData(params);
        bpmApiService.completeUserTask(sbtTask.getId(), data);
        getTestCaseHelper().waitForProcessEnded(sbtProcessInstance.getId(), 15000);
        DataObjectWrapper processInstanceContext = processInstanceService.getProcessInstanceContext(sbtProcessInstance);
        ExecutionContext variable = (ExecutionContext) processInstanceContext.getObjectData().get("serviceContext");
        assertEquals("Значение переменной должно быть равно zxcvb", "zxcvb", variable.getParameterByName("param1"));
        assertEquals("Значение переменной должно быть равно true", new Boolean(true), variable.getParameterByName("param2"));
        assertEquals("Значение переменной должно быть равно 666", new Integer(666), variable.getParameterByName("param3"));
        assertEquals("Значение переменной должно быть равно 6", 6L, (long)processInstanceContext.getObjectData().get("inVariable"));
    }*/
}

