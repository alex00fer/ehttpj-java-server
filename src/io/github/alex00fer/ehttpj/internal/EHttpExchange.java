package io.github.alex00fer.ehttpj.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

/**
 * An object that contains the values of the http request
 * and the values assigned for the response.<br><br>
 * 
 * This class gives you access to the header, get and post values of the 
 * request. Use the given methods to access them.<br>
 * 
 * If you need deeper control you can get access to the underlying HttpExchange,
 * used for the HttpServer, with getUnderlyingExchange().<br>
 * 
 * 
 * Use setContentType() to change the MIME of the response (text/html by default).<br>
 * Use setResponseCode() to change the responsee code (200 by default).<br>
 * 
 * 
 */
public class EHttpExchange {
	protected HttpExchange underExchange;
	protected Headers headers;
	protected Map<String, Object> get = new HashMap<String, Object>();
	protected Map<String, Object> post = new HashMap<String, Object>();
	protected List<String> responseContentType = new ArrayList<String>();
	protected int responseCode = 200;
	
	public EHttpExchange() {
		responseContentType.add("text/html"); // default content type
	}
	
	public String get (String key) {
		if (get.containsKey(key))
			return get.get(key).toString();
		else
			return null;
	}
	
	public boolean hasGet (String name) {
		return get.containsKey(name);
	}

	public boolean hasPost (String name) {
		return post.containsKey(name);
	}
	
	
	
	public Map<String, Object> getAll() {
		return get;
	}

	public Map<String, Object> postAll() {
		return post;
	}

	public String post(String name) {
		if (post.containsKey(name))
			return get.get(name).toString();
		else
			return null;
	}
	
	public String headerFirst(String name) {
		return headers.getFirst(name);
	}
	
	public void setContentType (String type) {
		responseContentType.clear();
		responseContentType.add(type);
	}
	
	public void setResponseCode(int code) {
		this.responseCode = code;
	}
	
	/**
	 *  Provides direct access to the underlying HttpExchange object
	 */
	public HttpExchange getUnderlyingExchange() {
		return underExchange;
	}
}