package com.bignerdranch.android.criminalintent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends /*FragmentActivity*/ SingleFragmentActivity {
    //implementation before SingleFragmentActivity, using only one fragment.
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //getSupportFragmentManager() is used because you calling androidx
        // library, if it was internal library it would be just
        // getFragmentManager()
        FragmentManager fm = getSupportFragmentManager();
        //When you need to retrieve the CrimeFragment from the FragmentManager
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = new CrimeFragment(); //<---------
            //"create a new fragment transaction, include one add operation
            // in it, and then commit it."
            fm.beginTransaction()
                    //the code .add() creates and commits a fragment
                    // transaction
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }*/

    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android" +
            ".criminalintent.crime_id";

    public static Intent newIntent (Context packageContext, UUID crimeId){
        Intent intent = new Intent (packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        //return new CrimeFragment();
        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
