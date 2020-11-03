package io.github.alex00fer.ehttpj.samples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map.Entry;

import io.github.alex00fer.ehttpj.EHttpCookie;
import io.github.alex00fer.ehttpj.EHttpPage;
import io.github.alex00fer.ehttpj.EHttpServer;
import io.github.alex00fer.ehttpj.EHttpStatic;
import io.github.alex00fer.ehttpj.html.EHtmlHeading;
import io.github.alex00fer.ehttpj.html.EHtmlHyperlink;
import io.github.alex00fer.ehttpj.html.EHtmlImage;
import io.github.alex00fer.ehttpj.html.EHtmlList;
import io.github.alex00fer.ehttpj.html.EHtmlParagraph;
import io.github.alex00fer.ehttpj.html.EHtmlRoot;
import io.github.alex00fer.ehttpj.html.EHtmlTable;
import io.github.alex00fer.ehttpj.html.internal.EHtml;
import io.github.alex00fer.ehttpj.internal.EHttpExchange;
import io.github.alex00fer.ehttpj.internal.EHttpRawPage;

public class EHttpSample {
	
	public void start() throws Exception {
		
		// Create the http server on port 6776
		EHttpServer s = new EHttpServer(6776);
		// Add all the routings
		s.addRouting("/", new EHttpSamplePage());
		s.addRouting("/raw", new EHttpSampleRawPage());
		s.addRouting("/static", new EHttpStatic("/static", "webcontent"));
		// Start the server
		s.start();
		System.out.println("Server running at <http://localhost:6776>");
		
	}

}
