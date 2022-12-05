-- friend_shop.cart_mng definition

CREATE TABLE if not exists  `cart_mng` (
  `cart_seq` varchar(30) NOT NULL COMMENT '카트 아이디',
  `prdt_seq` varchar(30) NOT NULL COMMENT '상품 아이디',
  `prdt_cnt` int(30) NOT NULL COMMENT '상품 수',
  `reg_uid` varchar(100) NOT NULL COMMENT '작성자',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
  PRIMARY KEY (`cart_seq`),
  KEY `PK_CART_MNG` (`cart_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.cmm_category definition

CREATE TABLE if not exists  `cmm_category` (
  `cate_seq` varchar(5) NOT NULL COMMENT '카테고리 seq',
  `cate_nm` varchar(20) NOT NULL COMMENT '카테고리 명',
  `cate_up_seq` varchar(20) DEFAULT NULL COMMENT '상위 카테고리 seq',
  PRIMARY KEY (`cate_seq`),
  KEY `PK_CMM_CATEGORY` (`cate_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.cmm_character definition

CREATE TABLE if not exists  `cmm_character` (
  `char_seq` varchar(5) NOT NULL COMMENT '캐릭터 seq',
  `char_nm` varchar(20) NOT NULL COMMENT '캐릭터 명',
  `char_cate_img` varchar(200) NOT NULL COMMENT '캐릭터 카테고리 이미지',
  `char_info_img` varchar(200) DEFAULT NULL COMMENT '캐릭터 설명 이미지',
  `ka_star_info` varchar(200) DEFAULT NULL COMMENT '카스타그램 설명',
  `ka_thum_img` varchar(200) DEFAULT NULL COMMENT '카스타그램 썸네일이미지',
  PRIMARY KEY (`char_seq`),
  KEY `PK_CMM_CHARACTER` (`char_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.ka_star_gram definition

CREATE TABLE if not exists  `ka_star_gram` (
  `ka_star_seq` varchar(30) NOT NULL COMMENT '카스타그램 아이디',
  `char_seq` varchar(5) NOT NULL COMMENT '캐릭터 아이디',
  `ka_star_con` varchar(200) DEFAULT NULL COMMENT '카스타그램 내용',
  `ka_star_rel_url` varchar(200) DEFAULT NULL COMMENT '카스타그램 내용',
  `ka_star_tag` varchar(200) DEFAULT NULL COMMENT '카스타그램 태그(| 구분)',
  `more_name` varchar(200) DEFAULT NULL COMMENT '추가 연결 명',
  `more_url` varchar(200) DEFAULT NULL COMMENT '추가 연결 url',
  `del_yn` varchar(2) DEFAULT 'N' COMMENT '삭제여부',
  `display_sdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '시작일',
  `display_edate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '게시 종료일',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
  PRIMARY KEY (`ka_star_seq`),
  KEY `PK_KA_STAR_GRAM` (`ka_star_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.member_mng definition

CREATE TABLE if not exists  `member_mng` (
  `uid` varchar(100) NOT NULL COMMENT '회원 seq uuid',
  `access_token` varchar(50) NOT NULL COMMENT '엑세스 토큰',
  `refresh_token` varchar(50) NOT NULL COMMENT '리플레쉬 토큰 - 토큰 갱신용',
  `refresh_token_expires_in` int(11) NOT NULL COMMENT '리플레쉬 토큰만료시간',
  `mem_nm` varchar(50) NOT NULL COMMENT '회원명',
  `mem_email` varchar(50) NOT NULL COMMENT '회원 이메일',
  `mem_point` int(11) DEFAULT NULL COMMENT '회원 포인트',
  PRIMARY KEY (`uid`),
  KEY `PK_MEMBER_MNG` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.order_mng definition

CREATE TABLE if not exists  `order_mng` (
  `ord_num` varchar(30) NOT NULL COMMENT '주문번호',
  `ord_uid` varchar(100) NOT NULL COMMENT '주문자아이디',
  `ord_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시 - 주문일',
  `pay_status` varchar(5) NOT NULL COMMENT '결제상태(입금전/입금완료(수동,자동)/결제완료)',
  `delivery_price` int(10) NOT NULL COMMENT '배송금액',
  `use_point` int(10) NOT NULL COMMENT '포인트 사용금액',
  `ord_nm` varchar(50) NOT NULL COMMENT '주문자',
  `ord_phone_num` varchar(10) NOT NULL COMMENT '주문자 전화번호(01012345368) 하이픈 제외',
  `ord_email` varchar(50) NOT NULL COMMENT '주문자 이메일',
  `recipient_nm` varchar(50) NOT NULL COMMENT '받는사람',
  `recipient_phone_num` varchar(10) NOT NULL COMMENT '받는사람 전화번호(01012345368) 하이픈 제외',
  `zip` varchar(6) NOT NULL COMMENT '우편번호',
  `address` varchar(200) NOT NULL COMMENT '주소',
  `address_etc` varchar(200) NOT NULL COMMENT '나머지 주소',
  `messeage` varchar(200) DEFAULT NULL COMMENT '배송메시지',
  PRIMARY KEY (`ord_num`),
  KEY `PK_ORDER_MNG` (`ord_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.product_mng definition

CREATE TABLE if not exists  `product_mng` (
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품seq',
  `prdt_nm` varchar(100) NOT NULL COMMENT '제품명',
  `prdt_price` int(20) NOT NULL COMMENT '제품가격',
  `prdt_sale_price` int(20) DEFAULT 0 COMMENT '세일금액',
  `prdt_img` varchar(200) NOT NULL COMMENT '제품이미지',
  `prdt_stock` int(20) NOT NULL COMMENT '제품재고',
  `prdt_character` varchar(30) DEFAULT NULL COMMENT '제품캐릭터',
  `prdt_season` varchar(30) DEFAULT NULL COMMENT '제품시즌',
  `prdt_dtl` blob DEFAULT NULL COMMENT '제품상세',
  `prdt_info` blob DEFAULT NULL COMMENT '제품정보',
  `korea_shipping_yn` varchar(2) DEFAULT NULL COMMENT '국내배송 여부',
  `intl_shipping_yn` varchar(2) DEFAULT NULL COMMENT '해외배송 여부',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
  `reg_uid` varchar(50) NOT NULL COMMENT '등록 회원',
  `mdf_dtm` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '수정일시',
  `mdf_uid` varchar(50) DEFAULT NULL COMMENT '수정 회원',
  PRIMARY KEY (`prdt_seq`),
  KEY `PK_PRODUCT_MNG` (`prdt_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.product_review definition

CREATE TABLE if not exists  `product_review` (
  `review_seq` varchar(30) NOT NULL COMMENT '리뷰 아이디',
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품 아이디',
  `ord_num` varchar(30) NOT NULL COMMENT '주문 번호',
  `reg_uid` varchar(100) NOT NULL COMMENT '작성자',
  `review_score` int(20) NOT NULL COMMENT '리뷰점수 0~5',
  `review_con` varchar(200) DEFAULT '0' COMMENT '리뷰내용',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
  `del_yn` varchar(2) DEFAULT 'N' COMMENT '삭제여부',
  PRIMARY KEY (`review_seq`),
  KEY `PK_PRODUCT_REVIEW` (`review_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.product_review_good definition

CREATE TABLE if not exists  `product_review_good` (
  `review_seq` varchar(30) NOT NULL COMMENT '리뷰 아이디',
  `reg_uid` varchar(100) NOT NULL COMMENT '작성자',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
  PRIMARY KEY (`review_seq`,`reg_uid`),
  KEY `PK_PRODUCT_REVIEW_GOOD` (`review_seq`,`reg_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.cancel_history definition

CREATE TABLE if not exists  `cancel_history` (
  `pay_history_seq` varchar(30) NOT NULL COMMENT '결제이력코드',
  `ord_num` varchar(30) NOT NULL COMMENT '주문번호',
  `cancel_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '캔슬',
  `cancel_price` int(50) NOT NULL COMMENT '취소금액',
  PRIMARY KEY (`pay_history_seq`),
  KEY `PK_CANCEL_HISTORY` (`pay_history_seq`),
  KEY `FK_ORDER_MNG_TO_CANCEL_HISTORY` (`ord_num`),
  CONSTRAINT `FK_ORDER_MNG_TO_CANCEL_HISTORY` FOREIGN KEY (`ord_num`) REFERENCES `order_mng` (`ord_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.ka_star_gram_comment definition

CREATE TABLE if not exists  `ka_star_gram_comment` (
  `comment_seq` varchar(30) NOT NULL COMMENT '댓글 아이디',
  `ka_star_seq` varchar(30) NOT NULL COMMENT '카스타그램 아이디',
  `comment` varchar(200) NOT NULL COMMENT '댓글내용',
  `up_comment_seq` varchar(30) DEFAULT NULL COMMENT '상위 댓글 아이디',
  `del_yn` varchar(2) DEFAULT 'N' COMMENT '삭제여부',
  `reg_uid` varchar(100) NOT NULL COMMENT '등록자',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
  PRIMARY KEY (`comment_seq`),
  KEY `PK_KA_STAR_GRAM_COMMENT` (`comment_seq`),
  KEY `FK_KA_STAR_GRAM_TO_KA_STAR_GRAM_COMMENT` (`ka_star_seq`),
  CONSTRAINT `FK_KA_STAR_GRAM_TO_KA_STAR_GRAM_COMMENT` FOREIGN KEY (`ka_star_seq`) REFERENCES `ka_star_gram` (`ka_star_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.ka_star_gram_comment_good definition

CREATE TABLE if not exists  `ka_star_gram_comment_good` (
  `comment_seq` varchar(30) NOT NULL COMMENT '댓글 아이디',
  `reg_uid` varchar(100) NOT NULL COMMENT '등록자',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
  PRIMARY KEY (`comment_seq`,`reg_uid`),
  KEY `PK_KA_STAR_GRAM_COMMENT_GOOD` (`comment_seq`,`reg_uid`),
  CONSTRAINT `FK_KA_STAR_GRAM_COMMENT_TO_KA_STAR_GRAM_COMMENT_GOOD` FOREIGN KEY (`comment_seq`) REFERENCES `ka_star_gram_comment` (`comment_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.ka_star_gram_good definition

CREATE TABLE if not exists  `ka_star_gram_good` (
  `ka_star_seq` varchar(30) NOT NULL COMMENT '카스타그램 아이디',
  `reg_uid` varchar(100) NOT NULL COMMENT '등록자',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
  PRIMARY KEY (`ka_star_seq`,`reg_uid`),
  KEY `PK_KA_STAR_GRAM_GOOD` (`ka_star_seq`,`reg_uid`),
  CONSTRAINT `FK_KA_STAR_GRAM_TO_KA_STAR_GRAM_GOOD` FOREIGN KEY (`ka_star_seq`) REFERENCES `ka_star_gram` (`ka_star_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.ka_star_gram_media definition

CREATE TABLE if not exists  `ka_star_gram_media` (
  `ka_star_seq` varchar(30) NOT NULL COMMENT '카스타그램 아이디',
  `ka_star_media_type` varchar(2) NOT NULL COMMENT '카스타그램 미디어 타입',
  `ka_star_media` varchar(200) NOT NULL COMMENT '카스타그램 미디어',
  PRIMARY KEY (`ka_star_seq`,`ka_star_media`),
  KEY `PK_KA_STAR_GRAM` (`ka_star_seq`,`ka_star_media`),
  CONSTRAINT `FK_KA_STAR_GRAM_TO_KA_STAR_GRAM_MEDIA` FOREIGN KEY (`ka_star_seq`) REFERENCES `ka_star_gram` (`ka_star_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.ka_star_gram_rel_product definition

CREATE TABLE if not exists  `ka_star_gram_rel_product` (
  `ka_star_seq` varchar(30) NOT NULL COMMENT '카스타그램 아이디',
  `prdt_seq` varchar(30) NOT NULL COMMENT '상품 아이디',
  PRIMARY KEY (`ka_star_seq`,`prdt_seq`),
  KEY `PK_KA_STAR_GRAM` (`ka_star_seq`,`prdt_seq`),
  CONSTRAINT `FK_KA_STAR_GRAM_TO_KA_STAR_GRAM_REL_PRODUCT` FOREIGN KEY (`ka_star_seq`) REFERENCES `ka_star_gram` (`ka_star_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.order_detail definition

CREATE TABLE if not exists  `order_detail` (
  `ord_num` varchar(30) NOT NULL COMMENT '주문번호',
  `prdt_seq` varchar(30) NOT NULL COMMENT '상품 아이디',
  `prdt_count` int(20) NOT NULL COMMENT '상품 수량',
  `prdt_price` int(50) NOT NULL COMMENT '상품 가격',
  `ord_status` varchar(5) NOT NULL COMMENT '주문상태(상품준비중/배송준비중/배송보류/배송대기/배송중/배송완료/구매확정)',
  `cs_status` varchar(5) NOT NULL COMMENT '취소/교환/반품/환불',
  `confirm_dtm` timestamp NULL DEFAULT NULL COMMENT '확정일자',
  PRIMARY KEY (`ord_num`,`prdt_seq`),
  KEY `PK_ORDER_DETAIL` (`ord_num`,`prdt_seq`),
  KEY `FK_PRODUCT_MNG_TO_ORDER_DETAIL` (`prdt_seq`),
  CONSTRAINT `FK_ORDER_MNG_TO_ORDER_DETAIL` FOREIGN KEY (`ord_num`) REFERENCES `order_mng` (`ord_num`),
  CONSTRAINT `FK_PRODUCT_MNG_TO_ORDER_DETAIL` FOREIGN KEY (`prdt_seq`) REFERENCES `product_mng` (`prdt_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.pay_mng definition

CREATE TABLE if not exists  `pay_mng` (
  `pay_seq` varchar(30) NOT NULL COMMENT '결제코드',
  `ord_num` varchar(30) NOT NULL COMMENT '주문번호',
  `pay_method` varchar(30) NOT NULL COMMENT '결제수단',
  `pay_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '결제일',
  `pay_price` int(50) NOT NULL COMMENT '결제금액',
  PRIMARY KEY (`pay_seq`),
  KEY `PK_PAY_MNG` (`pay_seq`),
  KEY `FK_ORDER_MNG_TO_PAY_MNG` (`ord_num`),
  CONSTRAINT `FK_ORDER_MNG_TO_PAY_MNG` FOREIGN KEY (`ord_num`) REFERENCES `order_mng` (`ord_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.product_cate definition

CREATE TABLE if not exists  `product_cate` (
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품아이디',
  `cate_seq` varchar(5) NOT NULL COMMENT '카테고리 코드',
  PRIMARY KEY (`prdt_seq`,`cate_seq`),
  KEY `PK_PRODUCT_CATE` (`prdt_seq`,`cate_seq`),
  KEY `FK_CMM_CATEGORY_TO_PRODUCT_CATE` (`cate_seq`),
  CONSTRAINT `FK_CMM_CATEGORY_TO_PRODUCT_CATE` FOREIGN KEY (`cate_seq`) REFERENCES `cmm_category` (`cate_seq`),
  CONSTRAINT `FK_PRODUCT_MNG_TO_PRODUCT_CATE` FOREIGN KEY (`prdt_seq`) REFERENCES `product_mng` (`prdt_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.product_character definition

CREATE TABLE if not exists  `product_character` (
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품아이디',
  `char_seq` varchar(5) NOT NULL COMMENT '캐릭터 코드',
  PRIMARY KEY (`prdt_seq`,`char_seq`),
  KEY `PK_PRODUCT_CHARACTER` (`prdt_seq`,`char_seq`),
  KEY `FK_CMM_CHARACTER_TO_PRODUCT_CHARACTER` (`char_seq`),
  CONSTRAINT `FK_CMM_CHARACTER_TO_PRODUCT_CHARACTER` FOREIGN KEY (`char_seq`) REFERENCES `cmm_character` (`char_seq`),
  CONSTRAINT `FK_PRODUCT_MNG_TO_PRODUCT_CHARACTER` FOREIGN KEY (`prdt_seq`) REFERENCES `product_mng` (`prdt_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.product_img definition

CREATE TABLE if not exists  `product_img` (
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품seq',
  `prdt_img` varchar(200) NOT NULL COMMENT '제품이미지',
  PRIMARY KEY (`prdt_seq`,`prdt_img`),
  KEY `PK_PRODUCT_MNG` (`prdt_seq`,`prdt_img`),
  CONSTRAINT `FK_PRODUCT_MNG_TO_PRODUCT_IMG` FOREIGN KEY (`prdt_seq`) REFERENCES `product_mng` (`prdt_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;