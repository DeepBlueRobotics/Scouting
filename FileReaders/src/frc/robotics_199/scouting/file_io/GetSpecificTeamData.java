package frc.robotics_199.scouting.file_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.GuardedObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class GetSpecificTeamData {

	// 8, 751, 4171
	public static final String team = "1662";

	public static void main(String[] args) {

		File output_folder = new File("csv_output_files");
		File input_folder = new File("csv_input_files");
		File[] output_folder_components = output_folder.listFiles();
		File[] input_folder_components = input_folder.listFiles();
		File correct_team = null;
		String teamName = "";
		
		for (File f : output_folder_components) {
			String x = f.getName();
			x = x.replace(".csv", "");
			if (team.equals(x)) {
				correct_team = f;
				teamName = x;
			}
		}  

		try {
			Scanner scanner = new Scanner(correct_team);
			Scanner scanner2 = new Scanner(input_folder_components[0]);
			String columns = scanner2.nextLine();
			String[] column = columns.split(",");
			scanner2.close();
			ArrayList<String> list = new ArrayList<String>();
			while (scanner.hasNext()) {
				// Output team data
				String one = "";
				String out = scanner.nextLine();
				String[] data = out.split(",");
				for(int i = 0; i < data.length; i++){
					one += column[i] + ":" + data[i] + " /// ";
				}
				list.add(one);
				System.out.println(one);
			}
			File file = WritingToDataTextFile.createNewFile("team_data/" + teamName + ".txt");
			try {
				Files.write(file.toPath(), list, StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.err.println("error writing to file");
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("E1" + e.toString());
		}

	}

}
