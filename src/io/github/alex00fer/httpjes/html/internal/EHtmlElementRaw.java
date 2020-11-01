package io.github.alex00fer.httpjes.html.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class EHtmlElementRaw implements EHtml{
	
	HashMap<String, String> attributes = new HashMap<String, String>();
	
	final private String TAG;
	
	/**
	 * @param tag The tag for the element without <> (e.g h1)
	 */
	public EHtmlElementRaw(String tag) {
		TAG = tag;
	}
	
	/**
	 * Adds a new custom attribute to this element.
	 * @param att Name of the attribute (e.g. src) 
	 * @param value The new content to be added. If a attribute already exists
	 * the new content will be concatenated in the end after a blank space
	 */
	public void addAttribute(String att, String value) {
		if (attributes.containsKey(att)) {
			// attribute already exists
			// modify the string to add the new value separated by a space
			attributes.put(att, attributes.get(att) + " " + value);
		}
		else {
			// add the new attribute and value
			attributes.put(att, value);
		}
	}
	
	
	/**
	 * Adds a new attribute to this element or replaces the existing content
	 * @param att Name of the attribute (e.g. src) 
	 * @param value The new content to be added or replaced.
	 */
	public void setAttribute(String att, String value) {
		attributes.put(att, value);
	}
	
	public void setId(String id) {
		setAttribute("id", id);
	}
	
	public void addClass(String className) {
		addAttribute ("class", className);
	}
	
	
	/**
	 * @param styleName (e.g. background-color)
	 * @param styleValue (e.g orange)
	 */
	public void addStyle(String styleName, String styleValue) {
		// Format as -> background-color:orange;
		String value = String.format("%s:%s;", styleName, styleValue);
		addAttribute ("style", value);
	}
	
	public void onClick(String jsAction) {
		setAttribute("onclick", jsAction);
	}
	
	protected String getHtmlAttributes() {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> element : attributes.entrySet()) {
			String att = element.getKey();
			String val = element.getValue();
			
			sb.append(' ');
			sb.append(att);
			sb.append("='");
			sb.append(val);
			sb.append("'"); // plus space in case there is another attribute
		}
		return sb.toString();
	}
	
	protected String getHtmlOpeningTag() {
		return String.format("<%s%s>\n", TAG, getHtmlAttributes());
	}
	protected String getHtmlClosingTag() {
		return String.format("</%s>\n", TAG);
	}
	
	public abstract String toHtml();

}
