package com.bokakao.batch;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bokakao.cmm.category.domain.CmmCategoryDomain;
import com.bokakao.cmm.category.mapper.CmmCategoryMapper;
import com.bokakao.product.cate.domain.ProductCateDomain;
import com.bokakao.product.mng.domain.ProductMngDomain;
import com.bokakao.product.mng.service.ProductMngService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CrawlingBatchExecutor {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss:SSS");
	
	// chromdriver 설치 경로
	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "src/main/resources/chromedriver.exe";
	
	@Autowired
	private CmmCategoryMapper<CmmCategoryDomain> cmmCategoryMapper;
	
	@Autowired
	private ProductMngService productMngService;
	
//	@Autowired
//	private ProductMngMapper<ProductMngDomain> productMngMapper;
	
	@Scheduled(cron="40 44 10 * * *")
	public void crawling() {
		try {
			System.out.println("==== start :: crawling ====");
			
			// 지존마로님과 대화 해보니 프렌즈샵은 리액트라고 한다. 
			// WebDriver 경로 설정
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
			
			// WebDriver 옵션 설정
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-popup-blocking");
	        
			ChromeDriver driver = new ChromeDriver(options);
			
			String url = "https://store.kakaofriends.com/home";
			
			driver.get(url);
			Thread.sleep(1000);
			
			// 카카오프렌즈 이용안내 끄기
			//driver.findElement(By.className("ico_close_n")).click();
			
			// gnb 메뉴 클릭
			driver.findElement(By.className("ico_gnb_menu")).click();
			
			String html = driver.getPageSource().toString();
			
			Thread.sleep(2000);
			
			Document doc = Jsoup.parse(html);
			
			// 카테고리 Elements
			Elements el = doc.getElementsByClass("link_category");
			
			List<CmmCategoryDomain> cate_list = new ArrayList<CmmCategoryDomain>();
			
			for(int i=0; i < el.size(); i++) {
				CmmCategoryDomain category = new CmmCategoryDomain();
				
				String href = el.get(i).attr("href"); // href = /products/category/subject?categorySeq=103 
				String name = el.get(i).text();
				int divison_index = href.indexOf("=");
				String cate_seq = href.substring(divison_index + 1);
				
				category.setCate_nm(name);
				category.setCate_seq(cate_seq);
				
				cate_list.add(category);
				
				// 상위 카테고리 저장
				//cmmCategoryMapper.mergeCmmCategory(category); 
			}
			
			// 하위 카테고리 저장 
			for(CmmCategoryDomain cate :  cate_list) {
				String cate_url = "https://store.kakaofriends.com/category?categorySeq=" + cate.getCate_seq();
				
				driver.get(cate_url);
				List<WebElement> cate_dtl_list = driver.findElements(By.className("link_category"));
				// 전체 제외
				if(!cate.getCate_seq().equals("3")) {
					// 헤더의 상위카테고리, 전체 제외를 위해 2 부터 시작
					for(int i=2; i < cate_dtl_list.size(); i++) {
						CmmCategoryDomain category = new CmmCategoryDomain();
						//driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[1]/ul/li["+ i +"]/a")).click();
						// 일부 엘리먼트 에러 발생으로 소스 수정
						WebElement cate_el = driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[1]/ul/li["+ i +"]/a"));
						driver.executeScript("arguments[0].click();", cate_el);
						
						String curt_url = driver.getCurrentUrl(); // https://store.kakaofriends.com/category?categorySeq=64&subCategorySeq=65
						int idx = curt_url.indexOf("subCategorySeq");
						String cate_seq = curt_url.substring(idx + "subCategorySeq=".length());
						
						category.setCate_seq(cate_seq);
						category.setCate_up_seq(cate.getCate_seq());
						category.setCate_nm(cate_dtl_list.get(i).getText());
						
						// 하위 카테고리 저장
						//cmmCategoryMapper.mergeCmmCategory(category); 
						
						List<WebElement> product_list = driver.findElements(By.className("product_contents"));
						Thread.sleep(3000);
						
						String match = "[^0-9]";
						
						for(int j=1; j <= product_list.size(); j++) {
							ProductMngDomain product = new ProductMngDomain();
							// https://store.kakaofriends.com/products/8856							  
							String product_seq_url = driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[3]/fs-view-product-list/cu-infinite-scroll/div/ul/li["+ j +"]/div/div/a")).getAttribute("href");
							product.setPrdt_seq(product_seq_url.substring("https://store.kakaofriends.com/products/".length()));
							product.setPrdt_nm(driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[3]/fs-view-product-list/cu-infinite-scroll/div/ul/li["+ j +"]/div/div/a/strong")).getText());
							product.setPrdt_img(driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[3]/fs-view-product-list/cu-infinite-scroll/div/ul/li["+ j +"]/div/a/div/img")).getAttribute("src"));
							product.setPrdt_stock(10); // 기본 재고 10
							product.setMdf_uid("0");
							product.setReg_uid("0");
							
							// 세일가가 존재 할 경우
							try {														
								Integer price = Integer.parseInt(driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[3]/fs-view-product-list/cu-infinite-scroll/div/ul/li["+ j +"]/div/div/a/span/span[3]/span")).getText().replaceAll(match, ""));
								Integer sale_price = Integer.parseInt(driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[3]/fs-view-product-list/cu-infinite-scroll/div/ul/li["+ j +"]/div/div/a/span/em/span")).getText().replaceAll(match, ""));
								//Integer sale = (price-sale_price) / price * 100;
								product.setPrdt_sale_price(sale_price);
								product.setPrdt_price(price);
							}catch(NoSuchElementException e) {
								product.setPrdt_sale_price(0);
								product.setPrdt_price(Integer.parseInt(driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[3]/fs-view-product-list/cu-infinite-scroll/div/ul/li["+ j +"]/div/div/a/span/em/span")).getText().replaceAll(match, "")));
							}
							
							// 서비스로 이동예정 - 왤케 할게 많노.. ㅠ 괴롭다 지존희빈님...
							List<ProductCateDomain> prdt_cate_list = new ArrayList<ProductCateDomain>();
							ProductCateDomain prdt_cate = new ProductCateDomain();
							ProductCateDomain sub_prdt_cate = new ProductCateDomain();
							
							// 상위 카테고리 등록
							prdt_cate.setPrdt_seq(product_seq_url.substring("https://store.kakaofriends.com/products/".length()));
							prdt_cate.setCate_seq(cate.getCate_seq());
							prdt_cate_list.add(prdt_cate);
							
							// 하위 카테고리 등록
							sub_prdt_cate.setPrdt_seq(product_seq_url.substring("https://store.kakaofriends.com/products/".length()));
							sub_prdt_cate.setCate_seq(cate_seq);
							prdt_cate_list.add(sub_prdt_cate);
							
							// 트레잭션 처리
							productMngService.mergeProductMng(product, prdt_cate_list);
							
						}
					}
				}
			}
			
			System.out.println("==== end :: crawling ====");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
