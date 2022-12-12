package com.bokakao.order.domain;

import com.bokakao.util.domain.CommonDomain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class OrderDomain extends CommonDomain {
	
	private String ord_num;
	private String ord_uid;
	private String ord_dtm;
	private String pay_status;
	private String delivery_price;
	private String use_point;
	private String ord_nm;
	private String ord_phone_num;
	private String ord_email;
	private String recipient_nm;
	private String recipient_phone_num;
	private String zip;
	private String address;
	private String address_etc;
	private String messeage;
}