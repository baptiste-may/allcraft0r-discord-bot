package fr.djredstone.botdiscord.tasks;

import java.util.Timer;
import java.util.TimerTask;

import fr.djredstone.botdiscord.Main;

public class messageByMinuteTest {
	
	Timer timer;

	public messageByMinuteTest(Main main) {
		
		timer = new Timer();
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {

				Main.getMessageByMinute().clear();
				
			}
		}, 0, 60*1000);
		
	}

}
