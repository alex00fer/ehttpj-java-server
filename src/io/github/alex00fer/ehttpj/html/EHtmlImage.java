package io.github.alex00fer.ehttpj.html;

/**
 * An HTML image
 */
public class EHtmlImage extends EHtmlElementVoid {

	/**
	 * @param src The URI of the image on the server. It can be either relative or absolute
	 */
	public EHtmlImage(String src) {
		super("img");
		
		setAttribute("src", src);
	}
	
	/**
	 * @param src The URI of the image on the server. It can be either relative or absolute
	 * @param width The width of the image in px
	 * @param height The height of the image in px
	 */
	public EHtmlImage(String src, int width, int height) {
		this(src);
		
		setAttribute("width", width + ""); // +"" used to be converted to a string
		setAttribute("width", height + "");
	}
	
	
	/**
	 * @param src The URI of the image on the server. It can be either relative or absolute
	 * @param alt A description of the image. Important for accesibility.
	 */
	public EHtmlImage(String src, String alt) {
		this(src);

		setAttribute("alt", alt);
	}
}
