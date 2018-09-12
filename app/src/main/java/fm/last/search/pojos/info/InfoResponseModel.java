package fm.last.search.pojos.info;

import com.google.gson.annotations.SerializedName;

public class InfoResponseModel {
    @SerializedName("album")
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
