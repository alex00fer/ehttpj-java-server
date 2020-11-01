package io.github.alex00fer.ehttpj.html;
import io.github.alex00fer.ehttpj.html.internal.EHtmlElementList;
import io.github.alex00fer.ehttpj.html.internal.EHtmlElementRaw;

/**
 * The root element of HTML (the html tag). This includes the recommended
 * doctype declaration and charset UTF-8 by default.
 * 
 * Use addHead() to add new elements to the head of the html.
 * Use add() to add new elements to the body of the html.
 
 * You can have direct access to the head and body elements using getHeadElement()
 * and getBodyElement() respectively.
 * 
 * Use setLang() to set the recommended language attribute of the html.
 */
public class EHtmlRoot extends EHtmlElementRaw {
	
		private EHtmlElement head = new EHtmlElement("head");
		private EHtmlElement body = new EHtmlElement("body");

		/**
		 * @param title The page title
		 */
		public EHtmlRoot(String title) {
			super("html");
			
			// set document title
			EHtmlElement titleElement = new EHtmlElement("title");
			titleElement.innerText = title;
			head.add(titleElement);
			
			// set utf-8 charset
			EHtmlElementVoid meta = new EHtmlElementVoid("meta");
			meta.addAttribute("charset", "utf-8");
			head.add(meta);
		}
		
		public String toHtml() {
			StringBuilder sb = new StringBuilder();
			sb.append("<!doctype html>\n"); // recommended declaration
			sb.append(getHtmlOpeningTag()); // e.g <h1 class="red">
			sb.append(head.toHtml()); // all the inner head elements
			sb.append(body.toHtml()); // all the inner head elements
			sb.append(getHtmlClosingTag()); // e.g </h1>
			return sb.toString();
		}
		
		public void add (EHtmlElementRaw elmt) {
			body.add(elmt);
		}
		
		public void addHead (EHtmlElementRaw elmt) {
			head.add(elmt);
		}
		
		public EHtmlElement getHeadElement() {
			return head;
		}
		
		public EHtmlElement getBodyElement() {
			return body;
		}
		
		public void addMetaTag(String name, String content) {
			EHtmlElementVoid meta = new EHtmlElementVoid("meta");
			meta.addAttribute("name", name);
			meta.addAttribute("content", content);
			head.add(meta);
		}
		
		
		/**
		 * Sets the lang attribute to the html tag
		 * @param langCode (e.g. en)
		 */
		public void setLanguage(String langCode) {
			setAttribute("lang", langCode);
		}
		
		public void addCSS(String href) {
			EHtmlElementVoid link = new EHtmlElementVoid("link");
			link.addAttribute("rel", "stylesheet");
			link.addAttribute("href", href);
			head.add(link);
		}


}
