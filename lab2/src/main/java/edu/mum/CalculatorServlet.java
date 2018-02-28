package edu.mum;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int add1 = Integer.parseInt(req.getParameter("add1"));
		int add2 = Integer.parseInt(req.getParameter("add2"));
		int addResult = add1 + add2;
		req.setAttribute("add1", add1);
		req.setAttribute("add2", add2);
		req.setAttribute("addResult", addResult);

		int mul1 = Integer.parseInt(req.getParameter("mul1"));
		int mul2 = Integer.parseInt(req.getParameter("mul2"));
		int mulResult = mul1 * mul2;
		req.setAttribute("mul1", mul1);
		req.setAttribute("mul2", mul2);
		req.setAttribute("mulResult", mulResult);

		req.getRequestDispatcher("/default.jsp").forward(req, resp);
	}
}
