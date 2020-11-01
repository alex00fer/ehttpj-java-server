package io.github.alex00fer.httpjes.html;

import io.github.alex00fer.httpjes.html.internal.EHtmlElementList;
import io.github.alex00fer.httpjes.html.internal.EHtmlElementRaw;

/**
 * This element only prints its child elements, this
 * means that this does not have neither a tag nor any attributes.
 * Use add() to add new child elements.
 */
public class EHtmlFrame extends EHtmlElementRaw{
	
		private EHtmlElementList elements = new EHtmlElementList();

		public EHtmlFrame() {
			super("");
		}
		
		public String toHtml() {
			return elements.toHtml(); // returns all the inner elements html
		}
		
		public void add (EHtmlElementRaw elmt) {
			elements.add(elmt);
		}

}
