package com.tdmobile.template.controller;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, Principal principal, RedirectAttributes flash,
	    @RequestParam(required = false) String error,Locale locale) {
	if (principal != null) {
	    flash.addFlashAttribute("info", messageSource.getMessage("text.repeat.login", null, locale));
	    return "redirect:/";
	}
	if (error != null) {
	    model.addAttribute("error", messageSource.getMessage("text.error.login", null, locale));
	}
	return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, RedirectAttributes flash) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null) {
	    new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	flash.addFlashAttribute("info", "Ha cerrado sesión correctamente");
	return "redirect:/?logout";
    }
}
