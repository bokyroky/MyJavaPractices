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

		return "home";
	}

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

	@RequestMapping(value = "/articleNo", method = RequestMethod.GET)
	@ResponseBody
	public String getArticleContent(@RequestParam("idArticle") String idArticle) {
		return dbAccess.getArticleContent(idArticle);
	}

	@RequestMapping(value = "/contains", method = RequestMethod.GET,
	produces = "application/text; charset=utf-8")
	@ResponseBody
	public String containsSubstring(@RequestParam("articleContent") String articleContent,
			@RequestParam("subString") String subString) {
		if( subString.equals(""))
			return "0";
		boolean contains = articleContent.contains(subString);
		if(contains)
			return "1";
		else return "0";

	}

}
