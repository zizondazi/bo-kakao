package com.bokakao.util.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonDomain {
	
	private String uid;				// 회원 id
	private String reg_uid;			// 등록자 id
	private String reg_dtm;			// 등록일
	private String mdf_uid;			// 수정자 id
	private String mdf_dtm;			// 수정일
	private String list_sort; 		// 정렬 순
	
    private Integer row_num;        // 번호
    private Integer tot_cnt;     	// 페이징-전체 데이터 수량
    private Integer page_size;   	// 페이징-페이지 사이즈
    private Integer first_idx;   	// 페이징-시작 번호
    private Integer last_idx;    	// 페이징-종료 번호
    private Integer page_no;     	// 페이징-페이지 번호
    private Integer page_unit;   	// 페이징-페이징 범위
	
	/* 검색 조건 */
	private String sch_cate_seq;	// 검색 - 카테고리 seq	
	private String sch_char_seq;	// 검색 - 캐릭터 seq	
	
    public void setPage_no(Integer page_no) {
        this.page_no = page_no;
        if(this.page_no != null && this.page_size != null) {
            this.first_idx = (this.page_no == 1 ? 1 : ( (this.page_no-1) * this.page_size) + 1);
            this.last_idx = ((this.page_no-1) * this.page_size) + this.page_size;
        }
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
        if(this.page_no != null && this.page_size != null) {
            this.first_idx = (this.page_no == 1 ? 1 : ((this.page_no-1) * this.page_size) + 1);
            this.last_idx = ((this.page_no - 1) * this.page_size) + this.page_size;
        }
    }
}
