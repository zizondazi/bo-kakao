package com.bokakao.batch;

import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bokakao.category.domain.CmmCategoryDomain;
import com.bokakao.category.mapper.CmmCategoryMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CrawlingBatchExecutor {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss:SSS");
	
	// chromdriver 설치 경로
	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "D:/chromedriver.exe";
	
	@Autowired
	private CmmCategoryMapper<CmmCategoryDomain> cmmCategoryMapper;
	
	//@Scheduled(cron="30 38 10 * * *")
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
			driver.findElement(By.className("ico_close_n")).click();
			// gnb 메뉴 클릭
			driver.findElement(By.className("ico_gnb_menu")).click();
			
			String html = driver.getPageSource().toString();
			
			Thread.sleep(2000);
			
			Document doc = Jsoup.parse(html);
			
			// 카테고리 Elements
			Elements el = doc.getElementsByClass("link_category");
			
		
			for(int i=0; i < el.size(); i++) {
				CmmCategoryDomain category = new CmmCategoryDomain();
				
				String href = el.get(i).attr("href"); // href = /products/category/subject?categorySeq=103 
				String name = el.get(i).text();
				int divison_index = href.indexOf("=");
				String cate_seq = href.substring(divison_index + 1);
				
				category.setCate_nm(name);
				category.setCate_seq(cate_seq);
				
				cmmCategoryMapper.mergeCmmCategory(category);
			}
			
			System.out.println("==== end :: crawling - category ====");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
