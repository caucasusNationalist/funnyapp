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
	int procCount;
	int appState = 0;

	public InstanceManager(String[] args) {
		if(args.length != 0 && args[0] != null) {
			appState = Integer.parseInt(args[0]);
		}
//		getRunningLocation();
//		taskScheduler = new Timer();
//		window.label.setText("it edits the instance !!!!");
		
		window = new Window(300, appState);
		new Thread(window).start();
	
	}

	public int getInstanceCount() {
		int count = 0;

		try {
			String line;

			Process p = Runtime.getRuntime()
					.exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe /fo csv /nh");

			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {

				if (line.contains("javaw.exe")) {
					count++;
				}

			}
			input.close();

		} catch (Exception err) {
			err.printStackTrace();
		}

		System.out.println(procCount);
		return count;
	}


	public void runBackupInstance() {
		appState++;
		String locale = "";
		try {
			locale = new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			Runtime.getRuntime().exec("java -jar " + locale + " " + appState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
