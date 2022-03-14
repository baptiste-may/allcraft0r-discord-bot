package fr.djredstone.botdiscord.commands.forAll;

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
import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandQuitteOuDouble extends ListenerAdapter {
	
	private static final HashMap<User, Integer> mise = new HashMap<>();
	private static final HashMap<User, String> IDs = new HashMap<>();
	
	private static final Random r = new Random();

	public CommandQuitteOuDouble(@Nullable String option, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		if (event1 == null && event2 == null) return;

		User user;
		if (event1 != null) user = event1.getAuthor();
		else user = event2.getUser();
        	
       	if(!mise.containsKey(user)) {

			int nb = 50;
			if (option != null) {
				try {
					nb = Integer.parseInt(option);
				} catch (NumberFormatException ignored) {
				}
			}

			int userMoney;
			try {
				userMoney = Main.getMoney(user);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}

			MessageChannel messageChannel;
			if (event1 != null) {
				messageChannel = event1.getChannel();
				event1.getMessage().delete().queue();
			}
			else messageChannel = event2.getChannel();

			if (nb < userMoney && nb > 0) {

				if (r.nextBoolean()) {
					try {
						Main.setMoney(user, Main.getMoney(user) - nb);
					} catch (SQLException e) {
						e.printStackTrace();
						return;
					}
					mise.put(user, nb*2);

					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("Quitte ou double !");
					embed.setDescription("Bravo " + Objects.requireNonNull(user).getAsMention() + "! Ta mise est de **" + mise.get(user) + " redstones** maintenant. Vas-tu tenter le double ?");
					embed.setColor(Color.GREEN);

					if (event2 != null) event2.reply("La partie a commencé !").setEphemeral(true).queue();
					messageChannel.sendMessageEmbeds(embed.build()).setActionRow(
							Button.success("qod_yes", "Oui"),
							Button.danger("qod_no", "Non")
					).queue(message -> IDs.put(user, message.getId()));

				} else {
					try {
						Main.setMoney(user, Main.getMoney(user) - nb);
					} catch (SQLException e) {
						e.printStackTrace();
						return;
					}

					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("Quitte ou double !");
					embed.setDescription("Dommage ! " + Objects.requireNonNull(user).getAsMention() + ", tu viens de perdre la mise de **" + nb + " redstones** !");
					embed.setColor(Color.RED);

					UtilsCommands.replyOrSend(embed, event1, event2);
				}

			} else UtilsCommands.replyOrSend(user.getAsMention() + ", tu n'as pas assez de redstones !", event1, event2);
		}
	}

	public void onButtonInteraction(ButtonInteractionEvent event) {

		if (IDs.containsKey(event.getUser())) if (!Objects.equals(IDs.get(event.getUser()), event.getMessageId())) return;
		int localMise = mise.get(event.getUser());

		if (event.getComponentId().equals("qod_yes")) {
			if (r.nextBoolean()) {
				mise.put(event.getUser(), localMise*2);

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
		} else if (event.getComponentId().equals("qod_no")) {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Quitte ou double !");
			embed.setDescription(Objects.requireNonNull(event.getUser()).getAsMention() + ", tu récupères **" + localMise + " redstones** !");
			embed.setColor(Color.YELLOW);

			event.editMessageEmbeds(embed.build()).setActionRows(new ArrayList<>()).queue();

			try {
				Main.setMoney(event.getUser(), Main.getMoney(event.getUser()) + localMise);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			mise.remove(event.getUser());
		}
	}
}
