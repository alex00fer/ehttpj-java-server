import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import io.github.alex00fer.ehttpj.*;
import io.github.alex00fer.ehttpj.html.EHtmlFrame;
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
import io.github.alex00fer.ehttpj.samples.EHttpSample;

public class Main {

	public static void main(String[] args) throws Exception {
		
		new EHttpSample().start();
		EHttpUtils.tryOpenWebpage("http://localhost:6776/");
	}

}
