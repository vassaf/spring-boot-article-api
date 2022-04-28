package com.burak.article.api.repository;

import com.burak.article.api.model.Article;
import com.burak.article.api.model.IStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select CAST(date as DATE), count(*) from article group by CAST(date AS DATE) order by date asc", nativeQuery = true)
    List<IStatistic> getStatistics();
}
