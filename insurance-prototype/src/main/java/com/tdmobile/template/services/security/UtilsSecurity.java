package com.tdmobile.template.services.security;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

public class UtilsSecurity {
    public static String secureToken() {
	SecureRandom random = new SecureRandom();
	byte bytes[] = new byte[128];
	random.nextBytes(bytes);
	Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	String token = encoder.encodeToString(bytes);
	System.out.println(token);
	return token;
    }
}
