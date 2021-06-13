package net.smallacademy.fragmentexample;

import androidx.fragment.app.Fragment;

public class FragmentFactory {
    public Fragment getIntance(FragmentE fragmemt){
        if(fragmemt==null) return null;
        switch (fragmemt){
            case HOME:
                return  HomeFragment.getInstance();
            case FAV:
                return FavFragment.getIntance();
            case SEAR:
                return SecondFragment.getIntance();
        }
        return null;
    }
}
