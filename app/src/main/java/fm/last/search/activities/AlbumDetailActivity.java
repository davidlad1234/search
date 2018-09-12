package fm.last.search.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.last.david.searchlastfm.R;
import fm.last.search.fragments.AlbumDetailFragment;

/**
 * An activity representing a single AlbumMatches detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item songs are presented side-by-side with a list of items
 * in a {@link AlbumListActivity}.
 */
public class AlbumDetailActivity extends AlbumActivity {

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @Nullable
    CollapsingToolbarLayout appBarLayout;

    String albumName = null;
    String artistName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            albumName = getIntent().getStringExtra(AlbumDetailFragment.ARG_ALBUM_NAME);
            artistName = getIntent().getStringExtra(AlbumDetailFragment.ARG_ALBUM_ARTIST);

            arguments.putString(AlbumDetailFragment.ARG_ALBUM_ID,
                    getIntent().getStringExtra(AlbumDetailFragment.ARG_ALBUM_ID));

            arguments.putString(AlbumDetailFragment.ARG_ALBUM_NAME, albumName);

            arguments.putString(AlbumDetailFragment.ARG_ALBUM_ARTIST, artistName);

            AlbumDetailFragment fragment = new AlbumDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.album_detail_container, fragment)
                    .commit();
        }
    }

    @OnClick(R.id.fab)
    public void onClickFab(View view) {
        Snackbar.make(view, albumName+ " - "+artistName , Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more songs, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, AlbumListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAppBarTitle(String artist) {
        if (appBarLayout != null) {
            appBarLayout.setTitle(artist);
            setTitle(artist);
        }
    }
}
