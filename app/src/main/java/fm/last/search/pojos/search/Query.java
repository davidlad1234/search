package fm.last.search.pojos.search;

import com.google.gson.annotations.SerializedName;

//@SerializedName("OpenSearch:Query")
public class Query {

    @SerializedName("searchTerms")
    private String searchTerms;
    @SerializedName("role")
    private String role;
    @SerializedName("#text")
    private String text;
    @SerializedName("startPage")
    private String startPage;

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    @Override
    public String toString() {
        return "ClassPojo [searchTerms = " + searchTerms + ", role = " + role + ", text = " + text + ", startPage = " + startPage + "]";
    }

}
