package com.bokakao;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculaor {
	
	public LocalDate calculateExpiryDate(PayData data) {
		int addedMonths = data.getPayAmount() / 10_000;
		
		int share = (int) addedMonths / 10;
		int remainder = addedMonths % 10;
		addedMonths = (share * 12) + remainder;
		
		if(data.getFirstBillingDate() != null) {
			return expiryDateUsingFirstBillingDate(data, addedMonths);
		}else {
			return data.getBillingDate().plusMonths(addedMonths);
		}
		
	}
	
	private LocalDate expiryDateUsingFirstBillingDate(PayData data, int addedMonths) {
		LocalDate candidateExp = data.getBillingDate().plusMonths(addedMonths);
		final int dayOfFirstBilling = data.getFirstBillingDate().getDayOfMonth();
		final int lastDayOfMonthByCanExp = YearMonth.from(candidateExp).lengthOfMonth();
		
		if(dayOfFirstBilling != candidateExp.getDayOfMonth()) {
			if(lastDayOfMonthByCanExp < data.getFirstBillingDate().lengthOfMonth()) {
				return candidateExp.withDayOfMonth(lastDayOfMonthByCanExp);
			}
			return candidateExp.withDayOfMonth(dayOfFirstBilling);
		}else {
			return candidateExp;
		}
	}
}
