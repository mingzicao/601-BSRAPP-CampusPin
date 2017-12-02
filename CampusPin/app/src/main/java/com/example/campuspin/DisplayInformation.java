package com.example.campuspin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayInformation extends AppCompatActivity {
    DatabaseReference myRefBuilding;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);
        Intent intent = getIntent();
        final String searchString = intent.getStringExtra("key");
        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // Capture the layout's TextView and set the string as its text
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRefBuilding = database.getReference("Building");
        myRef = database.getReference();
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(searchString.toString());
        final TextView address = (TextView) findViewById(R.id.address);
        myRefBuilding.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(searchString).child("address").getValue()!=null){
                    address.setText(dataSnapshot.child(searchString).child("address").getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {            }
        });
        final TextView phone = (TextView) findViewById(R.id.phone);
        myRefBuilding.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(searchString).child("phone").getValue()!=null){
                    phone.setText(dataSnapshot.child(searchString).child("phone").getValue().toString());
                }
                else{
                    phone.setText("No record");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {            }
        });

    }
}
