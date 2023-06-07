package com.tulamweb.common;

import java.util.regex.Pattern;

public class StaticVariable {

		public static String role = "";
		
		public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$");

		
		public static final String HOST = "smtp.gmail.com";
		public static final String POST = "587";
		public static final String MAIL_SEVER = "dotronghai123456789@gmail.com";
		public static final String PASSWORD = "azogzujhdnhhftxg";
		
		
		public static final String PART = "D:\\upload";
}
