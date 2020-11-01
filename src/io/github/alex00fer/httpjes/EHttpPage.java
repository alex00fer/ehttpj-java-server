package io.github.alex00fer.httpjes;

import java.io.IOException;
import java.io.OutputStream;

import io.github.alex00fer.httpjes.html.internal.EHtml;
import io.github.alex00fer.httpjes.internal.EHttpExchange;
import io.github.alex00fer.httpjes.internal.EHttpRawPage;

/**
 * A page that can be added to a server routing table. <br>
 * This is an abstract class so you have to derive a class from this
 * one and implement the content method. This method will be used to
 * create the page content.
 */
public abstract class EHttpPage extends EHttpRawPage{

	@Override
	public void response(OutputStream os, EHttpExchange values) throws IOException {
		
		EHtml htmlContent = content(values);
		String content = htmlContent.toHtml();
		
		// NOTE This can consume triple the memmory of the content size, something that can
		// be a problem if the string is +very+ large as it is first copied with getBytes
		// and then is copied again to the stream. A buffer based write directly from the string
		// may be a better idea.
		os.write(content.getBytes()); 
	}
	
	
	/**
	 * The method for generating the web content. <br>
	 * You must create and then return an EHtml object. This can be any EHtml element that
	 * can be found on {@code httpejs.html} package. You probably want to use a EHtmlRoot element
	 * 
	 * @param values Http exchange that has extra information of the request
	 * and response
	 * @return You must return a EHtml element. It is highly recommended to be
	 * a EHtmlRoot element.
	 */
	public abstract EHtml content (EHttpExchange values);

}
