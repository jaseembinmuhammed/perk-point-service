package com.service.perkpoint.base;

public class PpStringUtil {

	public static String maskString(String input) {
		if (input == null || input.isEmpty()) {
			return input; // Return as is for null or empty input
		}

		int length = input.length();
		int maskStartIndex = length / 2;
		StringBuilder masked = new StringBuilder(input.substring(0, maskStartIndex));

		for (int i = maskStartIndex; i < length; i++) {
			masked.append("*"); // Append '*' for each character in the second half
		}

		return masked.toString();
	}

}
