package com.tdmobile.template.controller.api;

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

import com.tdmobile.template.exceptions.UsersExceptions;
import com.tdmobile.template.model.users.Login;
import com.tdmobile.template.services.UsersService;

@RestController
@RequestMapping("/api/access/")
public class ControlAccessRestController {

    @Autowired
    UsersService userService;

    @Autowired
    private MessageSource messageSource;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "login.do", method = {
	    RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JsonResponseView login(HttpServletRequest request, @RequestBody(required = true) Login login,@RequestHeader(name="Content-Language",required=false) String locale) {
	JsonResponseView json = new JsonResponseView();
	try {
	    userService.loginApi(login);
	    json.getResponse().put("data", userService.findUserApi(login.getUser()));
	} catch (UsersExceptions ex) {
	    json.getResponse().put("success", false);
	    json.getResponse().put("error", messageSource.getMessage(ex.getMessage(), null, new Locale(locale.split("_")[0])));
	}
	return json;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "logout.do", method = {
	    RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody JsonResponseView logout(HttpServletRequest request,
	    @RequestHeader(name = "TokenSystem", required = true) String token,@RequestHeader(name="Content-Language",required=false) String locale) {
	JsonResponseView json = new JsonResponseView();
	try {
	    userService.logoutApi(token);
	} catch (UsersExceptions ex) {
	    json.getResponse().put("success", false);
	    json.getResponse().put("error", messageSource.getMessage(ex.getMessage(), null, new Locale(locale.split("_")[0])));
	}
	return json;
    }
}
