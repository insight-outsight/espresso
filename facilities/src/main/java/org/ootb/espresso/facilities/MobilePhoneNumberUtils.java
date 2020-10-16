package org.ootb.espresso.facilities;

import org.apache.commons.lang3.StringUtils;

public class MobilePhoneNumberUtils {

	private final static String PHONE_NUMBER_PATTERN = 
			"^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
	
	public static boolean isMobileNumber(String s){
		return StringUtils.isNotBlank(s) && s.matches(PHONE_NUMBER_PATTERN);
	}

	public static void main(String[] args) {
		System.out.println("1."+("18016381232".matches(PHONE_NUMBER_PATTERN)));
		System.out.println("2."+("18511381232".matches(PHONE_NUMBER_PATTERN)));
		System.out.println("3."+("13800138000".matches(PHONE_NUMBER_PATTERN)));
		System.out.println("4."+("18016382321".matches(PHONE_NUMBER_PATTERN)));
		System.out.println("5."+("  ".matches(PHONE_NUMBER_PATTERN)));
		System.out.println("6."+("18016382321".matches(PHONE_NUMBER_PATTERN)));
		System.out.println("7."+isMobileNumber(null));
		System.out.println("8."+isMobileNumber("13961232888"));
		System.out.println("9."+isMobileNumber("9161232888"));
	}

}