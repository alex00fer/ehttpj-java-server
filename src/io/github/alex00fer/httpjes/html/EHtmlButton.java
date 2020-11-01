package io.github.alex00fer.httpjes.html;

public class EHtmlButton extends EHtmlElement {

	/**
	 * Creates an HTML button
	 * @param text The text of the button
	 * @param action The javascript action to be executed when clicked
	 */
	public EHtmlButton(String text, String action) {
		super("button");
		innerText = text;
		setAttribute("type", "button");
		onClick(action);
	}
}
