package com.example.shrey.docs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdminEnterActivity extends AppCompatActivity {

    String ad_name , ad_mobile, ad_email;
    TextView a_n;
    TextView a_m;
    TextView a_e;
    private static final String TAG = "MainActivity";
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_enter);


        ActionBar bar = getSupportActionBar();
        bar.setTitle("Admin Menu");

        //ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        a_n = findViewById(R.id.admin_name);
        a_e = findViewById(R.id.admin_email);
        a_m = findViewById(R.id.admin_mobile);




        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("Users");


        FirebaseUser user = mAuth.getCurrentUser();

        if(user == null){
            Toast.makeText(AdminEnterActivity.this,"Please Login...!!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AdminEnterActivity.this,MainActivity.class);
            startActivity(intent);
        }

        String uid = user.getUid();
        String name = user.getEmail();
        a_e.setText(name);

       ref.child(uid).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            ad_name =(String) dataSnapshot.child("name").getValue();
           ad_mobile= (String) dataSnapshot.child("mobile").getValue();

           a_n.setText(ad_name);
           a_m.setText(ad_mobile);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }

    public  void add_shop(View view){

        Intent intent = new Intent(AdminEnterActivity.this,AddActivity.class);
        startActivity(intent);
    }
    public void settings(View view){
        Intent intent = new Intent(AdminEnterActivity.this,SettingActivity.class);
        startActivity(intent);
    }
    public  void see_bookings(View view){
        Intent intent = new Intent(AdminEnterActivity.this,BookingActivity.class);
        startActivity(intent);
    }

    public void signout(View view){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        Intent intent = new Intent(AdminEnterActivity.this,AdminActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(AdminEnterActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
