package com.akm.myserver.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.akm.myserver.server.Main;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Handlers {
	public static class RootHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange he) throws IOException {
			String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + Main.port + "</h1>";
			response=new Handlers().readFileContent();
			he.sendResponseHeaders(200, response.length());
			Main.getServerGUIMaker().getRequestHeaderTextArea().setText(he.getRequestHeaders().toString());
			OutputStream os = he.getResponseBody();
			byte [] body= new byte[he.getRequestBody().available()];
			if(body.length>=0)
			{
				
				Main.getServerGUIMaker().getRequestBodyTextArea().setText(new String(body));
			}
			Main.getServerGUIMaker().getResposeHeaderTextArea().setText(he.getResponseHeaders().toString());
			Main.getServerGUIMaker().getResponseBodyTextArea().setText(response);
			os.write(response.getBytes());
			os.close();
		}
	}

	public static class EchoHeaderHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange he) throws IOException {
			Headers headers = he.getRequestHeaders();
			Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
			String response = "";
			for (Map.Entry<String, List<String>> entry : entries)
				response += entry.toString() + "\n";
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}
	}

	public static class EchoGetHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange he) throws IOException {
			// parse request
			Map<String, Object> parameters = new HashMap<String, Object>();
			URI requestedUri = he.getRequestURI();
			String query = requestedUri.getRawQuery();
			parseQuery(query, parameters);
			// send response
			String response =Main.getServerGUIMaker().getResponseBodyTextArea().getText();
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();
		}

	}

	public static class EchoPostHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange he) throws IOException {
			System.out.println("Served by /echoPost handler...");
			// parse request
			Main.getServerGUIMaker().getRequestHeaderTextArea().setText(he.getRequestHeaders().toString());
			// send response
			String response =Main.getServerGUIMaker().getResposeHeaderTextArea().getText();
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.toString().getBytes());
			os.close();

		}
	}

	@SuppressWarnings("unchecked")
	public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

		if (query != null) {
			String pairs[] = query.split("[&]");

			for (String pair : pairs) {
				String param[] = pair.split("[=]");

				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						List<String> values = (List<String>) obj;
						values.add(value);
					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}
	public String readFileContent()
	{
		String content="";
		
		File file=new File("C:\\Users\\JANVI\\Downloads\\Compressed\\SimpleJavaHttpServer\\src\\com\\happylife\\demo\\Handlers.java");
		if(file.exists() && file.isFile())
		{
			try {
				FileInputStream in= new FileInputStream(file);
				int size=in.available();
				byte [] bytes= new byte[size];
				in.read(bytes);
				content=new String(bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return content;
	}
}
