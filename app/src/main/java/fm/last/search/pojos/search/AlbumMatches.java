package fm.last.search.pojos.search;

import com.google.gson.annotations.SerializedName;

public class AlbumMatches {
    @SerializedName("album")
    private Album[] album;

    public Album[] getAlbum() {
        return album;
    }

    public void setAlbum(Album[] album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "ClassPojo [album = " + album + "]";
    }
}
