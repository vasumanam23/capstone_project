package com.vcs.core.services;

import java.util.List;

import com.vcs.core.models.ArticleBannerModel;

public interface TrendingArticleService {
    
    public List<ArticleBannerModel> getTrendingArticles();
}
