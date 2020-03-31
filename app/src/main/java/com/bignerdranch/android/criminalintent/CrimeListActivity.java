package com.bignerdranch.android.criminalintent;

import android.util.Log;

import androidx.fragment.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {
    private static final String TAG = "CrimeActivity";

    @Override
    protected Fragment createFragment(){
        Log.d(TAG, "calling CrimeListFragment()");
        return new CrimeListFragment();
    }
}
