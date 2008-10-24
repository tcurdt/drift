package org.vafer.drift.generator.generated;

public final class Migration {

	private static final Migration instance = new Migration();
	
	private Migration() {		
	}
	
	public static Migration getInstance() {
		return instance;
	}

	
	public String convert_odate_to_date(String s) {
		return s;
	}

	public int convert_dateTime_to_timestamp(String s) {
		return 0;
	}

	public String convert_theUri_to_theId(String s) {
		return s;
	}
	
	public String convert_date_to_dateTime(String s) {
		return s;
	}
	
	
	public String convert_d_to_c(String s) {
		return s;
	}
	
	public int convert_f_to_e(String s) {
		return 0;
	}
	
	public String convert_e_to_d(int i) {
		return "";
	}
	
}
