package com.bd.myapp.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bd.myapp.Article;

@Repository
public class SQLServerDBAccess {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void insertArticle(Article article) {

		this.jdbcTemplate.update("insert into Article (Title, Content) values (?, ?)",
				new Object[] { article.getTitle(), article.getContent() });
	}
	
	public String getArticleContent(String idArticle){
		String articleContent = this.jdbcTemplate.queryForObject(
		        "select Content from Article where IDArticle = ?",
		        new Object[]{idArticle}, String.class);
		return articleContent;
	}

	public List<Article> getAllArticles() {
		return this.jdbcTemplate.query("select IDArticle, Title, Content from Article", new UserMapper());
	}

	private static final class UserMapper implements RowMapper<Article> {

		public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
			Article article = new Article();
			article.setId(rs.getInt("IDArticle"));
			article.setTitle(rs.getString("Title"));
			article.setContent(rs.getString("Content"));
			return article;
		}
	}
}
