package com.film.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCheck {
	public static boolean isNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;

    }
	
	public static boolean isPassword(String str) {
        String code="^[a-zA-Z]\\w{7,19}$";
        Pattern p = Pattern.compile(code);  
        Matcher m = p.matcher(str); 
        return m.matches();
		
	}
	
	public static boolean isPhone(String str) {
		String code="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		Pattern p = Pattern.compile(code);  
		Matcher m = p.matcher(str);  
		return m.matches();
	}
}
