package controller.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandImpl implements Serializable, Command {

	private Map<Object, Object> parameterMap = new HashMap<Object,Object>();
	private List<Object> resultList = new ArrayList<Object>();
	private int statusCode;
	public CommandImpl(){}
	
	public void init(){}
	public void setParameter(String name, Object value){
		parameterMap.put(name, value);
	}
	public Object getParameter(String name){
		return parameterMap.get(name);
	}
	public void execute(){}
	
	public List<Object> getResults(){
		return resultList;
	}
	public void addResult(Object result){
		resultList.add(result);
	}

	public void clearResult(){
		resultList.clear();
	}
	public void setStatusCode(int code){
		statusCode = code;
	}
	public int getStatusCode(){
		return statusCode;
	}

}
