package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends Fragment {
    private static final String TAG = "CrimeActivity";


    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private int mCurrentPosition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        Log.d(TAG, "CrimeListFragment - onCreateView ");
        View view = inflater.inflate(R.layout.fragment_crime_list, container,
                false);

        mCrimeRecyclerView =
                (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(mCurrentPosition);
    }
    private void updateUI(int position){
        Log.d(TAG,"updateUI(int)");
        mAdapter.notifyItemChanged(position);
    }


    //sets up CrimeListFragment's user interface
    private void updateUI() {
        Log.d(TAG, "CrimeListFragment - updateUI ");
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
/*     //USING notifyDataSetChange()
        if (mAdapter == null) {

        } else {
            mAdapter.notifyDataSetChanged();
        }*/

    }

    //VIEWHOLDER
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "CrimeActivity";
        public TextView mTitleTextView;
        public TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Log.d(TAG, "CrimeListFragment - VIEWHOLDER ");

            mTitleTextView =
                    (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView =
                    (TextView) itemView.findViewById(R.id.list_item_date_text_view);
            mSolvedCheckBox =
                    (CheckBox) itemView.findViewById(R.id.list_item_solved_check_box);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
          /*  Toast.makeText(getActivity(), mCrime.getTitle() + " Clicked!",
                    Toast.LENGTH_SHORT).show();*/
            //    Intent intent = new Intent(getActivity(), CrimeActivity
            //    .class);
            Log.d(TAG, "onClick");
            mCurrentPosition = getAdapterPosition();
            Intent intent = CrimeActivity.newIntent(getActivity(),
                    mCrime.getId());
            startActivity(intent);
        }
    }


    //ADAPTER
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private static final String TAG = "CrimeActivity";

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        //Second this method is called by RecyclerView when it needs a new VIEW
        // to display an item
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
            Log.d(TAG, "CrimeListFragment - ADAPTER ");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            //inflate a layout from Android standard library called
            // simple_list_item_1 - this layout contains a single TextView
        /*    View crimeView =
                    layoutInflater.inflate(android.R.layout
                    .simple_list_item_1, parent, false);*/
            View crimeView = layoutInflater.inflate(R.layout.list_item_crime,
                    parent, false);
            return new CrimeHolder(crimeView);
        }

        //Third, this method will bind a ViewHolder's View to your model obj.
        @Override
        public void onBindViewHolder(CrimeHolder holder,
                                     int position) {
            Log.d(TAG, "mAdapter: " + (mAdapter == null));
            //use position to find the right model data
            Crime crime = mCrimes.get(position);
            //update the view to reflect that model data
            //   holder.mTitleTextView.setText(crime.getTitle());
            holder.bindCrime(crime);

         //   notifyItemChanged(holder.getAdapterPosition());

        }

        //First RecyclerView calls this method
        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
