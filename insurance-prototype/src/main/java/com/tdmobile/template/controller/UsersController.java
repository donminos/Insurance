package com.tdmobile.template.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tdmobile.template.model.users.Create;
import com.tdmobile.template.services.RolesService;
import com.tdmobile.template.services.UsersService;

@Controller
@RequestMapping("/users/")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    RolesService roleService;

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
	model.addAttribute("Users", usersService.findAll());
	return "Users/index";
    }

    @Secured({ "ROLE_ADMIN" })
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Model model, @RequestParam(required = false) Integer idUser) {
	Create user = new Create();
	if (idUser != null) {
	    user = usersService.findId(idUser);
	}
	model.addAttribute("usercreate", user);
	model.addAttribute("roles", roleService.findAll());
	return "Users/form";
    }

    @Secured({ "ROLE_ADMIN" })
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String crear(Model model, @Valid @ModelAttribute("usercreate") Create user, BindingResult error) {
	if (error.hasErrors()) {
	    model.addAttribute("roles", roleService.findAll());	    
	    return "Users/form";
	}
	if (user.getIdUser() != null) {
	    usersService.edit(user);
	} else {
	    usersService.insert(user);
	}
	return "redirect:/users/";
    }
}
