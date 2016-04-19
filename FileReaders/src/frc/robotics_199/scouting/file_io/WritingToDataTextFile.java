package frc.robotics_199.scouting.file_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

public class WritingToDataTextFile {

	public static void main(String[] args) {

		String[] columns = "id,match_id,team_name_id,team_points,win_loss,ranking_points,auto_points,low_bar,portcullis,cheval_de_frise,moat,ramparts,drawbridge,sallyport,rockwall,rough_terrain,shooter_type,position,shots_made,shots_taken,defense_or_offense,end_game,penalties,speed_stability_notes,driver_skill"
				.split(",");
		
		final String csv = ".csv"; 

		File input_folder = new File("csv_input_files");
		File output_folder = new File("csv_output_files");
		File[] inputs = input_folder.listFiles();
		File[] outputs = output_folder.listFiles();

		ArrayList<String> file_inputs = new ArrayList<String>();
		ArrayList<String> final_file_inputs = new ArrayList<String>();

		try {
			for (File f : inputs) {
				Scanner input = new Scanner(f);
				while (input.hasNextLine()) {
					String in = input.nextLine();
					if(!in.split(",")[0].equals(columns[0]))
						file_inputs.add(in);
				}
				input.close();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Can't Find: " + e.toString());
		}
		
		ArrayList<String> match_id = new ArrayList<String>(), team_name_id = new ArrayList<String>();
		for(String string:file_inputs){
			if(string.split(",").length == 1) continue;
			
			if(!hasMatchandTeamAlready(string, match_id, team_name_id)){
				match_id.add(string.split(",")[1]);
				team_name_id.add(string.split(",")[2]);
				final_file_inputs.add(string);
			}
		}
		
		ArrayList<String> teams = new ArrayList<String>();
		for(String string:final_file_inputs){
			if(!teams.contains(string.split(",")[2])){
				teams.add(string.split(",")[2]);
			}
		}
		
		for(String string:teams){
			ArrayList<String> thingy = new ArrayList<String>();
			for(String string2:final_file_inputs){
				if(string2.split(",")[2].equals(string)){
					thingy.add(string2);
				}
			}
			File file = createNewFile("csv_output_files/" + string + csv);
			try {
				Files.write(file.toPath(), thingy, StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		
		try {
			Files.write(Paths.get(outputs[0].getPath()), final_file_inputs, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static boolean hasMatchandTeamAlready(String input, ArrayList<String> f, ArrayList<String> s){
		int size1 = f.size();
		int size2 = f.size();
		String matchid = input.split(",")[1];
		String teamid = input.split(",")[2];
		if (size1 != size2) return false;
		for(int i = 0; i < size1; i++){
			if(f.get(i).equals(matchid) && s.get(i).equals(teamid)){
				return true;
			}
		}
		return false;
	}

	public static File createNewFile(String path) {
		try {

			File file = new File(path);

			if (file.createNewFile()) {
				System.out.println("File is created!");
				return file;
			} else {
				System.out.println("File already exists.");
				return file;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String removeUnneededSpaces(String input){
		String output = input;
		output.replace("\n", "");
		return output;
	}
	
	public static boolean isBadInput(String string){
		boolean csvFormat = false, primaryKeyExists = false;
		if(string.contains(","))  csvFormat = true;
		else return false;
		if(!string.split(",")[0].equals("")) primaryKeyExists = true;
		else return false;
		
		return csvFormat && primaryKeyExists;
	}
	
	
}
