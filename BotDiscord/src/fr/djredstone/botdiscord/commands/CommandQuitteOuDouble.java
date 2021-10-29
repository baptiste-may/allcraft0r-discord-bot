package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandQuitteOuDouble extends ListenerAdapter {
	
	private HashMap<User, Integer> mise = new HashMap<>();
	public HashMap<String, User> messagesID = new HashMap<>();
	
	Random r = new Random();
	
	private void error(SlashCommandEvent event) {
		event.reply("Utilisation : " + Main.prefix + "quitteoudouble <nombre>").queue();
	}
	
	public void onSlashCommand(SlashCommandEvent event) {
    
        if(event.getName().equalsIgnoreCase("quitteoudouble")) {
        	
        	if(mise.get(event.getUser()) == null) {
        		
        		try {
    				
    				int nb = (int) event.getOption("nb_depart_mise").getAsLong();
    				int userMoney = Main.getMoney(event.getUser());
    				
    				if(nb < userMoney) {
    					
    					mise.put(event.getUser(), nb);
    					Main.setMoney(event.getUser(), Main.getMoney(event.getUser()) - nb);
    					
    					EmbedBuilder embed = new EmbedBuilder();
    					embed.setTitle("Quitte ou double !");
    					embed.setDescription(event.getUser().getAsMention() + ", tu proposes **" + mise.get(event.getUser()) + " redstones**. Vas-tu tenter le double ?");
    					embed.setColor(Color.ORANGE);
    					
    					event.getChannel().sendMessage(embed.build()).queue(message -> {
    						messagesID.put(message.getId(), event.getUser());
    						message.addReaction("✅").queue();
    						message.addReaction("❌").queue();
    					});
    					
    				} else {
    					event.getChannel().sendMessage(event.getUser().getAsMention() + ", tu n'as pas assez de redstones !").queue();
    				}
    				
    			} catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
    				error(event);
    			}

			}
        	
        }
        
	}
	
	public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
		
		if(messagesID.get(event.getMessageId()) == event.getUser()) {
			
			if(event.getReactionEmote().getEmoji().equalsIgnoreCase("✅")) {
				
				if(r.nextBoolean()) {
					
					mise.put(event.getUser(), mise.get(event.getUser())*2);
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("Quitte ou double !");
					embed.setDescription("Bravo " + Objects.requireNonNull(event.getUser()).getAsMention() + "! Ta mise est de **" + mise.get(event.getUser()) + " redstones** maintenant. Vas-tu tenter le double ?");
					embed.setColor(Color.GREEN);
					
					event.getChannel().retrieveMessageById(event.getMessageId()).queue(message -> messagesID.remove(message.getId()));
					
					event.getChannel().sendMessage(embed.build()).queue(message -> {
						messagesID.put(message.getId(), event.getUser());
						message.addReaction("✅").queue();
						message.addReaction("❌").queue();
					});
					
				} else {
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("Quitte ou double !");
					embed.setDescription("Dommage ! " + Objects.requireNonNull(event.getUser()).getAsMention() + ", tu viens de perdre la mise de **" + mise.get(event.getUser()) + " redstones** !");
					embed.setColor(Color.RED);
					
					event.getChannel().sendMessage(embed.build()).queue();
					
					mise.remove(event.getUser());
					
					event.getReaction().clearReactions().queue();
					event.getChannel().retrieveMessageById(event.getMessageId()).queue((message) -> {
						    for(int i = 0; i != message.getReactions().size(); i++) {
						    	message.getReactions().get(i).removeReaction().queue();
						    }
					});
					
					event.getChannel().retrieveMessageById(event.getMessageId()).queue(message -> {
						messagesID.remove(message.getId());
						message.delete().queue();
					});
					
				}
				
			} else if(event.getReactionEmote().getEmoji().equalsIgnoreCase("❌")) {
		
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("Quitte ou double !");
				embed.setDescription(Objects.requireNonNull(event.getUser()).getAsMention() + ", tu récupères **" + mise.get(event.getUser()) + " redstones** !");
				embed.setColor(Color.YELLOW);
				
				event.getChannel().sendMessage(embed.build()).queue();
				
				Main.setMoney(event.getUser(), Main.getMoney(event.getUser()) + mise.get(event.getUser()));
				
				mise.remove(event.getUser());
				
				event.getChannel().retrieveMessageById(event.getMessageId()).queue(message -> {
					messagesID.remove(message.getId());
					message.delete().queue();
				});
				
			}
			
			
		}
	}

}
