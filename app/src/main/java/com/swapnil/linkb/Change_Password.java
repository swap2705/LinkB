package com.shweta.linkb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Change_Password extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Firebase ref;
    String s1,s2,s3,s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__password);
        ref=new Firebase("https://linkb-7da3f.firebaseio.com/Details");
        et1= (EditText) findViewById(R.id.editText3);
        et2= (EditText) findViewById(R.id.editText4);
        et3= (EditText) findViewById(R.id.editText5);
        et4= (EditText) findViewById(R.id.editText6);
        Button b2= (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s1=et1.getText().toString().trim();
                s2=et2.getText().toString().trim();
                s3=et3.getText().toString().trim();
                s4=et4.getText().toString().trim();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //get all the children
                        // s.setText(dataSnapshot.getKey().toString());//key is details


                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            String password = dataSnapshot.child(s1).child("Password").getValue().toString();

                            if(s2.equals(password))
                            {
                                if(s3.equals(s4)){
                                    Firebase mref=ref.child(s1).child("Password");
                                    mref.setValue(s3);
                                    startActivity(new Intent(Change_Password.this,Main_Screen.class));
                                }
                                else if(!s3.equals(s4)){
                                    Toast.makeText(Change_Password.this, "Entered passwords do not match", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else if(!s2.equals(password))
                            {
                                Toast.makeText(Change_Password.this, "Old password entered is wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }



                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }


                });
            }
        });
    }
}
