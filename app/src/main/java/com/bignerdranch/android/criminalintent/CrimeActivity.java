package com.bignerdranch.android.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        //getSupportFragmentManager() is used because you calling androidx
        // library, if it was internal library it would be just
        // getFragmentManager()
        FragmentManager fm = getSupportFragmentManager();
        //When you need to retrieve the CrimeFragment from the FragmentManager
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = new CrimeFragment();
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
