package com.example.shrey.docs;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



public class ThirdActivity extends AppCompatActivity {
    int pos;
    String uid,type,n;
     ArrayList<String> array_list;
     TextView shop_n,shop_a,shop_c ;
     EditText cus_name,cus_mobile;
    DatabaseReference ref;
    FirebaseDatabase database;
    Random r ;
    private AdView mAdView;
    private static final String TAG = "ThirdActivity";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //ads

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Center Booking");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        cus_name = findViewById(R.id.cus_name);
        cus_mobile =findViewById(R.id.cus_mobile);
        shop_n = findViewById(R.id.s_na);
        shop_a = findViewById(R.id.s_address);
        shop_c = findViewById(R.id.contact_s);

        r= new Random();

        type = getIntent().getStringExtra("type");
        pos = getIntent().getIntExtra("position",0);
        array_list = getIntent().getStringArrayListExtra("uid_list");
        uid = array_list.get(pos);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        ref.child(type).child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String n = (String) dataSnapshot.child("shop_name").getValue();
                shop_n.setText(n);

                String b =(String) dataSnapshot.child("shop_pincode").getValue();
                String a = (String) dataSnapshot.child("shop_address").getValue();
                String c = a+"\n"+"\t"+b;
                shop_a.setText(c);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String con = (String) dataSnapshot.child("mobile").getValue();
                String email = (String) dataSnapshot.child("email").getValue();

               String f = con + "\n" +email ;
                shop_c.setText(f);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

public void book(View view){
    String name1 = cus_name.getText().toString();
    String mobile1 = cus_mobile.getText().toString();

    if(name1.equals("")){
        Toast.makeText(ThirdActivity.this,"Please Enter the Nmae",Toast.LENGTH_LONG).show();
    }
    else if(mobile1.equals("")){
        Toast.makeText(ThirdActivity.this,"Please Enter the Mobile No",Toast.LENGTH_LONG).show();
    }
    else {
             String time = DateFormat.getDateTimeInstance().format(new Date());
          ref.child("Users").child(uid).child("booking").child(mobile1).child("Name").setValue(name1);
          ref.child("Users").child(uid).child("booking").child(mobile1).child("Mobile").setValue(mobile1);
          ref.child("Users").child(uid).child("booking").child(mobile1).child("Time").setValue(time);
          Toast.makeText(ThirdActivity.this,"Booking Confirm...,You will get Message",Toast.LENGTH_LONG).show();
          cus_name.setText("");
          cus_mobile.setText("");
    }
}

}
