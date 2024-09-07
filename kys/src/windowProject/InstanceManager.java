package windowProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Timer;

public class InstanceManager{
	Window window;
	
	
	String runningLocation = "";
	Timer taskScheduler;
	public InstanceManager(Window window) {
		this.window = window;
		getRunningLocation();
		//taskScheduler = new Timer();
		getInstanceIntegrity(); //temp
	}
	
    
    
	public boolean getInstanceIntegrity() {
		int procCount = 0;
		try {
		    String line;
		    
		    Process p = Runtime.getRuntime().exec
		    	    (System.getenv("windir") +"\\system32\\"+"tasklist.exe /fo csv /nh");

		    BufferedReader input =
		            new BufferedReader(new InputStreamReader(p.getInputStream()));
		    while ((line = input.readLine()) != null) {
		    	
		        if(line.contains("javaw.exe")){
		        	procCount++;
		        }
		        
		    }
		    input.close();
		    
		} catch (Exception err) {
		    err.printStackTrace();
		}
		
		System.out.println(procCount);
		if(procCount > 1) return true;
		
		return false;
	}
	
	
	public String getRunningLocation() {
		String locale = "";
		try {
		locale = new File(Window.class.getProtectionDomain().getCodeSource().getLocation()
			    .toURI()).getPath();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return locale;
	}

}
