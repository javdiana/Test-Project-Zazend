package com.di.jav.testprojectzazend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PeopleListFragment extends Fragment implements View.OnClickListener {
    private EditText mSeedEditText;
    private EditText mSearchEditText;
    private TextView mCurrentSeedTextView;

    public static PeopleListFragment newInstance() {
        return new PeopleListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people_list, container, false);
        RecyclerView peopleRecyclerView = view.findViewById(R.id.recyclerView_people);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        peopleRecyclerView.setAdapter(new PeopleAdapter(new ArrayList<Person>()));// TODO: 7/8/2019 add list with people

        mSeedEditText = view.findViewById(R.id.editText_seed);
        Button applyButton = view.findViewById(R.id.button_apply);
        applyButton.setOnClickListener(this);

        mCurrentSeedTextView = view.findViewById(R.id.textView_current_seed);
        mSearchEditText = view.findViewById(R.id.editText_search);
        Button clearButton = view.findViewById(R.id.button_clear);
        clearButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_apply:
                // TODO: 7/8/2019 change seed
                break;
            case R.id.button_clear:
                mSearchEditText.setText("");
                break;

        }
    }

    private class PeopleViewHolder extends RecyclerView.ViewHolder {
        private ImageView mPhotoImageView;
        private TextView mFirstNameTextView;
        private TextView mLastNameTextView;
        private TextView mDateOfBirthTextView;
        private TextView mAgeTextView;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.imageView_photo);
            mFirstNameTextView = itemView.findViewById(R.id.textView_firstname);
            mLastNameTextView = itemView.findViewById(R.id.textView_lastname);
            mDateOfBirthTextView = itemView.findViewById(R.id.textView_date_of_bith);
            mAgeTextView = itemView.findViewById(R.id.textView_age);
        }

        public void bind(Person person) {
            mPhotoImageView.setImageBitmap(person.getPhoto());
            mFirstNameTextView.setText(person.getFirstName());
            mLastNameTextView.setText(person.getLastName());
            mDateOfBirthTextView.setText(person.toString());
            mAgeTextView.setText(String.format("%d %d", R.string.age, person.getAge()));
        }
    }

    private class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> {
        private List<Person> mPeople;

        public PeopleAdapter(List<Person> people) {
            mPeople = people;
        }

        @NonNull
        @Override
        public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.item_person, viewGroup, false);
            return new PeopleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PeopleViewHolder peopleViewHolder, int position) {
            Person person = mPeople.get(position);
            peopleViewHolder.bind(person);
        }

        @Override
        public int getItemCount() {
            return mPeople.size();
        }
    }
}
