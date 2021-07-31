package com.shweta.linkb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Forgot2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot2);
        Button b1=(Button) findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back
                startActivity(new Intent(Forgot2Activity.this,Login.class));
            }
        });
        Button b2=(Button) findViewById(R.id.button7);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //next
                String s=((EditText) findViewById(R.id.editText18)).getText().toString();
                if(s!=null) {
                    Intent i = new Intent(Forgot2Activity.this, Forgot_Password.class);
                    i.putExtra("id", s);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Forgot2Activity.this, "Please enter the SmartCardID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
