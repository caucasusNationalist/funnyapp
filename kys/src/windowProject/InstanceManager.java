package windowProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Timer;

public class InstanceManager{
	Window window;
	//task id = 5364
	String runningLocation = "";
	Timer taskScheduler;
	public InstanceManager(Window window) {
		this.window = window;
		try {
			runningLocation = new File(Window.class.getProtectionDomain().getCodeSource().getLocation()
				    .toURI()).getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		taskScheduler = new Timer();
		
	}
	
    
    
	public boolean getInstanceIntegrity() {
		try {
		    String line;
		    Process p = Runtime.getRuntime().exec("ps -e");
		    BufferedReader input =
		            new BufferedReader(new InputStreamReader(p.getInputStream()));
		    while ((line = input.readLine()) != null) {
		        System.out.println(line); //<-- Parse data here.
		    }
		    input.close();
		} catch (Exception err) {
		    err.printStackTrace();
		}
		
		
		return false; // placeholder please replace
	}

}
