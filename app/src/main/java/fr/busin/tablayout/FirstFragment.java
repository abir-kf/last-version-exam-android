package fr.busin.tablayout;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;
import fr.busin.tablayout.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FirstFragment extends Fragment {
    private RecyclerView rvMovie;
    MovieService movieService;
    private GridLayoutManager lm  ;
    private List<Movies> movies = new ArrayList<>();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_first, container, false);
        rvMovie = root.findViewById(R.id.rvMovie);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvMovie.setLayoutManager(linearLayoutManager);



        lm = new GridLayoutManager(getContext(),1);
        movieService = new Retrofit.Builder()
                .baseUrl(MovieService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);
        getPopularMovies();

        return root;
    }





    public void getPopularMovies(){

        movieService.getPopularMovies(1).enqueue(new Callback<MoviesS>() {
            @Override
            public void onResponse(Call<MoviesS> call, Response<MoviesS> response) {
                if(response.body()!= null) {
                    movies.clear();
                    movies.addAll(response.body().getMovies());//dial moviesS
                    Adapter adapter = new Adapter(movies);
                    rvMovie.setAdapter(adapter);
                    rvMovie.setLayoutManager(lm);
            }





            }

            @Override
            public void onFailure(Call<MoviesS> call, Throwable t) {
                Toast.makeText(getContext(),"Aucune corresponsance trouvée", Toast.LENGTH_LONG).show();

            }


        });
    }
}