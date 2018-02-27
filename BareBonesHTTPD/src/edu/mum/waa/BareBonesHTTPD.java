package edu.mum.waa;

import java.io.*;
import java.lang.reflect.Constructor;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BareBonesHTTPD extends Thread {

	private static final int PortNumber = 8080;
	
	private static final String RootDoc = "D:\\GitRepo\\Waa\\BareBonesHTTPD\\docs";
	
	private AppSetting configs;

	Socket connectedClient = null;

	public BareBonesHTTPD(Socket client) {
		connectedClient = client;
		configs = new AppSetting();
	}

	public void run() {

		try {
			System.out.println(connectedClient.getInetAddress() + ":" + connectedClient.getPort() + " is connected");

			BBHttpRequest httpRequest = getRequest(connectedClient.getInputStream());

			if (httpRequest != null) {
				BBHttpResponse httpResponse = new BBHttpResponse();
				
				if ("/".equals(httpRequest.getUri())) { // default code, print out request for the home page
					processRequest(httpRequest, httpResponse);
				} else if (isDotWebRequests(httpRequest.getUri())) {
					httpResponse = processDotWebRequest(httpRequest, httpResponse);
				} else if (isStaticFile(httpRequest.getUri())) {
					processFileRequest(httpRequest, httpResponse);
				} else {
					processError(404, "File Not Found", httpResponse);
				}
				
				sendResponse(httpResponse);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processError(int status, String mesage, BBHttpResponse httpResponse) {
		httpResponse.setMessage(mesage);
		httpResponse.setStatusCode(status);
	}
	
	private void processFileRequest(BBHttpRequest httpRequest, BBHttpResponse httpResponse) {
		StringBuilder response = new StringBuilder();
		try {
			response.append(new String(Files.readAllBytes(Paths.get(RootDoc + httpRequest.getUri()))));
			httpResponse.setStatusCode(200);
			httpResponse.setMessage(response.toString());
		} catch (IOException e) {
			e.printStackTrace();
			httpResponse.setStatusCode(500);
		}
	}
	
	private boolean isStaticFile(String uri) {
		File f = new File(RootDoc + uri);
		if (f.exists() && f.isFile()) {
			return true;
		}
	
		return false;
	}
	
	private BBHttpResponse processDotWebRequest(BBHttpRequest httpRequest, BBHttpResponse httpResponse) {
		//DotWebResponse resp = new DotWebResponse(httpRequest, httpResponse);
		String configClass = configs.getConfig(AppSetting.DOT_WEB_CLASS_CONFIG, AppSetting.DOT_WEB_CLASS_CONFIG_DEFAULT);
		DotWebResponse resp;
		try {
			Class c = Class.forName(configClass);
			Constructor constructor = c.getConstructor(new Class[] { BBHttpRequest.class, BBHttpResponse.class });
			resp = (DotWebResponse) constructor.newInstance(new Object[] { httpRequest, httpResponse });
		} catch (Exception e) {
			e.printStackTrace();
			resp = new DotWebResponse(httpRequest, httpResponse);
		}
		return resp.process();
	}
	
	private boolean isDotWebRequests(String uri) {
		//if (uri.equalsIgnoreCase("/welcome.web") || uri.equalsIgnoreCase("/contacts.web")) {
		String configUri = configs.getConfig(AppSetting.DOT_WEB_URI_CONFIG, AppSetting.DOT_WEB_URI_CONFIG_DEFAULT);
		if (configUri.indexOf(uri) >= 0) {
			return true;
		}
		return false;
	}

	private void processRequest(BBHttpRequest httpRequest, BBHttpResponse httpResponse) {

		StringBuilder response = new StringBuilder();
		response.append("<!DOCTYPE html>");
		response.append("<html>");
		response.append("<head>");
		response.append("<title>Almost an HTTP Server</title>");
		response.append("</head>");
		response.append("<body>");
		response.append("<h1>This is the HTTP Server</h1>");
		response.append("<h2>Your request was:</h2>\r\n");
		response.append("<h3>Request Line:</h3>\r\n");
		response.append(httpRequest.getStartLine());
		response.append("<br />");
		response.append("<h3> Header Fields: </h3>");
		for (String headerField : httpRequest.getFields()) {
			response.append(headerField.replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;"));
			response.append("<br />");
		}
		response.append("<h3> Payload: </h3>");
		for (String messageLine : httpRequest.getMessage()) {
			response.append(messageLine.replace("<", "&lt;").replace("&", "&amp;"));
			response.append("<br />");
		}
		response.append("</body>");
		response.append("</html>");

		httpResponse.setStatusCode(200);
		httpResponse.setMessage(response.toString());
	}
	
	private BBHttpRequest getRequest(InputStream inputStream) throws IOException {

		BBHttpRequest httpRequest = new BBHttpRequest();

		BufferedReader fromClient = new BufferedReader(new InputStreamReader(inputStream));

		String headerLine = fromClient.readLine();

		if ((headerLine == null)||(headerLine.isEmpty())) {
			return null;
		}

		System.out.println("The HTTP request is ....");
		System.out.println(headerLine);

		// Header Line
		StringTokenizer tokenizer = new StringTokenizer(headerLine);
		httpRequest.setMethod(tokenizer.nextToken());
		httpRequest.setUri(tokenizer.nextToken());
		httpRequest.setHttpVersion(tokenizer.nextToken());

		// Header Fields and Body
		boolean readingBody = false;
		ArrayList<String> fields = new ArrayList<>();
		ArrayList<String> body = new ArrayList<>();

		while (fromClient.ready()) {

			headerLine = fromClient.readLine();
			System.out.println(headerLine);

			if (!headerLine.isEmpty()) {
				if (readingBody) {
					body.add(headerLine);
				} else {
					fields.add(headerLine);
				}
			} else {
				readingBody = true;
			}
		}
		httpRequest.setFields(fields);
		httpRequest.setMessage(body);
		return httpRequest;
	}

	private void sendResponse(BBHttpResponse response) throws IOException {

		String statusLine = null;
		if (response.getStatusCode() == 200) {
			statusLine = "HTTP/1.1 200 OK" + "\r\n";
		} else {
			statusLine = "HTTP/1.1 501 Not Implemented" + "\r\n";
		}

		String serverdetails = "Server: BareBones HTTPServer" + "\r\n";
		String contentLengthLine = "Content-Length: " + response.getMessage().length() + "\r\n";
		String contentTypeLine = "Content-Type: " + response.getContentType() + " \r\n";

		try (DataOutputStream toClient = new DataOutputStream(connectedClient.getOutputStream())) {

			toClient.writeBytes(statusLine);
			toClient.writeBytes(serverdetails);
			toClient.writeBytes(contentTypeLine);
			toClient.writeBytes(contentLengthLine);
			toClient.writeBytes("Connection: close\r\n");
			toClient.writeBytes("\r\n");
			toClient.writeBytes(response.getMessage());

		}
	}

	public static void main(String args[]) throws Exception {

		try (ServerSocket server = new ServerSocket(PortNumber, 10, InetAddress.getByName("127.0.0.1"))) {
			System.out.println("Server Started on port " + PortNumber);

			while (true) {
				Socket connected = server.accept();
				(new BareBonesHTTPD(connected)).start();
			}
		}
	}
}
