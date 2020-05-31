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
		{"cena_od", "Cena od"},
		{"cena_do", "Cena do"},
		{"submit", "Pokaż wynik wyszukiwania"},
		{"footer", new String[]{}}
		// {"resCode", new String[]{}}
		
	};
	
	public Object[][] getContents(){
		return contents;
	}
}
