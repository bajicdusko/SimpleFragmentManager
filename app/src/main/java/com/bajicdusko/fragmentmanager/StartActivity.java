package com.bajicdusko.fragmentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by Dusko Bajic on 24.06.17.
 * GitHub @bajicdusko
 */

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.activity_start_bt_kotlin).setOnClickListener(this);
        findViewById(R.id.activity_start_bt_java).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_start_bt_kotlin) {
            Intent intent = new Intent(this, com.bajicdusko.fragmentmanager.kotlinsample.HomeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, com.bajicdusko.fragmentmanager.javasample.HomeActivity.class);
            startActivity(intent);
        }
    }
}
