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
import android.widget.TextView;

public class PeopleListFragment extends Fragment implements View.OnClickListener {
    private EditText seedEditText;
    private EditText searchEditText;
    private TextView currentSeedTextView;

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
        // TODO: 7/8/2019 setAdapter peopleRecyclerView.setAdapter();

        seedEditText = view.findViewById(R.id.editText_seed);
        Button applyButton = view.findViewById(R.id.button_apply);
        applyButton.setOnClickListener(this);

        currentSeedTextView = view.findViewById(R.id.textView_current_seed);
        searchEditText = view.findViewById(R.id.editText_search);
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
                searchEditText.setText("");
                break;

        }
    }
}
