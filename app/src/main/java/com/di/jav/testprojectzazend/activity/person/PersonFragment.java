package com.di.jav.testprojectzazend.activity.person;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.di.jav.testprojectzazend.R;
import com.di.jav.testprojectzazend.model.entity.Person;
import com.squareup.picasso.Picasso;

public class PersonFragment extends Fragment {
    private ImageView mLargeImageView;
    private TextView mNameTextView;
    private TextView mBirthTextView;
    private TextView mGenderTextView;
    private TextView mLocationTextView;
    private TextView mEmailTextView;

    private Person mPerson;

    private static final String EXTRA_PERSON = "com.di.jav.testprojectzazend.person";

    public static PersonFragment newInstance(Person person) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_PERSON, person);

        PersonFragment fragment = new PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mPerson = savedInstanceState.getParcelable(EXTRA_PERSON);
        }

        mPerson = getArguments().getParcelable(EXTRA_PERSON);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        mLargeImageView = view.findViewById(R.id.imageView_large_image);
        setMinSizeForImage();
        Picasso.get().load(mPerson.getPicture().getLarge()).into(mLargeImageView);

        mNameTextView = view.findViewById(R.id.textView_name);
        mNameTextView.setText(new StringBuilder().append(mPerson.getName().getFirstName()).append(" ").append(mPerson.getName().getLastName()).toString());

        mBirthTextView = view.findViewById(R.id.textView_birth);
        mBirthTextView.setText("Bith: " + mPerson.getDateOfBirthday().getDate());

        mGenderTextView = view.findViewById(R.id.textView_gender);
        mGenderTextView.setText("Gender: " + mPerson.getGender());

        mLocationTextView = view.findViewById(R.id.textView_location);
        mLocationTextView.setText("Location: " + mPerson.getLocation().getCity() + " " + mPerson.getLocation().getStreet());

        mEmailTextView = view.findViewById(R.id.textView_email);
        mEmailTextView.setText("Email: " + mPerson.getEmail());

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_PERSON, mPerson);
    }

    private void setMinSizeForImage() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.heightPixels;
        int height = metrics.widthPixels;

        mLargeImageView.setMinimumHeight(height - height / 20);
        mLargeImageView.setMinimumWidth(width - 8);
    }
}
