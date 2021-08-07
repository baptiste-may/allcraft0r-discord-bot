package fr.djredstone.botdiscord.listener;

import java.awt.Color;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.P4Game;
import net.dv8tion.jda.api.EmbedBuilder;
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
						
						if(event.getMember().getUser().getId().equalsIgnoreCase(main.P4startMessageUser.get(event.getMessageId()))) {
							
							event.getReaction().removeReaction(event.getMember().getUser()).queue();
							
						} else {
							
							EmbedBuilder embed = new EmbedBuilder();
							embed.setTitle("**" + event.getMember().getUser().getName() + "** ⚔️ **" + Main.jda.getUserById(main.P4startMessageUser.get(event.getMessageId())).getName() + "**");
							embed.setDescription(":white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: \n"
									+ ":white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: \n"
									+ ":white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: \n"
									+ ":white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: \n"
									+ ":white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: \n"
									+ ":white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle: :white_circle:");
							embed.setAuthor("C'est au tour de " + event.getMember().getUser().getName());
							embed.setThumbnail("https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Puissance4_01.svg/1200px-Puissance4_01.svg.png");
							embed.setColor(Color.GREEN);
							
							event.getChannel().sendMessage(embed.build()).queue(message -> {
								message.addReaction("1️⃣").queue();
								message.addReaction("2️⃣").queue();
								message.addReaction("3️⃣").queue();
								message.addReaction("4️⃣").queue();
								message.addReaction("5️⃣").queue();
								message.addReaction("6️⃣").queue();
								});
							event.getTextChannel().retrieveMessageById(event.getMessageId()).complete().delete().queue();
							
							main.P4tour.put(event.getMessageId(), event.getMember().getId());
							main.P4firstPlayer.put(event.getMessageId(), event.getMember().getId());
							main.P4secondPlayer.put(event.getMessageId(), main.P4startMessageUser.get(event.getMessageId()));
							
						}
						

					} else {
						
						switch(event.getReaction().getReactionEmote().getEmoji()) {
							
							case "1️⃣":
								if(main.P4tour.get(event.getMessageId()).equalsIgnoreCase(main.P4firstPlayer.get(event.getMessageId()))) {
									P4Game.addToken(event.getMessageId(), 1, "P1");
								} else {
									P4Game.addToken(event.getMessageId(), 1, "P2");
								}
								P4Game.updateMessage(event.getMessageId());
								break;
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}

}
