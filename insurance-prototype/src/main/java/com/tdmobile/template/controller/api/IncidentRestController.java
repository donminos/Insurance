package com.tdmobile.template.controller.api;

import java.io.Serializable;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tdmobile.template.model.incidents.Create;
import com.tdmobile.template.services.IncidentServices;

@RestController
@RequestMapping("/api/incidents/")
public class IncidentRestController implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -7858851581461471600L;
    
    @Autowired
    IncidentServices incidentServices;
    
    @Autowired
    private MessageSource messageSource;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "create.do", method = {RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JsonResponseView create(HttpServletRequest request, @RequestBody(required = true) Create create ,@RequestHeader(name = "Content-Language", required = false) String locale) {
	JsonResponseView json = new JsonResponseView();
	try {
	    incidentServices.insert(create);
	} catch (Exception ex) {
	    json.getResponse().put("success", false);
	    json.getResponse().put("error",messageSource.getMessage(ex.getMessage(), null, new Locale(locale.split("_")[0])));
	}
	return json;
    }

}
