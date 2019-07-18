package com.di.jav.testprojectzazend.activity.personslist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.jav.testprojectzazend.R;
import com.di.jav.testprojectzazend.activity.person.PersonActivity;
import com.di.jav.testprojectzazend.databinding.FragmentPeopleListBinding;
import com.di.jav.testprojectzazend.databinding.ItemPersonBinding;
import com.di.jav.testprojectzazend.model.entity.Person;
import com.di.jav.testprojectzazend.model.repository.PersonRepository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PersonsListFragment extends Fragment {
    public static PersonsListFragment newInstance() {
        return new PersonsListFragment();
    }

    private PersonRepository mPersonRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPersonRepository = new PersonRepository(new ArrayList<Person>());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPersonRepository.clearSubscription();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPeopleListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_people_list, container, false);

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerViewPeople.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewPeople.setAdapter(new PeopleAdapter(mPersonRepository.getPeople()));
        binding.progressBar.setVisibility(View.INVISIBLE);

        //TODO: 7/18/2019 add et, tv and buttons
        return binding.getRoot();
    }

    private class PeopleViewHolder extends RecyclerView.ViewHolder {
        private ItemPersonBinding mBinding;
        private Person mPerson;

        public PeopleViewHolder(ItemPersonBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Person person) {
            Picasso.get().load(person.getPicture().getThumbnail()).into(mBinding.imageViewPhoto);
            mBinding.textViewFirstname.setText(person.getName().getFirstName());
            mBinding.textViewLastname.setText(person.getName().getLastName());
            mBinding.textViewDateOfBith.setText(person.getDateOfBirthday().getDate());
            mBinding.textViewAge.setText("Age: " + person.getDateOfBirthday().getAge());
// TODO: 7/18/2019 add if not data
            mPerson = person;
        }

        public void start(View v) {
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
            ItemPersonBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_person, viewGroup, false);
            return new PeopleViewHolder(binding);
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
