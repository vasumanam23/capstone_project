package com.vcs.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselChildModel {

    @ValueMapValue
    private String img;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String link;

    public String getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }
}
