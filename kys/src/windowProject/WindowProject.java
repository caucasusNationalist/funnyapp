package windowProject;

public class WindowProject {

	public static void main(String[] args) {
		new Thread(new Window(300, args)).start();
	}

}
