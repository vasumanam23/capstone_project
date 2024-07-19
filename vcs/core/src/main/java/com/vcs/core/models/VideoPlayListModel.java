package com.vcs.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoPlayListModel {

    @ValueMapValue
    private String[] youtubeLinks;

    private List<YoutubeVideoResponse> videoList;

    @PostConstruct
    protected void init() {
        videoList = new ArrayList<>();
        if (youtubeLinks != null) {
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                for (String youtubeLink : youtubeLinks) {
                    HttpGet request = new HttpGet("https://www.youtube.com/oembed?url=" + youtubeLink);
                    try (CloseableHttpResponse response = client.execute(request)) {
                        String entity = EntityUtils.toString(response.getEntity());
                        if (entity != null) {
                            try (JsonReader jsonReader = Json.createReader(new StringReader(entity))) {
                                JsonObject jsonObject = jsonReader.readObject();
                                YoutubeVideoResponse youtubeResponseDto = new YoutubeVideoResponse();
                                youtubeResponseDto.setHtml(jsonObject.getString("html"));
                                youtubeResponseDto.setTitle(jsonObject.getString("title"));
                                youtubeResponseDto.setThumbnailUrl(jsonObject.getString("thumbnail_url"));
                                videoList.add(youtubeResponseDto);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<YoutubeVideoResponse> getVideoList() {
        return videoList;
    }

    public static class YoutubeVideoResponse {
        private String html;
        private String title;
        private String thumbnailUrl;

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }
    }
}
