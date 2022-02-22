package fr.busin.tablayout;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {


    public static final String ENDPOINT = "https://api.themoviedb.org";
    static String api_key = "3c66aaf4a3332dc5308306eb61003e72";



    @GET("/3/movie/popular?api_key="+api_key)
    Call<MoviesS> getPopularMovies(@Query("page") int page);


    @GET("/3/movie/{movie_id}?api_key="+api_key+"&include_adult=false")
    Call<Movies> getMovieInfo(@Path("movie_id") int movie_id);

    @GET("/3/search/movie?api_key="+api_key+"&include_adult=false")
    Call<MoviesS> getSearchedMovie(@Query("query") String query);
    //Call<Movies> searchMovie(@Query("query") String query,@Query("page") int page);


}