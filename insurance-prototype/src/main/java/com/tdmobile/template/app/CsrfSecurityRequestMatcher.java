package com.tdmobile.template.app;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class CsrfSecurityRequestMatcher implements RequestMatcher {
    private final Pattern allowedMethods = Pattern.compile("^(GET)$");
    private final Pattern unprotectedMatcher = Pattern.compile("^/api.*");
    //private final RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/unprotectedSystems/", null);

    @Override
    public boolean matches(HttpServletRequest request) {
        if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
        }

        return !unprotectedMatcher.matcher(request.getRequestURI()).matches();
    }
}
