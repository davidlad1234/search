package fm.last.search.pojos.info;

import com.google.gson.annotations.SerializedName;

public class Wiki {

    @SerializedName("content")
    private String content;
    @SerializedName("summary")
    private String summary;
    @SerializedName("published")
    private String published;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "ClassPojo [content = " + content + ", summary = " + summary + ", published = " + published + "]";
    }
}