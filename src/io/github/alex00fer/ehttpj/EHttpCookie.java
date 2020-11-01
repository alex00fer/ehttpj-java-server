package io.github.alex00fer.ehttpj;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * An HTTP cookie. This class allows the creation of detailed cookies and
 * response header ready encoding.
 * <br>
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Set-Cookie
 */
public class EHttpCookie {
	
	private String name;
	private HashMap<String, String> pairs = new HashMap<String, String>();
	
	/**
	 * @param name The name should be just ASCII characters without any special chars
	 * @param value The cookie's value. It will be encoded using utf-8
	 */
	public EHttpCookie (String name, String value) {
		this.name = name;
		try {
			pairs.put(name, URLEncoder.encode(value, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.err.println("Cookie " + name + " failed to be encoded");
			pairs.put(name, ""); // fallback
		}
	}
	
	/**
	 * The maximum lifetime of the cookie as an HTTP-date timestamp
	 * @param date Date must be formatted like: Wed, 21 Oct 2015 07:28:00 GMT 
	 */
	public void setExpireDate (String date) {
		pairs.put("Expires", date);
	}
	
	/**
	 * Number of seconds until the cookie expires. A zero or negative number will expire the cookie immediately.
	 * @param seconds Number of seconds until the cookie expires
	 */
	public void setMaxAge (int seconds) {
		pairs.put("Max-Age", seconds+"");
	}
	
	/**
	 * Host to which the cookie will be sent. By default the cookies is associated
	 * to the host of the current document URL, not including subdomains.
	 * @param domain If a domain is specified, then subdomains are always included.
	 */
	public void setDomain (String domain) {
		pairs.put("Domain", domain);
	}
	
	/**
	 * This cookie will only be sent if the specified path exists in the requested URL
	 * @param path (i.e. /blog)
	 */
	public void setPath (String path) {
		pairs.put("Path", path);
	}
	
	
	/**
	 * The cookie is only sent to the server when a request is made with the https
	 */
	public void setSecure () {
		pairs.put("Secure", null);
	}
	
	/**
	 * Forbids JavaScript from accessing the cookie. This mitigates cross-site scripting attacks
	 */
	public void setHttpOnly () {
		pairs.put("HttpOnly", null);
	}
	
	/**
	 * Returns the cookie ready to be added to the Set-Cookie response header
	 */
	@Override
	public String toString() {
		LinkedList<String> pairsList = new LinkedList<String>();
		
		for (Entry<String, String> pair : pairs.entrySet()) {
			// the name-value needs to always be the first pair
			if (pair.getKey() == name) 
				pairsList.addFirst(encodePairForHeader(pair));
			else
				pairsList.addLast(encodePairForHeader(pair));
		}
		
		return String.join("; ", pairsList);
	}
	
	private String encodePairForHeader(Entry<String, String> p) {
		if (p.getValue() == null)
			return p.getKey();
		else 
			return String.format("%s=%s", p.getKey(), p.getValue());
	}
}
