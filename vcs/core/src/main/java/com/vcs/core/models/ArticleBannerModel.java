package com.vcs.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleBannerModel{

    @ValueMapValue
    private String bannerImage;

    @ValueMapValue
    private String[] bannerText;

    public String getBannerImage() {
        return bannerImage;
    }

    public String[] getBannerText() {
        return bannerText;
    }

    @ValueMapValue(name="jcr:created")
    private Date jcrCreated;

    private String createdDateStr;

    private String pagePath;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    

    @PostConstruct
    private void init(){
        SimpleDateFormat format=new SimpleDateFormat("MMMM d,yyyy");
        createdDateStr=format.format(jcrCreated);
    }

    public String getCreatedDateStr() {
        return createdDateStr;
    }


}