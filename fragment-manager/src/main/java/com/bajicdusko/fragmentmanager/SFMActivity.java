package com.bajicdusko.fragmentmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dusko Bajic on 24.06.17.
 * GitHub @bajicdusko
 */

public abstract class SFMActivity extends AppCompatActivity {

  protected abstract int getFrameLayoutContainerId();

  public SimpleFragmentManager simpleFragmentManager;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    simpleFragmentManager = new SimpleFragmentManager(getSupportFragmentManager(), getFrameLayoutContainerId());
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    simpleFragmentManager.onSaveInstanceState(outState);
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    simpleFragmentManager.onRestoreInstanceState(savedInstanceState);
  }

  protected void onSFMBackPressed() {
    if (!simpleFragmentManager.onBackPressed()) {
      finish();
    }
  }

  @Override public void onBackPressed() {
    onSFMBackPressed();
  }
}
