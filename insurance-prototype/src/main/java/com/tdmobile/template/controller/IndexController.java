package com.tdmobile.template.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listar(Model model) {
	return "index";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String denied(Model model) {
	return "access_denied";
    }
}
