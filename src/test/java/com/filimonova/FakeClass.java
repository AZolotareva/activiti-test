package com.filimonova;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class FakeClass implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("fake task");
        delegateExecution.setVariable("s", "dhdhdhdh");
        delegateExecution.setVariableLocal("s1", "q1w2e3e3");
    }
}
