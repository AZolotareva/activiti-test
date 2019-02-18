package com.filimonova;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import static org.junit.Assert.assertEquals;

/**
 * Created by Egorov1-EA on 31.10.2018
 */
public class BaseServiceTaskForObjectCorrectExecutor implements JavaDelegate {

    //1)Происходит проверка значений, которые были изменены в UserTask.
    //2)В serviceTask изменяются эти значения на новые, которые дальше передаютя на выход call activiti
    @Override
    public void execute(final DelegateExecution delegateExecution) {
        ExecutionContext serviceContext = (ExecutionContext) delegateExecution.getVariable("serviceContext");
        long inVariable = (Long) delegateExecution.getVariable("inVariable");
        assertEquals("Значение переменной должно быть равно asdfg", "asdfg", serviceContext.getParameterByName("param1"));
        assertEquals("Значение переменной должно быть равно false", false, serviceContext.getParameterByName("param2"));
        assertEquals("Значение переменной должно быть равно 321", new Integer(321), serviceContext.getParameterByName("param3"));
        assertEquals("Значение переменной должно быть равно 5", 5L, inVariable);
        ExecutionContext newVals = new ExecutionContext();
        newVals.setParameterByName("param1", "zxcvb");
        newVals.setParameterByName("param2", true);
        newVals.setParameterByName("param3", 666);
        delegateExecution.setVariable("serviceContext", newVals);
        delegateExecution.setVariable("inVariable", 6L);
    }
}

