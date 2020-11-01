import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import io.github.alex00fer.httpjes.*;
import io.github.alex00fer.httpjes.html.EHtmlFrame;
import io.github.alex00fer.httpjes.html.EHtmlHeading;
import io.github.alex00fer.httpjes.html.EHtmlHyperlink;
import io.github.alex00fer.httpjes.html.EHtmlImage;
import io.github.alex00fer.httpjes.html.EHtmlList;
import io.github.alex00fer.httpjes.html.EHtmlParagraph;
import io.github.alex00fer.httpjes.html.EHtmlRoot;
import io.github.alex00fer.httpjes.html.EHtmlTable;
import io.github.alex00fer.httpjes.html.internal.EHtml;
import io.github.alex00fer.httpjes.internal.EHttpExchange;
import io.github.alex00fer.httpjes.internal.EHttpRawPage;

public class Main {

	public static void main(String[] args) throws Exception {
		
		EHttpServer s = new EHttpServer(6776);
		s.addRouting("/raw", new EHttpRawPage() {
			@Override
			public void response(OutputStream os, EHttpExchange $) {
				try {
					os.write("<h1>Welcome. This page is being generated using EHttpRawPage</h1>".getBytes());
					os.write("<h3> Header User-Agent: </h3>".getBytes());
					os.write($.headerFirst("User-Agent").getBytes());
					os.write("<h3> Get parameters: </h3>".getBytes());
					for (Entry<String, Object> o : $.getAll().entrySet()) {
						os.write(" ".getBytes());
						os.write(o.toString().getBytes());
					}
					$.setContentType("text/html");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		s.addRouting("/", new EHttpPage() {
			
			@Override
			public EHtml content(EHttpExchange values) {
				EHtmlRoot doc = new EHtmlRoot("Hello world!");
				doc.setLanguage("en");
				EHtmlParagraph p = new EHtmlParagraph("Hola mundo");
				p.setTextSize(32, "px");
				p.addStyle("color", "red");
				p.setId("hola");
				p.addClass("hola");
				p.addClass("mundo");
				p.innerText += "2";
				EHtmlImage img = new EHtmlImage("/static/image.png", "glass");
				EHtmlHeading h = new EHtmlHeading("Welcome!", 3);
				EHtmlHyperlink link = new EHtmlHyperlink("/static/image.png", "View image", true);
				EHtmlList list = new EHtmlList(false);
				list.newListItem().add(new EHtmlParagraph("A"));
				list.newListItem().add(new EHtmlParagraph("B"));
				EHtmlTable table = new EHtmlTable("Table example");
				table.newHeaderRow("A", "B", "C");
				table.newRow("D", "E", "F");
				doc.addCSS("/static/style.css");
				doc.add(p);
				doc.add(img);
				doc.add(h);
				doc.add(link);
				doc.add(list);
				doc.add(table);
				return doc;
			}
		});
		
		s.addRouting("/static", new EHttpStatic("/static", "static"));
		
		s.start();
		
		
		EHttpUtils.tryOpenWebpage("http://localhost:6776/");
		
		

	}

}
