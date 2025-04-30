package com.toeic.util;

public class PrintUtil {
	public static final boolean isDebug = true;
	
	public static void print(String str) {
		if(!isDebug) {
			return;
		}

		System.out.println(str);
	}
}