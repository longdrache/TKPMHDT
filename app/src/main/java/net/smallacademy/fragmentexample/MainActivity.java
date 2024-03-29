package net.smallacademy.fragmentexample;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import net.smallacademy.fragmentexample.Drawer.DrawerAdapter;
import net.smallacademy.fragmentexample.Drawer.DrawerItem;
import net.smallacademy.fragmentexample.Drawer.SimpleItem;
import net.smallacademy.fragmentexample.Drawer.SpaceItem;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Toolbar toolbar;
    FloatingActionButton fab_add_restaurant;
    BottomNavigationView bottomNavigation;
    private static final int POS_HOME = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_FAV = 3;
    private static final int POS_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;
    SlidingRootNav slidingRootNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        fab_add_restaurant = findViewById(R.id.fab_add_restaurant);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");

        fab_add_restaurant.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddRestaurantActivity.class)));

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {


            FragmentFactory fragment = new FragmentFactory();
            switch (menuItem.getItemId()) {

                case R.id.home:
                    loadFragment(fragment.getIntance(FragmentE.HOME));
                    return true;
                case R.id.search:
                    loadFragment(fragment.getIntance(FragmentE.SEC));
                    return true;
                case R.id.fav:
                    loadFragment(fragment.getIntance(FragmentE.FAV));
                    return true;
                case R.id.personal:
                    return true;
            }
            return false;
        });

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .withContentClickableWhenMenuOpened(false)
                .inject();
        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_FAV),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        loadFragment(HomeFragment.getInstance());
    }

    private void loadFragment(Fragment secondFragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,secondFragment);
        fragmentTransaction.commit();
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.onselect))
                .withSelectedTextTint(color(R.color.onselect));
    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {
            FirebaseAuth.getInstance().signOut();
            Intent intent
                    = new Intent(MainActivity.this,
                    LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        slidingRootNav.closeMenu();
        if(position == POS_ACCOUNT){
            loadFragment(SecondFragment.getIntance());
        }
        if (position == POS_FAV) {
        }
        if (position == POS_HOME) {
            loadFragment(HomeFragment.getInstance());
        }
    }
    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }
    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }
    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
