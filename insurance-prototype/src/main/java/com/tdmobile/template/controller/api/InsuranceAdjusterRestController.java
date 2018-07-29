package com.tdmobile.template.controller.api;

import java.util.Enumeration;
import java.util.List;
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

import com.tdmobile.template.model.insuranceadjuster.CreateLocation;
import com.tdmobile.template.services.InsuranceAdjusterService;

@RestController
@RequestMapping("/api/insuranceAdjuster/")
public class InsuranceAdjusterRestController {

    @Autowired
    private InsuranceAdjusterService insuranceAdjusterService;
    
    @Autowired
    private MessageSource messageSource;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "registerLocation.do", method = {
	    RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JsonResponseView registerLocation(HttpServletRequest request
	    ,@RequestBody(required = true) List<CreateLocation> location
	    ,@RequestHeader(name = "Content-Language", required = false) String locale
	    ,@RequestHeader(name = "Token", required = false) String token) {
	JsonResponseView json = new JsonResponseView();
	//try {
	
	insuranceAdjusterService.registerLocation(location.get(0),token);
	/*} catch (InsuranceAdjusterExceptions ex) {
	    json.getResponse().put("success", false);
	    json.getResponse().put("error", messageSource.getMessage(ex.getMessage(), null, locale));
	}*/
	return json;
    }
}
