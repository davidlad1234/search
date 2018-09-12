package fm.last.search.remote;

import fm.last.search.pojos.info.Album;
import fm.last.search.pojos.info.InfoResponseModel;
import fm.last.search.pojos.search.SearchResponseModel;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FMDataService {

    @GET("/2.0/?method=album.search&api_key=c4f073a17c5b38abf960188b828d3fae&format=json")
    Single<SearchResponseModel> getAlbum(@Query("album") String album);

    @GET("/2.0/?method=album.getinfo&api_key=c4f073a17c5b38abf960188b828d3fae&format=json")
    Single<InfoResponseModel> getAlbumInfo(@Query("artist") String artist, @Query("album") String  album);
}
