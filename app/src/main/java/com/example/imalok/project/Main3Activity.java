package com.example.imalok.project;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity
{

    EditText editTop;
    Uri selectedImage;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    ImageView imageView;
    private static int RESULT_LOAD_IMAGE =1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editTop = (EditText) findViewById(R.id.editTop);
        imageView = (ImageView) findViewById(R.id.memeImage);
    }
    public void addImage(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,RESULT_LOAD_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null!= data)
        {
            selectedImage = data.getData();
            String [] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageURI(selectedImage);
        }
    }
    public void tryMeme(View view)
    {
        if ((imageView.getDrawable()!=null) && (editTop.getText().toString().trim().length()>0))
        {
            hideKeyboard(view);
            Intent i = new Intent(Main3Activity.this,Main4Activity.class);
            i.putExtra("wish",editTop.getText().toString());
            i.putExtra("uri",selectedImage);
            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Insert Image & Text",Toast.LENGTH_SHORT).show();
        }
    }
    public void hideKeyboard(View view)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
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
        if (item.getItemId()==R.id.about){
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