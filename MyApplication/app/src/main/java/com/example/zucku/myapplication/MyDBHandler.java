package com.example.zucku.myapplication;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {

    /**
     * Used a static variable because of how it can be easily used between all the classes.
     */
    public static MyDBHandler handler;
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME_LITE = "team_database";
    public static final String DATABASE_NAME = DATABASE_NAME_LITE + ".db";
    public static final String TEAMS_TABLE_NAME = "teams";
    public static final String TEAMS_COLUMN_ID = "id";
    public static final String TEAMS_COLUMN_MATCH_NUM = "match_id";
    public static final String TEAMS_COLUMN_NAME = "team_name_id";
    public static final String TEAMS_COLUMN_POINTS = "team_points";
    public static final String TEAMS_COLUMN_POSITION = "position";
    public static final String TEAMS_COLUMN_WIN_LOSS = "win_loss";
    public static final String TEAMS_COLUMN_RANKING_POINTS = "ranking_points";
    public static final String TEAMS_COLUMN_AUTO_POINTS = "auto_points";
    public static final String TEAMS_COLUMN_LOW_BAR_DEFENSE = "low_bar";
    public static final String TEAMS_COLUMN_PORTCULLIS_DEFENSE = "portcullis";
    public static final String TEAMS_COLUMN_CHEVAL_DE_FRISE_DEFENSE = "cheval_de_frise";
    public static final String TEAMS_COLUMN_MOAT_DEFENSE = "moat";
    public static final String TEAMS_COLUMN_RAMPARTS_DEFENSE = "ramparts";
    public static final String TEAMS_COLUMN_DRAWBRIDGE_DEFENSE = "drawbridge";
    public static final String TEAMS_COLUMN_SALLYPORT_DEFENSE = "sallyport";
    public static final String TEAMS_COLUMN_ROCKWALL_DEFENSE = "rockwall";
    public static final String TEAMS_COLUMN_ROUGH_TERRAIN_DEFENSE = "rough_terrain";
    public static final String TEAMS_COLUMN_SHOOTER_TYPE = "shooter_type";
    public static final String TEAMS_COLUMN_SHOTS_MADE = "shots_made";
    public static final String TEAMS_COLUMN_SHOTS_TAKEN = "shots_taken";
    public static final String TEAMS_COLUMN_DEFENSE_OR_OFFENSE = "defense_or_offense";
    public static final String TEAMS_COLUMN_END_GAME = "end_game";
    public static final String TEAMS_COLUMN_PENALTIES = "penalties";
    public static final String TEAMS_COLUMN_SPEED_STABILITY_NOTES = "speed_stability_notes";
    public static final String TEAMS_COLUMN_DRIVER_SKILL = "driver_skill";

    /**
     * created
     * @param context
     */
    public MyDBHandler(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        Log.i("ME", "Creating DB Handler");

        Log.i("ME", "Trying to create object");
        try{
            onCreate(this.getReadableDatabase());
            Log.i("ME", "Created " + DATABASE_NAME);
        }catch(RuntimeException e) {
            Log.i("ME", DATABASE_NAME + " already exists");
        }
        Log.i("ME", "Finished Init of MyDBHandler Object");

        handler = this;
    }

    /**
     * create database with sql command statement
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("ME", "Created Table Start");
        try {
            db.execSQL(
                    "create table " + TEAMS_TABLE_NAME + " " +
                            "(" +
                            TEAMS_COLUMN_ID + " integer primary key autoincrement, " +
                            TEAMS_COLUMN_MATCH_NUM + " text, " +
                            TEAMS_COLUMN_NAME + " text, " +
                            TEAMS_COLUMN_POINTS + " text, " +
                            TEAMS_COLUMN_WIN_LOSS + " text, " +
                            TEAMS_COLUMN_RANKING_POINTS + " text, " +
                            TEAMS_COLUMN_AUTO_POINTS + " text, " +
                            TEAMS_COLUMN_LOW_BAR_DEFENSE + " text, " +
                            TEAMS_COLUMN_PORTCULLIS_DEFENSE + " text, " +
                            TEAMS_COLUMN_CHEVAL_DE_FRISE_DEFENSE + " text, " +
                            TEAMS_COLUMN_MOAT_DEFENSE + " text, " +
                            TEAMS_COLUMN_RAMPARTS_DEFENSE + " text, " +
                            TEAMS_COLUMN_DRAWBRIDGE_DEFENSE + " text, " +
                            TEAMS_COLUMN_SALLYPORT_DEFENSE + " text, " +
                            TEAMS_COLUMN_ROCKWALL_DEFENSE + " text, " +
                            TEAMS_COLUMN_ROUGH_TERRAIN_DEFENSE + " text, " +
                            TEAMS_COLUMN_SHOOTER_TYPE + " text, " +
                            TEAMS_COLUMN_POSITION + " text, " +
                            TEAMS_COLUMN_SHOTS_MADE + " text, " +
                            TEAMS_COLUMN_SHOTS_TAKEN + " text, " +
                            TEAMS_COLUMN_DEFENSE_OR_OFFENSE + " text, " +
                            TEAMS_COLUMN_END_GAME + " text, " +
                            TEAMS_COLUMN_PENALTIES + " text, " +
                            TEAMS_COLUMN_SPEED_STABILITY_NOTES + " text, " +
                            TEAMS_COLUMN_DRIVER_SKILL + " text" +
                            ")"
            );
        }catch(SQLiteException e){
            Log.i("ME", e.toString());
        }

        Log.i("ME", "Created Table End");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        Log.i("ME", "Upgrading From: " + oldVersion + " To: " + newVersion);
        Log.i("ME", db.toString());
        Log.i("ME", db.getPath());
        db.execSQL("DROP TABLE " + TEAMS_TABLE_NAME);
        Log.i("ME", "Creating a new Database");
        onCreate(db);
    }

    public boolean insertTeam(String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8, String col9, String col10, String col11, String col12, String col13, String col14, String col15, String  col16, String col17, String col18, String col19, String col20, String col21, String col22, String col23, String col24)
    {
        Log.i("ME", "Getter for a Writable Database");

        SQLiteDatabase db = this.getWritableDatabase();

        Log.i("ME", "Creating Team");

        ContentValues contentValues = new ContentValues();
        contentValues.put(TEAMS_COLUMN_MATCH_NUM, col1);
        contentValues.put(TEAMS_COLUMN_NAME, col2);
        contentValues.put(TEAMS_COLUMN_POINTS, col3);
        contentValues.put(TEAMS_COLUMN_WIN_LOSS, col4);
        contentValues.put(TEAMS_COLUMN_RANKING_POINTS, col5);
        contentValues.put(TEAMS_COLUMN_AUTO_POINTS, col6);
        contentValues.put(TEAMS_COLUMN_LOW_BAR_DEFENSE, col7);
        contentValues.put(TEAMS_COLUMN_PORTCULLIS_DEFENSE, col8);
        contentValues.put(TEAMS_COLUMN_CHEVAL_DE_FRISE_DEFENSE, col9);
        contentValues.put(TEAMS_COLUMN_MOAT_DEFENSE, col10);
        contentValues.put(TEAMS_COLUMN_RAMPARTS_DEFENSE, col11);
        contentValues.put(TEAMS_COLUMN_DRAWBRIDGE_DEFENSE, col12);
        contentValues.put(TEAMS_COLUMN_SALLYPORT_DEFENSE, col13);
        contentValues.put(TEAMS_COLUMN_ROCKWALL_DEFENSE, col14);
        contentValues.put(TEAMS_COLUMN_ROUGH_TERRAIN_DEFENSE, col15);
        contentValues.put(TEAMS_COLUMN_SHOOTER_TYPE, col16);
        contentValues.put(TEAMS_COLUMN_POSITION, col17);
        contentValues.put(TEAMS_COLUMN_SHOTS_MADE, col18);
        contentValues.put(TEAMS_COLUMN_SHOTS_TAKEN, col19);
        contentValues.put(TEAMS_COLUMN_DEFENSE_OR_OFFENSE, col20);
        contentValues.put(TEAMS_COLUMN_END_GAME, col21);
        contentValues.put(TEAMS_COLUMN_PENALTIES, col22);
        contentValues.put(TEAMS_COLUMN_SPEED_STABILITY_NOTES, col23);
        contentValues.put(TEAMS_COLUMN_DRIVER_SKILL, col24);

        Log.i("ME", "Inserted ContentValue into Database");

        db.insert(TEAMS_TABLE_NAME, null, contentValues);
        Log.i("ME", "Inserted Element: " + col1);
        return true;
    }

    public Cursor getData(int id){

        Log.i("ME", "Getting readable database");

        SQLiteDatabase db = this.getReadableDatabase();

        Log.i("ME", "Creating cursor query");

        Cursor res =  db.rawQuery( "select * from " + TEAMS_TABLE_NAME + " where " + TEAMS_COLUMN_ID + "="+id+"", null );

        Log.i("ME", "Returning query");
        return res;
    }

    public int numberOfRows(){

        Log.i("ME", "Getting readable database");

        SQLiteDatabase db = this.getReadableDatabase();

        Log.i("ME", "Returning number of rows");

        int numRows = (int) DatabaseUtils.queryNumEntries(db, TEAMS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8, String col9, String col10, String col11, String col12, String col13, String col14, String col15, String  col16, String col17, String col18, String col19, String col20, String col21, String col22, String col23, String col24) {
        Log.i("ME", "Getting writable database");

        SQLiteDatabase db = this.getWritableDatabase();

        Log.i("ME", "Init ContentValues Object");

        ContentValues contentValues = new ContentValues();

        contentValues.put(TEAMS_COLUMN_MATCH_NUM, col1);
        contentValues.put(TEAMS_COLUMN_NAME, col2);
        contentValues.put(TEAMS_COLUMN_POINTS, col3);
        contentValues.put(TEAMS_COLUMN_WIN_LOSS, col4);
        contentValues.put(TEAMS_COLUMN_RANKING_POINTS, col5);
        contentValues.put(TEAMS_COLUMN_AUTO_POINTS, col6);
        contentValues.put(TEAMS_COLUMN_LOW_BAR_DEFENSE, col7);
        contentValues.put(TEAMS_COLUMN_PORTCULLIS_DEFENSE, col8);
        contentValues.put(TEAMS_COLUMN_CHEVAL_DE_FRISE_DEFENSE, col9);
        contentValues.put(TEAMS_COLUMN_MOAT_DEFENSE, col10);
        contentValues.put(TEAMS_COLUMN_RAMPARTS_DEFENSE, col11);
        contentValues.put(TEAMS_COLUMN_DRAWBRIDGE_DEFENSE, col12);
        contentValues.put(TEAMS_COLUMN_SALLYPORT_DEFENSE, col13);
        contentValues.put(TEAMS_COLUMN_ROCKWALL_DEFENSE, col14);
        contentValues.put(TEAMS_COLUMN_ROUGH_TERRAIN_DEFENSE, col15);
        contentValues.put(TEAMS_COLUMN_SHOOTER_TYPE, col16);
        contentValues.put(TEAMS_COLUMN_POSITION, col17);
        contentValues.put(TEAMS_COLUMN_SHOTS_MADE, col18);
        contentValues.put(TEAMS_COLUMN_SHOTS_TAKEN, col19);
        contentValues.put(TEAMS_COLUMN_DEFENSE_OR_OFFENSE, col20);
        contentValues.put(TEAMS_COLUMN_END_GAME, col21);
        contentValues.put(TEAMS_COLUMN_PENALTIES, col22);
        contentValues.put(TEAMS_COLUMN_SPEED_STABILITY_NOTES, col23);
        contentValues.put(TEAMS_COLUMN_DRIVER_SKILL, col24);

        db.update(TEAMS_TABLE_NAME, contentValues, TEAMS_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});

        Log.i("ME", "Updated Element: " + col1 + " " + col2 + " " + col3);

        return true;
    }

    public Integer deleteContact (Integer id)
    {
        Log.i("ME", "Getting Writable Database");

        SQLiteDatabase db = this.getWritableDatabase();

        Log.i("ME", "Returning through running deleting the id at the current location");

        int delete = db.delete(TEAMS_TABLE_NAME,
                TEAMS_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });

        return delete;
    }

    public ArrayList<String> getAllTeams()
    {
        Log.i("ME", "Getting all teams");

        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TEAMS_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add("Match ID: " + res.getString(res.getColumnIndex(TEAMS_COLUMN_MATCH_NUM))+ " Team Name:  " + res.getString(res.getColumnIndex(TEAMS_COLUMN_NAME)));
            Log.i("ME", "Got team: " + array_list.get(array_list.size() - 1));
            res.moveToNext();
        }

        Log.i("ME", "Finished getting all teams");

        return array_list;
    }
}