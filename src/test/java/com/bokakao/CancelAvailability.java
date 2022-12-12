package com.bokakao;

import java.time.LocalDate;

public class CancelAvailability {
	
	public boolean cancelAvailability(LocalDate deliveryCompleted) {
		
		// 오늘-7 > 배송완료 날짜 
		if(LocalDate.now().minusDays(7).isAfter(deliveryCompleted)) {
			return false;
		}
		return true;
	}
}
