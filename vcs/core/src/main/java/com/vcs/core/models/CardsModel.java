package com.vcs.core.models;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardsModel {

    @ChildResource
    private List<CardsListModel> cardField;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String[] desc;

    public String getTitle() {
        return title;
    }

    public String[] getDesc() {
        return desc;
    }

    public List<CardsListModel> getCardField() {
        return cardField;
    }

}
