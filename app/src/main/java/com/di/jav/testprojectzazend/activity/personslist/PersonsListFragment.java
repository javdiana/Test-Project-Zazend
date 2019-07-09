package com.di.jav.testprojectzazend.activity.personslist;

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

import com.di.jav.testprojectzazend.R;
import com.di.jav.testprojectzazend.model.entity.Person;
import com.di.jav.testprojectzazend.model.entity.Result;
import com.di.jav.testprojectzazend.model.service.http.UserGeneratorClient;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class PersonsListFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = PersonsListFragment.class.getSimpleName();

    private EditText mSeedEditText;
    private EditText mSearchEditText;
    private TextView mCurrentSeedTextView;

    private Result mResult;
    private Subscription mSubscription;

    public static PersonsListFragment newInstance() {
        return new PersonsListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResult = new Result();

        getPeople(10);
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
        RecyclerView peopleRecyclerView = view.findViewById(R.id.recyclerView_people);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        peopleRecyclerView.setAdapter(new PeopleAdapter(mResult.getPerson() != null ? mResult.getPerson() : new ArrayList<Person>()));

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
                        mResult = result;
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
            mFirstNameTextView.setText(person.getName().getFirst());
            mLastNameTextView.setText(person.getName().getLast());
            mDateOfBirthTextView.setText(person.getDateOfBirth().getDate());
            mAgeTextView.setText(String.format("%d %d", R.string.age, person.getDateOfBirth().getAge()));
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
