// Generated code from Butter Knife. Do not modify!
package com.example.prem.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.prem.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SecondActivity_ViewBinding implements Unbinder {
  private SecondActivity target;

  private View view2131230860;

  private View view2131230758;

  @UiThread
  public SecondActivity_ViewBinding(SecondActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SecondActivity_ViewBinding(final SecondActivity target, View source) {
    this.target = target;

    View view;
    target.messageEditText = Utils.findRequiredViewAsType(source, R.id.message_edit_text, "field 'messageEditText'", EditText.class);
    target.chat_recycler_view = Utils.findRequiredViewAsType(source, R.id.chat_recycler_view, "field 'chat_recycler_view'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.send_message_image_view, "method 'onSendMessage'");
    view2131230860 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSendMessage();
      }
    });
    view = Utils.findRequiredView(source, R.id.camera_image, "method 'startCamera'");
    view2131230758 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startCamera();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SecondActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.messageEditText = null;
    target.chat_recycler_view = null;

    view2131230860.setOnClickListener(null);
    view2131230860 = null;
    view2131230758.setOnClickListener(null);
    view2131230758 = null;
  }
}
