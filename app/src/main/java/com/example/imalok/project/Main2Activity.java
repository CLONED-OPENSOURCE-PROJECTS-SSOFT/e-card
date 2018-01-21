package com.example.imalok.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity
{
    Button reg;
    EditText name,email,phone,pass,repass;
    @Override
    protected void onCreate(Bundle saved)
    {
        super.onCreate(saved);
        setContentView(R.layout.activity_main2);

        reg = (Button)findViewById(R.id.reg);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        pass = (EditText)findViewById(R.id.pass);
        repass = (EditText)findViewById(R.id.repass);
        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String n = name.getText().toString();
                String e = email.getText().toString();
                String ph = phone.getText().toString();
                String ps = pass.getText().toString();
                String rps = repass.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String MobilePattern = "[789][0-9]{9}";
                if ((n.trim().length() > 0) && (e.trim().length() > 0) && (ph.trim().length() > 0) && (ps.trim().length() > 0) && (rps.trim().length() > 0))
                {
                    if(email.getText().toString().matches(emailPattern))
                    {
                        if(phone.getText().toString().matches(MobilePattern))
                        {
                            if (pass.getText().toString().length() == 4)
                            {
                                if (ps.equals(rps))
                                {
                                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                                    db.register(n, e, ph, ps);
                                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Enter the Same Password in Both Fields", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Pin Length Should be 4", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Enter Correct Mobile Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Enter Valid Email Adress",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All Fields Mandatory",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==R.id.setting)
        {
            Toast.makeText(getApplicationContext(),"Wait For The Update",Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.about)
        {
            Intent i = new Intent(this,Main6Activity.class);
            startActivity(i);
        }
        if (item.getItemId()==R.id.exit)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}