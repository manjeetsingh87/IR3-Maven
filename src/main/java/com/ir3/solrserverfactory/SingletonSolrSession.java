package com.ir3.solrserverfactory;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class SingletonSolrSession {
	private static final String URI = "http://blesson.me:8983/solr/projectb";
	public static HttpSolrServer serverInstance = null;
    
	private SingletonSolrSession() {
        // private constructor
    }
 
    public static synchronized HttpSolrServer getInstance() throws Exception{
        if(null == serverInstance);
        	serverInstance = new HttpSolrServer(URI);
        
        return serverInstance;	
    }
}