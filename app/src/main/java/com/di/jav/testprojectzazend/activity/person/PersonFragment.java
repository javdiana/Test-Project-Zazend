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

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mPerson = getActivity().getIntent().getParcelableExtra(Person.class.getCanonicalName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        mLargeImageView = view.findViewById(R.id.imageView_large_image);
        setMinSizeForImage();
        Picasso.get().load(mPerson.getPicture().getThumbnail()).into(mLargeImageView);

        mNameTextView = view.findViewById(R.id.textView_name);
        mNameTextView.setText(new StringBuilder().append(mPerson.getName().getFirstName()).append(" ").append(mPerson.getName().getFirstName()).toString());

        mBirthTextView = view.findViewById(R.id.textView_birth);
        mBirthTextView.setText(mPerson.getDateOfBirthday().getDate());

        mGenderTextView = view.findViewById(R.id.textView_gender);
        mGenderTextView.setText(mPerson.getGender());

        mLocationTextView = view.findViewById(R.id.textView_location);
        mLocationTextView.setText(new StringBuilder().append(mPerson.getLocation().getCity()).append(" ").append(mPerson.getLocation().getStreet()).toString());

        mEmailTextView = view.findViewById(R.id.textView_email);
        mEmailTextView.setText(mPerson.getEmail());

        return view;
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
