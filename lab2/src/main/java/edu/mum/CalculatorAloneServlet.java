package edu.mum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="CalculatorAloneServlet", urlPatterns = "/calc")
public class CalculatorAloneServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String content = getHtmlContent().toString();
		PrintWriter out = resp.getWriter();
		out.write(content);
	}
	
	private String getHtmlContent() throws IOException {
		InputStream content = getServletContext().getResourceAsStream("/WEB-INF/jspform.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		StringBuilder responseText = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			responseText.append(line);
		}
		return responseText.toString();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int add1 = Integer.parseInt(req.getParameter("add1"));
		int add2 = Integer.parseInt(req.getParameter("add2"));
		int addResult = add1 + add2;

		int mul1 = Integer.parseInt(req.getParameter("mul1"));
		int mul2 = Integer.parseInt(req.getParameter("mul2"));
		int mulResult = mul1 * mul2;
		
		String content = getHtmlContent().toString();
		content = content.replace("add1Val", "" + add1);
		content = content.replace("add2Val", "" + add2);
		content = content.replace("addResultVal", "" + addResult);
		content = content.replace("mul1Val", "" + mul1);
		content = content.replace("mul2Val", "" + mul2);
		content = content.replace("mulResultVal", "" + mulResult);
		
		PrintWriter out = resp.getWriter();
		out.write(content);
	}
}
