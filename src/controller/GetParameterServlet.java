package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import localization.BundleInfo;

@WebServlet("/getparameters")
public class GetParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletContext context;
    private String resultBundleServlet;
    public GetParameterServlet() {
        super();
    }

    public void init(){
    	context = getServletContext();
    	resultBundleServlet = context.getInitParameter("resultBundleServlet");    	
    }
    public void serviceRequest(HttpServletRequest request,
    						   HttpServletResponse response)
								throws ServletException, IOException
    {
    	RequestDispatcher dispatcher =  context.getRequestDispatcher(resultBundleServlet);
    	dispatcher.include(request, response);
    	
    	String charset = BundleInfo.getCharset(),
    		   headers[] = BundleInfo.getHeaders(),
    		   parameterNames[] = BundleInfo.getCommandParameterNames(),
    		   parametersDescription[] = BundleInfo.getCommandParamDescr(),
    		   submitingMessage = BundleInfo.getSubmitingMessage(),
    		   footers[] = BundleInfo.getFooters(),
    		   cena_do = BundleInfo.getCenaDo(),
			   cena_od = BundleInfo.getCenaOd();
    	
    	request.setCharacterEncoding(charset);
    	
    	
    	HttpSession session = request.getSession();
    	
    	response.setCharacterEncoding(charset);
    	PrintWriter out = response.getWriter();
    	out.println("<center><h2>"); 
    	for(int i =0; i < headers.length; i++)
    		out.println(headers[i] + "\n");
    	out.println("</h2></center><hr>");
    	
    	out.println("<form method= \\\"post\\\" >");
    	for(int  i = 0; i < parameterNames.length; i++){
    		out.println(parametersDescription[i] + "<br>");
    		out.print("<input type=\\\"text\\\" size=\\\"30\\\" name=\\"
    					+ parameterNames[i] + "\\\""
    				);
    		String parameterValue = (String) session.getAttribute("parameter_" + parameterNames[i]);
    		if(parameterValue != null) out.print(" value=\\\"" + parameterValue + "\\\"");
    		out.println("><br>");
    	}
    	out.println("<pre>" + cena_od + ":  		 	" + cena_do + ":\n" +
    			" <input type=\\\"text\\\" size=\\\"30\\\" name=\\\"cena_od\\\"> <input type=\\\"text\\\" size=\\\"30\\\" name=\\\"cena_do\\\"><pre/>");
    	out.println("<br><input type=\\\"submit\\\" value=\\\"" + submitingMessage + "\\\">");
    	out.println("</form>");
    
    	for(int i = 0; i < parameterNames.length; i++ ){
    		String parameterValue = request.getParameter(parameterNames[i]);
    		if(parameterValue == null) return;
    		session.setAttribute("parameter_" + parameterNames[i], parameterValue);
    		
    	}
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

}
