package fr.busin.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Details extends AppCompatActivity {

    MovieService MovieService;
    String Idmovie;
    int movieId;
    public TextView original_title;
    public TextView release_date;
    public TextView overview;
    public ImageView backdrop_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //final TextView textView = findViewById(R.id.section_id);
        original_title = findViewById(R.id.original_title);
        release_date = findViewById(R.id.release_date);
        overview = findViewById(R.id.overview);
        backdrop_path = findViewById(R.id.backdrop_path);

        Intent intent = getIntent();
        movieId = intent.getIntExtra("movieId", 1);
        Log.v("Tagy", "movieId Details Activity " +movieId);
        Idmovie = String.valueOf(movieId);
        //textView.setText(String.valueOf(Idmovie));

        //View details = findViewById(R.id.details);




         MovieService = new Retrofit.Builder()
                .baseUrl(MovieService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        getMovieInfo();
    }

    public void getMovieInfo(){
     MovieService.getMovieInfo(movieId).enqueue(new Callback<Movies>(){

         @Override
         public void onResponse(Call<Movies> call, Response<Movies> response) {

             response.body().getBackdrop_path();

             original_title.setText( response.body().getOriginal_title());
             overview.setText( response.body().getOverview());
             release_date.setText(response.body().getRelease_date());


             //ImageView: Using Glide Library
             Glide.with(backdrop_path.getContext())
                     .load("https://image.tmdb.org/t/p/w500/" + response.body().getBackdrop_path())
                     .into(backdrop_path);


         }

         @Override
         public void onFailure(Call<Movies> call, Throwable t) {
             Toast.makeText(Details.this,"Aucune corresponsance trouvée", Toast.LENGTH_LONG).show();

         }


    });
    }




}