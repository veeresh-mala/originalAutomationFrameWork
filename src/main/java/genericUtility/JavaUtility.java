package genericUtility;

import java.util.Date;
import java.util.Random;

/*
 * This method is used to get the random number;
 */
public class JavaUtility {
	public int toGerRandomNumber() {
		Random r = new Random();
		int randomNumber = r.nextInt(1000);
		return randomNumber;
	}

	/*
	 * This method is used to get system time and date
	 */
	public String toGetSystemDateAndTime() {
		Date d = new Date();
		String[] date = d.toString().split(" ");
		String day = date[0];
		String month = date[1];
		String date1 = date[2];
		String time = date[3].replace(":", "-");
		String year = date[5];
		String finalDate = day + " " + month + " " + date1 + " " + time + " " + year;
		return finalDate;
	}
}
