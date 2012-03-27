package org.wite.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.wite.model.Turn;

public class WITEParser {
	String engLogFileName;
	
	public WITEParser(String s) {
		engLogFileName = s;
	}

	public Turn parse() {
		FileReader fr;
		Turn turn = new Turn();
		
		try {
			fr = new FileReader(engLogFileName);
			BufferedReader br = new BufferedReader(fr);	
			
			String line = br.readLine();
			
			while (line != null) {
				if (line.indexOf("Turn") != -1) {
					// Turn 002 6-26-1941 Axis
					StringTokenizer st = new StringTokenizer(line, " ");
					st.nextToken(); // return Turn
					turn.num = Integer.parseInt(st.nextToken());
					turn.date = st.nextToken();
				} 
				else if (line.indexOf("Axis Combat") != -1) {
					//Axis Combat_________3851
					turn.axisCombat = extract(line);
				}
				else if (line.indexOf("Axis Retreat") != -1) {
					//Axis Combat_________3851
					turn.axisRetreat = extract(line);
				}
				else if (line.indexOf("Axis Attrition") != -1) {
					//Axis Combat_________3851
					turn.axisAttrition = extract(line);
				}
				else if (line.indexOf("Axis Surrender") != -1) {
					//Axis Combat_________3851
					turn.axisSurrender = extract(line);
				}
				else if (line.indexOf("Axis Misc.") != -1) {
					//Axis Combat_________3851
					turn.axisMisc = extract(line);
				}
				else if (line.indexOf("Soviet Combat") != -1) {
					//Axis Combat_________3851
					turn.sovietCombat = extract(line);
				}
				else if (line.indexOf("Soviet Retreat") != -1) {
					//Axis Combat_________3851
					turn.sovietRetreat = extract(line);
				}
				else if (line.indexOf("Soviet Attrition") != -1) {
					//Axis Combat_________3851
					turn.sovietAttrition = extract(line);
				}
				else if (line.indexOf("Soviet Surrender") != -1) {
					//Axis Combat_________3851
					turn.sovietSurrender = extract(line);
				}
				else if (line.indexOf("Soviet Misc.") != -1) {
					//Axis Combat_________3851
					turn.sovietMisc = extract(line);
				}
				
				//
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return turn;
	}

	private int extract(String line) {
		String newLine = line.replace('_', ' ');
		StringTokenizer st = new StringTokenizer(newLine, " ");
		String side = st.nextToken(); // Axis
		String type = st.nextToken(); // Combat
		return Integer.parseInt(st.nextToken());
	}
	
}
