package com.example.shrey.docs;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference ref;
    private static final String TAG = "MainActivity";
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Admin Setting");
        //ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user  = mAuth.getCurrentUser();

        if (user== null){
            Toast.makeText(SettingActivity.this,"Please Login.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SettingActivity.this,MainActivity.class);
            startActivity(intent);
        }

        String user_n = user.getDisplayName();
        TextView display_name = findViewById(R.id.setting_name);

        String d_n = display_name.getText().toString();

        display_name.setText("\t\t"+d_n);

    }
    public void update_shop(View view){
        Intent intent = new Intent(SettingActivity.this,AddActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(SettingActivity.this,AdminEnterActivity.class);
        startActivity(intent);
    }

    public void update_name(View view){
        EditText name  = findViewById(R.id.c_n);
        String n = name.getText().toString();

        if (n.equals("")){
            Toast.makeText(SettingActivity.this,"Please Enter the name",Toast.LENGTH_LONG).show();
        }
        else{
            ref = database.getReference().child("Users");
            user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            ref.child(uid).child("name").setValue(n);
            Toast.makeText(SettingActivity.this,"Changed Nmae Successful",Toast.LENGTH_LONG).show();

        }

    }
    public  void update_mob(View view){
        EditText pass = findViewById(R.id.c_m);
        String p = pass.getText().toString();

        if(p.equals("")){
            Toast.makeText(SettingActivity.this,"Please Enter the Mobile",Toast.LENGTH_LONG).show();
        }
        else {
            ref = database.getReference().child("Users");
            user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            ref.child(uid).child("mobile").setValue(p);
            Toast.makeText(SettingActivity.this,"Changed Mobile Successful",Toast.LENGTH_LONG).show();

        }
    }
}


