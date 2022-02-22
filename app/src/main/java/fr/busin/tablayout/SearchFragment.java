package fr.busin.tablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView rvMovie;
    MovieService movieService;
    private GridLayoutManager lm  ;
    private List<Movies> movies = new ArrayList<>();






    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = root.findViewById(R.id.search);

        rvMovie = root.findViewById(R.id.rvSearch);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvMovie.setLayoutManager(linearLayoutManager);



        lm = new GridLayoutManager(getContext(),1);
        movieService = new Retrofit.Builder()
                .baseUrl(MovieService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        //SearchView
        SetupSearchView();


        final TextView textView = root.findViewById(R.id.section_label);

        textView.setText("Searched Fragment");

        return root;
    }


    //Getting data from searchView and query the api to get the results (Movies)
    private void SetupSearchView() {



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchedMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }


    public void getSearchedMovie(String query){

        movieService.getSearchedMovie(query).enqueue(new Callback<MoviesS>() {
            @Override
            public void onResponse(Call<MoviesS> call, Response<MoviesS> response) {
                if(response.body()!= null) {
                    movies.clear();
                    movies.addAll(response.body().getMovies());
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