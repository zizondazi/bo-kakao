package com.bokakao.batch;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CrawlingBatchExecutor {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss:SSS");
	
	@Scheduled(cron="0 * * * * *")
	public void crawling() {
		try {
			
			String url = "https://store.kakaofriends.com/best";
			Document doc = Jsoup.connect(url).get();
			Elements imgEl = doc.getElementsByClass("thumb_g");
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
