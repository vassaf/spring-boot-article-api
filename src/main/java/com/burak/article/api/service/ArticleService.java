package com.burak.article.api.service;

import com.burak.article.api.model.Article;
import com.burak.article.api.model.IStatistic;
import com.burak.article.api.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article create(Article article){
        debugLog("Article saving : "+ article);
        return this.articleRepository.save(article);
    }

    public List<Article> listAll(){
        return this.articleRepository.findAll();
    }

    public Page<Article> page(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return this.articleRepository.findAll(pageable);
    }

    public List<IStatistic> getStatistics(){
        return this.articleRepository.getStatistics();
    }


    private void debugLog(String message){
        if(log.isDebugEnabled()){
            log.debug(message);
        }
    }

}
