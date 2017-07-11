package com.bajicdusko.fragmentmanager.javasample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.bajicdusko.fragmentmanager.R;
import com.bajicdusko.fragmentmanager.SFMActivity;

/**
 * Created by Dusko Bajic on 24.06.17.
 * GitHub @bajicdusko
 */

public class HomeActivity extends SFMActivity implements ExampleFragmentChannel {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(toolbar);
        simpleFragmentManager.enableLogs(true);
        simpleFragmentManager.addFragment(FirstFragment.newInstance());
    }

    @Override
    public void setToolbarTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
    }

    @Override
    public void openSecondFragment() {
        simpleFragmentManager.replaceFragment(SecondFragment.newInstance());
    }

    @Override
    protected int getFrameLayoutContainerId() {
        return R.id.activity_home_fragment_container;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        simpleFragmentManager.onActivityResult(requestCode, resultCode, data);
    }
}
