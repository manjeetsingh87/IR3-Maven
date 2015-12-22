package com.ir3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ir3.pojo.ResponseDataPojo;
import com.ir3.service.crawl.CrawlerTrigger;
import com.ir3.service.dao.SolrSearch;
import com.ir3.solrserverfactory.SingletonSolrSession;

@Controller
@RequestMapping("/search")
public class FrontController {	
	
	public static Map<String, Long> langFacetMap = new HashMap<String, Long>();
	public static Map<String, Long> sentimentFacetMap = null;
	
	@RequestMapping(value="", method=RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
		new CrawlerTrigger().startDataCrawl();
		return new ModelAndView("search" );
    }
	
	@RequestMapping(value="", method=RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		SolrSearch querySolr = new SolrSearch();
		List<ResponseDataPojo> searchResult = new ArrayList<ResponseDataPojo>();
		ModelAndView modelMap = new ModelAndView();
		try {
			String inputQuery = request.getParameter("searchQuery").trim();
			HttpSolrServer serverInstance = getServerInstance();
			if (null != serverInstance) {
				searchResult = querySolr.querySolr(inputQuery, serverInstance);
				modelMap.addObject("searchResults", searchResult);
				modelMap.addObject("langFacets", langFacetMap);
				modelMap.addObject("sentimentCount", sentimentFacetMap);
				
				if(null != request.getSession().getAttribute("sentimentCount"))
					request.getSession().removeAttribute("sentimentCount");
				
				request.getSession().setAttribute("sentimentCount", sentimentFacetMap);
				request.getSession().setAttribute("results", searchResult);
				request.getSession().setAttribute("langFacets", langFacetMap);
				
			} else 
				modelMap.addObject("searchResults", "I'll be back. Unable to contact SkyNet right now");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/filter", method=RequestMethod.POST)
    public ModelAndView filterByFacetLang(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelMap = new ModelAndView();
		try {
			String language = request.getParameter("facetlang").trim();
			if (null != request.getSession() && null != request.getSession().getAttribute("results")) {
				List<ResponseDataPojo> searchResult = (List<ResponseDataPojo>) request.getSession().getAttribute("results");
				List<ResponseDataPojo> filteredData = new ArrayList<ResponseDataPojo>(searchResult);
				Iterator<ResponseDataPojo> dataItr = filteredData.iterator();
				while(dataItr.hasNext()) {
					ResponseDataPojo obj = dataItr.next();
					if(!obj.getLang().equalsIgnoreCase(language))
						dataItr.remove();
				}
				modelMap.addObject("searchResults", filteredData);
				modelMap.addObject("langFacets", (Map<String, Long>)request.getSession().getAttribute("langFacets"));
				if(language.equalsIgnoreCase("en"))
					modelMap.addObject("language", "English");
				else if(language.equalsIgnoreCase("de"))
					modelMap.addObject("language", "German");
				else if(language.equalsIgnoreCase("fr"))
					modelMap.addObject("language", "French");
				else if(language.equalsIgnoreCase("ru"))
					modelMap.addObject("language", "Russian");
			} else 
				modelMap.addObject("searchResults", "I'll be back. Unable to contact SkyNet right now");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelMap;
    }
	
	private HttpSolrServer getServerInstance() {
		HttpSolrServer serverInstance = null;
		try {
			serverInstance =  SingletonSolrSession.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serverInstance;
	}
}