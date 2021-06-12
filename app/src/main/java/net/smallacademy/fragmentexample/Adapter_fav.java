package net.smallacademy.fragmentexample;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adapter_fav extends FirebaseRecyclerAdapter<fav_model,Adapter_fav.myviewholder>
{

    public Adapter_fav(@NonNull FirebaseRecyclerOptions<fav_model> options){super(options);}

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final fav_model model) {
        final String postkey=getRef(position).getKey();
        holder.namefav.setText(model.getName());
        holder.address.setText(model.getAddress());
        Glide.with(holder.img_fav.getContext()).load(model.getPurl()).into(holder.img_fav);

        holder.card.setOnClickListener(view -> {
            Context context= view.getContext();
            Intent intent = new Intent(context, DetailRestaurantActivity.class);
            intent.putExtra("postkey",postkey);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        if (model.getAverageRating()!=null) holder.rating_card.setRating(model.getAverageRating());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_layout, parent, false);
        return new myviewholder(view);
    }

    @Override
    public int getItemCount(){
        return super.getItemCount();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img_fav;
        CardView card;
        TextView namefav,address;
        RatingBar rating_card;
        public myviewholder(@NonNull View view) {
            super(view);
            img_fav = view.findViewById(R.id.purl);
            card= view.findViewById(R.id.layout_fav);
            namefav = view.findViewById(R.id.name_);
            address = view.findViewById(R.id.address_);
            rating_card = itemView.findViewById(R.id.rating_);
        }
    }
}
