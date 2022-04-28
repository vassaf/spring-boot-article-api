package com.burak.article.api.controller;

import com.burak.article.api.model.Article;
import com.burak.article.api.model.IStatistic;
import com.burak.article.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequestMapping(path = "/article")
public class AritceController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/page")
    public ResponseEntity<Page<Article>> page(@RequestParam("page") int page,
                                              @RequestParam("size") int size) {

        return ResponseEntity.ok(this.articleService.page(page, size));
    }

    @PostMapping("/create")
    public ResponseEntity<Article> create(@RequestBody Article article) {
        return new ResponseEntity<>(this.articleService.create(article), HttpStatus.CREATED);
    }


    @GetMapping("/statistics")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<IStatistic>> getStatistics() {
        return ResponseEntity.ok(this.articleService.getStatistics());
    }

}
