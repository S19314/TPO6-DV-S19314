package controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import localization.BundleInfo;
import controller.command.Command;
import exception.NoCommandException;
//import controller.*;
import controller.command.*;
/**
 * Servlet implementation class ContollerServlet
 */
@WebServlet("/ContollerServlet")
public class ContollerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ServletContext context;
	private Command command;
	private String presentationServlet;
	private String getParametersServlet;
	private Object lock = new Object();
	
	public void init(){
		context = getServletContext();
		
		presentationServlet = context.getInitParameter("presentationServlet");
		getParametersServlet = context.getInitParameter("getParametersServlet");
		String commandClassName = context.getInitParameter("commandClassName");
		
		try{
			Class commandClass = Class.forName(commandClassName);
			command = (Command) commandClass.newInstance();

		}catch(Exception exc){
			throw new NoCommandException("Nie mogę stworczyć obiektu klasy commandClassName w Servlecie " + this.getServletName() +  " __ " + exc.getMessage());
		}
		
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
		response.setContentType("text/html");
		
		RequestDispatcher dispatcher = context.getRequestDispatcher(getParametersServlet);
		dispatcher.include(request, response);
	//	/ *
		HttpSession session = request.getSession();
		String[] parameterNames = BundleInfo.getCommandParameterNames();
		for(int i = 0; i < parameterNames.length; i++){
			String parameterValue = (String)session.getAttribute("parameter_"+parameterNames[i]);
			// if(para,m == null)
			command.setParameter(parameterNames[i], parameterValue);
		}
		// command.setParameter("cena_od", value);
		
		Lock mainLock = new ReentrantLock();
		mainLock.lock();
		command.execute();
		List<Object> results = (List)command.getResults();
		System.out.println("Results " + (results).toString());
		session.setAttribute("Results", results);
		session.setAttribute("Lock", mainLock);
		
		dispatcher = context.getRequestDispatcher(presentationServlet);
		dispatcher.forward(request, response);
   // * /
   }
	

}
