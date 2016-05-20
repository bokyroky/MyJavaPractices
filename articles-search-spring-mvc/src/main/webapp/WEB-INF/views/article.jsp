<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>

<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
	
<spring:url value="/resources/css/theme1/style1.css" var="mainCss" />

<link href="${mainCss}" rel="stylesheet" />


<title>Home</title>
</head>
<body>
	<div id="divContainer" class="container">

		<div id="divContent">
			<div id="divHeader" style="margin: auto">
				<h1>Articles</h1>
			</div>
			<form action="#" th:action="@{/}" name="formArticle" method="post">

				<div id="divArticlesList" class="form-control-wrapper ctrl-right">
					<form:select id="ddlTitles" path="articleList" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${articleList}" itemValue="id"
							itemLabel="title" />
					</form:select>
				</div>
				<div id="articleTitle" class="form-control-wrapper ctrl-left">
					<label>Article title:</label> <input type="text"
						name="articleTitle" class="form-control" />
				</div>

				<div id="divArticlesText" style="margin: 2em">
					<textarea autofocus id="txtArtContent" name="articleText" rows="10"
						cols="10" class="form-control" style="padding: 2em">
					
					</textarea>
				</div>

				<div class="button-wrapper">
					<input type="submit" value="Save article" class="btn btn-default" />
				</div>
				<div class="form-control-wrapper ctrl-right">
					<label>Search for:</label> <input type="text" id="txtSearch"
						class="form-control" />
				</div>

			</form>

		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {

		var articleContent;
		
		$('#ddlTitles').on('change', function() {
			refreshTextArea();
			var idArticle = $(this).val();
			$.get("article" + "?idArticle=" + idArticle, function(data) {
				articleContent = data.trim();
			});
			return false;
		});
		
		//find our text inside article on client or server
		$("#txtSearch").keyup(function() {
			var subString = $(this).val().trim();
			findSubstringClient(subString);
			//findSubstringServer(subString);

		});
		
		//find on client
		function findSubstringClient(subString) {
			if (subString == "") {
				refreshTextArea();
				return;
			}
			if (articleContent.indexOf(subString) >= 0) {
				showArticleContent();

			} else {
				refreshTextArea();
			}
		}
        //find on server
		function findSubstringServer(subString) {
			$.get("contains" + "?articleContent=" + articleContent + "&"
					+ "subString=" + subString, function(data) {
				if (data === "1") {
					showArticleContent();
				}

				else
					refreshTextArea();
			});
		}
		
		//show article in textarea element
		function showArticleContent() {
			$("#txtArtContent").val(articleContent);
		}
		
        // clear textarea
		function refreshTextArea() {
			$("#txtArtContent").val("");
		}
        
        //could be used with some other html elements containing text, where nesting <span> tag is possible
        
		/*function highlight_words(keywords, element) {
		    if(keywords) {
		        var textNodes;
		        keywords = keywords.replace(/\W/g, '');
		        var str = keywords.split(" ");
		        $(str).each(function() {
		            var term = this;
		            var textNodes = $(element).contents().filter(function() { return this.nodeType === 3 });
		            textNodes.each(function() {
		              var content = $(this).text();
		              var regex = new RegExp(term, "gi");
		              content = content.replace(regex, '<span class="highlight">' + term + '</span>');
		              $(this).replaceWith(content);
		            });
		        });
		    }
		}*/
	})
</script>
