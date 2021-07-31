package com.shweta.linkb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

public class Jeev extends AppCompatActivity {

    List<String> Subject=new ArrayList<>();
    Firebase ref,mref;
    ListView lv;
    String Dep="Bhu Mandir",not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeev);

        lv= (ListView) findViewById(R.id.listJeev);

        ref = new Firebase("https://linkb-7da3f.firebaseio.com/Notices");
        ref.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for (DataSnapshot child1 : dataSnapshot.getChildren()) {

                    if(Dep.equals(child1.child("Department").getValue().toString())) {
                        Subject.add(child1.getKey().toString().trim());
                    }
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        lv.setAdapter(new ArrayAdapter<String>(Jeev.this,android.R.layout.simple_list_item_1,Subject));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {

                mref = new Firebase("https://linkb-7da3f.firebaseio.com/Notices");
                mref.addValueEventListener(new com.firebase.client.ValueEventListener() {
                    @Override
                    public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                        for (DataSnapshot child1 : dataSnapshot.getChildren()) {
                            not=dataSnapshot.child(Subject.get(position)).child("NoticeNo").getValue().toString();
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });if(not!=null) {
                    Intent i = new Intent(Jeev.this, Notices.class);
                    i.putExtra("n", not);
                    startActivity(i);
                }
            }
        });


    }
}
