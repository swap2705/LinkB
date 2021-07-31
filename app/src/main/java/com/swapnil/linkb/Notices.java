package com.shweta.linkb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Notices extends AppCompatActivity {

    TextView tsub,tmat,tno;
    Firebase ref;
    boolean ces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);
        Intent i=getIntent();
        final String not=i.getStringExtra("n");
        tsub= (TextView) findViewById(R.id.textView15);
        tmat= (TextView) findViewById(R.id.textView17);
        tno= (TextView) findViewById(R.id.textView21);
        ref=new Firebase("https://linkb-7da3f.firebaseio.com/Notices");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child1 : dataSnapshot.getChildren()) {
                    try {
                        ces = not.equals(child1.child("NoticeNo").getValue().toString().trim());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    if(ces) {
                        tsub.setText(child1.getKey().toString().trim());
                        tmat.setText(child1.child("Matter").getValue().toString());
                        tno.setText(not);
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
