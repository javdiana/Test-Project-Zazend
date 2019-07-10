package com.di.jav.testprojectzazend.activity.person;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.di.jav.testprojectzazend.activity.base.SingleFragmentActivity;
import com.di.jav.testprojectzazend.model.entity.Person;

public class PersonActivity extends SingleFragmentActivity {
    private static final String EXTRA_PERSON = "com.di.jav.testprojectzazend.person";

    private static Person mPerson;
    @Override
    protected Fragment createFragment() {
        return PersonFragment.newInstance(mPerson);
    }

    public static Intent newIntent(Context packageContext, Person person){
        Intent intent = new Intent(packageContext, PersonActivity.class);
        mPerson = person;
        return intent;
    }
}
