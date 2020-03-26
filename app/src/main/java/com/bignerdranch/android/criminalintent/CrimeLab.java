package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//CrimeLAb class is a Singleton
public class CrimeLab {
    //s in sCrimeLab is android convention to make it clear that is a static
    // variable
    private static CrimeLab sCrimeLab;
    //List<E> - is an interface that supports an ordered list of obj. of a
    // given type.
    private List<Crime> mCrimes; //list of crimes var.

    public static CrimeLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    //private constructor
    private CrimeLab(Context context){
        //A commonly used implementation of List is ArrayList.
        mCrimes = new ArrayList<>(); //empty list of crimes
        //for now 100 boring crimes to populate the list
        for (int i = 0; i<100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); //Every other one
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }
    public Crime getCrime(UUID id){
        for (Crime crime : mCrimes){
            if (crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
