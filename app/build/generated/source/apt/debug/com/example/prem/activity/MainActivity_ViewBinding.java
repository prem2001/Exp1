// Generated code from Butter Knife. Do not modify!
package com.example.prem.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.prem.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131230812;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.passwordET = Utils.findRequiredViewAsType(source, R.id.password_text_view, "field 'passwordET'", EditText.class);
    target.mobileET = Utils.findRequiredViewAsType(source, R.id.mobile_text_view, "field 'mobileET'", EditText.class);
    view = Utils.findRequiredView(source, R.id.login_button, "method 'onButton'");
    view2131230812 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButton();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.passwordET = null;
    target.mobileET = null;

    view2131230812.setOnClickListener(null);
    view2131230812 = null;
  }
}
