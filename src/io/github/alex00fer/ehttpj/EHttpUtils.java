package io.github.alex00fer.ehttpj;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A class that offers static methods for different utilities related
 * to this HTTP library and other useful web related things.
 */
public class EHttpUtils {
	
	/**
	 * Tries to open the given URL in the default user browser
	 * @return Whether it was sucessfully opened or not. However 
	 * this is <b>NOT GUARANTED</b> to be correct
	 */
	public static boolean tryOpenWebpage(String url) {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
				return false;
			}
		} else { // alternative method (windows only)
			Runtime rt = Runtime.getRuntime(); 
			try {
				rt.exec("start " + url);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true; // a website was apparently oppened
	}
}
