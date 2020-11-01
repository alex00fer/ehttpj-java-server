package io.github.alex00fer.ehttpj;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * This is an HttpHandler that provides a direct link between
 * an HTTP server path and a local folder on this computer. <br>
 * This is very useful for providing access through the internet
 * to files (like images, css or javascript files) directly mapped
 * to a folder. <br>
 * This object is added as a routing in the {@link EHttpServer} <br>
 * Note that this is not an abstract class so there is no point
 * on extending this unless you need extra functionality.
 */
public class EHttpStatic implements HttpHandler {

	private String staticFolder, baseRoute;
	
	/**
	 * Usage example: In you HTML page you have an image src to /static/img.jpg. Now on the
	 * EHttpServer you add a new routing like this: <br>
	 * {@code server.addRouting("/static", new EHttpStatic("/static", "webfiles"))} <br>
	 * Then you only need put the image img.jpg inside the 'webfiles' folder and the
	 * image is already accesible.
	 * @param routing This needs to be the <b>same</b> path that you used to add this
	 * object to the server routing table.
	 * @param folderPath The local folder with the static content. Can be an absolute path
	 * or a relave path dependant on the project location.
	 */
	public EHttpStatic(String routing, String folderPath) {
		this.staticFolder = folderPath;
		this.baseRoute = routing;
	}
	
	@Override
	public void handle(HttpExchange he) throws IOException {
		
		try {
			// Fill stream and prepare headers
			String file = he.getRequestURI().getPath().replace(baseRoute, "");
			//System.out.println(file);
			
			Path filePath = Paths.get(staticFolder, file);
			File reqFile = new File(filePath.toString());
			//System.out.println(reqFile + " " +  reqFile.exists());
			
			if (reqFile.exists()) {
				// Get and set content type
				String mimeType = Files.probeContentType(filePath);
				//System.out.println(mimeType);
				List<String> contentTypeList = new ArrayList<String>();
				contentTypeList.add(mimeType);
				he.getResponseHeaders().put("Content-Type", contentTypeList);
				// Set required header
				he.sendResponseHeaders(200, reqFile.length());
				// Copy file to output stream and finish the request
				Files.copy(filePath, he.getResponseBody());
				he.getResponseBody().close();
			}
			else {
				// requested file not found
				he.sendResponseHeaders(404, 0);
				he.getResponseBody().close();
			}

		} catch (Exception e) {
			System.err.println("Exception while serving static content " + he.getRequestURI().toString() + 
					" at "  + this.getClass().getName());
			e.printStackTrace();

			// Closes the connection with internal server error 500
			he.sendResponseHeaders(500, 0);
			he.getResponseBody().close();
		}

		
	}

}
