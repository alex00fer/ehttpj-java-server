package io.github.alex00fer.ehttpj;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Creates a new HTTP server object. Use start() once you want to launch it. <br>
 * Use addRouting() to add new pages in a new route. You will have to create an HttpHandler
 * page. This can be a EHttpPage derived class. <br>
 * 
 * Then you probably will probably want to create static access to files (images or CSS files for example)
 * through the web. To do so you can have to add routing with a {@link EHttpStatic} object. <br><br>
 * 
 * A simple usage example:
 * <pre>
 * {@code
 * EHttpServer server = new EHttpServer(80);
 * server.addRouting("/", new MyFirstPage());
 * server.start();
 * }
 * </pre>
 */
public class EHttpServer {
	
	int port;
	HttpServer server;
	
	/**
	 * @param port The port the server will be initialized (i.e 8080)
	 * @throws Exception Probably a Bind Exception when a port is already on use
	 */
	public EHttpServer(int port) throws Exception {
		this.port = port;
		server = HttpServer.create(
				new InetSocketAddress(port),
				0 /*maximum queued TCP connections*/
		);
	}
	
	/**
	 * Starts the http server
	 */
	public void start() {
		server.setExecutor(null); // creates a new executor
		server.start();
	}
	
	
	/**
	 * Adds a new routing table to a relative URI path
	 * @param url The relative URI path the page/contents will be served in (i.e /welcome)
	 * @param handler The object that will receive the http request 
	 * (i.e a {@link EHttpPage} or {@link EHttpStatic})
	 */
	public void addRouting(String url, HttpHandler handler) {
		server.createContext(url, handler);
	}
	

}
