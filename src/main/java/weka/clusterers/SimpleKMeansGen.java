package weka.clusterers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class SimpleKMeansGen {
	String whatway = "";
	
	public SimpleKMeansGen(){
		
	}
	
	public void Setway(int way){
		whatway = String.valueOf(way)+"way";
	}
	
	public void removefiles(){
		File dir = new File("./testoutputs/");
		
		for(File file: dir.listFiles()) 
		    if (!file.isDirectory()) 
		        file.delete();
	}
	
	public void generateresult() throws IOException {
		removefiles();
		// TODO Auto-generated method stub
		String max_candidates;
		String min_density;
		String periodic_pruning;
		String T1;
		String T2;
		String output_debug_info;
		String V;
		String distanceFunction;
		String R;
		String D;
		String functionV;
		String do_not_check_capabilities;
		String M;
		String fast;
		String init;
		String I;
		String N;
		String num_slots;
		String O;
		String C;
		String S;

		
		byte[] encoded = Files.readAllBytes(Paths.get("./inputs/K-Means-" + whatway + ".csv"));
	    String file = new String(encoded, "UTF-8");
	    String[] lines = file.split("\r\n");
	    
	    for (int j = 0; j < lines.length; j++){
	    	
	    	String[] inputValues = lines[j].split(","); 
	    	
	    	//0/-max-candidates
	    	if (inputValues[0].equals("2147483647")){
	    		max_candidates = "-max-candidates " + "2147483647";
	    	}else{
	    		max_candidates = "-max-candidates " + inputValues[0];
	    	}
	    	
			//1/-min-density
	    	min_density = "-min-density " + inputValues[1];

			//2/-periodic-pruning
	    	periodic_pruning = "-periodic-pruning " + inputValues[2];

			//3/-t1
			if (inputValues[3].equalsIgnoreCase("MaxFloat")) {
				T1 = "-t1 " + String.valueOf(Double.MAX_VALUE);
			} else {
				T1 = "-t1 " + inputValues[3];
			}

			//4/-t2
			if (inputValues[4].equalsIgnoreCase("MaxFloat")) {
				T2 = "-t2 " + String.valueOf(Double.MAX_VALUE);
			} else {
				T2 = "-t2 " + inputValues[4];
			}

            //5/-output-debug-info
			if (inputValues[5].equals("Y")){
				output_debug_info = "-output-debug-info";
			}else{
				output_debug_info = "";
			}

			//6/-V
			if (inputValues[6].equals("Y")){
				V = "-V";
			}else{
				V = "";
			}

			//7/-A
			distanceFunction = "-A " + inputValues[7];

			//8/-R
			if (inputValues[8].equals("firstcommalast")){
				R = "-R " + "first,last";
			}else if (inputValues[8].equals("last+1")){
				R = "-R " + "9";
			}else{
				R = "-R " + inputValues[8];
			}

			//9/-D
			if (inputValues[9].equals("Y")){
				D = "-D";
			}else{
				D = "";
			}

			//10/-V
			if (inputValues[10].equals("Y")){
				functionV = "-V";
			}else{
				functionV = "";
			}

			//11/-do-not-check-capabilities
			if (inputValues[11].equals("Y")){
				do_not_check_capabilities = "-do-not-check-capabilities";
			}else{
				do_not_check_capabilities = "";
			}

			//12/-M
			if (inputValues[12].equals("Y")){
				M = "-M";
			}else{
				M = "";
			}
			
			//13/-fast
			if (inputValues[13].equals("Y")){
				fast = "-fast";
			}else{
				fast = "";
			}
			
			//14/-init
			init = "-init " + inputValues[14];
			
			//15/-I
			I = "-I " + inputValues[15];
			
			//16/-N
			if (inputValues[16].equals("2147483647")){
				N = "-N " + "768";
			}else{
				N = "-N " + inputValues[16];
			}
			
			
			//17/-num-slots
			num_slots = "-num-slots " + inputValues[17];
			
			//18/-O
			if (inputValues[18].equals("Y")){
				O = "-O";
			}else{
				O = "";
			}
			
			//19/-C
			if (inputValues[19].equals("Y")){
				C = "-C";
			}else{
				C = "";
			}
			
			//20/-S
			S = "-S " + inputValues[20];
			
//	    	instance.main();
//	    	//PrintWriter out = new PrintWriter(lines[j] + ".txt");
//			out.println(outContent.toString());
//			out.close();

	    	String option = max_candidates + " " + min_density + " " + periodic_pruning + " " + T1 + " " + T2 + " " + output_debug_info + " " + V + " " + distanceFunction + " " + do_not_check_capabilities + " " + M + " " + fast + " " + init + " " + I + " " + N + " " + num_slots + " " + O + " " + C + " " + S;
	    	String[] options = option.split("\\s+");
//	    	for (int i1 = 0; i1 < options.length; i1++){
//	    		System.out.println(options[i1]);
//	    	}
	    	
	    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(outContent));
		    
		    SimpleKMeans kmeans = new SimpleKMeans();
		    try {
				kmeans.setOptions(options);
				Instances data;
				data = DataSource.read("./hypothyroid.arff");
				String functionOption = R + " " + D + " " + functionV;
				String[] functionOptions = functionOption.split("\\s+"); 
				kmeans.getDistanceFunction().setOptions(functionOptions);
				kmeans.buildClusterer(data);
				System.out.println(kmeans);
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				System.out.println("===========");
				System.out.println("Unable to read the dataset or invalid output");
				System.out.println("===========");
			} catch (OutOfMemoryError E){
				System.out.println(E);
				System.out.println("===========");
				System.out.println("Unable to read the dataset or invalid output");
				System.out.println("===========");
			}
			
		    String outputstring = outContent.toString();
		    
//		    int truncateIndex = outputstring.length();
//	    	
//	        for (int i = 0; i < 1; i++) {
//	            truncateIndex = outputstring.lastIndexOf('\n', truncateIndex - 1);
//	        }
//	        
//
//	        outputstring = outputstring.substring(0, truncateIndex);
	        
	        
	        String filepath = "./testoutputs/" + Integer.toString(j) + ".txt";
	        
			PrintWriter out = new PrintWriter(filepath);
	        out.println(outputstring);
	        out.close();
	        
	    }

	}

}
