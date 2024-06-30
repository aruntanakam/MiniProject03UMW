package com.umw.commons;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtils {
	
	public static String generateTempPassword()
	{
		String s=RandomStringUtils.randomAlphanumeric(10);
		return s;
	}

}
