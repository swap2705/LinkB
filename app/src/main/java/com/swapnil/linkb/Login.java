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
import com.firebase.client.ValueEventListener;

public class Login extends AppCompatActivity {

    Firebase ref;
    EditText sp,s;
    TextView s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this.getApplication());
        ref=new Firebase("https://linkb-7da3f.firebaseio.com/Details");
        TextView t1=(TextView) findViewById(R.id.textView2);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Forgot Password
                startActivity(new Intent(Login.this,Forgot2Activity.class));
            }
        });
        TextView t2=(TextView) findViewById(R.id.textView3);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SignUp
                startActivity(new Intent(Login.this,Sign_Up.class));
            }
        });
        Button b1=(Button) findViewById(R.id.button);
        assert b1 != null;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login in
                s= (EditText) findViewById(R.id.editText);
                sp= (EditText) findViewById(R.id.editText2);
                s1= (TextView) findViewById(R.id.textView);
                final String sid=s.getText().toString().trim();
                final String pw=sp.getText().toString().trim();

                if(sid!=null && pw!=null) {
                    //database
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {

                                String password = dataSnapshot.child(sid).child("Password").getValue().toString();
                                String user = dataSnapshot.child(sid).child("User Type").getValue().toString();


                                if (pw.equals(password)) {
                                    if (user.equals("Student")) {
                                        startActivity(new Intent(Login.this, Main_Screen_Students.class));
                                    } else if (user.equals("Staff")) {
                                        startActivity(new Intent(Login.this, Main_Screen.class));
                                    }

                                } else if (!pw.equals(password)) {
                                    Toast.makeText(Login.this, "Incorrect id or password!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }


                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            System.out.println("The read failed: " + firebaseError.getMessage());
                        }

                    });
                }
                else{
                    Toast.makeText(Login.this, "Please enter the required details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

