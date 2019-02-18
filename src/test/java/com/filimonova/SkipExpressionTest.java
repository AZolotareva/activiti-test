package com.filimonova;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.test.Deployment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkipExpressionTest extends PluggableActivitiTestCase {

    @Deployment(resources = {"ExpressionServiceTaskTest.testSetServiceResultToProcessVariablesWithSkipExpression.bpmn20.xml"})
    public void testSkip(){
        Map<String, Object> variables = new HashMap<>();
        variables.put("skip", true);
        variables.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED", true);
        runtimeService.startProcessInstanceByKey("setServiceResultToProcessVariablesWithSkipExpression", variables);
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().list();
        assertTrue(list.size()==2);
    }

}
