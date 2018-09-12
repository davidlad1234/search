package fm.last.search.pojos.info;

import com.google.gson.annotations.SerializedName;

public class Tracks {
    @SerializedName("track")
    private Track[] track;

    public Track[] getTrack ()
    {
        return track;
    }

    public void setTrack (Track[] track)
    {
        this.track = track;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [track = "+track+"]";
    }
}
