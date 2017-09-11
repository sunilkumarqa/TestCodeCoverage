package com.writer.aspiring.unittestingdemo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    LinearLayout signIn;
    EditText userName,password;
    TextView result;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       signIn = (LinearLayout)findViewById(R.id.layout_signin);
        userName = (EditText) findViewById(R.id.UserName);
        password = (EditText) findViewById(R.id.password);
        result = (TextView) findViewById(R.id.result_signin);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStoragePermissions(MainActivity.this);
                String useNam = userName.getText().toString();
                String passWrd = password.getText().toString();
                UserCredentials userCredentials = new UserCredentials();
                if (userCredentials.validCredentials(useNam,passWrd))
                {
                    result.setText("Sign In Successful");
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                }
                else
                {
                    result.setText("Sign In Unsuccessful");
                    Toast.makeText(getApplicationContext(),"Please enter the valid Credentials !!",Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart called", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {

        super.onResume();
        Toast.makeText(getApplicationContext(), "onResumed called", Toast.LENGTH_LONG).show();
        if(BuildConfig.DEBUG)
        {
            String TAG = "jacoco";
            try {
                String covPath = Environment.getExternalStorageDirectory().getPath() + "/coverage.exec";
                File coverageFile = new File(covPath);
                Class<?> emmaRTClass = Class.forName("com.vladium.emma.rt.RT");
                Method dumpCoverageMethod = emmaRTClass.getMethod("dumpCoverageData",coverageFile.getClass(), boolean.class, boolean.class);
                dumpCoverageMethod.invoke(null, coverageFile, true, false);
            } catch (Exception e) {
            }

        }

    }

    @Override
    protected void onPause() {

        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause called", Toast.LENGTH_LONG).show();


    }


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED ||
                readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
