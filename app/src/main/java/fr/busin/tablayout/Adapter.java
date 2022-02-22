package fr.busin.tablayout;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fr.busin.tablayout.Movies;
import fr.busin.tablayout.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final List<Movies> mMovies;

    public Adapter(List<Movies> mMovies){
        this.mMovies = mMovies;
    }

    @NonNull
    @Override
    //onCreateViewHolder utilisé pour déclarer le xml de la vue d’un item
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        // l’objet LayoutInflater pour déclarer quelle vue va décrire un item de la liste
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.item, parent, false);

        return new ViewHolder(movieView);
    }

    @Override
    //onBindViewHolder utilisé pour afficher les données passées en paramètres de l’adapter 9nous allons afficher et définir les différentes vues d’un item.  0
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies pos = mMovies.get(position);

        TextView firstNameTextView = holder. original_title;
        firstNameTextView.setText(pos.getOriginal_title());

        TextView lastNameTextView = holder.release_date;
        lastNameTextView.setText(pos.getRelease_date());

        ImageView backdrop_path= holder.backdrop_path;


        //ImageView: Using Glide Library
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + pos.getBackdrop_path())
                .into(backdrop_path);



        holder.backdrop_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Tagy", "movieId Adapter " +pos.getId());
                Intent intent = new Intent(view.getContext(), Details.class);//call details activity
                intent.putExtra("movieId",pos.getId());
                view.getContext().startActivity(intent);


            }





    });

    }

    @Override
    //getItemCount utilisé pour déterminer le nombre d’éléments dans la liste
    public int getItemCount() {
        return mMovies.size();
    }


    //objet "ViewHolder" qui décrit et donne accès à toutes les vues de chaque ligne d'élément.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView original_title;
        public TextView release_date;
        public ImageView backdrop_path;


        public ViewHolder(View itemView){
            super(itemView);
            original_title = itemView.findViewById(R.id.original_title);

            release_date = itemView.findViewById(R.id.release_date);

            backdrop_path = itemView.findViewById(R.id.backdrop_path);


        }

    }
}
