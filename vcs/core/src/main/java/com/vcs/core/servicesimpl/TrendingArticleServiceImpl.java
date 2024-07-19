package com.vcs.core.servicesimpl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vcs.core.models.ArticleBannerModel;
import com.vcs.core.services.TrendingArticleService;
import com.vcs.core.services.ResourceResolverService;

import javax.jcr.query.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = TrendingArticleService.class, immediate = true)
public class TrendingArticleServiceImpl implements TrendingArticleService {

    private static final Logger log = LoggerFactory.getLogger(TrendingArticleServiceImpl.class);

    @Reference
    private ResourceResolverService resourceResolverService;

    @Override
    public List<ArticleBannerModel> getTrendingArticles() {
        List<ArticleBannerModel> bannerList = new ArrayList<>();
        ResourceResolver resolver = null;

        try {
            resolver = resourceResolverService.getResourceResolver();
            if (resolver == null) {
                log.error("ResourceResolver is null");
                return bannerList;
            }
            
            String query = "SELECT * FROM [cq:Page] AS s WHERE ISDESCENDANTNODE([/content/vcs/us/en]) ORDER BY s.[jcr:content/jcr:created] DESC";
            Iterator<Resource> result = resolver.findResources(query, Query.JCR_SQL2);

            while (result.hasNext()) {
                Resource resource = result.next();
                Resource articleResource = resolver.getResource(resource.getPath() + "/jcr:content/root/container/banner");
                if (articleResource != null) {
                    ArticleBannerModel articleBanner = articleResource.adaptTo(ArticleBannerModel.class);
                    if (articleBanner != null) {
                        articleBanner.setPagePath(resource.getPath());
                        if (bannerList.size() < 5) {
                            bannerList.add(articleBanner);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error retrieving trending articles", e);
        } finally {
            if (resolver != null) {
                resolver.close();
            }
        }

        return bannerList;
    }
}
