package com.bokakao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CancelAvailabilityTest {
	
	@Test
	@DisplayName("배송완료 후 7일이 지난 경우")
	void deliveryCompleted_after_7days() {
		LocalDate deliveryCompleDate = LocalDate.of(2022, 12, 1);
		CancelAvailability cancel = new CancelAvailability();
		boolean isCancel = cancel.cancelAvailability(deliveryCompleDate);
		assertEquals(false, isCancel);
	}
}
