package org.bosco.jdk;

import java.sql.Timestamp;

public class TimeStampTest {

	public void testTimeStamp() {
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		Timestamp t2 = new Timestamp(System.currentTimeMillis());
		if (t1.after(t2))
		{
			System.out.println("t1 is after t2");
		}
		else {
			System.out.println("t2 is after t1");
		}
		
	}
}
