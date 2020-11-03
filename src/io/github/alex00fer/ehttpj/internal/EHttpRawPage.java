package io.github.alex00fer.ehttpj.internal;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import sun.misc.IOUtils;

/**
 * This is an abstract class designed to work as a base class
 * for generating pages. This class creates the EHttpEchange
 * and prepare all its values. <br>
 * You need to use the response method for adding the page
 * content. <br><br>
 * The difference between this class and the EHttpPage class
 * is that this one generetes content directly to a binary
 * Output Stream while EHttpPage works with Strings and is
 * designed to be used along the prefabricated EHtml elements.
 * Also this is the base class for the EHttpPage class.
 */
public abstract class EHttpRawPage implements HttpHandler {

	@Override
	public void handle(HttpExchange he) throws IOException {
		//System.out.println(he.getRequestURI().getRawPath());
		try {
			// Read request values and save them on the EHttpExchange object
			// 1. Create the exchange object
			EHttpExchange exchange = new EHttpExchange(he); // passes the original httpexchange
			// 2. Read GET parameters
			URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            parseQuery(query, exchange.get);
            // 3. Read POST parameters
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            query = br.readLine();
            parseQuery(query, exchange.post);
            // 4. Load cookies
            try {
    			List<String> cookiesRaw = he.getRequestHeaders().get("Cookie");
    			if (cookiesRaw != null) {
    				for (String line : cookiesRaw) {
    					
    					String[] pairs = line.split(";");
    					
    					for (String pairText : pairs) {
    						String[] pair = pairText.trim().split("=");
    						exchange.cookies.put(pair[0], URLDecoder.decode(pair[1], "utf-8"));
    					}
    				}
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    			System.err.println("Failed to load requested cookie(s)");
    		}

			// Fill stream and prepare headers

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			response(outputStream, exchange); // calls abstract response method
			he.sendResponseHeaders(exchange.responseCode, outputStream.size());
			
			// Write to the final output response stream

			OutputStream stream = he.getResponseBody();
			outputStream.writeTo(stream); // copy contents of the temp stream to the response stream
			stream.close();

		} catch (Exception e) {
			System.err.println("Exception while serving page " + this.getClass().getName());
			e.printStackTrace();
			
			// Closes the connection with internal server error 500
			he.sendResponseHeaders(500, 0);
			he.getResponseBody().close();
		}

	}

	abstract public void response(OutputStream os, EHttpExchange values) throws Exception;

	public static void parseQuery(String query, Map<String, Object> parameters) 
			throws UnsupportedEncodingException {

		if (query != null) {
			String pairs[] = query.split("[&]");
			for (String pair : pairs) {
				String param[] = pair.split("[=]");
				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						@SuppressWarnings("unchecked") // always is a List<String>
						List<String> values = (List<String>) obj;
						values.add(value);

					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}

}
