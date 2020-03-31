package com.bignerdranch.android.criminalintent;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    //Fragmet Argument
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //1 - direct shortcut to retrieving the extra & fetching CRIME
        //mCrime = new Crime();
        /*getIntent() returns the INTENT that was used to start CrimeActivity
        . You call getSerializable(String) on the Intent to pull the UUID out
         into a variable*/
//        UUID crimeId =
//                (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        /*after you retrieved the ID, you use it to fetch the Crime from
        CrimeLab.*/
//        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

        //2 - Getting Crime ID from the arguments
        UUID crimeId = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    //using onCreateView you explicitly inflate the fragment's view by
    // calling layoutInflater.inflate(...). with this method you also the
    // place to wire uo the EditText to respond to user input.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        //wiring up the EditText to respond to user input
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this one too
            }
        });

        //get ref. to the new Button, setting text as the date of the crime
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        //for now the button is disable - ensuring that it will not respond
        // in any way
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                //Set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });


        return v;
    }
}
