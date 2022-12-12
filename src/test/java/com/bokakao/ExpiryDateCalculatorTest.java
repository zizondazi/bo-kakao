package com.bokakao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

	@Test
	void pay_10000_won() {
		assertExpiryDate(
				PayData.builder()
					.billingDate(LocalDate.of(2022, 12, 5))
					.payAmount(10_000)
					.build(),
				LocalDate.of(2023, 1, 5));
	}
	
	@Test
	void 납부일과_한달_뒤_일자가_같지_않음() {
		assertExpiryDate(
				PayData.builder()
					.billingDate(LocalDate.of(2023, 1, 31))
					.payAmount(10_000)
					.build(),
				LocalDate.of(2023, 2, 28));
	}
	
	@Test
	void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
		assertExpiryDate(
				PayData.builder()
					.firstBillingDate(LocalDate.of(2019, 1, 31))
					.billingDate(LocalDate.of(2019, 2, 28))
					.payAmount(10_000)
					.build(),
				LocalDate.of(2019, 3, 31));
		assertExpiryDate(
				PayData.builder()
				.firstBillingDate(LocalDate.of(2019, 1, 30))
				.billingDate(LocalDate.of(2019, 2, 28))
				.payAmount(10_000)
				.build(),
				LocalDate.of(2019, 3, 30));
		assertExpiryDate(
				PayData.builder()
				.firstBillingDate(LocalDate.of(2019, 5, 31))
				.billingDate(LocalDate.of(2019, 6, 30))
				.payAmount(10_000)
				.build(),
				LocalDate.of(2019, 7, 31));
	}
	
	@Test
	void 이만원_이상_납부하면_비례해서_계산() {
		assertExpiryDate(
				PayData.builder()
				.billingDate(LocalDate.of(2019, 6, 30))
				.payAmount(20_000)
				.build(),
				LocalDate.of(2019, 8, 30));
	}
	
	@Test
	void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
		assertExpiryDate(
				PayData.builder()
				.firstBillingDate(LocalDate.of(2019, 1, 31))
				.billingDate(LocalDate.of(2019, 2, 28))
				.payAmount(20_000)
				.build(),
				LocalDate.of(2019, 4, 30));
	}
	
	@Test
	void 십만원_납부_시_1년_제공() {
		assertExpiryDate(
				PayData.builder()
				.firstBillingDate(LocalDate.of(2019, 1, 31))
				.billingDate(LocalDate.of(2019, 2, 28))
				.payAmount(100_000)
				.build(),
				LocalDate.of(2020, 2, 29));
	}
	
	@Test
	void 십만원_이상_납부_시_테스트() {
		assertExpiryDate(
				PayData.builder()
				.firstBillingDate(LocalDate.of(2019, 1, 31))
				.billingDate(LocalDate.of(2019, 2, 28))
				.payAmount(230_000)
				.build(),
				LocalDate.of(2021, 5, 31));
	}
	
	private void assertExpiryDate(PayData payData, LocalDate expiryDate) {
		ExpiryDateCalculaor cal = new ExpiryDateCalculaor();
		LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
		
		assertEquals(realExpiryDate, expiryDate);
	}
}
