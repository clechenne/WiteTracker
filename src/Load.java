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

		File  folder = new File ("./");
		File [] listOfFiles = folder.listFiles();

		FileWriter fw = new FileWriter("./export.csv");
		fw.write("Num;Date;" +
				"Axis Combat;Axis Retreat;Axis Attrition;Axis Surrender;Axis Misc.;" + 
				"Soviet Combat;Soviet Retreat;Soviet Attrition;Soviet Surrender;Soviet Misc.;"
				+ CAR_RET);
		for (File f : listOfFiles) {
			if (f.getName().indexOf("EventLog(") != -1) {
				System.out.println("File : " + f.getName());
				WITEParser wp = new WITEParser(f.getName());
				Turn turn = wp.parse();		
				
				try {
					fw.write(turn.num + ";" 
							+ turn.date + ";" 
							+ turn.axisCombat + ";"
							+ turn.axisSurrender + ";"
							+ turn.axisAttrition + ";"
							+ turn.axisMisc + ";"
							+ turn.axisRetreat + ";"
							+ turn.sovietCombat + ";"
							+ turn.sovietSurrender + ";"
							+ turn.sovietAttrition + ";"
							+ turn.sovietMisc + ";"
							+ turn.sovietRetreat + ";"
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
