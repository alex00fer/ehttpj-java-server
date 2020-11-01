package io.github.alex00fer.ehttpj.html;

/**
 * An HTML table element.
 * Use the newHeaderRow() and newRow() methods to add
 * content more easily.
 */
public class EHtmlTable extends EHtmlElement {

	/**
	 * @param tableCaption The caption to be used on top of the table.
	 */
	public EHtmlTable(String tableCaption) {
		super("table");
		
		if (tableCaption != null) {
			EHtmlElement caption = new EHtmlElement("caption");
			caption.innerText = tableCaption;
			this.add(caption);
		}
	}
	
	public EHtmlTable() {
		this(null);
	}
	
	/**
	 * Adds a header to the table with the columns names
	 * @param columns The column names of the table
	 */
	public void newHeaderRow(String... columns) {
		EHtmlElement tr = new EHtmlElement("tr");
		for (String value : columns) {
			EHtmlElement th = new EHtmlElement("th");
			th.innerText = value;
			tr.add(th);
		}
		this.add(tr);
	}
	
	/**
	 * Adds a new row of values
	 * @param values The new values to be added in each column
	 */
	public void newRow(String... values) {
		EHtmlElement tr = new EHtmlElement("tr");
		for (String value : values) {
			EHtmlElement th = new EHtmlElement("td");
			th.innerText = value;
			tr.add(th);
		}
		this.add(tr);
	}
	
}
