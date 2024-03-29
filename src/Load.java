import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.wite.core.WITEParser;
import org.wite.model.Turn;


public class Load {
	public final static String CAR_RET = "\r\n";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("WITETracker v0.2");
		
		// new export data
		int axisCumulativeLosses = 0;
		int axisCurrentLosses = 0;
		int sovCumulativeLosses = 0;
		int sovCurrentLosses = 0;
		
		File  folder = new File ("./");
		File [] listOfFiles = folder.listFiles();

		FileWriter fw = new FileWriter("./export.csv");
		fw.write("Num;Date;" +
				"Axis Combat;Axis Retreat;Axis Attrition;Axis Surrender;Axis Misc.;Axis turn losses; Axis cum losses" + 
				"Soviet Combat;Soviet Retreat;Soviet Attrition;Soviet Surrender;Soviet Misc.;Soviet turn losses; Soviet cum losses"
				+ CAR_RET);
		for (File f : listOfFiles) {
			if (f.getName().indexOf("EventLog(") != -1) {
				
				System.out.println("File : " + f.getName());
				
				WITEParser wp = new WITEParser(f.getName());
				Turn turn = wp.parse();		
				
				axisCurrentLosses = turn.axisCombat +  turn.axisRetreat + turn.axisAttrition + turn.axisSurrender + turn.axisMisc;
				axisCumulativeLosses += axisCurrentLosses;
				
				sovCurrentLosses = turn.sovietCombat +  turn.sovietRetreat + turn.sovietAttrition + turn.sovietSurrender + turn.sovietMisc;
				sovCumulativeLosses +=  sovCurrentLosses;
				
				try {
					fw.write(turn.num + ";" 
							+ turn.date + ";" 
							+ turn.axisCombat + ";"
							+ turn.axisRetreat + ";"
							+ turn.axisAttrition + ";"
							+ turn.axisSurrender + ";"
							+ turn.axisMisc + ";"
							+ axisCurrentLosses + ";"
							+ axisCumulativeLosses + ";"
							+ turn.sovietCombat + ";"
							+ turn.sovietRetreat + ";"
							+ turn.sovietAttrition + ";"
							+ turn.sovietSurrender + ";"
							+ turn.sovietMisc + ";"
							+ sovCurrentLosses + ";"
							+ sovCumulativeLosses + ";"

							+ CAR_RET);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fw.flush();
		}
		

		
	}

}
