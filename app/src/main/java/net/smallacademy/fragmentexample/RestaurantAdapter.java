package net.smallacademy.fragmentexample;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RestaurantAdapter extends FirebaseRecyclerAdapter<Restaurant_model, RestaurantAdapter.myviewholder>
{

    public RestaurantAdapter(@NonNull FirebaseRecyclerOptions<Restaurant_model> options){super(options);}

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final Restaurant_model model) {
        final String postkey=getRef(position).getKey();
        holder.name_.setText(model.getName());
        holder.address_.setText(model.getAddress());
        holder.more_.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
            popupMenu.inflate(R.menu.pop_up_card_admin_menu);
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.edit_Res:
                        break;
                    case R.id.delete_Res:
                        FirebaseDatabase.getInstance().getReference("restaurants").child(postkey).removeValue();
                        FirebaseDatabase.getInstance().getReference("famous_restaurants").child(postkey).removeValue();
                        break;
                }
                return false;
            });
        });

        Glide.with(holder.img_.getContext()).load(model.getPurl()).into(holder.img_);

        holder.card_.setOnClickListener(view -> {
            Context context= view.getContext();
            Intent intent = new Intent(context, DetailRestaurantActivity.class);
            intent.putExtra("postkey",postkey);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        if (model.getAverageRating()!=null) holder.rating_card_.setRating(model.getAverageRating());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.largecard_layout, parent, false);
        return new myviewholder(view);
    }

    @Override
    public int getItemCount(){
        return super.getItemCount();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img_,more_;
        CardView card_;
        TextView name_, address_;
        RatingBar rating_card_;
        public myviewholder(@NonNull View view) {
            super(view);
            more_ = view.findViewById(R.id.more_option);
            img_ = view.findViewById(R.id.purl);
            card_ = view.findViewById(R.id.layout_);
            name_ = view.findViewById(R.id.name_);
            address_ = view.findViewById(R.id.address_);
            rating_card_ = itemView.findViewById(R.id.rating_);
        }
    }
}
