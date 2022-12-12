package com.bokakao.order.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class OrderDetailDomain extends OrderDomain {
	private String prdt_seq;
	private Integer prdt_count;
	private Integer prdt_price;
	private String ord_status;
	private String cs_status;
	private String confirm_dtm;
}
