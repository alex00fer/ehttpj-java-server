package io.github.alex00fer.ehttpj.html;

/**
 * An HTML anchor element. Aka a link
 */
public class EHtmlHyperlink extends EHtmlElement{

	/**
	 * @param href The link to be followed
	 * @param text The text for the link
	 * @param newTab Whether the link should be openend in a new tab or not
	 */
	public EHtmlHyperlink(String href, String text, boolean newTab) {
		super("a");
		
		innerText = text;
		setAttribute("href", href);
		if (newTab) {
			setAttribute("target", "_blank");
		}
	}
	
	/**
	 * Changes the link after being created
	 */
	public void setHref(String href) {
		setAttribute("href", href);
	}

}
