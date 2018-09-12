package fm.last.search.utils;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fm.last.search.pojos.info.Track;
import fm.last.search.pojos.info.Tracks;

public class FMViewUtils {
    public static void loadImage(Context context, ImageView imageView,  String url) {

        if (url != null && !url.isEmpty()) {
            Picasso picasso = Picasso.with(context);
            picasso.load(url).centerCrop().fit().into(imageView);
        }
    }

    public static void formatString(Tracks tracks, TextView textView){
        StringBuilder stb = new StringBuilder();
        for( Track track:tracks.getTrack()){
            stb.append(track.getName()+"<BR/>");
        }
        textView.setText(Html.fromHtml(stb.toString()));
    }

}
