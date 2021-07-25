package fr.djredstone.botdiscord.listener;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class messageReactionAddListener extends ListenerAdapter {
	
	Main main;
	
	public messageReactionAddListener(Main main) {
		this.main = main;
	}

	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		
		for(String ID : main.P4startMessageID) {
			
			if(event.getMessageId().equalsIgnoreCase(ID)) {
				
				if(!event.getMember().getUser().equals(event.getJDA().getSelfUser())) {
					
					if(event.getReaction().getReactionEmote().getEmoji().equalsIgnoreCase("⚔️")) {
						
						event.getChannel().sendMessage("Test réussi !").queue();
						event.getTextChannel().retrieveMessageById(event.getMessageId()).complete().delete().queue();

					}
					
				}
				
			}
			
		}
		
	}

}
