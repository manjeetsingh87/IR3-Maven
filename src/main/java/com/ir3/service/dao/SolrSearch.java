package com.ir3.service.dao;

import static com.ir3.controller.FrontController.langFacetMap;
import static com.ir3.controller.FrontController.sentimentFacetMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.ir3.pojo.ResponseDataPojo;

public class SolrSearch {

	public List<ResponseDataPojo> querySolr(String userQuery, HttpSolrServer server){
		List<ResponseDataPojo> dataList = new ArrayList<ResponseDataPojo>();
		sentimentFacetMap = new HashMap<String, Long>();
		SolrQuery query = new SolrQuery();
		query.setQuery(userQuery);
		query.setFacet(true);
		query.addFacetField("lang");
		query.addFacetField("sentiment");
		query.set("defType", "dismax");
		query.setStart(0);
		query.setRows(1000);
		query.set("qf", "text_en text_de text_ru text_fr");
		QueryResponse rsp;
		try {
			rsp = server.query(query);
			dataList = rsp.getBeans(ResponseDataPojo.class);
			if(! rsp.getFacetFields().isEmpty()) {
				List<Count> facetLangList = rsp.getFacetFields().get(0).getValues();
				for(Count facet : facetLangList){
					langFacetMap.put(facet.getName(), facet.getCount());
				}
				
				List<Count> facetSentimentList = rsp.getFacetFields().get(1).getValues();
				Float defaultCount = new Float(0.0);
				for(Count facet : facetSentimentList){
					Float sentimentCount = new Float(facet.getName());
					Long count = facet.getCount();
					if(sentimentCount.compareTo(defaultCount) > 0 && sentimentFacetMap.containsKey("Positive")) {
						sentimentFacetMap.put("Positive", sentimentFacetMap.get("Positive")+count);
					} else if(sentimentCount.compareTo(defaultCount) > 0 && !sentimentFacetMap.containsKey("Positive")) {
						sentimentFacetMap.put("Positive", count);
					} else if(sentimentCount.compareTo(defaultCount) == 0 && sentimentFacetMap.containsKey("Neutral")) {
						sentimentFacetMap.put("Neutral", sentimentFacetMap.get("Neutral")+count);
					} else if(sentimentCount.compareTo(defaultCount) == 0 && !sentimentFacetMap.containsKey("Neutral")) {
						sentimentFacetMap.put("Neutral", count);
					} else if(sentimentCount.compareTo(defaultCount) < 0 && sentimentFacetMap.containsKey("Negative")) {
						sentimentFacetMap.put("Negative", sentimentFacetMap.get("Negative")+count);
					} else if(sentimentCount.compareTo(defaultCount) < 0 && !sentimentFacetMap.containsKey("Negative")) {
						sentimentFacetMap.put("Negative", count);
					}  
				}
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dataList;
	}
}