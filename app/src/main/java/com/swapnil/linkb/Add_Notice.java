


package com.shweta.linkb;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;


public class Add_Notice extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText et1, et2;
    Spinner sp;
    String sub, not, dep, non;
    int notn,notno;
    private Firebase mref,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__notice);
        Firebase.setAndroidContext(this.getApplication());


        ref = new Firebase("https://linkb-7da3f.firebaseio.com/Notices");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child1 : dataSnapshot.getChildren()) {
                    try {
                        non = child1.child("NoticeNo").getValue().toString().trim();
                        try {
                            notn = Integer.parseInt(non);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (notno < notn) {
                            notno = notn;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        et1 = (EditText) findViewById(R.id.editText15);
        et2 = (EditText) findViewById(R.id.editText16);

        //spinner
        sp = (Spinner) findViewById(R.id.spinner);
        sp.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) Add_Notice.this);
        List<String> Department = new ArrayList<String>();
        Department.add("Surya Mandir");
        Department.add("Aim and Act");
        Department.add("Vani Mandir");
        Department.add("Urja Mandir");
        Department.add("Jeev Mandir");
        Department.add("Vigyan Mandir");
        Department.add("Bhu Mandir");
        Department.add("Wisdom");

        mref = new Firebase("https://linkb-7da3f.firebaseio.com/");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Department);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp.setAdapter(dataAdapter);
        Button b1 = (Button) findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //database
                sub = et1.getText().toString();
                not = et2.getText().toString();
                notno = notno + 1;
                non = Integer.toString(notno);
                dep = sp.getSelectedItem().toString();
                Firebase ref1 = mref.child("Notices");
                Firebase mrefchild = ref1.child(sub);
                Firebase mc = mrefchild.child("Matter");
                mc.setValue(not);
                mc = mrefchild.child("Department");
                mc.setValue(dep);
                mc = mrefchild.child("NoticeNo");
                mc.setValue(non);
                startActivity(new Intent(Add_Notice.this, Main_Screen.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


