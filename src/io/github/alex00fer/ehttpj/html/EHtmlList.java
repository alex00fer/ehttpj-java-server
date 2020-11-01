package io.github.alex00fer.ehttpj.html;

import io.github.alex00fer.ehttpj.html.internal.EHtmlElementRaw;

/**
 * An HTML list element.
 * Use newListItem() to create new list elements easily
 */
public class EHtmlList extends EHtmlElement {

	/**
	 * @param ordered Whether the list is ordered (ol) or unordered (ul)
	 */
	public EHtmlList(boolean ordered) {
		super(ordered ? "ol" : "ul");
	}
	
	public EHtmlElement newListItem() {
		EHtmlElement li = new EHtmlElement("li");
		this.add(li);
		return li;
	}

}
