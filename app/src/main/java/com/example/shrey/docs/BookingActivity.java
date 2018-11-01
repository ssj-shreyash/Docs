package com.example.shrey.docs;

import android.app.Notification;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class BookingActivity extends AppCompatActivity {

    ListView booking;
    FirebaseDatabase database;
    private static final String TAG = "MainActivity";
    private AdView mAdView;
    DatabaseReference ref ;
    ArrayList<String> arrayList;
    FirebaseAuth auth;
    ArrayAdapter adapter;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Booking Details");

        //ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Users");
        auth = FirebaseAuth.getInstance();
        uid = auth.getUid();
        arrayList = new ArrayList<>();
        booking =findViewById(R.id.bookings);
        adapter =new ArrayAdapter<>(BookingActivity.this,android.R.layout.simple_list_item_1,arrayList);


        ref.child(uid).child("booking").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String name = (String) dataSnapshot.child("Name").getValue();
                String mobile = (String) dataSnapshot.child("Mobile").getValue();
                String time = (String) dataSnapshot.child("Time").getValue();
                 arrayList.add(name +"\n"+mobile+"\n"+time);
                 adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String time = (String) dataSnapshot.child("Time").getValue();
                String name = (String) dataSnapshot.child("Name").getValue();
                String mobile = (String) dataSnapshot.child("Mobile").getValue();
                arrayList.remove(name);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BookingActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
            }

        });
        booking.setAdapter(adapter);


    }
    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(BookingActivity.this,AdminEnterActivity.class);
        startActivity(intent);
    }
}
