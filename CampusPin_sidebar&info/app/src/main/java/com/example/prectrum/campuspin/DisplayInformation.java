package com.example.prectrum.campuspin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisplayInformation extends AppCompatActivity {
    DatabaseReference myRefBuilding;
    DatabaseReference myRef;
    private TextView phone;
    private TextView address;
    private TextView name;
    private TextView resource;
    Bitmap newBitmap;
    String url;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);
        Intent intent = getIntent();






        final String searchString = intent.getStringExtra("key");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRefBuilding = database.getReference("Building");

        myRef = database.getReference();
        phone =  findViewById(R.id.showPhone);
        address =  findViewById(R.id.showAddress);
        name = findViewById(R.id.showName);
        resource = findViewById(R.id.showName);
        image = findViewById(R.id.SearchResult);

        name.setText(searchString);

        myRefBuilding.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(searchString).child("address").getValue()!=null){
                    address.setText(dataSnapshot.child(searchString).child("address").getValue().toString());
                } else{
                    address.setText("No record");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {            }

        });

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





        myRefBuilding.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(searchString).child("url").getValue()!=null){
                  //  System.out.println("url" + );
                    url = dataSnapshot.child(searchString).child("url").getValue().toString();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("url" + url);

                            newBitmap = getBitmapFromURL(url);

                            Thread timer = new Thread()
                            {
                                public void run()
                                {
                                    try
                                    {
                                        sleep(10);
                                        runOnUiThread(new Runnable() {
                                            public void run() {
                                                image.setImageBitmap(newBitmap);
                                            }
                                        });
                                    }
                                    catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                    finally
                                    {
                                        System.out.println("finally");
                                    }
                                }
                            };
                            timer.start();
                        }
                    }).start();

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {            }

        });


    }




    public Bitmap getBitmapFromURL(String src) {


        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }



}
