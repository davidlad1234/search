package fm.last.search.pojos.search;

import com.google.gson.annotations.SerializedName;

import fm.last.search.pojos.Image;

public class Album {

    @SerializedName("name")
    private String name;
    @SerializedName("artist")
    private String artist;
    @SerializedName("id")
    private long id;
    @SerializedName("url")
    private String url;
    @SerializedName("image")
    private Image[] image;
    @SerializedName("streamable")
    private boolean streamable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image[] getImage() {
        return image;
    }

    public void setImage(Image[] image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public boolean isStreamable() {
        return streamable;
    }

    public void setStreamable(boolean streamable) {
        this.streamable = streamable;
    }
}
