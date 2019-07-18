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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.di.jav.testprojectzazend.R;
import com.di.jav.testprojectzazend.activity.person.PersonActivity;
import com.di.jav.testprojectzazend.model.entity.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public class PersonsListFragment extends Fragment implements View.OnClickListener {
    private static final String EXTRA_PERSON = "com.di.jav.testprojectzazend.person";

    private EditText mSeedEditText;
    private EditText mSearchEditText;
    private TextView mCurrentSeedTextView;
    private RecyclerView mPeopleRecyclerView;
    private ProgressBar mProgressBar;

    private Subscription mSubscription;
    private IPersonsList.IPersonsListPresenter mPresenter;

    public static PersonsListFragment newInstance() {
        return new PersonsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new PersonsListPresenterImp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 3) {
                    initAdapter(new ArrayList<Person>(), mSeedEditText.getText().toString(), mSearchEditText.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button clearButton = view.findViewById(R.id.button_clear);
        clearButton.setOnClickListener(this);

        mProgressBar = view.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        initAdapter(new ArrayList<Person>(), mSeedEditText.getText().toString(), mSearchEditText.getText().toString());
        return view;
    }

    private void initAdapter(List<Person> people, String seed, String name) {
        mPresenter.getData(people, seed, name);//null
        mPeopleRecyclerView.setAdapter(new PeopleAdapter(people));

        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        mProgressBar.setVisibility(View.VISIBLE);
        initAdapter(new ArrayList<Person>(), mSeedEditText.getText().toString(), mSearchEditText.getText().toString());
        switch (v.getId()) {
            case R.id.button_apply:
                mCurrentSeedTextView.setText("Current seed: " + mCurrentSeedTextView.getText().toString());
                break;
            case R.id.button_clear:
                mSearchEditText.setText("");
                break;
        }
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
            //mPersonDownloader.queuePerson(peopleViewHolder, UserGeneratorClient.url);
        }

        @Override
        public int getItemCount() {
            return mPeople.size();
        }
    }
}
