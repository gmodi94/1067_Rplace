package com.gaurav.android.rplace;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.io.File;


public class GUIView extends ActionBarActivity {


    SQLiteDatabase StudentDb_newObject = null;
    EditText TestET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guiview);


        createDatabase();
        int TotalStudents = 0;

        int TotalITStudents = 0;
        int TotalComputerStudents = 0;
        int TotalEXTCStudents = 0;
        int NoOfStudentsPlaced = 0;
        int NoOfITStudentsPlaced = 0;
        int NoOfComputerStudentsPlaced = 0;
        int NoOfEXTCStudentsPlaced = 0;
        try {
             TotalStudents = TotalStudents();

             TotalITStudents = TotalITStudents();
             TotalComputerStudents = TotalComputerStudents();
             TotalEXTCStudents = TotalEXTCStudents();
             NoOfStudentsPlaced = TotalPlacedStudents();
             NoOfITStudentsPlaced = TotalPlacedITStudents();
             NoOfComputerStudentsPlaced = TotalPlacedComputerStudents();
             NoOfEXTCStudentsPlaced = TotalPlacedEXTCStudents();

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please Insert Atleast 1 student in each branch", Toast.LENGTH_LONG).show();
        }
        double pOverallPlaced= (NoOfStudentsPlaced/TotalStudents)*100;
        double pITplaced = (NoOfITStudentsPlaced/TotalITStudents)*100;
        double pCOMPPlaced=(NoOfComputerStudentsPlaced/TotalComputerStudents)*100;
        double pExtcPlaced=(NoOfEXTCStudentsPlaced/TotalEXTCStudents)*100;
        double OverallNotPlaced=100-pOverallPlaced;
        double pITnoPlace=100-pITplaced;
        double pCOMPnoPlace=100-pCOMPPlaced;
        double pEXTCnoPlace=100-pExtcPlaced;
        //Toast.makeText(this, "Total Students "+TotalStudents, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Total IT Students "+TotalITStudents, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Total Computer Students "+TotalComputerStudents, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Total EXTC Students "+TotalEXTCStudents, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Overall Placed Students "+NoOfStudentsPlaced, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Placed IT  Students "+NoOfITStudentsPlaced, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Placed Computer Students "+NoOfComputerStudentsPlaced, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Placed EXTC Students "+NoOfEXTCStudentsPlaced, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guiview, menu);
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


        try {


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


        } catch (Exception e) {


            Log.e("ERROR CREATING STUDENT DATABASE", "Error Creating Database");


        }


    }

    public int TotalStudents() {
        int TotalStudentsFinal=0;

        try {
            Cursor cursor = StudentDb_newObject.rawQuery("SELECT COUNT (*) AS TotalStudents FROM Student_new", null);
//            Toast.makeText(this, "Cursor Created!!", Toast.LENGTH_SHORT).show();


            int TotalStudentsColumn = cursor.getColumnIndex("TotalStudents");
  //          Toast.makeText(this, "TotalStudents Found!!", Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
    //        Toast.makeText(this, "Cursor Moved To First!!", Toast.LENGTH_SHORT).show();

            if (cursor != null && (cursor.getCount() > 0)) {


                TotalStudentsFinal = Integer.parseInt(cursor.getString(TotalStudentsColumn));

               // Toast.makeText(this, TotalStudentsFinal, Toast.LENGTH_SHORT).show();
                // Get the results and store them in a String

  //              TestET.setText(TotalStudentsFinal);

                //Variables


                // Keep getting results as long as they exist

            }



        } catch (Exception e) {
            Toast.makeText(this, "ERROR Occoured1!!", Toast.LENGTH_SHORT).show();
        }
        return(TotalStudentsFinal);

    }

    public int TotalITStudents() {
        int TotalStudentsFinal=0;
        try {
            Cursor cursor = StudentDb_newObject.rawQuery("SELECT COUNT (*) AS TotalITStudents FROM Student_new WHERE Branch='I.T.'", null);
//            Toast.makeText(this, "Cursor Created!!", Toast.LENGTH_SHORT).show();


            int TotalStudentsColumn = cursor.getColumnIndex("TotalITStudents");
  //          Toast.makeText(this, "TotalStudents Found!!", Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
    //        Toast.makeText(this, "Cursor Moved To First!!", Toast.LENGTH_SHORT).show();

            if (cursor != null && (cursor.getCount() > 0)) {


                TotalStudentsFinal = Integer.parseInt(cursor.getString(TotalStudentsColumn));
                //Toast.makeText(this, TotalStudentsFinal, Toast.LENGTH_SHORT).show();
                // Get the results and store them in a String
      //          TestET.setText(TotalStudentsFinal);
                //Variables


                // Keep getting results as long as they exist

            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR Occoured2!!", Toast.LENGTH_SHORT).show();
        }

        return TotalStudentsFinal;
    }

    public int TotalComputerStudents() {
        int TotalStudentsFinal=0;
        try {
            Cursor cursor = StudentDb_newObject.rawQuery("SELECT COUNT (*) AS TotalComputerStudents FROM Student_new WHERE Branch='Computer'", null);
//            Toast.makeText(this, "Cursor Created!!", Toast.LENGTH_SHORT).show();


            int TotalStudentsColumn = cursor.getColumnIndex("TotalComputerStudents");
  //          Toast.makeText(this, "TotalStudents Found!!", Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
    //        Toast.makeText(this, "Cursor Moved To First!!", Toast.LENGTH_SHORT).show();

            if (cursor != null && (cursor.getCount() > 0)) {


                TotalStudentsFinal = Integer.parseInt(cursor.getString(TotalStudentsColumn));
      //          Toast.makeText(this, "VariableAssigned!", Toast.LENGTH_SHORT).show();
                // Get the results and store them in a String
        //        TestET.setText(TotalStudentsFinal);
                //Variables


                // Keep getting results as long as they exist

            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR Occoured3!!", Toast.LENGTH_SHORT).show();
        }
        return TotalStudentsFinal;

    }

    public int TotalEXTCStudents() {
        int TotalStudentsFinal=0;
        try {
            Cursor cursor = StudentDb_newObject.rawQuery("SELECT COUNT (*) AS TotalEXTCStudents FROM Student_new WHERE Branch='E.X.T.C';", null);
          //  Toast.makeText(this, "Cursor Created!!", Toast.LENGTH_SHORT).show();


            int TotalStudentsColumn = cursor.getColumnIndex("TotalEXTCStudents");
            //Toast.makeText(this, "TotalStudents Found!!", Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
            //Toast.makeText(this, "Cursor Moved To First!!", Toast.LENGTH_SHORT).show();

            if (cursor != null && (cursor.getCount() > 0)) {


                TotalStudentsFinal = Integer.parseInt(cursor.getString(TotalStudentsColumn));
              //  Toast.makeText(this, "VariableAssigned!", Toast.LENGTH_SHORT).show();
                // Get the results and store them in a String
                //TestET.setText(TotalStudentsFinal);
                //Variables


                // Keep getting results as long as they exist

            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR Occoured4!!", Toast.LENGTH_SHORT).show();
        }
        return TotalStudentsFinal;

    }

    public int TotalPlacedStudents() {
        int TotalStudentsFinal=0;

        try {
            Cursor cursor = StudentDb_newObject.rawQuery("SELECT COUNT (*) AS NoOfStudentsPlaced FROM Student_new WHERE PlacementDone='Yes'", null);
            //Toast.makeText(this, "Cursor Created!!", Toast.LENGTH_SHORT).show();


            int TotalStudentsColumn = cursor.getColumnIndex("NoOfStudentsPlaced");
            //Toast.makeText(this, "TotalStudents Found!!", Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
            //Toast.makeText(this, "Cursor Moved To First!!", Toast.LENGTH_SHORT).show();

            if (cursor != null && (cursor.getCount() > 0)) {


                TotalStudentsFinal = Integer.parseInt(cursor.getString(TotalStudentsColumn));
              //  Toast.makeText(this, "VariableAssigned!", Toast.LENGTH_SHORT).show();
                // Get the results and store them in a String
            //    TestET.setText(TotalStudentsFinal);
                //Variables


                // Keep getting results as long as they exist

            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR Occoured5!!", Toast.LENGTH_SHORT).show();
        }
        return TotalStudentsFinal;
    }

    public int TotalPlacedITStudents() {
        int TotalStudentsFinal=0;
        try {
            Cursor cursor = StudentDb_newObject.rawQuery("SELECT COUNT (*) AS NoOfITStudentsPlaced FROM Student_new WHERE PlacementDone='Yes'AND Branch='I.T.'", null);
            //Toast.makeText(this, "Cursor Created!!", Toast.LENGTH_SHORT).show();


            int TotalStudentsColumn = cursor.getColumnIndex("NoOfITStudentsPlaced");
            //Toast.makeText(this, "TotalStudents Found!!", Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
            //Toast.makeText(this, "Cursor Moved To First!!", Toast.LENGTH_SHORT).show();

            if (cursor != null && (cursor.getCount() > 0)) {


                TotalStudentsFinal = Integer.parseInt(cursor.getString(TotalStudentsColumn));
              //  Toast.makeText(this, "VariableAssigned!", Toast.LENGTH_SHORT).show();
                // Get the results and store them in a String
                //TestET.setText(TotalStudentsFinal);
                //Variables


                // Keep getting results as long as they exist

            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR Occoured6!!", Toast.LENGTH_SHORT).show();
        }
        return TotalStudentsFinal;
    }
    public int TotalPlacedComputerStudents() {
        int TotalStudentsFinal=0;
        try {
            Cursor cursor = StudentDb_newObject.rawQuery("SELECT COUNT (*) AS NoOfComputerStudentsPlaced FROM Student_new WHERE PlacementDone='Yes'AND Branch='Computer'", null);
            //Toast.makeText(this, "Cursor Created!!", Toast.LENGTH_SHORT).show();


            int TotalStudentsColumn = cursor.getColumnIndex("NoOfComputerStudentsPlaced");
            //Toast.makeText(this, "TotalStudents Found!!", Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
            //Toast.makeText(this, "Cursor Moved To First!!", Toast.LENGTH_SHORT).show();

            if (cursor != null && (cursor.getCount() > 0)) {


                TotalStudentsFinal = Integer.parseInt(cursor.getString(TotalStudentsColumn));
                //Toast.makeText(this, "VariableAssigned!", Toast.LENGTH_SHORT).show();
                // Get the results and store them in a String
              //  TestET.setText(TotalStudentsFinal);
                //Variables


                // Keep getting results as long as they exist

            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR Occoured7!!", Toast.LENGTH_SHORT).show();
        }
        return TotalStudentsFinal;
    }

    public int TotalPlacedEXTCStudents() {
        int TotalStudentsFinal=0;
        try {
            Cursor cursor = StudentDb_newObject.rawQuery("SELECT COUNT (*) AS NoOfEXTCStudentsPlaced FROM Student_new WHERE PlacementDone='Yes'AND Branch='E.X.T.C';", null);
            //Toast.makeText(this, "Cursor Created!!", Toast.LENGTH_SHORT).show();


            int TotalStudentsColumn = cursor.getColumnIndex("NoOfEXTCStudentsPlaced");
            //Toast.makeText(this, "TotalStudents Found!!", Toast.LENGTH_SHORT).show();


            cursor.moveToFirst();
            //Toast.makeText(this, "Cursor Moved To First!!", Toast.LENGTH_SHORT).show();

            if (cursor != null && (cursor.getCount() > 0)) {


                TotalStudentsFinal = Integer.parseInt(cursor.getString(TotalStudentsColumn));
              //  Toast.makeText(this, "VariableAssigned!", Toast.LENGTH_SHORT).show();
                // Get the results and store them in a String
                //TestET.setText(TotalStudentsFinal);
                //Variables


                // Keep getting results as long as they exist

            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR Occoured8!!", Toast.LENGTH_SHORT).show();
        }
        return TotalStudentsFinal;
    }

    private void OpenChartIT(double pPlaced,double pUnplaced)
    {
        // Pie Chart Section Names
        String[] code = new String[] {
                "Placed IT Students", "Unplaced IT Students"
        };
// Pie Chart Section Value
        double[] distribution = { pPlaced, pUnplaced } ;

        // Color of each Pie Chart Sections
        int[] colors = {Color.GREEN, Color.RED };
        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Branch Placement Data");
        for(int i=0 ;i < distribution.length;i++){
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        DefaultRenderer defaultRenderer  = new DefaultRenderer();
        for(int i = 0 ;i<distribution.length;i++){
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);

            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setLegendTextSize(30);
        defaultRenderer.setChartTitle("Branch Placement Data");
        defaultRenderer.setChartTitleTextSize(30);
        defaultRenderer.setZoomButtonsVisible(true);

        Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries, defaultRenderer, "Branch Placement Details");

        // Start Activity
        startActivity(intent);






    }
    private void OpenChartComps(double pPlaced,double pUnplaced)
    {
        // Pie Chart Section Names
        String[] code = new String[] {
                "Placed Comps Students", "Unplaced Comps Students"
        };
// Pie Chart Section Value
        double[] distribution = { pPlaced, pUnplaced } ;

        // Color of each Pie Chart Sections
        int[] colors = {Color.GREEN, Color.RED };
        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Branch Placement Data");
        for(int i=0 ;i < distribution.length;i++){
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        DefaultRenderer defaultRenderer  = new DefaultRenderer();
        for(int i = 0 ;i<distribution.length;i++){
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);

            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setLegendTextSize(30);
        defaultRenderer.setChartTitle("Branch Placement Data");
        defaultRenderer.setChartTitleTextSize(30);
        defaultRenderer.setZoomButtonsVisible(true);

        Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries, defaultRenderer, "Branch Placement Details");

        // Start Activity
        startActivity(intent);






    }
    private void OpenChartEXTC(double pPlaced,double pUnplaced)
    {
        // Pie Chart Section Names
        String[] code = new String[] {
                "Placed EXTC Students", "Unplaced EXTC Students"
        };
// Pie Chart Section Value
        double[] distribution = { pPlaced, pUnplaced } ;

        // Color of each Pie Chart Sections
        int[] colors = {Color.GREEN, Color.RED };
        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Branch Placement Data");
        for(int i=0 ;i < distribution.length;i++){
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        DefaultRenderer defaultRenderer  = new DefaultRenderer();
        for(int i = 0 ;i<distribution.length;i++){
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);

            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setLegendTextSize(30);
        defaultRenderer.setChartTitle("Branch Placement Data");
        defaultRenderer.setChartTitleTextSize(30);
        defaultRenderer.setZoomButtonsVisible(true);

        Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries, defaultRenderer, "Branch Placement Details");

        // Start Activity
        startActivity(intent);






    }





    private void OpenChart(double pPlaced,double pUnplaced)
    {
        // Pie Chart Section Names
        String[] code = new String[] {
                "Overall Placed Students", "Overall Unplaced Students"
        };
// Pie Chart Section Value
        double[] distribution = { pPlaced, pUnplaced } ;

        // Color of each Pie Chart Sections
        int[] colors = {Color.GREEN, Color.RED };
        // Instantiating CategorySeries to plot Pie Chart
        CategorySeries distributionSeries = new CategorySeries("Branch Placement Data");
        for(int i=0 ;i < distribution.length;i++){
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        DefaultRenderer defaultRenderer  = new DefaultRenderer();
        for(int i = 0 ;i<distribution.length;i++){
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);

            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setLegendTextSize(30);
        defaultRenderer.setChartTitle("Branch Placement Data");
        defaultRenderer.setChartTitleTextSize(30);
        defaultRenderer.setZoomButtonsVisible(true);

        Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries, defaultRenderer, "Branch Placement Details");

        // Start Activity
        startActivity(intent);






    }



    public void GetItDataGraph(View view) {
        //OpenChart(pITplaced,pITnoPlace);
        int TotalStudents=TotalStudents();
        int TotalITStudents= TotalITStudents();
        int TotalComputerStudents=TotalComputerStudents();
        int TotalEXTCStudents=TotalEXTCStudents();
        int NoOfStudentsPlaced=TotalPlacedStudents();
        int NoOfITStudentsPlaced=TotalPlacedITStudents();
        int NoOfComputerStudentsPlaced=TotalPlacedComputerStudents();
        int NoOfEXTCStudentsPlaced=TotalPlacedEXTCStudents();


        double pOverallPlaced= ((double)NoOfStudentsPlaced/TotalStudents)*100;
        double pITplaced = ((double)NoOfITStudentsPlaced/TotalITStudents)*100;
        double pCOMPPlaced=((double)NoOfComputerStudentsPlaced/TotalComputerStudents)*100;
        double pExtcPlaced=((double)NoOfEXTCStudentsPlaced/TotalEXTCStudents)*100;
        double OverallNotPlaced=100-pOverallPlaced;
        double pITnoPlace=100-pITplaced;
        double pCOMPnoPlace=100-pCOMPPlaced;
        double pEXTCnoPlace=100-pExtcPlaced;
        Toast.makeText(this, "Placed % = "+pITplaced, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Unplaced % = "+pITnoPlace, Toast.LENGTH_SHORT).show();
        OpenChartIT(pITplaced,pITnoPlace);
    }

    public void GetComputerDataGraph(View view) {
        //OpenChart(pITplaced,pITnoPlace);
        int TotalStudents=TotalStudents();
        int TotalITStudents= TotalITStudents();
        int TotalComputerStudents=TotalComputerStudents();
        int TotalEXTCStudents=TotalEXTCStudents();
        int NoOfStudentsPlaced=TotalPlacedStudents();
        int NoOfITStudentsPlaced=TotalPlacedITStudents();
        int NoOfComputerStudentsPlaced=TotalPlacedComputerStudents();
        int NoOfEXTCStudentsPlaced=TotalPlacedEXTCStudents();


        double pOverallPlaced= ((double)NoOfStudentsPlaced/TotalStudents)*100;
        double pITplaced = ((double)NoOfITStudentsPlaced/TotalITStudents)*100;
        double pCOMPPlaced=((double)NoOfComputerStudentsPlaced/TotalComputerStudents)*100;
        double pExtcPlaced=((double)NoOfEXTCStudentsPlaced/TotalEXTCStudents)*100;
        double OverallNotPlaced=100-pOverallPlaced;
        double pITnoPlace=100-pITplaced;
        double pCOMPnoPlace=100-pCOMPPlaced;
        double pEXTCnoPlace=100-pExtcPlaced;
        Toast.makeText(this, "Placed % = "+pCOMPPlaced, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Unplaced % = "+pCOMPnoPlace, Toast.LENGTH_SHORT).show();
        OpenChartComps(pCOMPPlaced,pCOMPnoPlace);
    }

    public void GetEXTCDataGraph(View view) {
        //OpenChart(pITplaced,pITnoPlace);
        int TotalStudents=TotalStudents();
        int TotalITStudents= TotalITStudents();
        int TotalComputerStudents=TotalComputerStudents();
        int TotalEXTCStudents=TotalEXTCStudents();
        int NoOfStudentsPlaced=TotalPlacedStudents();
        int NoOfITStudentsPlaced=TotalPlacedITStudents();
        int NoOfComputerStudentsPlaced=TotalPlacedComputerStudents();
        int NoOfEXTCStudentsPlaced=TotalPlacedEXTCStudents();


        double pOverallPlaced= ((double)NoOfStudentsPlaced/TotalStudents)*100;
        double pITplaced = ((double)NoOfITStudentsPlaced/TotalITStudents)*100;
        double pCOMPPlaced=((double)NoOfComputerStudentsPlaced/TotalComputerStudents)*100;
        double pExtcPlaced=((double)NoOfEXTCStudentsPlaced/TotalEXTCStudents)*100;
        double OverallNotPlaced=100-pOverallPlaced;
        double pITnoPlace=100-pITplaced;
        double pCOMPnoPlace=100-pCOMPPlaced;
        double pEXTCnoPlace=100-pExtcPlaced;
        Toast.makeText(this, "Placed % = "+pExtcPlaced, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Unplaced % = "+pEXTCnoPlace, Toast.LENGTH_SHORT).show();
        OpenChartEXTC(pExtcPlaced,pEXTCnoPlace);
    }
    public void GetOverallDataGraph(View view) {
        //OpenChart(pITplaced,pITnoPlace);
        int TotalStudents=TotalStudents();
        int TotalITStudents= TotalITStudents();
        int TotalComputerStudents=TotalComputerStudents();
        int TotalEXTCStudents=TotalEXTCStudents();
        int NoOfStudentsPlaced=TotalPlacedStudents();
        int NoOfITStudentsPlaced=TotalPlacedITStudents();
        int NoOfComputerStudentsPlaced=TotalPlacedComputerStudents();
        int NoOfEXTCStudentsPlaced=TotalPlacedEXTCStudents();


        double pOverallPlaced= ((double)NoOfStudentsPlaced/TotalStudents)*100;
        double pITplaced = ((double)NoOfITStudentsPlaced/TotalITStudents)*100;
        double pCOMPPlaced=((double)NoOfComputerStudentsPlaced/TotalComputerStudents)*100;
        double pExtcPlaced=((double)NoOfEXTCStudentsPlaced/TotalEXTCStudents)*100;
        double pOverallNotPlaced=100-pOverallPlaced;
        double pITnoPlace=100-pITplaced;
        double pCOMPnoPlace=100-pCOMPPlaced;
        double pEXTCnoPlace=100-pExtcPlaced;
        Toast.makeText(this, "Placed % = "+pOverallPlaced, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Unplaced % = "+pOverallNotPlaced, Toast.LENGTH_LONG).show();
        OpenChart(pOverallPlaced,pOverallNotPlaced);
    }





}

