package org.springrain.system.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.system.entity.Article;
import org.springrain.system.service.IArticleService;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:55:40
 */

@Service("articleService")
public class ArticleServiceImpl extends BaseSpringrainServiceImpl implements IArticleService {

    @Override
    public String save(IBaseEntity entity) throws Exception {
        Article article = (Article) entity;
        return super.save(article).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        Article article = (Article) entity;
        return super.update(article);
    }

    @Override
    public Article findArticleById(String id) throws Exception {
        return super.findById(id, Article.class);
    }


}
