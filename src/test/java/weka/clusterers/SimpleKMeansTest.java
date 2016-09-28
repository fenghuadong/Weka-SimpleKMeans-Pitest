package weka.clusterers;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;

public class SimpleKMeansTest {

	@Test
	public void Test1() throws IOException {
		int whatwaynum = 1;
		String whatway = "1way";
		
		
		SimpleKMeansGen instance = new SimpleKMeansGen();
		instance.Setway(whatwaynum);
		instance.generateresult();
		
		byte[] encoded = Files.readAllBytes(Paths.get("./inputs/K-Means-" + whatway + ".csv"));
	    String file = new String(encoded, "UTF-8");
	    String[] lines = file.split("\r\n");
	    
	    for (int j = 0; j < lines.length; j++){
		    
		    byte[] expencoded = Files.readAllBytes(Paths.get("./outputs/K-Means-" + whatway + "/" + Integer.toString(j) + ".txt"));
		    String expoutputstring = new String(expencoded, "UTF-8");

	        byte[]actencoded = Files.readAllBytes(Paths.get("./testoutputs/" + Integer.toString(j) + ".txt"));
		    String actoutputstring = new String(expencoded, "UTF-8");
		    
		    assertEquals(expoutputstring,actoutputstring);
	    
	    }
	}

}
