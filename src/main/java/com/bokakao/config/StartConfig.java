package com.bokakao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bokakao.batch.CrawlingBatchExecutor;

@Component
public class StartConfig implements CommandLineRunner {
	
	@Value("${isExecutorCrawling}")
	private boolean isExecutorCrawling;
	
	@Autowired
	CrawlingBatchExecutor crawling;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("isExecutorCrawling :: " + isExecutorCrawling);
		if(isExecutorCrawling) {
			crawling.crawling();
		}
	}

}
