package fm.last.search.pojos.search;

import com.google.gson.annotations.SerializedName;

public class SearchResponseModel {
    @SerializedName("results")
    Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
