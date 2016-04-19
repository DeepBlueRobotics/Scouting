package com.example.zucku.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
* This program is where the user enters all the data.
*/
public class DisplayTeam extends Activity {
    TextView match_number;
    TextView team_id;
    TextView alliance_points;
    int id_To_Update = 0;

    int rankingPts = 0, lowbar = 0, portcullis = 0, cheval = 0, moat = 0, ramparts = 0, drawbridge = 0, sallyport = 0, rockwall = 0, rough_terrain = 0, shots_made = 0, shots_taken = 0;

    Spinner win_loss, shooter_type, position, defense_offense, end_game, driver_skill;
    Button decRankingPts, incRankingPts;
    Button decLowbar, incLowbar;
    Button decPortcullis, incPortcullis;
    Button decCheval, incCheval;
    Button decMoat, incMoat;
    Button decRamparts, incRamparts;
    Button decDrawbridge, incDrawbridge;
    Button decSallyport, incSallyport;
    Button decRockwall, incRockwall;
    Button decRoughTerrain, incRoughTerrain;
    Button decShotsMade, incShotsMade;
    Button decShotsTaken, incShotsTaken;

    TextView rankingPtsText, lowbarText, portcullisText, chevalText, moatText, rampartsText, drawbridgeText, sallyportText, rockwallText, rough_terrainText, shots_madeText, shots_takenText;

    EditText auto_points;
    EditText penalties;
    EditText speed_stability_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_team);

        match_number = (TextView) findViewById(R.id.match_number);
        team_id = (TextView) findViewById(R.id.team_id);
        alliance_points = (TextView) findViewById(R.id.points_alliance);

        win_loss = (Spinner) findViewById(R.id.win_loss);
        win_loss.setSelection(0);
        shooter_type = (Spinner) findViewById(R.id.shooter_type);
        shooter_type.setSelection(0);
        position = (Spinner) findViewById(R.id.position_spinner);
        position.setSelection(0);
        defense_offense = (Spinner) findViewById(R.id.defense_offense);
        defense_offense.setSelection(0);
        end_game = (Spinner) findViewById(R.id.end_game);
        end_game.setSelection(0);
        driver_skill = (Spinner) findViewById(R.id.driver_skill);
        driver_skill.setSelection(0);

        decRankingPts = (Button) findViewById(R.id.dec_ranking_points);
        incRankingPts = (Button) findViewById(R.id.inc_ranking_points);
        decLowbar = (Button) findViewById(R.id.dec_lowbar);
        incLowbar = (Button) findViewById(R.id.inc_lowbar);
        decPortcullis = (Button) findViewById(R.id.dec_portcullis);
        incPortcullis = (Button) findViewById(R.id.inc_portcullis);
        decCheval = (Button) findViewById(R.id.dec_cheval_de_frise);
        incCheval = (Button) findViewById(R.id.inc_cheval_de_frise);
        decMoat = (Button) findViewById(R.id.dec_moat);
        incMoat = (Button) findViewById(R.id.inc_moat);
        decRamparts = (Button) findViewById(R.id.dec_ramparts);
        incRamparts = (Button) findViewById(R.id.inc_ramparts);
        decDrawbridge = (Button) findViewById(R.id.dec_drawbridge);
        incDrawbridge = (Button) findViewById(R.id.inc_drawbridge);
        decSallyport = (Button) findViewById(R.id.dec_sallyport);
        incSallyport = (Button) findViewById(R.id.inc_sallyport);
        decRockwall = (Button) findViewById(R.id.dec_rockwall);
        incRockwall = (Button) findViewById(R.id.inc_rockwall);
        decRoughTerrain = (Button) findViewById(R.id.dec_rough_terrain);
        incRoughTerrain = (Button) findViewById(R.id.inc_rough_terrain);
        decShotsMade = (Button) findViewById(R.id.dec_shots_made);
        incShotsMade = (Button) findViewById(R.id.inc_shots_made);
        decShotsTaken = (Button) findViewById(R.id.dec_shots_taken);
        incShotsTaken = (Button) findViewById(R.id.inc_shots_taken);

        rankingPtsText = (TextView) findViewById(R.id.ranking_points);
        lowbarText = (TextView) findViewById(R.id.lowbar);
        portcullisText = (TextView) findViewById(R.id.portcullis);
        chevalText = (TextView) findViewById(R.id.cheval_de_frise);
        moatText = (TextView) findViewById(R.id.moat);
        rampartsText = (TextView) findViewById(R.id.ramparts);
        drawbridgeText = (TextView) findViewById(R.id.drawbridge);
        sallyportText = (TextView) findViewById(R.id.sallyport);
        rockwallText = (TextView) findViewById(R.id.rockwall);
        rough_terrainText = (TextView) findViewById(R.id.rough_terrain);
        shots_madeText = (TextView) findViewById(R.id.shots_made);
        shots_takenText = (TextView) findViewById(R.id.shots_taken);

        auto_points = (EditText) findViewById(R.id.auto_points_input);
        penalties = (EditText) findViewById(R.id.penalties);
        speed_stability_notes = (EditText) findViewById(R.id.speed_stability_notes);

        decRankingPts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rankingPts>0) {
                    rankingPts--;
                    rankingPtsText.setText((CharSequence) (rankingPts + ""));
                }
            }
        });
        incRankingPts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankingPts++;
                rankingPtsText.setText((CharSequence)(rankingPts + ""));
            }
        });

        decLowbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lowbar>0) {
                    lowbar--;
                    lowbarText.setText((CharSequence) (lowbar + ""));
                }
            }
        });
        incLowbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowbar++;
                lowbarText.setText((CharSequence)(lowbar + ""));
            }
        });

        decPortcullis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(portcullis>0) {
                    portcullis--;
                    portcullisText.setText((CharSequence) (portcullis + ""));
                }
            }
        });

        incPortcullis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                portcullis++;
                portcullisText.setText((CharSequence)(portcullis + ""));
            }
        });

        decCheval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cheval>0) {
                    cheval--;
                    chevalText.setText((CharSequence) (cheval + ""));
                }
            }
        });

        incCheval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheval++;
                chevalText.setText((CharSequence)(cheval + ""));
            }
        });

        decMoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moat>0) {
                    moat--;
                    moatText.setText((CharSequence) (moat + ""));
                }
            }
        });

        incMoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moat++;
                moatText.setText((CharSequence) (moat + ""));
            }
        });

        decRamparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ramparts>0) {
                    ramparts--;
                    rampartsText.setText((CharSequence) (ramparts + ""));
                }
            }
        });

        incRamparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ramparts++;
                rampartsText.setText((CharSequence)(ramparts + ""));
            }
        });

        decDrawbridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawbridge>0) {
                    drawbridge--;
                    drawbridgeText.setText((CharSequence) (drawbridge + ""));
                }
            }
        });

        incDrawbridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawbridge++;
                drawbridgeText.setText((CharSequence)(drawbridge + ""));
            }
        });

        decSallyport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sallyport>0) {
                    sallyport--;
                    sallyportText.setText((CharSequence) (sallyport + ""));
                }
            }
        });

        incSallyport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sallyport++;
                sallyportText.setText((CharSequence)(sallyport + ""));
            }
        });

        decRockwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rockwall>0) {
                    rockwall--;
                    rockwallText.setText((CharSequence) (rockwall + ""));
                }
            }
        });

        incRockwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rockwall++;
                rockwallText.setText((CharSequence)(rockwall + ""));
            }
        });

        decRoughTerrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rough_terrain>0) {
                    rough_terrain--;
                    rough_terrainText.setText((CharSequence) (rough_terrain + ""));
                }
            }
        });

        incRoughTerrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rough_terrain++;
                rough_terrainText.setText((CharSequence)(rough_terrain + ""));
            }
        });

        decShotsMade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shots_made>0) {
                    shots_made--;
                    shots_madeText.setText((CharSequence) (shots_made + ""));
                }
            }
        });

        incShotsMade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shots_taken++;
                shots_takenText.setText((CharSequence) (shots_taken + ""));
                shots_made++;
                shots_madeText.setText((CharSequence) (shots_made + ""));
            }
        });

        decShotsTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shots_taken>0) {
                    shots_taken--;
                    shots_takenText.setText((CharSequence) (shots_taken + ""));
                }
            }
        });

        incShotsTaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shots_taken++;
                shots_takenText.setText((CharSequence) (shots_taken + ""));
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            int Value = extras.getInt("id");

            if(Value>0){
                Cursor rs = MyDBHandler.handler.getData(Value);
                Log.i("ME", "Got Data");
                id_To_Update = Value;
                rs.moveToFirst();

                // Strings for Textview
                String match = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_MATCH_NUM));
                String id = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_NAME));
                String points = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_POINTS));
                String autoPts = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_AUTO_POINTS));
                String penalty = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_PENALTIES));
                String speedNotes = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_SPEED_STABILITY_NOTES));

                // Strings for Spinners
                String winloss = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_WIN_LOSS));
                String shootertype = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_SHOOTER_TYPE));
                String pos = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_POSITION));
                String defoff = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_DEFENSE_OR_OFFENSE));
                String endgame = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_END_GAME));
                String driverskill = rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_DRIVER_SKILL));

                for(int i = 0; i < rs.getColumnCount(); i++){
                    Log.i("ME", rs.getColumnName(i) + " " + rs.getString(i));
                }

                win_loss.setSelection(Integer.parseInt(winloss.substring(winloss.length() - 2, winloss.length() - 1)));
                win_loss.setClickable(true);
                win_loss.setFocusable(true);

                shooter_type.setSelection(Integer.parseInt(shootertype.substring(shootertype.length() - 2, shootertype.length() - 1)));
                shooter_type.setClickable(true);
                shooter_type.setFocusable(true);

                position.setSelection(Integer.parseInt(pos.substring(pos.length() - 2, pos.length() - 1)));
                position.setClickable(true);
                position.setFocusable(true);

                defense_offense.setSelection(Integer.parseInt(defoff.substring(defoff.length() - 2, defoff.length() - 1)));
                defense_offense.setClickable(true);
                defense_offense.setFocusable(true);

                end_game.setSelection(Integer.parseInt(endgame.substring(endgame.length() - 2, endgame.length() - 1)));
                end_game.setClickable(true);
                end_game.setFocusable(true);

                driver_skill.setSelection(Integer.parseInt(driverskill.substring(driverskill.length() - 2, driverskill.length() - 1)));
                driver_skill.setClickable(true);
                driver_skill.setFocusable(true);

                try {
                    rankingPts = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_RANKING_POINTS)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                rankingPtsText.setText((CharSequence) (rankingPts + ""));
                try{
                    lowbar = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_LOW_BAR_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                lowbarText.setText((CharSequence) (lowbar + ""));
                try {
                    portcullis = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_PORTCULLIS_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                portcullisText.setText((CharSequence) (portcullis + ""));
                try {
                    cheval = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_CHEVAL_DE_FRISE_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                chevalText.setText((CharSequence) (cheval + ""));
                try {
                    moat = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_MOAT_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                moatText.setText((CharSequence) (moat + ""));
                try {
                    ramparts = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_RAMPARTS_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                rampartsText.setText((CharSequence) (ramparts + ""));
                try {
                    drawbridge = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_DRAWBRIDGE_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                drawbridgeText.setText((CharSequence) (drawbridge + ""));
                try {
                    sallyport = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_SALLYPORT_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                sallyportText.setText((CharSequence) (sallyport + ""));
                try {
                    rockwall = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_ROCKWALL_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                rockwallText.setText((CharSequence) (rockwall + ""));
                try {
                    rough_terrain = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_ROUGH_TERRAIN_DEFENSE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                rough_terrainText.setText((CharSequence) (rough_terrain + ""));
                try {
                    shots_made = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_SHOTS_MADE)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                shots_madeText.setText((CharSequence) (shots_made + ""));
                try {
                    shots_taken = Integer.parseInt(rs.getString(rs.getColumnIndex(MyDBHandler.TEAMS_COLUMN_SHOTS_TAKEN)));
                }catch(NumberFormatException e){
                    Log.i("ME", "Error:" + e);
                }
                shots_takenText.setText((CharSequence) (shots_taken + ""));

                if (!rs.isClosed())
                {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);

                match_number.setText((CharSequence) match);
                match_number.setFocusable(true);
                match_number.setClickable(true);

                team_id.setText((CharSequence) id);
                team_id.setFocusable(true);
                team_id.setClickable(true);

                alliance_points.setText((CharSequence) points);
                alliance_points.setFocusable(true);
                alliance_points.setClickable(true);

                auto_points.setText((CharSequence) autoPts);
                auto_points.setFocusable(true);
                auto_points.setClickable(true);

                penalties.setText((CharSequence) penalty);
                penalties.setFocusable(true);
                penalties.setClickable(true);

                speed_stability_notes.setText((CharSequence) speedNotes);
                speed_stability_notes.setFocusable(true);
                speed_stability_notes.setClickable(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Bundle extras = getIntent().getExtras();

        if(extras !=null)
        {
            int Value = extras.getInt(MyDBHandler.TEAMS_COLUMN_ID);
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_team, menu);
            }

            else{
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.Edit_Contact:
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                match_number.setEnabled(true);
                match_number.setFocusableInTouchMode(true);
                match_number.setClickable(true);

                team_id.setEnabled(true);
                team_id.setFocusableInTouchMode(true);
                team_id.setClickable(true);

                alliance_points.setEnabled(true);
                alliance_points.setFocusableInTouchMode(true);
                alliance_points.setClickable(true);

                auto_points.setEnabled(true);
                auto_points.setFocusable(true);
                auto_points.setClickable(true);

                penalties.setEnabled(true);
                penalties.setFocusable(true);
                penalties.setClickable(true);

                speed_stability_notes.setEnabled(true);
                speed_stability_notes.setFocusable(true);
                speed_stability_notes.setClickable(true);

                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MyDBHandler.handler.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view)
    {
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                Log.i("ME", match_number.getText().toString() + " " + team_id.getText().toString() + " " + alliance_points.getText().toString());
                if(MyDBHandler.handler.updateContact(
                        id_To_Update,
                        match_number.getText().toString(),
                        team_id.getText().toString(),
                        alliance_points.getText().toString(),
                        win_loss.getSelectedItem().toString(),
                        rankingPts+"",
                        auto_points.getText().toString(),
                        lowbar+"",
                        portcullis+"",
                        cheval+"",
                        moat+"",
                        ramparts+"",
                        drawbridge+"",
                        sallyport+"",
                        rockwall+"",
                        rough_terrain+"",
                        shooter_type.getSelectedItem().toString(),
                        position.getSelectedItem().toString(),
                        shots_made+"",
                        shots_taken+"",
                        defense_offense.getSelectedItem().toString(),
                        end_game.getSelectedItem().toString(),
                        penalties.getText().toString(),
                        speed_stability_notes.getText().toString(),
                        driver_skill.getSelectedItem().toString()
                        )){
                    Log.i("ME", "Updating");
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(MyDBHandler.handler.insertTeam(
                        match_number.getText().toString(),
                        team_id.getText().toString(),
                        alliance_points.getText().toString(),
                        win_loss.getSelectedItem().toString(),
                        rankingPts+"",
                        auto_points.getText().toString(),
                        lowbar+"",
                        portcullis+"",
                        cheval+"",
                        moat+"",
                        ramparts+"",
                        drawbridge+"",
                        sallyport+"",
                        rockwall+"",
                        rough_terrain+"",
                        shooter_type.getSelectedItem().toString(),
                        position.getSelectedItem().toString(),
                        shots_made+"",
                        shots_taken+"",
                        defense_offense.getSelectedItem().toString(),
                        end_game.getSelectedItem().toString(),
                        penalties.getText().toString(),
                        speed_stability_notes.getText().toString(),
                        driver_skill.getSelectedItem().toString()
                )){
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                Log.i("ME", "Method named run");
            }
        }
    }

    public void delete(View view){
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                MyDBHandler.handler.deleteContact(Value);
            } else {
                Log.i("ME", "Stopped creating team.");
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Log.i("ME", "Delete Method");
        }
    }

}