package com.example.shrey.docs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

public class AdminActivity extends AppCompatActivity {

    FirebaseAuth mAuth ;
    FirebaseUser user;
    EditText user_name ;
    EditText password ;
    String name,pass;
    String emai;
    EditText reset_mail;
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);



        ActionBar bar = getSupportActionBar();
        bar.setTitle("Admin Page");

        mAuth= FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(user != null){
            updateUI(user);
        }


    }

    public void register(View view){
        Intent intent = new Intent(AdminActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    public void admin_login(View view){
        user = mAuth.getCurrentUser();
        user_name = findViewById(R.id.user_name);
        password = findViewById(R.id.user_password);

        name = user_name.getText().toString();
        pass = password.getText().toString();


        if(user != null){
            updateUI(user);
        }
        else if(name.equals("")){
            Toast.makeText(AdminActivity.this,"Please Enter the Email Id",Toast.LENGTH_LONG).show();
        }
        else if (pass.equals("")){
            Toast.makeText(AdminActivity.this,"Please Enter the Password",Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(name,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AdminActivity.this,"Login Succsseful",Toast.LENGTH_LONG).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    }
                    else{
                        String TAG = "IMP";
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(AdminActivity.this, "Authentication Failed",Toast.LENGTH_LONG).show();
                        updateUI(null);
                    }
                }
            });
        }

    }

    public void forgot_password(View view){
        Button  b  = findViewById(R.id.send);

        e = findViewById(R.id.password_reset);
        e.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);


        emai = e.getText().toString().trim();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emai =e.getText().toString().trim();
                if (emai.equals("")){
                    Toast.makeText(AdminActivity.this,"Please Enter Reg Email.",Toast.LENGTH_LONG).show();
                }
                else{

                    mAuth.sendPasswordResetEmail(emai).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                            Toast.makeText(AdminActivity.this,"Reset Password is Send Please check email",Toast.LENGTH_LONG).show();
                        }
                        else
                                Toast.makeText(AdminActivity.this,"Please Enter register Email",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            Intent intent =new Intent(this,AdminEnterActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(AdminActivity.this,"Please Login Again",Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(AdminActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
