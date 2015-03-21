package com.gaurav.android.rplace;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;


public class ViewData extends ActionBarActivity {

 EditText PidNumberET;
 EditText DisplayBoardET;
  SQLiteDatabase StudentDb_newObject=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        PidNumberET=(EditText)findViewById(R.id.PidETextBoxID);
        DisplayBoardET =(EditText)findViewById(R.id.DisplayBoardEtID);
        createDatabase();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_data, menu);
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
    public void createDatabase() {



        try{



            // Opens a current database or creates it

            // Pass the database name, designate that only this app can use it

            // and a DatabaseErrorHandler in the case of database corruption

            StudentDb_newObject = this.openOrCreateDatabase("StudentDb_new", MODE_PRIVATE, null);



            // Execute an SQL statement that isn't select

            StudentDb_newObject.execSQL("CREATE TABLE IF NOT EXISTS Student_new " +

                    "(Pid integer primary key, name VARCHAR, Gender VARCHAR, Branch VARCHAR, GPAPointer float, PlacementDone VARCHAR);");



            // The database on the file system

            File database = getApplicationContext().getDatabasePath("StudentDb_new.db");



            // Check if the database exists

            if (!database.exists()) {

                Toast.makeText(this, "Database Created", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Database Missing", Toast.LENGTH_SHORT).show();

            }



        }



        catch(Exception e){



            Log.e("ERROR", "Error Creating Database");
            Toast.makeText(this,"Error Creating Database",Toast.LENGTH_SHORT).show();


        }






    }

    public void GetStudentInfo(View view) {
    try
    {
        int pidNumber = Integer.parseInt(PidNumberET.getText().toString());
        Cursor cursor = StudentDb_newObject.rawQuery("SELECT * FROM Student_new WHERE Pid=" + pidNumber, null);
        int PidColumn = cursor.getColumnIndex("Pid");
        int nameColumn = cursor.getColumnIndex("name");
        int GenderColumn = cursor.getColumnIndex("Gender");
        int BranchColumn = cursor.getColumnIndex("Branch");
        int GPAPointerColumn = cursor.getColumnIndex("GPAPointer");
        int PlacementDoneColumn = cursor.getColumnIndex("PlacementDone");
        cursor.moveToFirst();
        String StudentDetailList = "";

        if (cursor != null && (cursor.getCount() > 0)) {


            do {

                // Get the results and store them in a String

                String pidFinal = cursor.getString(PidColumn);

                String nameFinal = cursor.getString(nameColumn);

                String BranchFinal = cursor.getString(BranchColumn);
                String Pointer = cursor.getString(GPAPointerColumn);
                String PlacementStatus = cursor.getString(PlacementDoneColumn);


                StudentDetailList = StudentDetailList + pidFinal + " : " + nameFinal + " : " + BranchFinal + " : "+Pointer+ " : "+ PlacementStatus+"\n";

                DisplayBoardET.setEnabled(true);

                // Keep getting results as long as they exist

            } while (cursor.moveToNext());


            DisplayBoardET.setText(StudentDetailList);
            DisplayBoardET.setEnabled(false);


        } else {


            Toast.makeText(this, "No Results to Show", Toast.LENGTH_SHORT).show();

            DisplayBoardET.setText("");
            DisplayBoardET.setEnabled(false);

        }

    }
    catch (Exception e)
    {
        Toast.makeText(this, "Please Enter PID Number", Toast.LENGTH_SHORT).show();
    }

}


}