package com.ir3.twitterfactory;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * @author MANJEET
 * 
 */
public class Twitter4jFactory {

	private static TwitterFactory twitterFactory;
	
	public static Twitter getInstance() {
		Twitter twitter = null;
		try {
			if (null != twitterFactory) 
				twitter = twitterFactory.getInstance();
			else {
				TwitterFactory tf = new TwitterFactory(ConfigBuilder.getConfig().build());
				if(null != tf)
					twitter = tf.getInstance();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return twitter;
	}
}