package com.ir3.pojo;

import java.util.ArrayList;
import java.util.List;

public class TweetsList {
	private List<Tweet> tweetList = new ArrayList<Tweet>();

	/**
	 * @return the tweetList
	 */
	public List<Tweet> getTweetList() {
		return tweetList;
	}
	/**
	 * @param tweetList the tweetList to set
	 */
	public void addToTweetList(List<Tweet> tweetList) {
		this.tweetList.addAll(tweetList);
	}
}