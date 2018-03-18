package com.example.prem.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prem.R;

/**
 * Created by prem on 12/3/18.
 */

public class DialogUtil {
    //private static Dialog dialog;
    private static AlertDialog mDialog;
    private static ProgressDialog mProgress;

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showNetworkDialog(Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.str_alert));
        builder.setMessage(mContext.getString(R.string.str_no_connection));
        builder.setCancelable(false);
        builder.setPositiveButton(mContext.getString(R.string.str_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();
    }

    public static void showSnackBar(View view) {
        String msg = "No Internet Connection";
        int color = Color.RED;
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        textView.setTextSize(18);
        snackbar.show();

    }

    public static void showProgressDialog(Context context, String msg) {

        mProgress = new ProgressDialog(context);
        mProgress.setMessage(msg);
        mProgress.setCancelable(false);
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
    }

    public static void hideProgressDialog() {
        if (mProgress != null) {
            if (mProgress.isShowing()) {
                mProgress.dismiss();
            }
        }
    }
}
