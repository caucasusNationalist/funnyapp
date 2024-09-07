package windowProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
//import java.net.URISyntaxException;
import java.util.Timer;

public class InstanceManager {

	String runningLocation = "";
	Timer taskScheduler;
	Window window;
	
	int appState = 0;

	public InstanceManager(String[] args) {
		if(args.length != 0) {
			appState = Integer.parseInt(args[0]);
		}
//		getRunningLocation();
//		taskScheduler = new Timer();
//		window.label.setText("it edits the instance !!!!");
		
		window = new Window(300, appState);
	
	}

	public boolean getInstanceIntegrity() {
		int procCount = 0;
		try {
			String line;

			Process p = Runtime.getRuntime()
					.exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /nh");

			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {

				if (line.contains("javaw.exe")) {
					procCount++;
				}

			}
			input.close();

		} catch (Exception err) {
			err.printStackTrace();
		}

		System.out.println(procCount);
		if (procCount > 1)
			return true;

		return false;
	}

	public String getRunningLocation() {
		String locale = "";
		try {
			locale = new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locale;
	}

}
