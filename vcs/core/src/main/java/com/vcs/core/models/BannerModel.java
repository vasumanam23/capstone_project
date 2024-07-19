package com.vcs.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerModel {

    @ValueMapValue
    private String img;

    @ValueMapValue
    private String alt;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String desc;

    public String getImg() {
        return img;
    }

    public String getAlt() {
        return alt;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}

