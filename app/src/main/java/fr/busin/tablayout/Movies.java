package fr.busin.tablayout;

public class Movies {


    private String  original_title;
    private String  release_date;
    private String  backdrop_path;
    private int id;
    private String  overview;

    public Movies(String original_title, String release_date, String backdrop_path, int id, String overview) {
        this.original_title = original_title;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
