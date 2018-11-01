package com.example.shrey.docs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class RegisterActivity extends AppCompatActivity {


    EditText name;
    EditText email;
    EditText password;
    EditText mobile;
    String u_pass,u_mobile;
    String u_email,u_name;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Admin Register");


        name = findViewById(R.id.name);
         email = findViewById(R.id.email);
         password = findViewById(R.id.password);
         mobile = findViewById(R.id.mobile);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user_1 = mAuth.getCurrentUser();

        if(user_1 != null){
            Intent intent = new Intent(RegisterActivity.this,AdminEnterActivity.class);
            startActivity(intent);
        }

            }

  public void register(View view) {

//        String user_name = name.getText().toString();

      //      String user_mobile = mobile.getText().toString();



      u_pass = password.getText().toString();
      u_email = email.getText().toString();
      u_name = name.getText().toString();
      u_mobile = mobile.getText().toString();

      if(u_name.equals("")){
          Toast.makeText(RegisterActivity.this,"Please Enter the name",Toast.LENGTH_LONG).show();
      }
     else if (u_email.equals("")) {
          Toast.makeText(RegisterActivity.this,"Enter the Email Address",Toast.LENGTH_SHORT).show();

      }
      else if (u_pass.equals("")) {
          Toast.makeText(RegisterActivity.this,"Enter the password",Toast.LENGTH_SHORT).show();

      }
      else if(u_mobile.equals("")){
          Toast.makeText(RegisterActivity.this,"Enter the Mobile No",Toast.LENGTH_SHORT).show();
      }
      else {


          mAuth = FirebaseAuth.getInstance();

          mAuth.createUserWithEmailAndPassword(u_email, u_pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                      Toast.makeText(RegisterActivity.this, "User is Created Successful", Toast.LENGTH_LONG).show();
                      FirebaseUser user = mAuth.getCurrentUser();
                      String uid = user.getUid();
                      AddUser u = new AddUser(u_name,u_email,u_mobile);
                      DatabaseReference ref =FirebaseDatabase.getInstance().getReference("Users");
                      ref.child(uid).setValue(u);
                      Toast.makeText(RegisterActivity.this,"Add Successfully",Toast.LENGTH_LONG).show();
                      updateUI(user);
                  } else {
                      Toast.makeText(RegisterActivity.this, "Authentication Faild", Toast.LENGTH_LONG).show();
                  }
              }

              private void updateUI(FirebaseUser user) {
                  Intent intent = new Intent(RegisterActivity.this,AdminEnterActivity.class);
                  startActivity(intent);
              }
          });

      }
  }

  }





