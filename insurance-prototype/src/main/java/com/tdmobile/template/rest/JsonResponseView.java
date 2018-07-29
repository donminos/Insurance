package com.tdmobile.template.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonResponseView implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonResponseView(){
        response=new HashMap<Object, Object>();
        response.put("success", true);
    }
    private Map<Object, Object> response;

    public Map<Object, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<Object, Object> response) {
        this.response = response;
    }
}
