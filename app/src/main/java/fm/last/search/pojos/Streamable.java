package fm.last.search.pojos;

import com.google.gson.annotations.SerializedName;

public class Streamable {
    @SerializedName("fulltrack")
    private String fulltrack;

    @SerializedName("#text")
    private String text;

    public String getFulltrack() {
        return fulltrack;
    }

    public void setFulltrack(String fulltrack) {
        this.fulltrack = fulltrack;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ClassPojo [fulltrack = " + fulltrack + ", text = " + text + "]";
    }
}