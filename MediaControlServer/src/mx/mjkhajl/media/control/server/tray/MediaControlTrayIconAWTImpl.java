package mx.mjkhajl.media.control.server.tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import mx.mjkhajl.media.control.server.MediaControlServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MediaControlTrayIconAWTImpl implements MediaControlTrayIcon{
	
	@Value("classpath:mx/mjkhajl/media/control/server/resources/img/bulb.gif")
	private Resource imgIcon;
	
	@Value("Media control server")
	private String helpMessage;
	
	@Value("Media Control Server\n\n\tAuthor: Mjkhajl\n\tE-mail: luismiguel.arteaga@gmail.com\n\n2013")
	private String aboutMessage;
	
	@Autowired
	private MediaControlServer mcServer; 
	
	private final SystemTray tray = SystemTray.getSystemTray();
	private TrayIcon trayIcon;
	
	@Override
	public void show() {

		UIManager.put("swing.boldMetal", Boolean.FALSE);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		tray.remove(trayIcon);
		mcServer.stop();
		System.exit(0);
	}

	private void createAndShowGUI() {
		// Check the SystemTray support
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		trayIcon = new TrayIcon( getImageIcon() );
		trayIcon.setToolTip(helpMessage);
		final PopupMenu popup = new PopupMenu();
		
		// Create a popup menu components
		final MenuItem aboutItem = new MenuItem("About");
		final MenuItem exitItem = new MenuItem("Exit");

		// Add components to popup menu
		popup.add(aboutItem);
		popup.addSeparator();
		popup.add(exitItem);

		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
			return;
		}

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, aboutMessage );
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destroy();
			}
		});
	}

	// Obtain the image URL
	protected Image getImageIcon() {
		try {
			URL imageURL = imgIcon.getURL();
			return (new ImageIcon(imageURL)).getImage();
		} catch (IOException e) {
			throw new RuntimeException( "Error loading image icon: " + imgIcon, e );
		}
	}
}
