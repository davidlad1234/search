package fm.last.search.pojos;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("#text")
    private String text;
    @SerializedName("size")
    private String size;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [text = "+text+", size = "+size+"]";
    }
}
