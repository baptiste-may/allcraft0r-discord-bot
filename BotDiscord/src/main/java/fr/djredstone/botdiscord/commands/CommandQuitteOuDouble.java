package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandQuitteOuDouble extends ListenerAdapter {
	
	private static final HashMap<User, Integer> mise = new HashMap<>();
	public static HashMap<String, User> messagesID = new HashMap<>();
	
	Random r = new Random();
	
	public CommandQuitteOuDouble(User user, String option, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		if(user == null) return;
        	
       	if(mise.get(user) == null) {

			try {

				int nb = Integer.parseInt(option);
				if (event2 != null) nb = (int) Objects.requireNonNull(event2.getOption("nb_max")).getAsLong();
				int userMoney;
				try {
					userMoney = Main.getMoney(user);
				} catch (SQLException e) {
					e.printStackTrace();
					return;
				}

				if (nb < userMoney) {

					mise.put(user, nb);
					try {
						Main.setMoney(user, Main.getMoney(user) - nb);
					} catch (SQLException e) {
						e.printStackTrace();
					}

					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("Quitte ou double !");
					embed.setDescription(user.getAsMention() + ", tu proposes **" + mise.get(user) + " redstones**. Vas-tu tenter le double ?");
					embed.setColor(Color.ORANGE);

					MessageChannel channel;
					if (event1 != null) channel = event1.getChannel();
					else {
						assert event2 != null;
						channel = event2.getTextChannel();
					}

					channel.sendMessageEmbeds(embed.build()).queue(message -> {
						messagesID.put(message.getId(), user);
						message.addReaction("✅").queue();
						message.addReaction("❌").queue();
					});
					channel.sendTyping().queue();
					if (event1 != null) event1.getMessage().delete().queue();

				} else UtilsCommands.replyOrSend(user.getAsMention() + ", tu n'as pas assez de redstones !", event1, event2);

			} catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
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
					
					event.getChannel().sendMessageEmbeds(embed.build()).queue(message -> {
						messagesID.put(message.getId(), event.getUser());
						message.addReaction("✅").queue();
						message.addReaction("❌").queue();
					});
					
				} else {
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("Quitte ou double !");
					embed.setDescription("Dommage ! " + Objects.requireNonNull(event.getUser()).getAsMention() + ", tu viens de perdre la mise de **" + mise.get(event.getUser()) + " redstones** !");
					embed.setColor(Color.RED);
					
					event.getChannel().sendMessageEmbeds(embed.build()).queue();
					
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
				
				event.getChannel().sendMessageEmbeds(embed.build()).queue();
				
				try {
					Main.setMoney(event.getUser(), Main.getMoney(event.getUser()) + mise.get(event.getUser()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				mise.remove(event.getUser());
				
				event.getChannel().retrieveMessageById(event.getMessageId()).queue(message -> {
					messagesID.remove(message.getId());
					message.delete().queue();
				});
				
			}
			
			
		}
	}

}
