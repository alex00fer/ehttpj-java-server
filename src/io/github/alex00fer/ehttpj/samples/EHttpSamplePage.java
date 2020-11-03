package io.github.alex00fer.ehttpj.samples;

import io.github.alex00fer.ehttpj.*;
import io.github.alex00fer.ehttpj.html.*;
import io.github.alex00fer.ehttpj.html.internal.*;
import io.github.alex00fer.ehttpj.internal.EHttpExchange;

public class EHttpSamplePage extends EHttpPage {
	@Override
	public EHtml content(EHttpExchange values) {
		
		// Root html element that has the head and body elements
		// This will be the element that will be returned as an EHtml elment
		EHtmlRoot doc = new EHtmlRoot("Hello world!"); // <html><head>...</head><body>...</body></html>
		doc.setLanguage("en");
		// Lets start to create the page html elements
		// Create a heading
		EHtmlHeading heading = new EHtmlHeading("Hello world!", 1); // <h1>...</h1>
		// Create a paragraph text
		EHtmlParagraph p = new EHtmlParagraph("This page is being generated using EHtml elements"); //<p>...</p>
		p.setTextSize(20, "px"); // changge the text size
		p.addStyle("color", "green"); // change the text color
		p.addClass("sometext"); // add a class to the text element
		p.innerText += " from a EHttpPage!"; // modify the text by adding more text
		// Create an image
		EHtmlImage img = new EHtmlImage("/static/image.png", "picture"); //<img src="..." alt="...">
		// Add separator
		EHtmlHorizontalSeparator separator = new EHtmlHorizontalSeparator();
		// Create a link that opens in a new tab (true paramenter)
		EHtmlHyperlink link = new EHtmlHyperlink("/raw?foo=bar", "A link to another page", true); // <a href="..." target="_blank">...</a>
		// Unordered list. Change the false to true to be ordered
		EHtmlList list = new EHtmlList(false); // <ul>...</ul>
		// Creating a child list item returns itself so we can add something inside them at the same time.
		list.newListItem().add(new EHtmlParagraph("A")); //<li><p>...</p></li>
		list.newListItem().innerText = "B"; //<li>...</li>
		EHtmlTable table = new EHtmlTable("Table example"); // <table>...</table>
		table.addStyle("border", "1px solid black"); // custom in-line styling
		table.newHeaderRow("Name", "City", "Age"); //  add table columns
		table.newRow("John", "London", "24"); // add table row
		// Create a form for sending a name value to the raw using POST
		EHtmlForm form = new EHtmlForm("/raw", "POST");
		form.newLabeledInput("Your name: ", "text", "name");
		form.newSubmitButton("Submit");
		// Add all the created elements to the root element!!!
		doc.add(heading);
		doc.add(p);
		doc.add(img);
		doc.add(separator);
		doc.add(link);
		doc.add(list);
		doc.add(table);
		doc.add(separator);
		doc.add(form);
		doc.addCSS("/static/style.css"); // Adds a css in the head
		
		// Sets a cookie
		EHttpCookie c = new EHttpCookie("hello", "world");
		c.setMaxAge(604800); // lifespan of 7 days
		values.addCookie(c); // adds the cookie
		
		// Finally return the root html element
		return doc;
	}
}
