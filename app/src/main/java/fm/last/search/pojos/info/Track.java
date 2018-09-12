package fm.last.search.pojos.info;

import com.google.gson.annotations.SerializedName;

import fm.last.search.pojos.Artist;
import fm.last.search.pojos.Streamable;

public class Track {
    @SerializedName("name")
    private String name;
    @SerializedName("duration")
    private int duration;
    @SerializedName("url")
    private String url;
    @SerializedName("streamable")
    private Streamable streamable;
    @SerializedName("artist")
    private Artist artist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public void setStreamable(Streamable streamable) {
        this.streamable = streamable;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                ", streamable=" + streamable +
                ", artist=" + artist +
                '}';
    }
}
