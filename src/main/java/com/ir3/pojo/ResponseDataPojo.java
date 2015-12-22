package com.ir3.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class ResponseDataPojo implements Serializable{
	
	private static final long serialVersionUID = 1927077458146604872L;
	
	@Field
	private long id;
	@Field
	private String text_en;
	@Field
	private String text_ru;
	@Field
	private String text_de;
	@Field
	private String lang;
	@Field
	private List<String> tweet_urls;
	@Field
	private List<String> tweet_hashtags;
	@Field
	private String text_fr;
	@Field
	private Date created_at;
	@Field
	private List<String> concept_tags;
	@Field
	private String summary;
	@Field
	private double sentiment;
	@Field
	private String geo_loc;
	
	private int facet_english;
	private int facet_german;
	private int facet_french;
	private int facet_russian;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the text_en
	 */
	public String getText_en() {
		return text_en;
	}
	/**
	 * @param text_en the text_en to set
	 */
	public void setText_en(String text_en) {
		this.text_en = text_en;
	}
	
	/**
	 * @return the text_ru
	 */
	public String getText_ru() {
		return text_ru;
	}
	/**
	 * @param text_ru the text_ru to set
	 */
	public void setText_ru(String text_ru) {
		this.text_ru = text_ru;
	}
	
	/**
	 * @return the text_de
	 */
	public String getText_de() {
		return text_de;
	}
	/**
	 * @param text_de the text_de to set
	 */
	public void setText_de(String text_de) {
		this.text_de = text_de;
	}
	
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	/**
	 * @return the tweet_urls
	 */
	public List<String> getTweet_urls() {
		return tweet_urls;
	}
	/**
	 * @param tweet_urls the tweet_urls to set
	 */
	public void setTweet_urls(List<String> tweet_urls) {
		this.tweet_urls = tweet_urls;
	}
	
	/**
	 * @return the tweet_hashtags
	 */
	public List<String> getTweet_hashtags() {
		return tweet_hashtags;
	}
	/**
	 * @param tweet_hashtags the tweet_hashtags to set
	 */
	public void setTweet_hashtags(List<String> tweet_hashtags) {
		this.tweet_hashtags = tweet_hashtags;
	}
	
	/**
	 * @return the created_at
	 */
	public Date getCreated_at() {
		return created_at;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * @return the text_fr
	 */
	public String getText_fr() {
		return text_fr;
	}
	/**
	 * @param text_fr the text_fr to set
	 */
	public void setText_fr(String text_fr) {
		this.text_fr = text_fr;
	}
	
	/**
	 * @return the concept_tags
	 */
	public List<String> getConcept_tags() {
		return concept_tags;
	}
	/**
	 * @param concept_tags the concept_tags to set
	 */
	public void setConcept_tags(List<String> concept_tags) {
		this.concept_tags = concept_tags;
	}
	
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * @return the sentiment
	 */
	public double getSentiment() {
		return sentiment;
	}
	/**
	 * @param sentiment the sentiment to set
	 */
	public void setSentiment(double sentiment) {
		this.sentiment = sentiment;
	}
	
	/**
	 * @return the geo_loc
	 */
	public String getGeo_loc() {
		return geo_loc;
	}
	/**
	 * @param geo_loc the geo_loc to set
	 */
	public void setGeo_loc(String geo_loc) {
		this.geo_loc = geo_loc;
	}
	
	/**
	 * @return the facet_english
	 */
	public int getFacet_english() {
		return facet_english;
	}
	/**
	 * @param facet_english the facet_english to set
	 */
	public void setFacet_english(int facet_english) {
		this.facet_english = facet_english;
	}
	
	/**
	 * @return the facet_german
	 */
	public int getFacet_german() {
		return facet_german;
	}
	/**
	 * @param facet_german the facet_german to set
	 */
	public void setFacet_german(int facet_german) {
		this.facet_german = facet_german;
	}
	
	/**
	 * @return the facet_french
	 */
	public int getFacet_french() {
		return facet_french;
	}
	/**
	 * @param facet_french the facet_french to set
	 */
	public void setFacet_french(int facet_french) {
		this.facet_french = facet_french;
	}
	
	/**
	 * @return the facet_russian
	 */
	public int getFacet_russian() {
		return facet_russian;
	}
	/**
	 * @param facet_russian the facet_russian to set
	 */
	public void setFacet_russian(int facet_russian) {
		this.facet_russian = facet_russian;
	}
}