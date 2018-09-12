package fm.last.search.pojos.search;

import com.google.gson.annotations.SerializedName;

public class Results {
    @SerializedName("albummatches")
    private AlbumMatches albumMatches;
    @SerializedName("opensearch:Query")
    private Query query;
    @SerializedName("opensearch:startIndex")
    private String startIndex;
    @SerializedName("opensearch:itemsPerPage")
    private String itemsPerPage;
    @SerializedName("openSearch:totalResults")
    private String totalsResults;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getTotalsResults() {
        return totalsResults;
    }

    public void setTotalsResults(String totalsResults) {
        this.totalsResults = totalsResults;
    }

    public AlbumMatches getAlbumMatches ()
    {
        return albumMatches;
    }

    public void setAlbumMatches (AlbumMatches albumMatches)
    {
        this.albumMatches = albumMatches;
    }

    @Override
    public String toString() {
        return "Results{" +
                "albumMatches=" + albumMatches +
                ", query=" + query +
                ", startIndex='" + startIndex + '\'' +
                ", itemsPerPage='" + itemsPerPage + '\'' +
                ", totalsResults='" + totalsResults + '\'' +
                '}';
    }
}
