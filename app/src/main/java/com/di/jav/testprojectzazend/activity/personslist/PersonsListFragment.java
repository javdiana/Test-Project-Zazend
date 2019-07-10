package com.di.jav.testprojectzazend.activity.personslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.di.jav.testprojectzazend.R;
import com.di.jav.testprojectzazend.activity.person.PersonActivity;
import com.di.jav.testprojectzazend.model.entity.Person;
import com.di.jav.testprojectzazend.model.entity.Result;
import com.di.jav.testprojectzazend.model.service.http.UserGeneratorClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PersonsListFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = PersonsListFragment.class.getSimpleName();
    private static final int NUMBER_OF_PERSONS = 100;

    private EditText mSeedEditText;
    private EditText mSearchEditText;
    private TextView mCurrentSeedTextView;
    private RecyclerView mPeopleRecyclerView;

    private Subscription mSubscription;

    private List<Person> mPeople;
    private String mSeed;

    private ProgressBar mProgressBar;

    private static final String EXTRA_PERSON = "com.di.jav.testprojectzazend.person";

    public static PersonsListFragment newInstance() {
        return new PersonsListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSeed = "";
        getPeople(NUMBER_OF_PERSONS, mSeed);
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people_list, container, false);
        mPeopleRecyclerView = view.findViewById(R.id.recyclerView_people);
        mPeopleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSeedEditText = view.findViewById(R.id.editText_seed);
        Button applyButton = view.findViewById(R.id.button_apply);
        applyButton.setOnClickListener(this);

        mCurrentSeedTextView = view.findViewById(R.id.textView_current_seed);

        mSearchEditText = view.findViewById(R.id.editText_search);
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (count >= 3) {
                    findByFirstOrLastName(mSearchEditText.getText().toString());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button clearButton = view.findViewById(R.id.button_clear);
        clearButton.setOnClickListener(this);

        mProgressBar = view.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);
        return view;
    }

    private void initAdapter(List<Person> people) {
        try {
            mPeopleRecyclerView.setAdapter(new PeopleAdapter(people));
        } catch (NullPointerException npe) {
            Log.e(TAG, npe.getMessage());
            Toast.makeText(getActivity(), R.string.could_not_load_data, Toast.LENGTH_SHORT).show();
        }
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_apply:
                mSeed = mSeedEditText.getText().toString();
                mCurrentSeedTextView.setText(mSeed);
                getPeople(NUMBER_OF_PERSONS, mSeed);
                mCurrentSeedTextView.setText("Current seed: " + mSeed);
                break;
            case R.id.button_clear:
                mSearchEditText.setText("");
                getPeople(NUMBER_OF_PERSONS, "");
                break;
        }
    }

    private void findByFirstOrLastName(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : mPeople) {
            if (person.getName().getFirstName().toLowerCase()
                    .contains(key.toLowerCase()) ||
                    person.getName().getLastName().toLowerCase()
                            .contains(key.toLowerCase())
            ) {
                result.add(person);
            }
        }
        initAdapter(result);
    }

    private void getPeople(int numberOfPersons, String seed) {
        mSubscription = UserGeneratorClient.getInstance()
                .getPeople(numberOfPersons, seed)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onNext(Result result) {
                        Log.d(TAG, "In onNext()");
                        mPeople = Arrays.asList(result.getPerson());
                        initAdapter(mPeople);
                    }
                });

    }


    private class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mPhotoImageView;
        private TextView mFirstNameTextView;
        private TextView mLastNameTextView;
        private TextView mDateOfBirthTextView;
        private TextView mAgeTextView;

        private Person mPerson;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.imageView_photo);
            mFirstNameTextView = itemView.findViewById(R.id.textView_firstname);
            mLastNameTextView = itemView.findViewById(R.id.textView_lastname);
            mDateOfBirthTextView = itemView.findViewById(R.id.textView_date_of_bith);
            mAgeTextView = itemView.findViewById(R.id.textView_age);
            itemView.setOnClickListener(this);
        }

        public void bind(Person person) {
            Picasso.get().load(person.getPicture().getThumbnail()).into(mPhotoImageView);
            mFirstNameTextView.setText(person.getName().getFirstName());
            mLastNameTextView.setText(person.getName().getLastName());
            mDateOfBirthTextView.setText(person.getDateOfBirthday().getDate());
            mAgeTextView.setText("Age: " + person.getDateOfBirthday().getAge());

            mPerson = person;
        }

        @Override
        public void onClick(View v) {
            Intent intent = PersonActivity.newIntent(getActivity(), mPerson);
            startActivity(intent);
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
