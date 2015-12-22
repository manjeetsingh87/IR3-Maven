<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<title>Twitter Hashtag Search</title>
	<meta charset="utf-8">
	<!--link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"
		integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd"
		crossorigin="anonymous"-->
<style>
@import url(http://fonts.googleapis.com/css?family=Lato);
body {
	margin:0;
	padding:0;
	font-family:Lato;
	height:100%;
	background-color:#000;
	color:#fff;
}

header{
	margin:0;
	padding:0;
	background-color:#8A73FF;
	height:25vh;
	width:100%;
}

header h1{
	text-align:center;
	margin-top:0;
	padding-top:2.5%;
	color:white;
}

header input[type="text"]{
	display:inline-block;
	min-height:5vh;
	min-width:40%;
	margin-left:30%;
	margin-top:2vh;
	font-size:2em;
	text-align:center;
	-moz-border-radius:30px;
	-webkit-border-radius:30px;
	border-radius:30px;
}

header button{
	background: url('http://mvallu.github.io/CS-424-Project-3/resources/images/search.png') no-repeat;
	background-size:contain;
	font-size:1.4em;
	border:0;
	min-height:3vh;
	min-width:5%;
	margin-left:2%;
}

#content{
	float:left;
	background-color:#fff;
	color:#000;
	margin-top:2%;
	margin-left:8%;
	width:50%;
	height:50vh;
	border: 1px solid white;
	overflow-y:scroll;
}

#content h2{
	font-size:1.5em;
	margin:0;
	padding-top:2%;
	padding-left:2%;
	padding-bottom:2%;
	color:white;
	background-color:#8A73FF;
}

#errorMessage{
	text-align:center;
	font-size:1.2em;
}

#contentHeading{
	position:fixed;
	width:49%;
}

#contentResult{
	margin-left:3%;
	margin-right:3%;
	margin-top:2%;
}

#contentBorder{

	border-bottom:1px solid red;
}

#contentWrap{
	margin-top:10%;
}



#sidebar{
	float:left;
	margin-left:3%;
	background-color:#fff;
	color:#000;
	margin-top:2%;
	width:15%;
	height:50vh;
	border: 1px solid white;
	overflow-y:scroll;
}

#sidebar h2{
	font-size:1.5em;
	margin:0;
	padding-top:2%;
	padding-left:2%;
	padding-bottom:2%;
	color:white;
	background-color:#8A73FF;

}

.sidebarHeading{
	position:fixed;
	width:inherit;
}

footer{
	background-color:#8A73FF;
	margin-top:5%;
	position:relative;
	clear:both;
	height:5vh;
	text-align:center;
}

#loading {
   width: 100%;
   height: 100%;
   top: 0px;
   left: 0px;
   position: fixed;
   display: block;
   opacity: 0.7;
   background-color: #fff;
   z-index: 99;
   text-align: center;
}

.list-group{
	margin-top:18%;
}

.list-group ul{
	padding:0;
	margin-top:10%;
	list-style:none;
}

.list-group li{
	padding-left:5%;
	padding-top:2%;
	margin-top:1%;
	border-bottom:1px solid grey;
}


.list-group a{
	margin-top:1.5%;
	padding-bottom:5%;
	font-size:1.2em;
	text-decoration:none;

}

#contentUL{
	list-style:none;
	
}

#contentUL li{
	margin-right:2%;
	border-radius:20px;
	background:#00ccff;
	width:30%;
	margin-bottom:1%;
	text-align:center;
}

#contentMain {
	font-size:1.5em;
}

#goToLink{
	color:#fff;
	border: 2px solid #FF5144;	
	background:#FF5144;
}



#loading-image {
  position: absolute;
  top: 400px;
  left: 850px;
  z-index: 100;
}

::-webkit-scrollbar{
    width: 12px;
}
::-webkit-scrollbar-track {
    background-color: #eaeaea;
    border-left: 1px solid #ccc;
}
::-webkit-scrollbar-thumb {
    background-color: #ccc;
}
::-webkit-scrollbar-thumb:hover {
    background-color: #aaa;
}
::-webkit-scrollbar {
    width: 12px;
}
::-webkit-scrollbar-track {
    background-color: #eaeaea;
    border-left: 1px solid #ccc;
}
::-webkit-scrollbar-thumb {
    background-color: #ccc;
}
::-webkit-scrollbar-thumb:hover {
    background-color: #aaa;
}
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>

</head>
	<body>
	
		<div id="loading">
		  <img id="loading-image" src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSBRCgwm8a84hkPrEAd_gNPJzfZF9N2MDhzvI6j1Y3LIj75DqQjew" alt="Loading..." />
		</div>
		<header>
			<h1>Twitter Hashtag Search</h1>
			<form id="searchForm" method="post">
				<input type="hidden" id="searchQuery" name="searchQuery" value="" />
				<input type="hidden" id="facetlang" name="facetlang" value="" />	
				<input id="query" type="text" class="form-control" placeholder="Search Keywords"> 
				<button id="goButton" class="btn btn-secondary" type="button"></button>
			</form>
			<p id="errorMessage">Enter a search query !</p>
		</header>

		<section id="content">
			<div id="contentHeading">
				<h2>Results</h2>
			</div>
			
			<div id ="contentWrap">	
			<c:if test="${!empty searchResults}">
			<c:forEach var="searchResult" items="${searchResults}">
			
			<div id="contentResult">
			<div id="contentMain">
				<c:if test="${fn:length(searchResult.text_en) > 0}">${searchResult.text_en}</c:if>
				<c:if test="${fn:length(searchResult.text_de) > 0}">${searchResult.text_de}</c:if>
				<c:if test="${fn:length(searchResult.text_fr) > 0}">${searchResult.text_fr}</c:if>
				<c:if test="${fn:length(searchResult.text_ru) > 0}">${searchResult.text_ru}</c:if>
			</div>
				<c:if test="${fn:length(searchResult.geo_loc) > 0}">
				<p class="card-text" style="font-style:italic; font-size: 90%;">Posted from ${searchResult.geo_loc}</p>
				</c:if>
				<c:if test="${!empty searchResult.concept_tags}">
				<b class="card-text">Content Tags:</b>
				<ul id="contentUL">
				<c:forEach var="tag" items="${searchResult.concept_tags}">
				<li class="contentLI">${tag}</li>
				</c:forEach>
				</ul>
				</c:if>
				<c:if test="${!empty searchResult.tweet_urls}">		
							
				<a target="_blank" href="${searchResult.tweet_urls[0]}" id="goToLink">Go to link</a>
				</c:if>
				<div id="contentBorder">
			<br>
				</div>	
							
			</div>
				</c:forEach>
				</c:if>
				<c:if test="${empty searchResults}">
				<h3>No Results to display</h3>
				</c:if>
		</section>
		<!-- <section id="middle">
			<div class="card-header">
				<h3 class="card-header">Summary</h3>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"></li>
				</ul>
				<div class="card-block">
					<a4 href="#" class="card-link">Card link</a> 
					<a href="#" class="card-link">Another link</a>
				</div>
				</div>
			</div>
		</section> -->
		<aside id="sidebar">
			<div class="sidebarHeading">
			<h2>Filtered Results: ${language}</h2>
			</div>
			<c:if test="${!empty langFacets}">
				<div class="list-group">
				<ul>
					<c:forEach var="facet" items="${langFacets}">
						<c:if test="${facet.key == 'en'}">
							<a href="#" class="list-group-item" onclick='javacript:searchByFacet("${facet.key}")'><li>English: ${facet.value}</li></a>
						</c:if>
						<c:if test="${facet.key == 'de'}">
						<a href="#" class="list-group-item" onclick='javacript:searchByFacet("${facet.key}")'><li>German: ${facet.value}</li></a>
						</c:if>
						<c:if test="${facet.key == 'fr'}">
						<a href="#" class="list-group-item" onclick='javacript:searchByFacet("${facet.key}")'><li>French: ${facet.value}</li></a>
						</c:if>
						<c:if test="${facet.key == 'ru'}">
						<a href="#" class="list-group-item" onclick='javacript:searchByFacet("${facet.key}")'><li>Russian: ${facet.value}</li></a>
						</c:if>
					</c:forEach>
				</ul>
				</div>
			</c:if>
		</aside>

      <script type="text/javascript">
	$(function(){
		$('#loading').hide();
		
		$('#errorMessage').hide();

		$('#query').keydown(function(event){
			if(event.keyCode==13){
				$('#goButton').trigger('click');
			}
		});

		$('#goButton').click(function(){
		
			var query = $("#query").val();
			if (query.trim() == "") {
				$('#errorMessage').show(0).delay(3000).hide(0);
				return false;
			} 
		
			else {
				document.getElementById("searchQuery").value = query;
				document.getElementById('searchForm').action = "/IR3-Maven/search";
				document.getElementById('searchForm').submit();
				$('#loading').show();
			}

		});
	});
	
	function searchByFacet(facetLang) {
		document.getElementById("facetlang").value = facetLang;
		document.getElementById('searchForm').action = "/IR3-Maven/search/filter";
		document.getElementById('searchForm').submit();
		$('#loading').show();
	}
	
</script>
</body>
</html>