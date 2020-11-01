package io.github.alex00fer.ehttpj.html;

/**
 * An HTML h1 to h6 element
 */
public class EHtmlHeading extends EHtmlElement {

	/**
	 * Default h1 heading
	 * @param text Heading text
	 */
	public EHtmlHeading(String text) {
		super("h1");
		innerText = text;
	}
	
	/**
	 * @param text Heading text
	 * @param importance  Heading importance from 1 (default) to 6, both inclusive
	 */
	public EHtmlHeading(String text, int importance) {
		super("h" + importance);
		innerText = text;
	}

}
