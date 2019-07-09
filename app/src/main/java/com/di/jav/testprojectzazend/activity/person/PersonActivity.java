package com.di.jav.testprojectzazend.activity.person;

import android.support.v4.app.Fragment;

import com.di.jav.testprojectzazend.activity.base.SingleFragmentActivity;

public class PersonActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return PersonFragment.newInstance();
    }
}
