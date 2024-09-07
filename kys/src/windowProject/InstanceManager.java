package windowProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
//import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class InstanceManager extends TimerTask {

	String runningLocation = "";
	Timer taskScheduler;
	Window window = null;

	int procCount;
	int restartCount = 0;
	boolean windowVisible = false;

	public InstanceManager(String[] args) {
		if (args.length != 0 && args[0] != null) {
				restartCount = Integer.parseInt(args[0]);
		}
//		getRunningLocation();
		taskScheduler = new Timer();
		taskScheduler.scheduleAtFixedRate(this, 0, 10);
//		window.label.setText("it edits the instance !!!!");

		window = new Window(300, restartCount);

	}

	@Override
	public void run() {
		if (getInstanceCount() < procCount) {
            
			restartCount++;
			window.restartCount = restartCount;
			
			runReplacementInstance();
			
			if(!windowVisible) {
				new Thread(window).start();
				windowVisible = true;
			}
			
		procCount = getInstanceCount();

		}
		
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

	public void runReplacementInstance() {
		String locale = "";
		try {
			locale = new File(Window.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			Runtime.getRuntime().exec("java -jar " + locale + " " + restartCount);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
