package com.example.shrey.docs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //ads
        MobileAds.initialize(this, "ca-app-pub-6143211471954524~6965528796");

        mAdView = findViewById(R.id.adView);
       // mAdView.setAdSize(AdSize.BANNER);
       // mAdView.setAdUnitId("ca-app-pub-6143211471954524/2787762011");

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


  public void addhar(View view){
      Intent intent = new Intent(this,SecondActivity.class);
      ArrayList<String> docs = new ArrayList<String>();
      docs.add("Pan card");
      docs.add("Ration card");
      docs.add("Birth Certificate");
      docs.add("voter id");
      docs.add("Electricity bill");
      intent.putExtra("docs",docs);
      intent.putExtra("type","Addhar");
      startActivity(intent);
  }

    public void pan(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        ArrayList<String> docs = new ArrayList<String>();
        docs.add("addhar card");
        docs.add("Ration card");
        docs.add("Driving License");
        docs.add("Birth Certificate");
        docs.add("Electricity Bill");
        intent.putExtra("docs",docs);
        intent.putExtra("type","Pan");
        startActivity(intent);
    }

    public void income(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        ArrayList<String> docs = new ArrayList<String>();
        docs.add("Talathi certificate");
        docs.add("Ration card");
        docs.add("addhar card");
        docs.add("Electricity Bill");
        intent.putExtra("docs",docs);
        intent.putExtra("type","Income");
        startActivity(intent);
    }

    public void cast(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        ArrayList<String> docs = new ArrayList<String>();
        docs.add("addhar card");
        docs.add("Electricity Bill");
        docs.add("Birth Certificate");
        docs.add("Affidavit for Caste Certificate");
        docs.add("Leaving Certificate");
        docs.add("Relative's cast certificate");
        intent.putExtra("docs",docs);
        intent.putExtra("type","Cast");
        startActivity(intent);
    }

    public void nationality(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        ArrayList<String> docs = new ArrayList<String>();
        docs.add("Addhar card");
        docs.add("Pan card");
        docs.add("Electricity Bill");
        docs.add("voter card");
        intent.putExtra("docs",docs);
        intent.putExtra("type","National");
        startActivity(intent);
    }

    public void other(View view){

    }


    public void admins(View view){
        Intent intent = new Intent(this,AdminActivity.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Appliction will get close ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}

