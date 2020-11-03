# EHttpJ 
EHttpJ is a **Java library** that provides an embedded HTTP server built on top of the [com.sun.net.httpserver](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html)


[TOC]


### Features
- Very flexible and modular design.
- Direct access to GET and POST paramters.
- Cookies support.
- Super-simple and intuitive server and pages creation.
- Ready to use for serving static resources like images or CSS files.
- Create the pages fully through Java code without any previous undestanding of HTML.
- All the common HTML tags are already mapped to Java classes.
- The generated HTML markup is completely correct (validated with W3C).
- Everything is well documented.
- Samples provided.
- Extra utilities included.

### Getting Started

##### The package structure
```
import io.github.alex00fer.ehttpj.*
```
- `EHttpServer` - The HTTP server
- `EHttpPage` - Abstract class for creating new pages
- `EHttpStatic` - For serving static content
- `EHttpUtils` - Other utilities


```
import io.github.alex00fer.ehttpj.html.*
```
- `EHtmlRoot` - The root element of any HTML page (the html tag)
- `EHtmlElement` - A generic customizable HTML element.
- `EHtmlElementVoid` - A generic customizable HTML void element (a.k.a. self closing tags).
- Many others common ready to use elements like `EHtmlHeading` `EHtmlParagraph` `EHtmlImage` `EHtmlForm` `EHtmlTable` `EHtmlHyperlink` `EHtmlList` and many more!

##### The server

Create an HTTP server object with the wanted port as a parameter (80 in this case)
```
EHttpServer server = new EHttpServer(80);
```
Add the routings with the created page objects (more on that later)
```
server.addRouting("/", new MyFirstPage());
server.addRouting("/static", new EHttpStatic("/static", "webcontent"));
```
And finally **start the server**
```
server.start();
```
The server now should be ready to be accessed at localhost:80

##### Your first page

```
public class MyFirstPage extends EHttpPage {

	@Override
	public EHtml content(EHttpExchange values) {
		
		// Root html element that has the head and body elements
		// This will be the element that will be returned as an EHtml element
		EHtmlRoot doc = new EHtmlRoot("Hello world!");
        
        // Set the page language as recommended per spec
		doc.setLanguage("en");
        
		// Create a heading
		EHtmlHeading heading = new EHtmlHeading("Hello world!", 1); 
		
		// Add the heading to the root element (its added inside its body)
		doc.add(heading);
		
		// Finally return the root html element
		return doc;
	}
}
```
See the included sample for a more complex page.

##### To be added...
- [ ] Static files access
- [ ] The EHttpExchange object
