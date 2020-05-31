package localization;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class BundleInfo {
	 static private String[] commandParameterNames,
							commandParameterDescription,
							// statusMessage,
							headers,
							footers; //,
				//			resultDescription;
	static private String charset,
						  submitingMessage,
						  title,
						  cenaOd,
						  cenaDo;
	public static void generateInfo(ResourceBundle resourceBundle){
		synchronized(BundleInfo.class){
			List<Object> parameterNameList = new ArrayList<Object>(),
						 parameterValueList = new ArrayList<Object>();;
			 Enumeration keys = resourceBundle.getKeys();
			while(keys.hasMoreElements()){
				String key = (String)keys.nextElement();
				String templateFoParameters ="parameter_"; 
				if(key.startsWith(templateFoParameters)){
					parameterNameList.add(key.substring(templateFoParameters.length()));
					parameterValueList.add(resourceBundle.getString(key));
				}
				else if(key.equals("header")) headers = resourceBundle.getStringArray(key);
				else if(key.equals("footer")) footers = resourceBundle.getStringArray(key);
			//	else if(key.equals("resultDescription")) resultDescription = resourceBundle.getStringArray(key);
				else if(key.equals("charset")) charset = resourceBundle.getString(key);
				else if(key.equals("submit")) submitingMessage = resourceBundle.getString(key);
				else if(key.equals("title")) title = resourceBundle.getString(key);
				else if(key.equals("cena_do")) cenaDo = resourceBundle.getString(key);
				else if(key.equals("cena_od")) cenaOd = resourceBundle.getString(key);
			}
			commandParameterNames = (String[])parameterNameList.toArray(new String[0]);
			commandParameterDescription = (String[])parameterValueList.toArray(new String[0]);			
		}
	}
	public static String getCharset() {
	    return charset;
	  }

	  public static String getSubmitingMessage() {
	    return submitingMessage;
	  }

	    public static String[] getCommandParameterNames() {
	    return commandParameterNames;
	  }

	  public static String[] getCommandParamDescr() {
	    return commandParameterDescription;
	  }

	  /*
	  public static String[] getStatusMsg() {
	    return statusMsg;
	  }
	  
	  public static String[] getResultDescr() {
		    return resultDescr;
	  }
	   */
	  public static String[] getHeaders() {
	    return headers;
	  }

	  public static String[] getFooters() {
	    return footers;
	  }

	  public static String getCenaDo(){
	  		return cenaDo;
	  }
	  
	  public static String getCenaOd(){
	  		return cenaOd;
	  }
	  public static String getTitle(){
	  		return title;
	  }
}
	