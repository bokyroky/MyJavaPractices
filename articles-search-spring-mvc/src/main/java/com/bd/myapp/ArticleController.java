package com.bd.myapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bd.myapp.data.SQLServerDBAccess;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ArticleController {

	@Autowired
	private SQLServerDBAccess dbAccess;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showArticles(Model model) {
		List<Article> articles = dbAccess.getAllArticles();
		if (!articles.isEmpty()) {
			model.addAttribute("articleList", articles);
		}

		return "article";
	}

	// saving article to db
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveArticle(@RequestParam("articleText") String articleText,
			@RequestParam("articleTitle") String articleTitle) {
		if (!articleText.trim().equals("")) {
			Article article = new Article();
			article.setTitle(articleTitle);
			article.setContent(articleText);
			dbAccess.insertArticle(article);

		}
		return "redirect:/";
	}

	// fetching certain article using it's identifier
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	@ResponseBody
	public String getArticleContent(@RequestParam("idArticle") String idArticle) {
		if (idArticle.equals("NONE")) {
			return "";
		}
		return dbAccess.getArticleContent(idArticle);
	}

	// checking if some text exists in article, simulating boolean return type
	@RequestMapping(value = "/contains", method = RequestMethod.GET, produces = "application/text; charset=utf-8")
	@ResponseBody
	public String containsSubstring(@RequestParam("articleContent") String articleContent,
			@RequestParam("subString") String subString) {
		//checking if text we are looking for exists at all
		if (subString.equals("")) {
			return "0";
		}
		//then we check if it is part of the article
		boolean contains = articleContent.contains(subString);
		if (contains)
			return "1";
		else
			return "0";

	}

}
