package controller;

import java.io.Serializable;
import controller.command.*;
public class FindBooksCommand extends CommandImpl implements Serializable {
	public FindBooksCommand(){}
	public void execute(){
		
		// ������������ � �� � ��������� ����������
		clearResult();
		String name_book = (String)getParameter("name_book");
		String name_autor = (String)getParameter("name_autor");
		String name_wydawcy = (String)getParameter("name_wydawcy");
		String rok = (String)getParameter("rok");
		String cena_od = (String)getParameter("cena_od");
		String cena_do = (String)getParameter("cena_do");
		
		addResult(name_book);
		addResult(name_autor); 
		addResult(name_wydawcy); 
		addResult(rok); 
		addResult(cena_od); 
		addResult(cena_do); 
	}
}
