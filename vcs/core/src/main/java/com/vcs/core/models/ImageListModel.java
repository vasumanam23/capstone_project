package com.vcs.core.models;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageListModel {

    @ValueMapValue
    private String img;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String linkText;

    public String getImg() {
        return img;
    }

    public String getLink() {
        return link;
    }

    public String getLinkText() {
        return linkText;
    }


}
