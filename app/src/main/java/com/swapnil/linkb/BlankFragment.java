package com.shweta.linkb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    List<String> Subject=new ArrayList<>();
    Firebase ref,mref;
    String not;
    ListView lv;

/*    public static BlankFragment newInstance(){
        BlankFragment blankFragment=new BlankFragment();
        return blankFragment;
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_blank,container,false);

        lv= (ListView) view.findViewById(R.id.listView2);

        Firebase.setAndroidContext(this.getActivity());

        ref = new Firebase("https://linkb-7da3f.firebaseio.com/Notices");
        ref.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for (DataSnapshot child1 : dataSnapshot.getChildren()) {

                    Subject.add(child1.getKey().toString().trim());
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        lv.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,Subject));



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


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
                });
                if(not!=null) {
                    Intent i = new Intent(getActivity(), Notices.class);
                    i.putExtra("n", not);
                    startActivity(i);
                }
            }
        });

        return view;
    }
}
