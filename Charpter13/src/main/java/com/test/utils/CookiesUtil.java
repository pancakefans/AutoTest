package com.test.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;
@Log4j
public  class CookiesUtil {
	public static Boolean verifyCookies(HttpServletRequest request) {
		Cookie[] cookie=request.getCookies();
		if (cookie ==null) {
			log.info("cookies为空");
			return false;
		}
		for(Cookie c:cookie) {
			if (c.getName().equals("login")&&c.getValue().equals("true")) {
				 log.info("cookies验证通过");
	                return true;
			}
		}
		return false;
	}

}
