package io.github.alex00fer.httpjes.html.internal;

import java.util.ArrayList;
import java.util.List;

public class EHtmlElementList {
	
	List<EHtmlElementRaw> elements = new ArrayList<EHtmlElementRaw>();
	
	public void add(EHtmlElementRaw element) {
		elements.add(element);
	}
	
	public String toHtml() {
		StringBuilder sb = new StringBuilder();
		for (EHtmlElementRaw element : elements) {
			sb.append(element.toHtml());
		}
		return sb.toString();
	}

}
