package com.writer.aspiring.unittestingdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout signIn;
    EditText userName,password;
    TextView result;


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
}
