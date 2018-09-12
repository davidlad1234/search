package fm.last.search.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.last.david.searchlastfm.R;
import fm.last.search.fragments.AlbumDetailFragment;
import fm.last.search.pojos.search.SearchResponseModel;
import fm.last.search.pojos.search.Results;
import fm.last.search.remote.FMDataService;
import fm.last.search.utils.FMViewUtils;
import fm.last.search.remote.RetrofitClient;
import fm.last.search.pojos.search.Album;
import fm.last.search.pojos.search.AlbumMatches;
import fm.last.search.pojos.Image;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * An activity representing a list of Albums. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of Albums, which when touched,
 * lead to a {@link AlbumDetailActivity} representing
 * AlbumMatches songs. On tablets, the activity presents the list of Albums and
 * AlbumMatches songs side-by-side using two vertical panes.
 */
public class AlbumListActivity extends AlbumActivity {

    private static final String TAG = AlbumListActivity.class.getClass().getSimpleName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean isTwoPane;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.album_detail_container)
    @Nullable
    LinearLayout detailContainer;

    @BindView(R.id.album_list)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar_layout)
    @Nullable
    CollapsingToolbarLayout appBarLayout;
    private Album[] albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (detailContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            isTwoPane = true;
        }

        assert recyclerView != null;

    }

    @Override
    public void onStart() {
        super.onStart();
        captureAlbums();
    }

    @OnClick(R.id.fab)
    public void onClickFab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * Makes the http request and sends the Results to the adapter.
     * TODO implement MVP and move this method to a presenter object
     */
    private void captureAlbums() {
        Retrofit retroClient = RetrofitClient.getRetrofitInstance();
        FMDataService dataService = retroClient.create(FMDataService.class);
        Call<SearchResponseModel> call = dataService.getAlbum("believe");
        call.enqueue(new Callback<SearchResponseModel>() {
            @Override
            public void onResponse(Call<SearchResponseModel> call, Response<SearchResponseModel> response) {
                if (response.isSuccessful()) {
                    SearchResponseModel model = response.body();
                    if(model !=null) {
                        Results res = model.getResults();
                        Log.d(TAG, "Received from server: " + res);
                        AlbumMatches matches = res.getAlbumMatches();
                        if (matches != null) {
                            albums = matches.getAlbum();
                            recyclerView.setAdapter(new SimpleAlbumRecyclerViewAdapter(AlbumListActivity.this, albums, isTwoPane));
                        } else {
                            Log.d(TAG, "Null match received from server..");
                        }
                    }
                    else{
                        Log.d(TAG, "Null model received from server..");
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponseModel> call, Throwable t) {
                Log.d(TAG, "Error: " + t.getMessage());
                Toast.makeText(AlbumListActivity.this, getString(R.string.data_error), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void setAppBarTitle(String artist) {
        if (appBarLayout != null) {
            appBarLayout.setTitle(artist);
            setTitle(artist);
        }
    }

    public class SimpleAlbumRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleAlbumRecyclerViewAdapter.ViewHolder> {

        private final AlbumListActivity mParentActivity;
        private final Album[] albums;
        private final boolean mTwoPane;

        SimpleAlbumRecyclerViewAdapter(AlbumListActivity parent,
                                       Album[] Albums,
                                       boolean twoPane) {
            this.albums = Albums;
            mParentActivity = parent;
            mTwoPane = twoPane;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            fetchImage(holder.imageView, albums[position]);
            holder.imageView.setTag(position);

            holder.albumView.setText(albums[position].getName());

            holder.albumView.setTag(position);

            holder.artistView.setText(albums[position].getArtist());
            holder.artistView.setTag(position);

            holder.urlView.setText(albums[position].getArtist());
            holder.urlView.setTag(position);
            holder.streamView.setChecked(albums[position].isStreamable());
            holder.streamView.setTag(position);



        }

        private void fetchImage(ImageView imageView, Album album) {
            if (album != null) {
                Image image = album.getImage()[0];
                if (image != null) {
                    String url = image.getText();
                    FMViewUtils.loadImage(AlbumListActivity.this,imageView, url);
                }
            }
            else{
                Log.d(TAG,"Album is null...");
            }
        }

        @Override
        public int getItemCount() {
            return albums != null ? albums.length : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.id_image)
            ImageView imageView;
            @BindView(R.id.album)
            TextView albumView;
            @BindView(R.id.artist)
            TextView artistView;

            @BindView(R.id.url)
            TextView urlView;

            @BindView(R.id.streamable)
            CheckBox streamView;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }

            @OnClick({R.id.id_image, R.id.artist, R.id.album,R.id.streamable})
            public void onClickViews(View view) {
                int position = (int) view.getTag();
                Album album = albums[position];
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(AlbumDetailFragment.ARG_ALBUM_ID, String.valueOf(album.getId()));
                    arguments.putString(AlbumDetailFragment.ARG_ALBUM_NAME, String.valueOf(album.getName()));
                    arguments.putString(AlbumDetailFragment.ARG_ALBUM_ARTIST, String.valueOf(album.getArtist()));

                    AlbumDetailFragment fragment = new AlbumDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.album_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AlbumDetailActivity.class);
                    intent.putExtra(AlbumDetailFragment.ARG_ALBUM_ID, String.valueOf(album.getId()));

                    intent.putExtra(AlbumDetailFragment.ARG_ALBUM_NAME, String.valueOf(album.getName()));
                    intent.putExtra(AlbumDetailFragment.ARG_ALBUM_ARTIST, String.valueOf(album.getArtist()));
                    context.startActivity(intent);
                }
            }
        }
    }


}
