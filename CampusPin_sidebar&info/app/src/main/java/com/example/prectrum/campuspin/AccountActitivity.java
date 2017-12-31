package com.example.prectrum.campuspin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class AccountActitivity extends AppCompatActivity implements View.OnClickListener,  NavigationView.OnNavigationItemSelectedListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    public static final String EXTRA_MESSAGE = "USERNAME";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Button mSendData;
  //  private DatabaseReference mDatabase;
    private EditText mTxt;
    private String searchString;
    private String username;
    private TextView mDisplayUser;
    private ImageView mImageView;
    private Button mSendPic;
    private StorageReference mStorageRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private Toolbar myToolbar;
    private TextView mheaderDisplay;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
    private DatabaseReference myRefBuilding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_actitivity);
        setRid();
        displayUsername();
        setSupportActionBar(myToolbar);

        myToolbar.setTitle("Campus Pin");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToggle = new ActionBarDrawerToggle (this, mDrawerLayout, myToolbar, R.string.open, R.string.close);
        mToggle.syncState();

        mAuth = FirebaseAuth.getInstance();
        setWidgetListner();
        myRefBuilding = database.getReference("Building");
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }
    private void setRid() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayOut);
        mDisplayUser = (TextView) findViewById(R.id.displayUser);
        mSendData = (Button) findViewById(R.id.txtBtn);
        mSendPic = (Button) findViewById(R.id.imgBtn);
        mImageView = (ImageView)findViewById(R.id.imageToUpoad);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // mheaderDisplay = (TextView) findViewById(R.layout.naviheader_layout.);
        mTxt = (EditText) findViewById(R.id.textSearch);


    }
    private void startSearchHistoryActivity(View view) { // send text from main
        // Do something in response to button
        Intent intent = new Intent(this, SearchHistory.class);
        intent.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent);
    }
    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navMenu);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void setWidgetListner (){
        setNavigationViewListner();
        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchString = mTxt.getText().toString();

                myRef = database.getReference();
                myRef.child("user").child("searchHistory").push().setValue(searchString);
               // mTxt.setText("");
                myRefBuilding.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String actualSearch ="none";

                        if(Objects.equals(searchString,"pho")||Objects.equals(searchString,"Photonics Center")||Objects.equals(searchString,"photonics center")||Objects.equals(searchString,"Photonic Center")||Objects.equals(searchString,"photonic center")){

                            actualSearch = "PHO";

//
                        }
                        else if(Objects.equals(searchString,"agganis arena")||Objects.equals(searchString,"Agganis")||Objects.equals(searchString,"agganis")||Objects.equals(searchString,"AgganisArena")||Objects.equals(searchString,"agganisarena")){
                            actualSearch = "Agganis Arena";
                        }
                        else if(Objects.equals(searchString,"marsh chapel")||Objects.equals(searchString,"MarshChapel")||Objects.equals(searchString,"marshchapel")||Objects.equals(searchString,"the Marsh Chapel")||Objects.equals(searchString,"the marsh chapel")){
                            actualSearch = "Marsh Chapel";
                        }
                        else{
                            actualSearch = searchString;
                        }
                     if (dataSnapshot.hasChild(actualSearch)){
                           Intent intent = new Intent(getApplicationContext(), DisplayInformation.class);
                          // Intent intent = new Intent(getApplicationContext(), newActivity.class);
                            intent.putExtra("key",actualSearch);

                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {            }
                });

            }
        });
        mImageView.setOnClickListener(this);

        mSendPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImgToFirebase();
                //recg();
                //  识别模块
                // if (valid)
                //
            }
        });

    }

    @IgnoreExtraProperties
    public class Building {
        public String name;
        public String address;

        public Building(String name, String address) {
            this.name = name;
            this.address = address;
        }
    }
    private void displayUsername() {
        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mDisplayUser.setText("Welcome, " + username);

    }



    @Override
    public void onClick(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override // when click image, here will be called
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode ==RESULT_OK && data != null) {
            Uri selctedImage = data.getData();   // uniform resourse address of the image in the phone
            mImageView.setImageURI(selctedImage);  // pic is saved in mImageView;

        }
    }
    // Handle navigation view item clicks here.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_history:
                System.out.println("history");
                Intent intent = new Intent(AccountActitivity.this,  SearchHistory.class);
                intent.putExtra(EXTRA_MESSAGE, username);
                startActivity(intent);

                return true;
            case R.id.nav_events:
                System.out.println("upload events");
                Intent intent0 = new Intent(AccountActitivity.this,  PostEvents.class);
                intent0.putExtra(EXTRA_MESSAGE, username);
                startActivity(intent0);
                return true;
            case R.id.nav_logout:
                System.out.println("user logout");
                mAuth.signOut();
                Intent intent1 = new Intent(AccountActitivity.this,  MainActivity.class);
                startActivity(intent1);
                return true;
            default: return true;
        }
    }

    private void saveImgToFirebase() {  // when click the search by photo;
        // 1. call regcognition part;
        // if (valid) {
                // save the image to Building : "img"  , user history : Building name;
                // give the user info of the building (google map, resources,)
         // } else {
              // tell user there is not a valid object;
        // }

    }


}
