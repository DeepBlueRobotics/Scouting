package com.example.zucku.myapplication;

import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * All the logic in this file goes into creating new teams, looking at previously created teams, and
 * finally uploading the info to a database (.csv) file.
 */
public class MainActivity extends AppCompatActivity {
    private ListView obj;
    private EditText fileName;
    Button refresh, export, delete;
    ArrayList array_list;
    ArrayAdapter arrayAdapter;
    AppCompatActivity x;

    /**
     * created on the creation of the app and initialize stuff.
     * @param savedInstanceState required
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyDBHandler handler = new MyDBHandler(this);

        array_list = MyDBHandler.handler.getAllTeams();

        Log.i("ME", "Set ArrayList");

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        Log.i("ME", "set array adapter");

        refresh = (Button) findViewById(R.id.refresh);

        Log.i("ME", "Init Button Refresh");

        export = (Button) findViewById(R.id.export);
        fileName = (EditText) findViewById(R.id.export_file_name);

        delete = (Button) findViewById(R.id.delete);

        x = this;

        // if delete button pressed
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ME", "Pressed Delete Database Button");
                Log.i("ME", "Deleting?");

                Log.i("ME", "Created Alert Dialog");
                new AlertDialog.Builder(x)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to delete this database?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                SQLiteDatabase sq = MyDBHandler.handler.getReadableDatabase();
                                MyDBHandler.handler.onUpgrade(sq, 1, 1);
                                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                Log.i("ME", "Finished Dialog");

                Log.i("ME", "Upgraded?");
            }
        });

        Log.i("ME", "Init Button Export");

        // if refresh button clicked
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ME", "Refresh Clicked");
                arrayAdapter.clear();
                Log.i("ME", "Cleared array adapter");
                arrayAdapter.addAll(MyDBHandler.handler.getAllTeams());
                Log.i("ME", "Added all teams to adapter");
            }
        });

        // if export button pressed
        export.setOnClickListener(new View.OnClickListener() {
            SQLiteDatabase sqldb = handler.getReadableDatabase(); //My Database class
            Cursor c = null;

            @Override
            public void onClick(View v) { //main code begins here
                try {
                    c = sqldb.rawQuery("select * from " + MyDBHandler.TEAMS_TABLE_NAME, null);
                    Log.i("ME", "Started Query");
                    int rowcount = 0;
                    int colcount = 0;
                    File sdCardDir = Environment.getExternalStorageDirectory();
                    Log.i("ME", "Directory: " + sdCardDir.toString());
                    String name = fileName.getText().toString();
                    String filename = "";
                    int z = new Random().nextInt();
                    if(name.contains(" ")||name.equals("")){
                        filename = "team_database" + z + ".csv";
                    }else{
                        filename = name + z + ".csv";
                    }
                    File saveFile = new File(sdCardDir, filename);
                    Log.i("ME", "Directory: " + saveFile.toString());
                    FileWriter fw = new FileWriter(saveFile);
                    BufferedWriter bw = new BufferedWriter(fw);
                    Log.i("ME", "FileWriter " + fw.toString());
                    Log.i("ME", "BufferedWriter: " + bw.toString());
                    rowcount = c.getCount();
                    colcount = c.getColumnCount();
                    if (rowcount > 0) {
                        c.moveToFirst();
                        for (int i = 0; i < colcount; i++) {
                            if (i != colcount - 1) {
                                bw.write(c.getColumnName(i) + ",");
                            } else {
                                bw.write(c.getColumnName(i));
                            }
                            Log.i("ME", "CSV Writing " + c.getColumnName(i) + " header");
                        }
                        bw.newLine();
                        for (int i = 0; i < rowcount; i++) {
                            c.moveToPosition(i);
                            for (int j = 0; j < colcount; j++) {
                                if (j != colcount - 1)
                                    bw.write(c.getString(j) + ",");
                                else
                                    bw.write(c.getString(j));
                            }
                            bw.newLine();
                        }
                        bw.flush();
                        Log.i("ME", "Done?");
                        fileName.setText("Exported Successfully.");
                    }
                } catch (Exception ex) {
                    if (sqldb.isOpen()) {
                        sqldb.close();
                        fileName.setText(ex.getMessage().toString());
                    }
                    Log.i("ME", "Error " + ex.toString());
                } finally {
                    Log.i("ME", "Finished");
                }
            }
        });

        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);

        // if object pressed
        obj.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Log.i("ME", "Item clicked");
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                Log.i("ME", "Starting Intent DisplayTeam (EDIT)" + " " + (arg2 + 1));
                Intent intent = new Intent(getApplicationContext(), DisplayTeam.class);
                intent.putExtras(dataBundle);
                Log.i("ME", "Running Intent");
                startActivity(intent);

            }
        });

        Log.i("ME", "Set List Item Listener");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("ME", "Added menu to add teams.");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        Log.i("ME", "Init Listener if Menu to add teams is pressed");

        switch(item.getItemId())
        {
            case R.id.item1:Bundle dataBundle = new Bundle();
                Log.i("ME", "Running Add another team");
                dataBundle.putInt("id", 0);

                Log.i("ME", "Running Intent DisplayTeam (ADDING)");
                Intent intent = new Intent(getApplicationContext(),DisplayTeam.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        Log.i("ME", "On Key Down Methods");
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

}