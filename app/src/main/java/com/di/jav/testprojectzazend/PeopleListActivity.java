package com.di.jav.testprojectzazend;

import android.support.v4.app.Fragment;

public class PeopleListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PeopleListFragment.newInstance();
    }
}
