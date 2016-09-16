package appnotification;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author JohnReMugar
 */
public class NotificationMessage extends JFrame {
    
    int duration;
    
    public NotificationMessage(int durationMillis) {
	duration = durationMillis;
	String message = "<html>received as a <span style=\"color:blue;\">notification</span>. <strong>Please</strong> read carefully.<br/><br/>Will get closed after "+Constants.defaultMessageDurationMillis/1000+" sec.";
	String header = "<html><h4>Open Pane</h4>";
	
	setSize(300, 125);

	setUndecorated(true);

	setLayout(new GridBagLayout());
	
	GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.weightx = 1.0f;
	constraints.weighty = 1.0f;
	constraints.insets = new Insets(5, 5, 5, 5);
	constraints.fill = GridBagConstraints.BOTH;
	JLabel headingLabel = new JLabel(header);
	headingLabel.setIcon(new ImageIcon("profile_picture1.png")); // --- use image icon you want to be as heading image.
	headingLabel.setOpaque(false);
	
	add(headingLabel, constraints);
	
	constraints.gridx++;
	constraints.weightx = 0f;
	constraints.weighty = 0f;
	constraints.fill = GridBagConstraints.NONE;
	constraints.anchor = GridBagConstraints.NORTH;
	JButton openButton = new JButton(new AbstractAction("Open") {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
//		open pane
	    }
	});
	openButton.setMargin(new Insets(1, 4, 1, 4));
	openButton.setFocusable(false);
	
	add(openButton, constraints);
	
	constraints.gridx++;
	constraints.weightx = 0f;
	constraints.weighty = 0f;
	constraints.fill = GridBagConstraints.NONE;
	constraints.anchor = GridBagConstraints.NORTH;
	JButton closeButton = new JButton(new AbstractAction("close") {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		dispose();
	    }
	});
	closeButton.setMargin(new Insets(1, 4, 1, 4));
	closeButton.setFocusable(false);
	
	add(closeButton, constraints);
	
	constraints.gridx = 0;
	constraints.gridy++;
	constraints.weightx = 1.0f;
	constraints.weighty = 1.0f;
	constraints.insets = new Insets(5, 5, 5, 5);
	constraints.fill = GridBagConstraints.BOTH;
	JLabel messageLabel = new JLabel(message);
	
	add(messageLabel, constraints);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setVisible(true);

	Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
	Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());// height of the task bar
	setLocation(scrSize.width - getWidth(), scrSize.height - toolHeight.bottom - getHeight());

	setAlwaysOnTop(true);

	new Thread() {
	    @Override
	    public void run() {
		try {
		    Thread.sleep(duration); // time after which pop up will be disappeared.
		    dispose();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}.start();
    }
    
}
