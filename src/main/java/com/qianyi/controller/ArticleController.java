package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.dto.AddArticleDto;
import com.qianyi.domain.dto.ArticleDto;
import com.qianyi.domain.entity.Article;
import com.qianyi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 千亦
 * @create 2022-11-17-9:55
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto addArticleDto){
        ResponseResult result = articleService.add(addArticleDto);
        return result;
    }

    /**
     * 文章列表接口设计
     * @return
     */
    @GetMapping("/list")
    public ResponseResult listAll(Integer pageNum, Integer pageSize, Article article){
        ResponseResult result = articleService.listAll(pageNum,pageSize,article);
        return result;
    }

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticle(@PathVariable Long id){
        ResponseResult result = articleService.getArticle(id);
        return result;
    }

    /**
     * 更新博文
     * @param articleDto
     * @return
     */
    @PutMapping
    public ResponseResult edit(@RequestBody ArticleDto articleDto){
        ResponseResult result = articleService.edit(articleDto);
        return result;
    }
    @DeleteMapping("/{id}")
    public ResponseResult delArticle(@PathVariable Long id){
        ResponseResult result = articleService.delArticle(id);
        return result;
    }
}
