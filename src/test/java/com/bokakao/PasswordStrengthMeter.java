package com.bokakao;

public class PasswordStrengthMeter {
	public PasswordStrength meter(String password) {
		if(password == null || password.isEmpty()) return PasswordStrength.INVALID;
		int metCounts = 0;
		
		if(password.length() >= 8) metCounts ++;
		if(meetsContainsUpperCase(password)) metCounts ++;
		if(meetsContainsNumber(password)) metCounts ++;
		
		if(metCounts <= 1) return PasswordStrength.WEAK;
		else if(metCounts == 2) return PasswordStrength.NORMAL;
		
		return PasswordStrength.STRONG;
	}
	
	private boolean meetsContainsUpperCase(String password) {
		for(char ch : password.toCharArray()) {
			if(Character.isUpperCase(ch)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean meetsContainsNumber(String password) {
		for(char ch : password.toCharArray()) {
			if(ch >= '0' && ch <='9') {
				return true;
			}
		}
		return false;
	}
}
