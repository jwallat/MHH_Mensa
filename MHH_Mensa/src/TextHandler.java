import java.util.ArrayList;
import java.util.List;

public class TextHandler {

	private String menu;
	private List<String[]> wochenplan = new ArrayList<String[]>();
	
	
	public TextHandler(String menu) {
		this.menu = menu;
	}
	
	public void cleanText() {
		int monday = menu.indexOf("Mo");
		int tuesday = menu.indexOf("Di");
		int wendsday = menu.indexOf("Mi");
		int thursday = menu.indexOf("Do");
		int friday = menu.indexOf("Fr");
		int saturday = menu.indexOf("Sa");
		
		String montag = menu.substring(monday, tuesday);
		String dienstag = menu.substring(tuesday, wendsday);
		String mittwoch = menu.substring(wendsday, thursday);
		String donnerstag = menu.substring(thursday, friday);
		String freitag = menu.substring(friday, saturday);
		
		wochenplan.add(getDayMeal(montag));
		wochenplan.add(getDayMeal(dienstag));
		wochenplan.add(getDayMeal(mittwoch));
		wochenplan.add(getDayMeal(donnerstag));
		wochenplan.add(getDayMeal(freitag));
	}
	
	private String[] getDayMeal(String allDayMeals) {
		String[] gerichte = allDayMeals.split("(\\d+ kcal\\s*.\\s*\\d+ kJ)");
		
		for(int i = 0; i < gerichte.length; i++) {
			String gericht = gerichte[i];
			gericht = gericht.replaceAll(System.lineSeparator(), " ");
			if (gericht.contains("Mo") || gericht.contains("Di") || gericht.contains("Mi") || gericht.contains("Do") || gericht.contains("Fr")) {
				gericht = gericht.substring(3, gericht.length());
			}
			if (gericht.contains("Wochenende")) {
				gericht = "";
			}
			gericht = gericht.trim();
			//System.out.println(gericht);
			gerichte[i] = gericht;
		}
		
		return gerichte;
	}
	
	public void printMeals() {
		System.out.println("\n");
		for (int i = 0; i < wochenplan.size(); i++) {
			for (String string : wochenplan.get(i)) {
				System.out.println(string);
			}
			System.out.println("\n");
		}
		
	}
}
