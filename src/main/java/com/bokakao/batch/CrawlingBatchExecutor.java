package com.bokakao.batch;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bokakao.cmm.category.domain.CmmCategoryDomain;
import com.bokakao.cmm.category.mapper.CmmCategoryMapper;

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
	
	@Scheduled(cron="20 2 16 * * *")
	public void crawling() {
		try {
			System.out.println("==== start :: crawling - category ====");
			
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
				cmmCategoryMapper.mergeCmmCategory(category); 
			}
			
			// 하위 카테고리 저장 
			for(CmmCategoryDomain cate :  cate_list) {
				String cate_url = "https://store.kakaofriends.com/category?categorySeq=" + cate.getCate_seq();
				
				driver.get(cate_url);
				List<WebElement> cate_dtl_list = driver.findElements(By.className("link_category"));
				// 전체 제외
				if(!cate.getCate_seq().equals("3")) {
					// 헤더의 상위카테고리, 전체 제외를 위해 1 부터 시작
					for(int i=2; i < cate_dtl_list.size(); i++) {
						CmmCategoryDomain category = new CmmCategoryDomain();
						// 일부 엘리먼트 에러 발생으로 소스 수정
						//driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[1]/ul/li["+ i +"]/a")).click();
						WebElement cate_el = driver.findElement(By.xpath("/html/body/fs-root/div/fs-pw-category-list/main/article/div/div[1]/ul/li["+ i +"]/a"));
						driver.executeScript("arguments[0].click();", cate_el);
						
						String curt_url = driver.getCurrentUrl(); // https://store.kakaofriends.com/category?categorySeq=64&subCategorySeq=65
						int idx = curt_url.indexOf("subCategorySeq");
						
						category.setCate_seq(curt_url.substring(idx + "subCategorySeq=".length()));
						category.setCate_up_seq(cate.getCate_seq());
						category.setCate_nm(cate_dtl_list.get(i).getText());
						
						// 하위 카테고리 저장
						cmmCategoryMapper.mergeCmmCategory(category); 
						
						Thread.sleep(3000);
					}
				}
			}
			
			System.out.println("==== end :: crawling - category ====");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
