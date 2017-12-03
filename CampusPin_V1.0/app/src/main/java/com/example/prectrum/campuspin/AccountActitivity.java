package com.example.prectrum.campuspin;

import android.app.ActionBar;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.*;
import com.google.firebase.database.*;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.*;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;

import org.w3c.dom.Text;

public class AccountActitivity extends AppCompatActivity  implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Button mSendData;
    private DatabaseReference mDatabase;
    private EditText mTxt;
    private String username;
    private TextView mDisplayUser;
    private ImageView mImageView;
    private Button mSendPic;
    private Button mLogOut;

    private StorageReference mStorageRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private Toolbar myToolbar;

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
        // Setting the actionbarToggle to drawer layout
        //drawerLayout.setDrawerListener(mToggle);
        // Calling sync state is necessary to show your hamburger icon...
        // or so I hear. Doesn't hurt including it even if you find it works
        // without it on your test device(s)
        mToggle.syncState();


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("user");
        setWidgetListner();
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }


    private void setRid() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayOut);
        mSendData = (Button) findViewById(R.id.txtBtn);
        mSendPic = (Button) findViewById(R.id.imgBtn);
        mImageView = (ImageView)findViewById(R.id.imageToUpoad);
        mLogOut = (Button) findViewById(R.id.logoutBtn);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);



    }
    private void setWidgetListner (){
        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mTxt.getText().toString();
                //   mDatabase.child("cmz").setValue(message);
                mDatabase.child(username).push().setValue(message);
            }
        });
        mImageView.setOnClickListener(this);
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent1 = new Intent(AccountActitivity.this,  MainActivity.class);
                startActivity(intent1);
            }
        });
        mSendPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send photo to
                //  识别模块
                // if (valid)
                //
            }
        });

    }

    //识别函数；

    private void displayUsername() {
        mDisplayUser = (TextView) findViewById(R.id.displayUser);
        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mDisplayUser.setText("Hi, " + username);
        mTxt = (EditText) findViewById(R.id.textSearch);
    }

    @Override
    public void onBackPressed() {

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
