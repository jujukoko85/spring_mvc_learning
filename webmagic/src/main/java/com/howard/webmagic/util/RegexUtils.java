package com.howard.webmagic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	private RegexUtils() {}
	
	public static String findGroup(String source, String regex, int group) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while(matcher.find()) {
			int groupCount = matcher.groupCount();
			
			if(group > groupCount) return null;
			
			for(int i = 0 ; i <= groupCount; i++) {
				if(i == group)
					return matcher.group(i);
			}
		}
		return null;
	}
}
