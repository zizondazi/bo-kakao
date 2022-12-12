package com.bokakao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {
	PasswordStrengthMeter meter = new PasswordStrengthMeter();
	
	@Test
	void meetsAllCriteria_Then_Strong() {
		assertStrength(PasswordStrength.STRONG, "ab12!@AB");
		assertStrength(PasswordStrength.STRONG, "abc!2CCD");
	}
	
	@Test
	void meetsOtherCriteria_except_for_Length_Then_Normal() {
		assertStrength(PasswordStrength.NORMAL, "abc1!A");
	}
	
	@Test
	void meetsOtherCriteria_except_for_number_Then_Normal() {
		assertStrength(PasswordStrength.NORMAL, "abcd!AEn");
	}
	
	@Test
	void nullInput_Then_Invalid() {
		assertStrength(PasswordStrength.INVALID, null);
	}
	
	@Test
	void emptyInput_Then_Invalid() {
		assertStrength(PasswordStrength.INVALID, "");
	}
	
	@Test
	void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
		assertStrength(PasswordStrength.NORMAL, "adbd!@2dd");
	}
	
	@Test
	void meetsOnlyLengthCriteria_Then_Weak() {
		assertStrength(PasswordStrength.WEAK, "abcdefsf");
	}
	
	@Test
	void meetsOnlyNumberCriteria_Then_Weak() {
		assertStrength(PasswordStrength.WEAK, "1234");
	}
	
	@Test
	void meetsOnlyUppserCaseCriteria_Then_Weak() {
		assertStrength(PasswordStrength.WEAK, "AVCD");
	}
	
	private void assertStrength(PasswordStrength expStr, String password) {
		PasswordStrength result = meter.meter(password);
		assertEquals(expStr, result);
	}
}
