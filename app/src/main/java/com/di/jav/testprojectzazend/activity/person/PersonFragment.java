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

public class PersonFragment extends Fragment {
    private ImageView mLargeImageView;
    private TextView mNameTextView;
    private TextView mBirthTextView;
    private TextView mGenderTextView;
    private TextView mLocationTextView;
    private TextView mEmailTextView;

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        mLargeImageView = view.findViewById(R.id.imageView_large_image);
        mNameTextView = view.findViewById(R.id.textView_name);
        mBirthTextView = view.findViewById(R.id.textView_birth);
        mGenderTextView = view.findViewById(R.id.textView_gender);
        mLocationTextView = view.findViewById(R.id.textView_location);
        mEmailTextView = view.findViewById(R.id.textView_email);

        setMinSizeForImage(view);

        return view;
    }

    private void setMinSizeForImage(View view) {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.heightPixels;
        int height = metrics.widthPixels;

        mLargeImageView.setMinimumHeight(height - height / 20);
        mLargeImageView.setMinimumWidth(width - 8);
        int i = 0;
    }
}
