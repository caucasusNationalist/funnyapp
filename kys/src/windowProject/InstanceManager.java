package windowProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
//import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class InstanceManager extends TimerTask {

	String runningLocation = "";
	Timer taskScheduler;
	int restartCount = 0;
	boolean windowVisible = false;
	Window window = new Window(300, restartCount);

	public InstanceManager(String[] args) {
		if (args.length != 0 && args[0] != null) {
			restartCount = Integer.parseInt(args[0]);
		}
		
		taskScheduler = new Timer();
		taskScheduler.scheduleAtFixedRate(this, 0, 20);
        window = new Window(300, restartCount);
		
		try {
			runningLocation = new File(
					InstanceManager.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(runningLocation.contains(" ")) {
			runningLocation = "\"" + runningLocation + "\"";
		}
	}

	public static void main(String[] args) {
		new InstanceManager(args);
	}

	@Override
	public void run() {
		int instanceCount = getInstanceCount();
		window.label.setText(Integer.toString(instanceCount));

		if (instanceCount <= 2) {

			runReplacementInstance();

			if (!windowVisible) {
				new Thread(window).start();
				windowVisible = true;
			}

		}

	}

	public int getInstanceCount() {
		int count = 0;

		try {
			String line;

			Process p = Runtime.getRuntime()
					.exec(System.getenv("windir") + "\\system32\\" + "tasklist /fo csv /nh /fi \"STATUS eq running\"");

			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = input.readLine()) != null) {
				if(line.contains("javaw.exe"))
					count++;
			}
			input.close();

		} catch (Exception err) {
			err.printStackTrace();
		}
		return count;
	}

	public void runReplacementInstance() {
		try {
			Runtime.getRuntime().exec("java -jar " + runningLocation + " " + restartCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (restartCount < 3) {
			restartCount++;
			window.restartCount++;
		}

	}

}
