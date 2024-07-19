package com.vcs.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrimaryHeaderModel {

    @ValueMapValue
    private String trainingText;

    @ValueMapValue
    private String trainingDesc;

    private String todayDate;

    @PostConstruct
    private void init(){
        SimpleDateFormat format=new SimpleDateFormat("EEEE,d MMMM yyyy");
        todayDate=format.format(new Date());
    }

    public String getTrainingText() {
        return trainingText;
    }

    public String getTrainingDesc() {
        return trainingDesc;
    }

    public String getTodayDate() {
        return todayDate;
    }

    




    
}
