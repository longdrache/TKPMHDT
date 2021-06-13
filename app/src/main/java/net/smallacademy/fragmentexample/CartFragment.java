package net.smallacademy.fragmentexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CartFragment extends Fragment {
    RecyclerView recview;
    BookAdapter bookAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recview = view.findViewById(R.id.book_rec);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<BookModel> options =
                new FirebaseRecyclerOptions.Builder<BookModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("booking"), BookModel.class)
                        .build();

        bookAdapter=new BookAdapter(options);
        recview.setAdapter(bookAdapter);

        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        bookAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        bookAdapter.stopListening();
    }
}