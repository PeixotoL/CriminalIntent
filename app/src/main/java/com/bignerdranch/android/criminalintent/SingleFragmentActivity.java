package com.bignerdranch.android.criminalintent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //getSupportFragmentManager() is used because you calling androidx
        // library, if it was internal library it would be just
        // getFragmentManager()
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = createFragment();
            //"create a new fragment transaction, include one add operation
            // in it, and then commit it."
            fm.beginTransaction()
                    //the code .add() creates and commits a fragment
                    // transaction
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
