package io.github.alex00fer.ehttpj.html;

import io.github.alex00fer.ehttpj.html.internal.EHtmlElementRaw;

/**
 * An HTML form
 */
public class EHtmlForm extends EHtmlElement {

	/**
	 * @param action The page the request will be sent
	 * @param method The method for the request (GET or POST)
	 */
	public EHtmlForm(String action, String method) {
		super("form");
		
		setAttribute("action", action);
		setAttribute("method", method);
	}
	
	
	/**
	 * Creates and adds to the form a new element under a label.
	 * This method is useful as a generic option in case of an element
	 * not being availiable to be added on any other methods.
	 * @param labelText The label text for this input
	 * @param element The EHtml element to be added to next to the label
	 * @return Returns the container just added to the form
	 */
	public EHtmlContainer newLabeledElement(String labelText, EHtmlElementRaw element) {
		
		EHtmlContainer cont = new EHtmlContainer();
		
		EHtmlElement label = new EHtmlElement("label");
		label.innerText = labelText;
		
		cont.add(label);
		cont.add(new EHtmlNewLine());
		cont.add(element);
		
		this.add(cont);
		
		return cont;
	}
	
	/**
	 * Creates a text input element with a label and is added to the form
	 * @param labelText The label text for this input
	 * @param inputType The type of the input (e.g. text)
	 * @param name The name for this input that will be sent to the server
	 * @return Returns the container just added to the form
	 */
	public EHtmlContainer newLabeledInput (String labelText, String inputType, String name) {
		
		EHtmlElement input = new EHtmlElement("input");
		input.setAttribute("type", inputType);
		
		return newLabeledElement(labelText, input);
	}
	
	/**
	 * Creates a submit button
	 * @param text The text of the button (e.g. Send)
	 * @param name The name for this button that will be sent
	 * to the server request. Useful to identify on the server
	 * which button was the one clicked.
	 * @return Returns the container just added that has the input button
	 */
	public EHtmlContainer newSubmitButton(String text, String name) {
		
		EHtmlContainer cont = new EHtmlContainer();
		
		EHtmlElement input = new EHtmlElement("input");
		input.setAttribute("type", "submit");
		input.setAttribute("value", text);
		input.setAttribute("name", name);
		
		cont.add(input);
		this.add(cont);
		return cont;
	}

}
