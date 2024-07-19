package com.vcs.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardsListModel {

    @ValueMapValue
    private String img;

    @ValueMapValue
    private String cardText;

    @ValueMapValue
    private String link;

    public String getImg() {
        return img;
    }

    public String getCardText() {
        return cardText;
    }

    public String getLink() {
        return link;
    }
}


