import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Application");

		// primaryStage.setScene(scene);
		primaryStage.show();

		fireEvent();
		setSystemVolume(50);
		setSystemBrightness(50);
	}

	public void setSystemVolume(int volume) {
		if (volume < 0 || volume > 100) {
			throw new RuntimeException(
					"Error: " + volume + " is not a valid number. Choose a number between 0 and 100");
		}

		else {
			double endVolume = 655.35 * volume;

			Runtime rt = Runtime.getRuntime();
			try {
				String nircmdFilePath = "nircmd-x64/nircmd.exe";
				rt.exec(nircmdFilePath + " setsysvolume " + endVolume);
				// Process pr = rt.exec(nircmdFilePath + " mutesysvolume 0");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setSystemBrightness(int brightness) {
		if (brightness < 0 || brightness > 100) {
			throw new RuntimeException(
					"Error: " + brightness + " is not a valid number. Choose a number between 0 and 100");
		}

		else {

			Runtime rt = Runtime.getRuntime();
			try {
				String nircmdFilePath = "nircmd-x64/nircmd.exe";
				rt.exec(nircmdFilePath + " setbrightness " + brightness);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void fireEvent() {
		try {
			final Thread t = new Thread(() -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Robot r;
				try {
					r = new Robot();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				int keyCode = KeyEvent.VK_SPACE; // the A key
				r.keyPress(keyCode);
				// later...
				// r.k eyRelease(keyCode);
			});
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
