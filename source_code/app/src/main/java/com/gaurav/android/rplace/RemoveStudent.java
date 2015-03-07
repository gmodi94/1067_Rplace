package com.gaurav.android.rplace;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;


public class RemoveStudent extends ActionBarActivity {

    long pid_number_to_be_removed=0;


    EditText Pid_No_Remove;
    Button RemovePidButton;
    Button GetInfoButton;
    Button DeleteDatabaseButton;
    EditText FetchDetailsET;


    SQLiteDatabase StudentDb_newObject=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_student);

        Pid_No_Remove=(EditText)findViewById(R.id.RemovePID_ET_ID);
        RemovePidButton=(Button)findViewById(R.id.RemoveActivityButton);
        FetchDetailsET=(EditText)findViewById(R.id.FetchDetailsET);
        GetInfoButton=(Button)findViewById(R.id.GetInfoButton);
        DeleteDatabaseButton=(Button)findViewById(R.id.DeleteDatabaseButtonID);
        //removeStudentByPid();
        FetchDetailsET.setEnabled(false);
        createDatabase();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_remove_student, menu);
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

    public void RemoveStudent(View view) {
        // Get the pid to delete

        try {
            int pid1 = Integer.parseInt(Pid_No_Remove.getText().toString());


            // Delete matching id in database

            StudentDb_newObject.execSQL("DELETE FROM Student_new WHERE Pid = " + pid1 + ";");


            Toast.makeText(this, "1 STUDENT REMOVED", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Unable to Delete Data!", Toast.LENGTH_SHORT).show();
        }
        }


    public void GetStudentInfo(View view) {
      try {
          int pidNumber = Integer.parseInt(Pid_No_Remove.getText().toString());

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


                  StudentDetailList = StudentDetailList + pidFinal + " : " + nameFinal + " : " + BranchFinal + "\n";

                  FetchDetailsET.setEnabled(true);

                  // Keep getting results as long as they exist

              } while (cursor.moveToNext());


              FetchDetailsET.setText(StudentDetailList);
              FetchDetailsET.setEnabled(false);


          } else {


              Toast.makeText(this, "No Results to Show", Toast.LENGTH_SHORT).show();

              FetchDetailsET.setText("");
              FetchDetailsET.setEnabled(false);

          }

      }
      catch (Exception e)
      {
          Toast.makeText(this, "Please Enter PID Number", Toast.LENGTH_SHORT).show();
      }

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



            Log.e("ERROR CREATING STUDENT DATABASE", "Error Creating Database");



        }






    }

    public void deleteDatabase(View view) {
        this.deleteDatabase("StudentDb_new");
    }
    @Override

    protected void onDestroy() {



        StudentDb_newObject.close();



        super.onDestroy();

    }

}
