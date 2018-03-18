package com.example.prem.runtimepermission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Marsh mallow permission request class
 */
public final class MarshMallowPermission {
    /**
     * External storage permission request code
     */
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;

    /**
     * Camera permission request code
     */
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    private static final int INTERNET_PERMISSION_REQUEST_CODE = 4;

    /**
     * Activity context
     */
    Activity activity;

    /**
     * Public constructor
     * @param activity for context
     */
    public MarshMallowPermission(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return true if the permission is granted, false otherwise
     */
    public boolean checkPermissionForExternalStorage(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @return true if the permission is granted, false otherwise
     */
    public boolean checkPermissionForCamera(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        return result == PackageManager.PERMISSION_GRANTED;
    }
    public boolean checkPermissionForInternet() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET);

        return result == PackageManager.PERMISSION_GRANTED;
    }
    /**
     * Request permission for external storage
     */
    public void requestPermissionForExternalStorage(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(activity, "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * Request permission for Camera access
     */
    public void requestPermissionForCamera(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
            Toast.makeText(activity, "Camera permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }


    public void requestPermissionForInternet() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.INTERNET)){
            Toast.makeText(activity, "Camera permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION_REQUEST_CODE);
        }
    }
}