package fm.last.search.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.last.david.searchlastfm.R;
import fm.last.search.activities.AlbumActivity;
import fm.last.search.activities.AlbumDetailActivity;
import fm.last.search.activities.AlbumListActivity;
import fm.last.search.pojos.Image;
import fm.last.search.pojos.info.Album;
import fm.last.search.pojos.info.InfoResponseModel;
import fm.last.search.remote.FMDataService;
import fm.last.search.remote.RetrofitClient;
import fm.last.search.utils.FMViewUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A fragment representing a single AlbumMatches detail screen.
 * This fragment is either contained in a {@link AlbumListActivity}
 * in two-pane mode (on tablets) or a {@link AlbumDetailActivity}
 * on handsets.
 */
public class AlbumDetailFragment extends Fragment {
    private static final String TAG = AlbumDetailFragment.class.getClass().getSimpleName();
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ALBUM_ID = "album_id";

    public static final String ARG_ALBUM_NAME = "album_name";
    public static final String ARG_ALBUM_ARTIST = "album_artist";
    public static final String ALBUM_DETAIL_FRAGMENT = "AlbumDetailFragment";

    /**
     * The  artist/album this fragment is presenting.
     */


    private String albumName;
    private String albumArtist;

//    @BindView(R.id.album_detail)
//    LinearLayout aTextView;

    @BindView(R.id.id_image)
    ImageView imageView;

    @BindView(R.id.url)
    TextView urlView;

    @BindView(R.id.wiki)
    TextView wikiView;

    @BindView(R.id.artist)
    TextView artistView;

    @BindView(R.id.album)
    TextView albumView;


    @BindView(R.id.tracks)
    TextView tracksView;
    private DisposableSingleObserver<InfoResponseModel> disposableSingleObserver;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlbumDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ALBUM_ID)) {
            // Load the dummy artist specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load artist from a artist provider.
            albumName = getArguments().getString(ARG_ALBUM_NAME);
            albumArtist = getArguments().getString(ARG_ALBUM_ARTIST);

            AlbumActivity activity = (AlbumActivity) this.getActivity();
            if (activity != null) {
                activity.setAppBarTitle(albumName);
            }

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.album_detail, container, false);
        ButterKnife.bind(this, rootView);

        AlbumActivity activity = (AlbumActivity) getActivity();
        activity.setAppBarTitle(albumName + " - " + albumArtist);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchAlbumInfo();
    }

    /**
     * Fetches the remote information
     * TODO migrate to MVP and use RXJAVA
     */
    private void fetchAlbumInfo() {
        Retrofit retroClient = RetrofitClient.getRetrofitInstance();
        FMDataService dataService = retroClient.create(FMDataService.class);
        disposableSingleObserver = dataService.getAlbumInfo(albumArtist, albumName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<InfoResponseModel>() {
                    @Override
                    public void onSuccess(InfoResponseModel response) {
                        if (response != null) {
                            Album album = response.getAlbum();
                            if (album != null) {
                                writeScreen(album);
                            } else {
                                Log.d(TAG, "Error getting album..");
                            }


                        } else {
                            Log.d(TAG, "Error getting response ");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(ALBUM_DETAIL_FRAGMENT, "Error: " + e.getMessage());
                        Toast.makeText(getContext(), getString(R.string.data_error), Toast.LENGTH_LONG).show();

                    }

                });

    }

    /**
     * Writes to the screen of the fragment
     *
     * @param albumInfo The album object obtained from the fm webservice
     */
    private void writeScreen(Album albumInfo) {

        FMViewUtils.formatString(albumInfo.getTracks(), tracksView);
        if (albumInfo.getWiki() != null) {
            Spanned spanner = Html.fromHtml(albumInfo.getWiki().getSummary());
            wikiView.setText(spanner);
        }
        Image[] images = albumInfo.getImage();
        if (images != null) {
            if (images[1] != null) {
                FMViewUtils.loadImage(getContext(), imageView, images[1].getText());
            }
        }

        albumView.setText(albumInfo.getName());
        artistView.setText(albumInfo.getArtist());
        urlView.setText(Html.fromHtml(albumInfo.getUrl()));


    }

    @Override
    public void onStop() {
        super.onStop();
        if (disposableSingleObserver != null && !disposableSingleObserver.isDisposed()) {
            disposableSingleObserver.dispose();
        }
    }


}
