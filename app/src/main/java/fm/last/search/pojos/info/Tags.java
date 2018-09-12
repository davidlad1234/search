package fm.last.search.pojos.info;

import com.google.gson.annotations.SerializedName;

public class Tags {
    @SerializedName("tag")
    private Tag[] tag;

    public Tag[] getTag ()
    {
        return tag;
    }

    public void setTag (Tag[] tag)
    {
        this.tag = tag;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tag = "+tag+"]";
    }
}
