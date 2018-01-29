package com.example.imalok.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main4Activity extends AppCompatActivity
{
    String dirpath,currentImage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        TextView t = (TextView)findViewById(R.id.memeBottomText);
        ImageView img=(ImageView)findViewById(R.id.memeImage);
        Bundle bd = getIntent().getExtras();
        Uri uri = bd.getParcelable("uri");
        Log.e("URI",uri.toString());
        try
        {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
            img.setImageBitmap(bitmap);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        t.setText(getIntent().getExtras().getString("wish"));

    }
    public static Bitmap getScreenshot(View view)
    {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        Canvas canvas=new Canvas(bitmap);
        canvas.drawColor(Color.rgb(208,211,212));
        canvas.drawBitmap(bitmap.createBitmap(view.getDrawingCache()),0,0,null);
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }
    public void store(Bitmap bm, String filename)
    {
        dirpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        File dir = new File(dirpath);
        if (!dir.exists())
        {
            dir.mkdir();
        }
        File file = new File(dirpath,filename);
        try
        {
            FileOutputStream fos = null;
            fos= new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG,100,fos);
            fos.flush();
            fos.close();
            Toast.makeText(this,"Image Saved,Check DCIM folder.",Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void addImage(View view)
    {
        View content = findViewById(R.id.lay1);
        Bitmap bitmap = getScreenshot(content);
        currentImage = "eCARD"+ System.currentTimeMillis()+".jpeg";
        store(bitmap,currentImage);
    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void shareImage(Uri imagePath) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        sharingIntent.setType("image/*");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, imagePath);
        startActivity(Intent.createChooser(sharingIntent, "Share Image Using"));
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
        if (item.getItemId()==R.id.share)
        {
            String filename = currentImage;
            String path = /**"/mnt/sdcard/"*/dirpath + filename;
            File f = new File(path);  //
            Uri imageUri = Uri.fromFile(f);
            shareImage(imageUri);
            Toast.makeText(getApplicationContext(),""+currentImage +dirpath,Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}


