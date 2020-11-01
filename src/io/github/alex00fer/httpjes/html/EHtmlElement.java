package io.github.alex00fer.httpjes.html;

import io.github.alex00fer.httpjes.html.internal.EHtmlElementList;
import io.github.alex00fer.httpjes.html.internal.EHtmlElementRaw;

/**
 * A generic HTML element that has an open and close tag
 * and can have both text and other HTML elements inside.
 */
public class EHtmlElement extends EHtmlElementRaw {
	
	/**
	 * The inner text for this element. This also processes HTML tags
	 */
	public String innerText = ""; 
	
	private EHtmlElementList elements = new EHtmlElementList();

	public EHtmlElement(String tag) {
		super(tag);
	}
	
	public EHtmlElement(String tag, String innerText) {
		this(tag);
		this.innerText = innerText;
	}
	
	public String toHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append(getHtmlOpeningTag()); // e.g <h1 class="red">
		sb.append(innerText); // inner text if any
		sb.append(elements.toHtml()); // all the inner web elements
		sb.append(getHtmlClosingTag()); // e.g </h1>
		return sb.toString();
	}
	
	/**
	 * Adds a HTML element inside this element
	 */
	public void add (EHtmlElementRaw elmt) {
		elements.add(elmt);
	}

}
