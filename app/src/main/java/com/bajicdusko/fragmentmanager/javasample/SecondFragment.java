package com.bajicdusko.fragmentmanager.javasample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bajicdusko.fragmentmanager.IFragment;
import com.bajicdusko.fragmentmanager.R;
import com.bajicdusko.fragmentmanager.SFMFragment;

/**
 * Created by Dusko Bajic on 24.06.17.
 * GitHub @bajicdusko
 */

public class SecondFragment extends SFMFragment<ExampleFragmentChannel> implements IFragment {
    private static final String FRAGMENT_NAME = "SecondJava";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle();
    }

    @Override
    public String getFragmentName() {
        return FRAGMENT_NAME;
    }

    @Override
    public void setTitle() {
        fragmenChannel.setToolbarTitle(R.string.second_java);
    }

    @Override
    public void dispose() {

    }

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }
}
