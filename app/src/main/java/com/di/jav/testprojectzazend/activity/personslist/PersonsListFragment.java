package com.di.jav.testprojectzazend.activity.personslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.di.jav.testprojectzazend.R;
import com.di.jav.testprojectzazend.activity.person.PersonActivity;
import com.di.jav.testprojectzazend.model.entity.Person;
import com.di.jav.testprojectzazend.model.entity.Result;
import com.di.jav.testprojectzazend.model.service.http.UserGeneratorClient;

import java.util.Arrays;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PersonsListFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = PersonsListFragment.class.getSimpleName();

    private EditText mSeedEditText;
    private EditText mSearchEditText;
    private TextView mCurrentSeedTextView;
    private RecyclerView peopleRecyclerView;

    private Subscription mSubscription;

    private List<Person> mPeople;

    public static PersonsListFragment newInstance() {
        return new PersonsListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPeople(100);
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
        peopleRecyclerView = view.findViewById(R.id.recyclerView_people);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSeedEditText = view.findViewById(R.id.editText_seed);
        Button applyButton = view.findViewById(R.id.button_apply);
        applyButton.setOnClickListener(this);

        mCurrentSeedTextView = view.findViewById(R.id.textView_current_seed);
        mSearchEditText = view.findViewById(R.id.editText_search);
        Button clearButton = view.findViewById(R.id.button_clear);
        clearButton.setOnClickListener(this);

//        initAdapter(mPeople);

        return view;
    }

    private void initAdapter(List<Person> people) {
        try {
            peopleRecyclerView.setAdapter(new PeopleAdapter(people));
        } catch (NullPointerException npe) {
            Log.e(TAG, npe.getMessage());
            Toast.makeText(getActivity(), R.string.could_not_load_data, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_apply:
                // TODO: 7/8/2019 change seed
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.button_clear:
                mSearchEditText.setText("");
                break;
        }
    }

    private void getPeople(int numberOfPersons) {
        mSubscription = UserGeneratorClient.getInstance()
                .getPeople(numberOfPersons)
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
            // mPhotoImageView.setImageBitmap(person.getPicture().getThumbnail());
            mFirstNameTextView.setText(person.getName().getFirstName());
            mLastNameTextView.setText(person.getName().getLastName());
            mDateOfBirthTextView.setText(person.getDateOfBirth().getDate());
            mAgeTextView.setText(R.string.age + " " + person.getDateOfBirth().getAge());
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
