package com.bokakao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bokakao.batch.CrawlingBatchExecutor;

@Component
public class StartConfig implements CommandLineRunner {
	
	@Value("${isExecutorCrawling}")
	private boolean isExecutorCrawling;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("isExecutorCrawling :: " + isExecutorCrawling);
		if(isExecutorCrawling) {
			CrawlingBatchExecutor crawling = new CrawlingBatchExecutor();
			crawling.crawling();
		}
	}

}
