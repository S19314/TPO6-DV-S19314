package localization;

import java.util.ListResourceBundle;

public class LibraryParametersDefinition_pl extends ListResourceBundle {
	static final Object[][] contents = {
		{"charset", "UTF-8"},
		{"header", new String[]{"Library", "Szukać według"}},
		{"title", "Library"},
		{"parameter_name_book", "Nazwa książki"},
		{"parameter_name_autor", "Autor"},
		{"parameter_name_wydawcy", "Wydawca"},
		{"parameter_rok", "Rok"},
		{"parameter_cena", "Cena"},
		 {"parameter_cena_od", "Cena od"},    
		 {"parameter_cena_do", "Cena do"}, 
		// {"cena_od", "Cena od"}, // Równieź są parametrami, ale   
		// {"cena_do", "Cena do"}, // nie biorą udzial w tworzeniu widoku przez cykl
		{"submit", "Pokaż wynik wyszukiwania"},
		{"footer", new String[]{}}
		// {"resCode", new String[]{}}
		
	};
	
	public Object[][] getContents(){
		return contents;
	}
}
