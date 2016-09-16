package appnotification;

import java.awt.EventQueue;
import javax.swing.UIManager;

/**
 *
 * @author JohnReMugar
 */
public class AppNotification {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	try {
//	    System.out.println("setting look and feel");
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
//	    System.out.println("Unable to set LookAndFeel");
	}

	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new App().setVisible(true);
	    }
	});
    }
    
}
