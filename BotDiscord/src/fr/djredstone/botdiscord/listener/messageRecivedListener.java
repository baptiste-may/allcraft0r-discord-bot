package fr.djredstone.botdiscord.listener;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class messageRecivedListener extends ListenerAdapter {
	
	Main mainClass;
	
	public messageRecivedListener(Main main) {
		mainClass = main;
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		Main.messagesByDay = Main.messagesByDay + 1;
		
		if(!Main.activeMembers.contains(event.getAuthor())) Main.activeMembers.add(event.getAuthor());
		
		if(!Main.activeChannels.contains(event.getMessageId())) Main.activeChannels.add(event.getMessageId());
		
		if(!event.getAuthor().isBot()) {
			
			if(mainClass.messageByMinute.get(event.getAuthor()) == null) {
				
				mainClass.messageByMinute.put(event.getAuthor(), 0);
				
			}
			
			mainClass.messageByMinute.put(event.getAuthor(), mainClass.messageByMinute.get(event.getAuthor()) + 1);
			
			if(mainClass.messageByMinute.get(event.getAuthor()) > (Integer) mainClass.getConfig().get("minNbMessageWarn")) {
				
				Main.jda.getTextChannelById("497141089480998912").sendMessage("**" + event.getAuthor().getAsTag() + "** est � plus de " + mainClass.getConfig().get("minNbMessageWarn") + " messages par minutes (actuellement � " + mainClass.messageByMinute.get(event.getAuthor()) + ")").queue();
			}
			
		}
		
	}

}
