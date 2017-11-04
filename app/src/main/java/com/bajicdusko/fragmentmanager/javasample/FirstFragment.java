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

public class FirstFragment extends SFMFragment<ExampleFragmentChannel> implements IFragment {
    private static final String FRAGMENT_NAME = "FirstJava";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.fragment_first_bt_open_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              fragmentChannel.openSecondFragment();
            }
        });
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
      fragmentChannel.setToolbarTitle(R.string.first_java);
    }

    @Override
    public void dispose() {
        // TODO: 24.06.17 Dispose something
    }

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }
}
