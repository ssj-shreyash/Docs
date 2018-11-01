package com.example.shrey.docs;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextView docs_req;
    TextView shop_names;
    TextView t1,t2;
    ListView listv;
    String pin;

    String type;
    int count;


    ArrayList<String> al = new ArrayList<>();
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adapter ,getAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listv = findViewById(R.id.listv);

        docs_req = findViewById(R.id.docs);

        type = getIntent().getStringExtra("type");
        ArrayList<String> docs = getIntent().getStringArrayListExtra("docs");

            int size = docs.size();
        for (int i = 0; i < size; i++) {

            String t = docs_req.getText() + docs.get(i) + " ,";
            docs_req.setText(t);
        }


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child(type);
       // final AddCenter addCenter = new AddCenter();


          adapter = new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1,arrayList);
        //  getAdapter = new ArrayAdapter<>(SecondActivity.this,android.R.layout.simple_list_item_1,al);


        EditText p = findViewById(R.id.pin);
        pin = p.getText().toString().trim();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String s1 =(String) dataSnapshot.child("shop_name").getValue();
                String s2 = (String) dataSnapshot.child("shop_address").getValue();
                String uid = (String) dataSnapshot.child("uid").getValue();
                String s3 = (String) dataSnapshot.child("shop_pincode").getValue();
                arrayList.add(s1+"\n"+s2+"\n"+s3);
                al.add(uid);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                arrayList.equals(pin);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String s1 = (String) dataSnapshot.child("shop_name").getValue();
                String s2 = (String) dataSnapshot.child("shop_address").getValue();
                String s3 = (String) dataSnapshot.child("shop_pincode").getValue();
                String uid = (String) dataSnapshot.child("uid").getValue();
                arrayList.remove(s1);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SecondActivity.this,"Error",Toast.LENGTH_LONG).show();

            }
        });


        listv.setAdapter(adapter);
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("uid_list",al);
                intent.putExtra("position",position);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });






    }

    }
