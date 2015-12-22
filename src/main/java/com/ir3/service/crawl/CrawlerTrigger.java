package com.ir3.service.crawl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import twitter4j.Twitter;

import com.ir3.pojo.Tweet;
import com.ir3.pojo.TweetsList;
import com.ir3.twitterfactory.Twitter4jFactory;
import com.ir3.utils.PropertiesFileReader;

public class CrawlerTrigger {
	
	public void startDataCrawl() {
		TweetsList listDataObj = new TweetsList();
		SolrDataIndexer dataIndexer = new SolrDataIndexer();
		try {
			Twitter twitterInstance = getTwitterInstance();
			List<Tweet> tweetsData = new ArrayList<Tweet>();
			Map<String, String> queryParamsMap = PropertiesFileReader.getProperties();
			List<String> languages = getQueryLanguage(queryParamsMap);
			String since = queryParamsMap.get("SINCE");
			String until = queryParamsMap.get("UNTIL");
			for(String language : languages) {
				List<String> criteria = getQuerySearchCriteria(queryParamsMap);
				if(!criteria.isEmpty())
					tweetsData = getTwitterData(twitterInstance, language, criteria, since, until);
				listDataObj.addToTweetList(tweetsData);
			}
			dataIndexer.indexData(listDataObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<Tweet> getTwitterData(Twitter twitterInstance, String language, List<String> criteria, String since, String until) {
		TweetsCrawler crawler = new TweetsCrawler();
		List<Tweet> tweetsDataList = new ArrayList<Tweet>();
		for(String criterion : criteria) {
			tweetsDataList.addAll(crawler.getTwitterData(twitterInstance, language, criterion, since, until));
		}
		return tweetsDataList;
	}
	
	private Twitter getTwitterInstance() {
		return Twitter4jFactory.getInstance();
	}
	
	private List<String> getQueryLanguage(Map<String, String> queryParamsMap) {
		String[] lang = queryParamsMap.get("LANGUAGE").split(",");
		List<String> languages = new ArrayList<String>();
		for(int i=0; i<lang.length; i++) {
			languages.add(lang[i]);
		}
		return languages;
	}
	
	private List<String> getQuerySearchCriteria(Map<String, String> queryParamsMap) {
		String[] queryCriteria = queryParamsMap.get("QUERY").split(",");
		List<String> criteria = new ArrayList<String>();
		for(int i=0; i<queryCriteria.length; i++) {
			criteria.add(queryCriteria[i]);
		}
		return criteria;
	}
}