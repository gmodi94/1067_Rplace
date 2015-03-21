package com.gaurav.android.rplace;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;


public class Rplace extends ActionBarActivity {
    Button AddUIButton;
    Button RemoveUIButton;
    Button DisplayUIButton;
    SQLiteDatabase StudentDb_newObject=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rplace);

        AddUIButton=(Button)findViewById(R.id.AddButtonID);
        //goto_add_student();
        RemoveUIButton=(Button)findViewById(R.id.RemoveActivityButton);
        //goto_RemoveStudent();
        DisplayUIButton=(Button)findViewById(R.id.DisplayButtonID);
        //goto_Display();
        createDatabase();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rplace, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void goto_add_student(View view)
    {
        Intent intent=new Intent(this,add_student.class);
        startActivity(intent);
    }

    public void goto_RemoveStudent(View view)
    {
        Intent intent=new Intent(this,RemoveStudent.class);
        startActivity(intent);
    }
    public void goto_Display_gateway(View view)
    {
        Intent intent=new Intent(this,ViewGateway.class);
        startActivity(intent);
    }

    public void createDatabase() {



        try{



            // Opens a current database or creates it

            // Pass the database name, designate that only this app can use it

            // and a DatabaseErrorHandler in the case of database corruption

            StudentDb_newObject = this.openOrCreateDatabase("StudentDb_new", MODE_PRIVATE, null);



            // Execute an SQL statement that isn't select

            StudentDb_newObject.execSQL("CREATE TABLE IF NOT EXISTS Student_new " +

                    "(Pid integer primary key, name VARCHAR, Gender VARCHAR, Branch VARCHAR, GPAPointer integer, PlacementDone VARCHAR);");



            // The database on the file system

            File database = getApplicationContext().getDatabasePath("StudentDb_new.db");



            // Check if the database exists

            if (!database.exists()) {

                //Toast.makeText(this, "Database Created", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Database Missing", Toast.LENGTH_SHORT).show();

            }



        }



        catch(Exception e){



            Log.e("CONTACTS ERROR", "Error Creating Database");



        }



        // Make buttons clickable since the database was created

      //  addContactButton.setClickable(true);

        //deleteContactButton.setClickable(true);

        //getContactsButton.setClickable(true);

        //deleteDBButton.setClickable(true);



    }

    public void goto_Settings_gateway(View view)
    {
        Intent intent=new Intent(this,AdvancedSettings.class);
        startActivity(intent);
    }




}
