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
	Window window = null;

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
		
		try {
			runningLocation = new File(
					InstanceManager.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		window = new Window(300, restartCount);

	}
	public static void main(String[] args) {
		new InstanceManager(args);
	}

	@Override
	public void run() {
		int instanceCount = getInstanceCount();
		
		if(instanceCount < 3) System.out.println("app is counting < 3 case");
		if(instanceCount <= 2) System.out.println("app is counting <= 2 case");
		if (instanceCount < 2) {
			if (restartCount < 3) {
				restartCount++;
				window.restartCount = restartCount;
			}

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
		System.out.println("the instanceCount is " + count);
		return count;
	}

	public void runReplacementInstance() {
		try {
			System.out.println("app is trying to run the thing");
			Runtime.getRuntime().exec("java -jar " + runningLocation + " " + restartCount);
			System.out.println("java -jar " + runningLocation + " " + restartCount);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
