package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.IterationTag;

import localization.BundleInfo;

@WebServlet("/presentation")
public class ResultPresent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResultPresent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		serviceRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	public void serviceRequest(HttpServletRequest request,
							   HttpServletResponse response)
								throws ServletException, IOException
	{
		
		ServletContext context = getServletContext();
		String getParameterServlet = context.getInitParameter("getParametersServlet");
		RequestDispatcher dispatcher = context.getRequestDispatcher(getParameterServlet);
		dispatcher.include(request, response);
		
		HttpSession session = request.getSession();
		Lock mainLock = (Lock) session.getAttribute("Lock");
		mainLock.unlock();
		List<Object> results = (List) session.getAttribute("Results");
		// Integer code = (Integer) session.getAttribue("StatusCode"); 
	
		PrintWriter out = response.getWriter();
		out.println("<hr>");
		/*
		  String msg = BundleInfo.getStatusMsg()[code.intValue()];
    	out.println("<h2>" + msg + "</h2>");
		 */
		// String[] dopiski = BundleInfo.getResultDesc
		// Wydrukowanie tablicy rezultatów
		out.println("<ul>");
		for(Iterator<Object> iterator = results.iterator(); iterator.hasNext(); ){
			out.println("<li>");
			// dopiski.
			Object result = iterator.next();
			if(result.getClass().isArray()){
				Object[] resultArray = (Object[])result;
				int i; 
				for( i = 0; i < resultArray.length; i++){
					out.print( resultArray[i]  + " ");
				}
			}
			out.println("</li>");
		}
	out.println("</ul>");
	}
}
