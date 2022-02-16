package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import fr.djredstone.botdiscord.Main;

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

					channel.sendMessageEmbeds(embed.build()).setActionRow(
							Button.success("qod_yes", "Oui"),
							Button.danger("qod_no", "Non")).queue(message -> messagesID.put(message.getId(), user));

					if (event1 != null) event1.getMessage().delete().queue();

				} else UtilsCommands.replyOrSend(user.getAsMention() + ", tu n'as pas assez de redstones !", event1, event2);

			} catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
			}
		}
	}

	public void onButtonInteraction(ButtonInteractionEvent event) {
		
		if(messagesID.get(event.getMessageId()) == event.getUser()) {
			
			if(event.getComponentId().equals("qod_yes")) {
				
				if(r.nextBoolean()) {
					
					mise.put(event.getUser(), mise.get(event.getUser())*2);
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("Quitte ou double !");
					embed.setDescription("Bravo " + Objects.requireNonNull(event.getUser()).getAsMention() + "! Ta mise est de **" + mise.get(event.getUser()) + " redstones** maintenant. Vas-tu tenter le double ?");
					embed.setColor(Color.GREEN);

					event.editMessageEmbeds(embed.build()).setActionRow(
							Button.success("qod_yes", "Oui"),
							Button.danger("qod_no", "Non")).queue();
					
				} else {
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("Quitte ou double !");
					embed.setDescription("Dommage ! " + Objects.requireNonNull(event.getUser()).getAsMention() + ", tu viens de perdre la mise de **" + mise.get(event.getUser()) + " redstones** !");
					embed.setColor(Color.RED);

					event.editMessageEmbeds(embed.build()).setActionRows(new ArrayList<>()).queue();
					
					mise.remove(event.getUser());
					
				}
				
			} else if(event.getComponentId().equals("qod_no")) {
		
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("Quitte ou double !");
				embed.setDescription(Objects.requireNonNull(event.getUser()).getAsMention() + ", tu récupères **" + mise.get(event.getUser()) + " redstones** !");
				embed.setColor(Color.YELLOW);

				event.editMessageEmbeds(embed.build()).setActionRows(new ArrayList<>()).queue();
				
				try {
					Main.setMoney(event.getUser(), Main.getMoney(event.getUser()) + mise.get(event.getUser()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				mise.remove(event.getUser());

			}
		}
	}

}
