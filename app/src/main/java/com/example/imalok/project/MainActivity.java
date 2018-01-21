package com.example.imalok.project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button log;
    TextView reg;
    EditText email, pass;
    TextView textView;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        TextView reg = (TextView)findViewById(R.id.reg);
       TextView textView = (TextView)findViewById(R.id.textView);
       textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        reg.setPaintFlags(reg.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        log = (Button) findViewById(R.id.log);
        reg = (TextView) findViewById(R.id.reg);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        textView = (TextView)findViewById(R.id.textView);

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Wait For The Update",Toast.LENGTH_SHORT).show();
            }
        });

        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

        log.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String e = email.getText().toString();
                String p = pass.getText().toString();

                if (e.trim().length() > 0 && p.trim().length() > 0)
                {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    if (db.verify(e, p))
                    {
                        Intent i = new Intent(MainActivity.this, Main3Activity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    if(e.trim().length() == 0)
                        email.setError("Enter Email");
                    else if(p.trim().length() == 0)
                        pass.setError("Enter Password");
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
    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            moveTaskToBack(true);
            return;
        }
        else
        {
            Toast.makeText(getBaseContext(), "Tap Back Button again in order to EXIT", Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case 1:
                {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                   // Toast.makeText(MainActivity.this, "Permission GRANTED to WRITE your External storage", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Permission denied to WRITE your External storage Please give permission to access the application", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
