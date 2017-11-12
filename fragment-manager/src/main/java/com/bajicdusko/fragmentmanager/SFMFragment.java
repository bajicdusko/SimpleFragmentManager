package com.bajicdusko.fragmentmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Dusko Bajic on 24.06.17.
 * GitHub @bajicdusko
 */

public abstract class SFMFragment<F extends FragmentChannel> extends Fragment implements IFragment {

  protected F fragmentChannel;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context != null && context instanceof FragmentChannel) {
      fragmentChannel = (F) context;
    }
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getParentFragment() != null && getParentFragment() instanceof FragmentChannel) {
      fragmentChannel = (F) getParentFragment();
    }
  }
}
