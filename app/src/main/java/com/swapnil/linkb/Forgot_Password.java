package com.shweta.linkb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class Forgot_Password extends AppCompatActivity {

    TextView sq;
    Firebase ref;
    String fsq,fans,s1,s2,s3,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        Button b1=(Button) findViewById(R.id.button4);
        //getting key
        Bundle bundle = getIntent().getExtras();
        final String s = bundle.getString("id");

        sq= (TextView) findViewById(R.id.textView12);
        ref=new Firebase("https://linkb-7da3f.firebaseio.com/Details");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    fsq = dataSnapshot.child(s).child("Security Question").getValue().toString();
                    fans= dataSnapshot.child(s).child("Answer").getValue().toString();
                    user=dataSnapshot.child(s).child("User Type").getValue().toString();
                    sq.setText(fsq);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change password
                s1=((EditText)findViewById(R.id.editText7)).getText().toString();
                s2=((EditText)findViewById(R.id.editText8)).getText().toString();
                s3=((EditText)findViewById(R.id.editText9)).getText().toString();
                if(s1!=null && s2!=null && s3!=null) {
                    if (s1.equals(fans)) {
                        if (s2.equals(s3)) {
                            Firebase mref = ref.child(s).child("Password");
                            mref.setValue(s3);
                            Toast.makeText(Forgot_Password.this, "Password changed", Toast.LENGTH_SHORT).show();
                            if (user.equals("Student")) {
                                startActivity(new Intent(Forgot_Password.this, Main_Screen_Students.class));
                            } else if (user.equals("Staff")) {
                                startActivity(new Intent(Forgot_Password.this, Main_Screen.class));
                            }
                        } else if (!s2.equals(s3)) {
                            Toast.makeText(Forgot_Password.this, "Entered passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    } else if (!s1.equals(fans)) {
                        Toast.makeText(Forgot_Password.this, "Answer to security question does not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Forgot_Password.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
