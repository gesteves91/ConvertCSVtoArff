
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
 
public class Cluster {
	
	public static Graph createSubGraph(Graph g1, ArrayList<Integer> e){
		
		int[] elements = new int[e.size()];
		
		for(int i = 0; i < e.size(); i++)
			elements[i] = e.get(i);
		
		for(int i = 0; i < elements.length - 1; i++)
			for(int j = i + 1; j < elements.length; j++){
				g1.insertEdge(elements[i],elements[j]);
				g1.insertEdge(elements[j], elements[i]);
			}
	
		return g1;
	}
 
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
		ArrayList<Integer> subGraph = new ArrayList<Integer>();
		int[] maxClusters = new int[n];
		BufferedWriter graph = null;
		
		try{
			File file = new File("graphs");
			file.createNewFile();
			graph = new BufferedWriter(new FileWriter(file));
			w = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("clusters"), "utf-8"));
			SimpleKMeans kmeans = new SimpleKMeans();

			kmeans.setSeed(10);

			//important parameter to set: preserver order, number of cluster.
			kmeans.setPreserveInstancesOrder(true);
			kmeans.setNumClusters(n);

			BufferedReader datafile = readDataFile("dataset.arff"); 
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

			//Graph Creation
			Graph g = new Graph(assignments.length);
			int total = 0;

			for(int i = 0; i < maxClusters.length; i++){
				w.write("Clusters -> " + i);
				w.write(System.getProperty("line.separator"));
				for(int j = 0; j < clusters.length; j++){
					if(clusters[j] == i){
						w.write("Instances -> " + instances[j]);
						subGraph.add(instances[j]);
						w.write(System.getProperty("line.separator"));
						total++;
					}
				}
				w.write("Total = " + total);
				g = createSubGraph(g, subGraph);
				subGraph.clear();
				total = 0;
				w.write(System.getProperty("line.separator"));	
				w.write(System.getProperty("line.separator"));	
			}

			System.out.println("The KMeans has worked properly!!!");
			System.out.println("Graphs file created containing all genes.");
			System.out.println("Clusters file created.");
			
			g.print(graph);

		} catch (IOException ex) {
			// report
		} finally {
			try {w.close();} catch (Exception ex) {/*ignore*/}
		}
	}
}