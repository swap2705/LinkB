package com.shweta.linkb;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import java.io.IOException;

public class Sign_Up extends AppCompatActivity {

    private Button bsignup;
    Firebase mref;
    String user;
    Cursor cur=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        Firebase.setAndroidContext(this);
        bsignup=(Button) findViewById(R.id.button3);

        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sign up
                mref = new Firebase("https://linkb-7da3f.firebaseio.com/");
                String sid = ((EditText) findViewById(R.id.editText10)).getText().toString();
                String sname = ((EditText) findViewById(R.id.editText11)).getText().toString();
                String spwd = ((EditText) findViewById(R.id.editText12)).getText().toString();
                String ssq = ((EditText) findViewById(R.id.editText13)).getText().toString();
                String sans = ((EditText) findViewById(R.id.editText14)).getText().toString();
                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);

                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.radioButton2:
                        user = "Student";
                        break;
                    case R.id.radioButton:
                        user = "Staff";
                        break;
                }

                if (sid != null && sname != null && spwd != null && ssq != null && sans != null) {

                    DatabaseHelper myDBHelper = new DatabaseHelper(Sign_Up.this);
                    try {
                        myDBHelper.createDatabase();
                    } catch (IOException e) {
                        throw new Error("Unable to create database");
                    }
                    try {
                        myDBHelper.openDatabase();
                    } catch (Exception sqle) {
                        sqle.printStackTrace();
                    }
                    cur = myDBHelper.query("University", null, null, null, null, null, null);
                    int c1 = 0;
                    if (cur.moveToFirst()) {
                        do {
                            if ((cur.getString(0)).equals(sid)) {
                                if (((cur.getString(1)).equals(sname)) && ((cur.getString(2)).equals(user))) {
                                    c1 = 1;

                                    //Database connectivity
                                    Firebase ref = mref.child("Details");
                                    Firebase mrefchid = ref.child(sid);
                                    Firebase mrefc = mrefchid.child("Name");
                                    mrefc.setValue(sname);
                                    mrefc = mrefchid.child("Password");
                                    mrefc.setValue(spwd);
                                    mrefc = mrefchid.child("Security Question");
                                    mrefc.setValue(ssq);
                                    mrefc = mrefchid.child("Answer");
                                    mrefc.setValue(sans);
                                    mrefc = mrefchid.child("User Type");
                                    mrefc.setValue(user);
                                    switch (rg.getCheckedRadioButtonId()) {
                                        case R.id.radioButton2:
                                            startActivity(new Intent(Sign_Up.this, Main_Screen_Students.class));
                                            break;
                                        case R.id.radioButton:
                                            startActivity(new Intent(Sign_Up.this, Main_Screen.class));
                                            break;
                                    }
                                } else {
                                    Toast.makeText(Sign_Up.this, "Details entered are not correct", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } while (cur.moveToNext());
                    }
                    if (c1 == 0) {
                        Toast.makeText(Sign_Up.this, "Not found in Banasthali Database", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Sign_Up.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}