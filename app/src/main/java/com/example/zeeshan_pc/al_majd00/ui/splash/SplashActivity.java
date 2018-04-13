package com.example.zeeshan_pc.al_majd00.ui.splash;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.dashboard.activity.DashboardActivity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SplashActivity extends AppCompatActivity {

    public static String[] permissionsRequired = new String[]{
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    ImageView imageView;
    private Boolean showSettingFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.splash_icon);

        //Check Runtime Permissions
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions(1);
        } else {
            startNextActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Method", "OnRequestedPermissionsResult call");

        switch (requestCode) {
            case 1: {
                checkNeverAskAgainCheck(permissions);
            }
        }
    }

    private void checkNeverAskAgainCheck(String[] permissions) {

        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                //denied
                Log.e("denied", permission);
                showSettingFlag = true;
            } else {
                if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                    //allowed
                    Log.e("allowed", permission);
                } else {
                    //set to never ask again
                    Log.e("set to never ask again", permission);
                    //do something here.
                    showSettingFlag = true;
                }
            }
        }

        Log.e("show Setting flag", showSettingFlag.toString());
        if (showSettingFlag) {
            startSetting();
        } else {
            startNextActivity();
        }

    }

    private void startSetting() {

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Require Permissions")
                .setMessage("Unable to open.\nGo to Settings > Permissions, then allow all the Permissions and try again")
                .setCancelable(false)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, 1);
                    }
                })
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Method", "OnRequestedPermissionsResult call");

        switch (requestCode) {
            case 1: {
                String[] ungrantedPermissions = requiredPermissionsStillNeeded();
                Log.e("ungranted permission", String.valueOf(ungrantedPermissions.length));

                if (ungrantedPermissions.length == 0) {
                    startNextActivity();
                } else {
                    startSetting();
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions(int requestCode) {
        String[] ungrantedPermissions = requiredPermissionsStillNeeded();
        if (ungrantedPermissions.length == 0) {
            startNextActivity();
        } else {
            requestPermissions(ungrantedPermissions, requestCode);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String[] requiredPermissionsStillNeeded() {

        Set<String> permissions = new HashSet<String>();
        for (String permission : getRequiredPermissions()) {
            permissions.add(permission);
        }
        for (Iterator<String> i = permissions.iterator(); i.hasNext(); ) {
            String permission = i.next();
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {

                i.remove();
            } else {

            }
        }
        return permissions.toArray(new String[permissions.size()]);
    }


    public String[] getRequiredPermissions() {
        return permissionsRequired;
    }

    void startNextActivity() {

        ScaleAnimation fade_in = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(1300);     // animation duration in milliseconds
        fade_in.setFillAfter(true);    // If fillAfter is true, the transformation that this animation performed will persist when it is finished.
        imageView.startAnimation(fade_in);

        fade_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
