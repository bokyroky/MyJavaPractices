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
<spring:url value="/resources/css/theme1/style1.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>

<title>Home</title>
</head>
<body>
	<div id="divContainer" class="container">

		<div id="divContent">
			<div id="divHeader" style="margin: auto">
				<h2>Articles</h2>
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
			$.get("articleNo" + "?idArticle=" + idArticle, function(data) {
				articleContent = data.trim();
			});
			return false;
		});
		$("#txtSearch").keyup(function() {
			var subString = $(this).val().trim();
			findSubstringClient(subString);
			//findSubstringServer(subString);

		});

		function findSubstringClient(subString) {
			if (subString == ""){
				refreshTextArea();
				return;				
			}
				
			if (articleContent.indexOf(subString) >= 0) {
				$("#txtArtContent").val(articleContent);
			} else {
				refreshTextArea();
			}
		}

		function findSubstringServer(subString) {
			$.get("contains?" + "articleContent=" + articleContent + "&"
					+ "subString=" + subString, function(data) {

				if (data === "1")
					$("#txtArtContent").val(articleContent);
				else
					refreshTextArea();
			});
		}

		function refreshTextArea() {
			$("#txtArtContent").val("");
		}
	})
</script>
