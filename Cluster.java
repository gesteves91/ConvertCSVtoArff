
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
 
public class Cluster {
 
	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;
 
		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}
 
		return inputReader;
	}
 
	public static void main(String[] args) throws Exception {
		Writer w = null;
		int n = 10;
		int[] instances, clusters;
		int[] maxClusters = new int[n];
		
		try{
			w = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("clusters.txt"), "utf-8"));
			SimpleKMeans kmeans = new SimpleKMeans();

			kmeans.setSeed(10);

			//important parameter to set: preserver order, number of cluster.
			kmeans.setPreserveInstancesOrder(true);
			kmeans.setNumClusters(n);

			BufferedReader datafile = readDataFile("/Users/gesteves/Documents/workspace/Weka/src/dataset.arff"); 
			Instances data = new Instances(datafile);

			kmeans.buildClusterer(data);

			// This array returns the cluster number (starting with 0) for each instance
			// The array has as many elements as the number of instances
			int[] assignments = kmeans.getAssignments();
			
			instances = new int[assignments.length];
			clusters = new int[assignments.length];
			
			for(int i = 0; i < maxClusters.length; i++)
				maxClusters[i] = i;
			
			for(int i = 0; i < assignments.length; i++){
				instances[i] = i;
				clusters[i] = assignments[i];
			}
			
			int total = 0;
			
			for(int i = 0; i < maxClusters.length; i++){
				w.write("Cluster " + i);
				w.write(System.getProperty("line.separator"));
				for(int j = 0; j < clusters.length; j++){
					if(clusters[j] == i){
						w.write("Instance " + instances[j]);
						w.write(System.getProperty("line.separator"));
						total++;
					}
				}
				w.write("Total = " + total);
				total = 0;
				w.write(System.getProperty("line.separator"));	
				w.write(System.getProperty("line.separator"));	
			}
			
			//for(int i = 0; i < assignments.length; i++){
				//w.write("Instance " + i + " -> Cluster " + assignments[i]);
				//w.write(System.getProperty("line.separator"));
			//}
				
				
			
			//int i=0;
			//for(int clusterNum : assignments) {
				//w.write("Instance %d -> Cluster %d", i, clusterNum);
				//w.write(System.getProperty("line.separator"));
				//System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
				//i++;
			//}
			
		System.out.println("This shit is done!!!");
			
		} catch (IOException ex) {
			// report
		} finally {
			try {w.close();} catch (Exception ex) {/*ignore*/}
		}
	}
}