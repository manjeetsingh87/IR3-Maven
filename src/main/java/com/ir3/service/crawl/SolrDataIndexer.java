package com.ir3.service.crawl;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import com.ir3.pojo.TweetsList;
import com.ir3.solrserverfactory.SingletonSolrSession;

public class SolrDataIndexer {
	public void indexData(TweetsList dataList){
		HttpSolrServer server = null;
		try {
			server = SingletonSolrSession.getInstance();
			//server.deleteByQuery( "*:*" );
			server.addBeans(dataList.getTweetList());
			server.commit();
		} catch (Exception e) {
			try {
				server.rollback();
			}catch (SolrServerException e1) {
				e1.printStackTrace();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}