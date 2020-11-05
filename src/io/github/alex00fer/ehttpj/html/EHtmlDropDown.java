package io.github.alex00fer.ehttpj.html;

/**
 * An HTML dropdown (the select tag). <br>
 * Use addOption() to add new options.
 */
public class EHtmlDropDown extends EHtmlElement {

	/**
	 * @param name The name of this field. In a form this will be the
	 * name for the value sent to the server.
	 */
	public EHtmlDropDown(String name) {
		super("select");
		this.setAttribute("name", name);
	}
	
	/**
	 * Adds a new option to this dropdown
	 * @param text The text that will be shown to the user
	 * @param value The value that will be sent to the server if it
	 * is used on a form
	 */
	public void addOption(String text, String value) {
		EHtmlElement opt = new EHtmlElement("option", text);
		opt.setAttribute("value", value);
		this.add(opt);
	}

}
