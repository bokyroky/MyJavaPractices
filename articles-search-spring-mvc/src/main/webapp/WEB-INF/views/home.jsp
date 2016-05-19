
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<html xmlns:th="http://www.thymeleaf.org">
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>

<title>Home</title>
</head>
<body>
	<div id="divContainer" class="container" style="margin: auto; width:50%">
		
		<div id="divContent" style="padding: 6em">
			<div id="divHeader" style="margin: auto">
				<h2>Articles</h2>
			</div>
			<form action="#" th:action="@{/}" name="formArticle" method="post">
				<div id="divArticlesList"
					style="margin: 2em; width: 10em; float: right">
					<form:select id="lbTitles" path="articleList" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${articleList}" itemValue="id"
							itemLabel="title" />
					</form:select>
				</div>
				<div id="articleTitle" style="width: 10em; margin: 2em; float: left">
					<label>Article title:</label> <input type="text"
						name="articleTitle" class="form-control" />
				</div>

				<div id="divArticlesText" style="margin: 2em">
					<textarea autofocus id="txtArtContent" name="articleText" rows="10"
						cols="10" class="form-control" style="padding: 2em">
					
					</textarea>
				</div>

				<div style="width: 2em; margin-left: 2em; float: left">
					<input type="submit" value="Save article" class="btn btn-default"
						class="form-control" />
				</div>
				<div style="margin-right: 2em; width: 10em; float: right">
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
		$('#lbTitles').on('change', function() {
			refreshTextArea();
			var idArticle = $(this).val();
			$.get("articleNo" + "?idArticle=" + idArticle, function(data) {
				articleContent = data.trim();
			});
			return false;
		});
		$("#txtSearch").keyup(function() {
			var searchValue = $(this).val().trim();
			if (articleContent.indexOf(searchValue) >= 0) {
				$("#txtArtContent").val(articleContent);
			} else {
				refreshTextArea();
			}

		});
		function refreshTextArea() {
			$("#txtArtContent").val("");
		}
	})
</script>
