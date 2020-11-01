package io.github.alex00fer.httpjes.html;

/**
 * A p paragraph HTML element.
 * This has several useful formatting methods
 */
public class EHtmlParagraph extends EHtmlElement {

	public EHtmlParagraph(String text) {
		super("p"); // Element tag
		innerText = text;
	}
	
	/**
	 * @param number (e.g. 32)
	 * @param unit (e.g. px)
	 */
	public void setTextSize (int number, String unit) {
		addStyle("font-size", number + unit);
	}

}
