package view;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainWindow {

	private Display display;
	private Shell shell;

	public void run() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("Searching Techniques");
		createContents();
		shell.open();
		while (! shell.isDisposed())
			if (! display.readAndDispatch())
				display.sleep();
		dispose();
	}

	private void createContents() {
		shell.setLayout(new FormLayout());
	}

	private void dispose() {
		display.dispose();
	}

	public static void main(String[] args) {
		new MainWindow().run();
	}
}
