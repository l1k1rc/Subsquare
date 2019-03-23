package engine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeSimulator
{	
	private Calendar calendar = Calendar.getInstance();;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy",Locale.ENGLISH);
	
	private int am_pm;

	private String currentMonth;
	private String currentDay;
		
	public TimeSimulator() {
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.HOUR,0);
		currentMonth = getMounth();
		currentDay = getDay();
		am_pm = calendar.get(Calendar.AM_PM);
	}

	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}
	
	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}
	
	public int getHour() {
		return calendar.get(Calendar.HOUR);
	}
	
	public String getTime() {
	    String ampm = "ampm";
	    if (am_pm == 0)
	        ampm = "AM";
	    else
	        ampm = "PM";
		return getHour()+"h:"+getMinute()+"min"+" "+ampm;
	}
	//am if = 0 pm else
	public boolean AM_PM() {
		return am_pm == 0;
	}
	
	public String getDay() {
		return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
	}
	
	public String getMounth() {
		return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
	}
	
	public String getDate() {
		return sdf.format(calendar.getTime());
	}
	
	public boolean isWeekEnd() {
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return day == Calendar.SATURDAY || day == Calendar.SUNDAY;
	}
	
	public boolean isEndOfMonth() {
		if(!getMounth().equals(currentMonth)) {
			currentMonth = getMounth();
			return true;
		}
		return false;
	}
	
	public boolean isEndOfDay() {
		if(!getDay().equals(currentDay)) {
			currentDay = getDay();
			return true;
		}
		return false;
	}
	
	public void update() {
		calendar.add(Calendar.MINUTE,20);
		am_pm = calendar.get(Calendar.AM_PM);
	}
}