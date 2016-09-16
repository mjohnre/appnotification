package appnotification;

import java.awt.AWTException;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static java.awt.Frame.NORMAL;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author JohnReMugar
 */
public class App extends JFrame {

    TrayIcon trayIcon;
    SystemTray tray;

    public App() {
	super("App");
//	System.out.println("creating instance");

	if (SystemTray.isSupported()) {
//	    System.out.println("system tray supported");
	    tray = SystemTray.getSystemTray();

	    Image image = Toolkit.getDefaultToolkit().getImage("profile_picture1.png");
	    ActionListener exitListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//		    System.out.println("Exiting....");
		    System.exit(0);
		}
	    };
	    PopupMenu popup = new PopupMenu();
	    MenuItem defaultItem = new MenuItem("Exit App");
	    defaultItem.addActionListener(exitListener);
	    popup.add(defaultItem);
	    defaultItem = new MenuItem("Open App");
	    defaultItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    tray.remove(trayIcon);
		    setVisible(true);
		    setExtendedState(JFrame.NORMAL);
		}
	    });
	    popup.add(defaultItem);
	    trayIcon = new TrayIcon(image, "App", popup);

	    MouseAdapter mouseAdapter = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
		    if (e.getButton() == MouseEvent.BUTTON1) {
			tray.remove(trayIcon);
			setVisible(true);
			setExtendedState(JFrame.NORMAL);
		    }
		}
	    };
	    trayIcon.addMouseListener(mouseAdapter);

	    trayIcon.setImageAutoSize(true);
	} else {
//	    System.out.println("system tray not supported");
	}
	addWindowStateListener(new WindowStateListener() {
	    public void windowStateChanged(WindowEvent e) {
		if (e.getNewState() == ICONIFIED) {
		    try {
			tray.add(trayIcon);
			setVisible(false);
//			System.out.println("added to SystemTray");
		    } catch (AWTException ex) {
//			System.out.println("unable to add to tray");
		    }
		}
		if (e.getNewState() == 7) {
		    try {
			tray.add(trayIcon);
			setVisible(false);
//			System.out.println("added to SystemTray");
		    } catch (AWTException ex) {
//			System.out.println("unable to add to system tray");
		    }
		}
		if (e.getNewState() == MAXIMIZED_BOTH) {
		    tray.remove(trayIcon);
		    setVisible(true);
//		    System.out.println("Tray icon removed");
		}
		if (e.getNewState() == NORMAL) {
		    tray.remove(trayIcon);
		    setVisible(true);
//		    System.out.println("Tray icon removed");
		}
	    }
	});
	setIconImage(Toolkit.getDefaultToolkit().getImage("profile_picture1.png"));

	setVisible(true);

	setSize(300, 200);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JButton button = new JButton();
	button.setText("Cancel Timer");
	button.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		NotificationTimer.cancel();
		System.out.println("Timer cancelled");
	    }
	});
	
	add(button);
	
	new NotificationTimer(Constants.defaultTimerDelayMillis, Constants.defaultTimerIntervalMillis);
    }

}