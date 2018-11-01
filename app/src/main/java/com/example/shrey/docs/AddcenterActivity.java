package com.example.shrey.docs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddcenterActivity extends AppCompatActivity {
    //private static final String TAG = "AddcenterActivity";
    EditText shop_name,shop_address,shop_pincode;
    String name_shop,address,pincode,key ;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcenter);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Add Center Detail ");


        //ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        shop_name =findViewById(R.id.shop_name);
        shop_address = findViewById(R.id.shop_address);
        shop_pincode = findViewById(R.id.shop_pincode);

        key = getIntent().getStringExtra("key");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user =  mAuth.getCurrentUser();

        if(user == null){
            Toast.makeText(AddcenterActivity.this,"Please Login Again",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddcenterActivity.this,MainActivity.class);
            startActivity(intent);
        }

    }

    public void submit(View view){

        name_shop = shop_name.getText().toString();
        address = shop_address.getText().toString();
        pincode = shop_pincode.getText().toString();



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(key);

        if(name_shop.equals("")){
            Toast.makeText(AddcenterActivity.this,"Please Enter the Shop Name",Toast.LENGTH_SHORT).show();
        }
        else if(address.equals("")){
            Toast.makeText(AddcenterActivity.this,"Please Enter the Address",Toast.LENGTH_SHORT).show();
        }
        else if(pincode.equals("")){
            Toast.makeText(AddcenterActivity.this,"Plaese Enter the Pincode",Toast.LENGTH_SHORT).show();
        }
        else{
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            AddCenter add = new AddCenter(name_shop,address,pincode);
            ref.child(userId).setValue(add);
            ref.child(userId).child("uid").setValue(userId);
            Toast.makeText(AddcenterActivity.this,"Add Successfully",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(AddcenterActivity.this,AddActivity.class);
        startActivity(intent);
    }

}
