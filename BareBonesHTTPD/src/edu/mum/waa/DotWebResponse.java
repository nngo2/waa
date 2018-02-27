package edu.mum.waa;

public class DotWebResponse {
	private static int counter = 0;

	private BBHttpRequest httpRequest;
	private BBHttpResponse httpResponse;

	public DotWebResponse(BBHttpRequest httpRequest, BBHttpResponse httpResponse) {
		this.httpRequest = httpRequest;
		this.httpResponse = httpResponse;
	}

	public BBHttpResponse process() {
		synchronized (this) {
			counter++;
		}
		
		StringBuilder response = new StringBuilder();
		response.append("<!DOCTYPE html>");
		response.append("<html>");
		response.append("<head>");
		response.append("<title>Class Name</title>");
		response.append("</head>");
		response.append("<body>");
		response.append("<p>Some static text</p>");
		response.append("<p>Some generated text, counter: " + counter + "</p>");
		response.append("</body>");
		response.append("</html>");
		
		httpResponse.setStatusCode(200);
		httpResponse.setMessage(response.toString());
		
		return httpResponse;
	}
}
