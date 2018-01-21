package com.example.imalok.project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity
{
    TextView alok;
    TextView sutanu;
    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        alok = (TextView)findViewById(R.id.textView7);
        sutanu = (TextView)findViewById(R.id.textView10);
        link = (TextView)findViewById(R.id.website);
        alok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+918240159173"));
                startActivity(i);
            }
        });
        sutanu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+917003896407"));
                startActivity(i);
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://alok722.github.io/online_resume/"));
                startActivity(i);
            }
        });
    }
}
