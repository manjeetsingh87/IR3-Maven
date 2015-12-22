package com.ir3.twitterfactory;

import twitter4j.conf.ConfigurationBuilder;

public class ConfigBuilder {

	private static final String OAuth_Consumer_Key = "REEAsqo0J7JtoASyohNkzM0o0";
	private static final String OAuth_Consumer_Secret = "uO0Q2T0fDXtCBOrPHbi2iLtoXAy6DVEQXseQlFx80fh2ib8VTq";
	private static final String OAuth_Access_Token = "70451816-APA6oKiGRw3rH14eKYj1VB7qMxQ2H6z8vyFdwjCxB";
	private static final String OAuth_Access_Secret = "XLKJQfQ245RpXcWXfoZUDuqn2YgjLg1hVmKRw9gjVuazd";
	private static ConfigurationBuilder conf;

	protected static final ConfigurationBuilder getConfig() {
		if(null == conf)
			conf = new ConfigurationBuilder();
		
		setConfBuilderParams();
		return conf;
	}
	
	private static void setConfBuilderParams() {
		conf.setDebugEnabled(true).setOAuthConsumerKey(OAuth_Consumer_Key)
				.setOAuthConsumerSecret(OAuth_Consumer_Secret)
				.setOAuthAccessToken(OAuth_Access_Token)
				.setOAuthAccessTokenSecret(OAuth_Access_Secret);
	}
}