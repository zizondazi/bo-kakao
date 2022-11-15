-- friend_shop.cmm_category definition

CREATE table if not exists `cmm_category` (
  `cate_seq` varchar(5) NOT NULL COMMENT '카테고리 seq',
  `cate_nm` varchar(20) NOT NULL COMMENT '카테고리 명',
  `cate_up_seq` varchar(20) DEFAULT NULL COMMENT '상위 카테고리 seq',
  PRIMARY KEY (`cate_seq`),
  KEY `PK_CMM_CATEGORY` (`cate_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- friend_shop.cmm_character definition

CREATE table if not exists `cmm_character` (
  `char_seq` varchar(5) NOT NULL COMMENT '캐릭터 seq',
  `char_nm` varchar(20) NOT NULL COMMENT '캐릭터 명',
  `char_cate_img` varchar(200) NOT NULL COMMENT '캐릭터 카테고리 이미지',
  `char_info_img` varchar(200) DEFAULT NULL COMMENT '캐릭터 설명 이미지',
  `ka_star_info` varchar(200) DEFAULT NULL COMMENT '카스타그램 설명',
  `ka_thum_img` varchar(200) DEFAULT NULL COMMENT '카스타그램 썸네일이미지',
  PRIMARY KEY (`char_seq`),
  KEY `PK_CMM_CHARACTER` (`char_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- friend_shop.member_mng definition

CREATE table if not exists `member_mng` (
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

CREATE table if not exists `order_mng` (
  `ord_num` varchar(30) NOT NULL COMMENT '주문번호',
  `purchaser_nm` varchar(50) NOT NULL COMMENT '주문자',
  `purchaser_phone_num` varchar(10) NOT NULL COMMENT '주문자 전화번호(01012345368) 하이픈 제외',
  `purchaser_email` varchar(50) NOT NULL COMMENT '주문자 이메일',
  `recipient_nm` varchar(50) NOT NULL COMMENT '받는사람',
  `recipient_phon_num` varchar(10) NOT NULL COMMENT '받는사람 전화번호(01012345368) 하이픈 제외',
  `zip` varchar(6) NOT NULL COMMENT '우편번호',
  `address` varchar(200) NOT NULL COMMENT '주소',
  `address_etc` varchar(200) NOT NULL COMMENT '나머지 주소',
  `messeage` varchar(200) DEFAULT NULL COMMENT '배송메시지',
  `ord_confirm` varchar(3) DEFAULT NULL COMMENT '주문확정여부(Y: 확정, N: 미확정)',
  `confirm_dtm` timestamp NULL DEFAULT NULL COMMENT '확정일자',
  `reg_uid` varchar(50) NOT NULL COMMENT '등록 회원',
  `reg_dtm` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일시 - 주문일',
  PRIMARY KEY (`ord_num`),
  KEY `PK_ORDER_MNG` (`ord_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- friend_shop.product_mng definition

CREATE table if not exists `product_mng` (
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품seq',
  `prdt_nm` varchar(100) NOT NULL COMMENT '제품명',
  `prdt_price` int(20) NOT NULL COMMENT '제품가격',
  `prdt_sale` int(3) DEFAULT NULL COMMENT '제품세일율',
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

-- friend_shop.product_character definition

CREATE table if not exists `product_character` (
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품아이디',
  `char_seq` varchar(5) NOT NULL COMMENT '캐릭터 코드',
  PRIMARY KEY (`prdt_seq`,`char_seq`),
  KEY `PK_PRODUCT_CHARACTER` (`prdt_seq`,`char_seq`),
  KEY `FK_CMM_CHARACTER_TO_PRODUCT_CHARACTER` (`char_seq`),
  CONSTRAINT `FK_CMM_CHARACTER_TO_PRODUCT_CHARACTER` FOREIGN KEY (`char_seq`) REFERENCES `cmm_character` (`char_seq`),
  CONSTRAINT `FK_PRODUCT_MNG_TO_PRODUCT_CHARACTER` FOREIGN KEY (`prdt_seq`) REFERENCES `product_mng` (`prdt_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- friend_shop.product_cate definition

CREATE table if not exists `product_cate` (
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품아이디',
  `cate_seq` varchar(5) NOT NULL COMMENT '카테고리 코드',
  PRIMARY KEY (`prdt_seq`,`cate_seq`),
  KEY `PK_PRODUCT_CATE` (`prdt_seq`,`cate_seq`),
  KEY `FK_CMM_CATEGORY_TO_PRODUCT_CATE` (`cate_seq`),
  CONSTRAINT `FK_CMM_CATEGORY_TO_PRODUCT_CATE` FOREIGN KEY (`cate_seq`) REFERENCES `cmm_category` (`cate_seq`),
  CONSTRAINT `FK_PRODUCT_MNG_TO_PRODUCT_CATE` FOREIGN KEY (`prdt_seq`) REFERENCES `product_mng` (`prdt_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE  if not exists `product_img` (
  `prdt_seq` varchar(30) NOT NULL COMMENT '제품seq',
  `prdt_img` varchar(200) NOT NULL COMMENT '제품이미지',
  PRIMARY KEY (`prdt_seq`, `prdt_img`),
  KEY `PK_PRODUCT_MNG` (`prdt_seq`, `prdt_img`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;	