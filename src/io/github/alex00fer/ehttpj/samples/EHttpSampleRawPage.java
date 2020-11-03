package io.github.alex00fer.ehttpj.samples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map.Entry;

import io.github.alex00fer.ehttpj.internal.EHttpExchange;
import io.github.alex00fer.ehttpj.internal.EHttpRawPage;

public class EHttpSampleRawPage extends EHttpRawPage{
	
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
			os.write("<h3> Post parameters: </h3>".getBytes());
			for (Entry<String, Object> o : $.postAll().entrySet()) {
				os.write(" ".getBytes());
				os.write(o.toString().getBytes());
			}
			os.write("<h3> Has cookie 'hello'? </h3>".getBytes());
			if ($.hasCookie("hello")) 
				os.write($.getCookie("hello").getBytes());
			else
				os.write("No".getBytes());
			$.setContentType("text/html");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
