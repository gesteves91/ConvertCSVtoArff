import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class Genes {

	public static void main(String[] args) {
		Writer writer = null;
		BufferedReader[] br = new BufferedReader[10];
		Scanner sc;
		String[] sCurrentLine = new String[10];

		try {
			br[0] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time0.txt"));
			br[1] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time05.txt"));
			br[2] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time1.txt"));
			br[3] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time2.txt"));
			br[4] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time4.txt"));
			br[5] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time6.txt"));
			br[6] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time12.txt"));
			br[7] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time24.txt"));
			br[8] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time48.txt"));
			br[9] = new BufferedReader(new FileReader("/Users/gesteves/Documents/workspace/Weka/src/time72.txt"));
			

		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("dataset_genes.arff"), "utf-8"));
		    writer.write("@Relation probe_gene_expression");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time0 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time0.5 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time2 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time4 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time6 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time8 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time12 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time24 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time48 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Attribute time72 NUMERIC");
		    writer.write(System.getProperty("line.separator"));
		    writer.write("@Data");
		    writer.write(System.getProperty("line.separator"));
		    
		    while ((sCurrentLine[0] = br[0].readLine()) != null) {
					for(int i = 1; i < 10; i++)
						sCurrentLine[i] = br[i].readLine();
					writer.write(sCurrentLine[0] + ", " + sCurrentLine[1] + ", " + sCurrentLine[2] + ", " + sCurrentLine[3] + ", " + sCurrentLine[4] + ", " + sCurrentLine[5] + ", " + sCurrentLine[6] + ", " + sCurrentLine[7] + ", " + sCurrentLine[8] + ", " + sCurrentLine[9]);
					writer.write(System.getProperty("line.separator"));
		    }
					
			System.out.println("The arff file was created successfully!");	

		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}

	}

}
