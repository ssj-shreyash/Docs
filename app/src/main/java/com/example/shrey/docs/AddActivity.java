package com.example.shrey.docs;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Add Center");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user == null){
            Toast.makeText(AddActivity.this,"Please Login Again",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
    public void add_addhar(View view){

        Intent intent = new Intent(AddActivity.this,AddcenterActivity.class);
        intent.putExtra("key","Addhar");
        startActivity(intent);

    }
    public void add_pan(View view){

        Intent intent = new Intent(AddActivity.this,AddcenterActivity.class);
        intent.putExtra("key","Pan");
        startActivity(intent);

    }
    public void add_income(View view){

        Intent intent = new Intent(AddActivity.this,AddcenterActivity.class);
        intent.putExtra("key","Income");
        startActivity(intent);

    }
    public void add_cast(View view){

        Intent intent = new Intent(AddActivity.this,AddcenterActivity.class);
        intent.putExtra("key","Cast");
        startActivity(intent);

    }
    public void add_national(View view){

        Intent intent = new Intent(AddActivity.this,AddcenterActivity.class);
        intent.putExtra("key","National");
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(AddActivity.this,AdminEnterActivity.class);
        startActivity(intent);
    }
}
