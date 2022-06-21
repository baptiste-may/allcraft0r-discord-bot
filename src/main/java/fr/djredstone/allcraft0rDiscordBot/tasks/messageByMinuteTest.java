package fr.djredstone.allcraft0rDiscordBot.tasks;

import java.util.Timer;
import java.util.TimerTask;

import fr.djredstone.allcraft0rDiscordBot.Main;

public class messageByMinuteTest {

	public messageByMinuteTest() {

		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {

				Main.getMessageByMinute().clear();
				
			}
		}, 0, 60*1000);
		
	}

}
