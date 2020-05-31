package controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import localization.BundleInfo;


@WebServlet("/resultBundleServlet")
public class ResourceBundleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String resultBundleName; 
	public ResourceBundleServlet() {
        super();
    }
    
	public void init(){
		resultBundleName = getServletContext().getInitParameter("resBundleName"); 
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
		HttpSession session = request.getSession();
		ResourceBundle parametersResult = (ResourceBundle)session.getAttribute("resultBundle");	
		
		if(parametersResult == null){
			Locale locale = new Locale("pl"); // request.getLocale();
			parametersResult = ResourceBundle.getBundle(resultBundleName, locale);
			session.setAttribute("resultBundle",parametersResult);
			
			BundleInfo.generateInfo(parametersResult);
		}
		// include ? 
    }
}
