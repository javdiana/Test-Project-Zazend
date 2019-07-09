package com.di.jav.testprojectzazend.activity.personslist;

import android.support.v4.app.Fragment;

import com.di.jav.testprojectzazend.activity.base.SingleFragmentActivity;

public class PersonsListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PersonsListFragment.newInstance();
    }
}
