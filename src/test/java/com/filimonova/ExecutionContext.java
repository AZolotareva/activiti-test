package com.filimonova;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {
    private Map parameter = new HashMap();

    public Object getParameterByName(String name) {
        return parameter.get(name);
    }

    public void setParameterByName(String name, Object value){
        parameter.put(name, value);
    }
}
