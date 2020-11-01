package io.github.alex00fer.ehttpj.internal;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import io.github.alex00fer.ehttpj.EHttpCookie;

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
 * If you want to redirect the user to another location use setRedirect(url)
 */
public class EHttpExchange {
	protected HttpExchange underExchange;
	protected Headers requestHeaders;
	protected Headers responseHeaders;
	protected Map<String, Object> get = new HashMap<String, Object>();
	protected Map<String, Object> post = new HashMap<String, Object>();
	protected Map<String, String> cookies = new HashMap<String, String>();
	protected List<String> responseContentType = new ArrayList<String>();
	protected int responseCode;
	
	public EHttpExchange(HttpExchange underExchange) {
		this.underExchange = underExchange;
		this.requestHeaders = underExchange.getRequestHeaders();
		this.responseHeaders = underExchange.getResponseHeaders();
		
		setContentType("text/html"); // default content type
		responseCode = 200; // default response code
	}
	
	/*
	private void loadCookies() {
		try {
			List<String> cookiesRaw = requestHeaders.get("Cookie");
			System.out.println(cookiesRaw);
			if (cookiesRaw != null) {
				for (String line : cookiesRaw) {
					
					String[] pairs = line.split(";");
					
					for (String pairText : pairs) {
						String[] pair = pairText.trim().split("=");
						cookies.put(pair[0], URLDecoder.decode(pair[1], "utf-8"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to load requested cookie(s)");
		}
		
	}
	*/
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
	
	public boolean hasCookie(String name) {
		return cookies.containsKey(name);
	}
	
	public String getCookie(String name) {
		return cookies.get(name);
	}
	
	public void addCookie(EHttpCookie cookie) {
		responseHeaders.add("Set-Cookie", cookie.toString());
	}
	
	public String headerFirst(String name) {
		return requestHeaders.getFirst(name);
	}
	
	public void setContentType (String type) {
		responseHeaders.set("Content-Type", type); 
	}
	
	public void setResponseCode(int code) {
		this.responseCode = code;
	}
	
	/**
	 * Sets the needed response code and Location header for causing
	 * a redirect on the client browser
	 * @param url The url the client will be redirected to (i.e. /account/login)
	 */
	public void setRedirect(String url) {
		this.responseCode = 302; // redirect default code
		responseHeaders.set("Location", url);
	}
	
	/**
	 *  Provides direct access to the underlying HttpExchange object
	 */
	public HttpExchange getUnderlyingExchange() {
		return underExchange;
	}
}