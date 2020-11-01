package io.github.alex00fer.httpjes.html;

import io.github.alex00fer.httpjes.html.internal.EHtmlElementRaw;

/**
 * A generic void (aka self-closing) HTML element.
 * This elements cannot have any content inside
 */
public class EHtmlElementVoid extends EHtmlElementRaw {

	public EHtmlElementVoid(String tag) {
		super(tag);
	}
	
	public String toHtml() {
		// All is needed for a void tag is the "opening tag"
		return getHtmlOpeningTag(); // e.g <img src="a.jpg">
	}

}
