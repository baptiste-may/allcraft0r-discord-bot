package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import javax.annotation.Nullable;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;
import fr.djredstone.allcraft0rDiscordBot.classes.money;

public class CommandSlot extends ListenerAdapter {

	private static final int max = 125;
	private static final int min = 75;

	public CommandSlot(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		if (event1 == null && event2 == null) return;

		User user;
		if (event1 != null) user = event1.getAuthor();
		else user = event2.getUser();

		int userMoney;
		try {
			userMoney = money.get(user);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		if (50 < userMoney) {

			if (Math.random()*(10)+1 < 4) {

				int randomNB = (int) Math.round(Math.random()*(max-min+1)+min);

				try {
					money.add(user, randomNB);
				} catch (SQLException e) {
					e.printStackTrace();
					return;
				}

				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle(String.format("%1$s Machine à sous %1$s", Emoji.fromMarkdown("\uD83C\uDF40")));
				embed.setDescription(String.format("Bravo %1$s ! Tu as gagner une mise est de **%2$s** %3$s !", Objects.requireNonNull(user).getAsMention(), randomNB, Main.getRedstoneEmoji()));
				embed.setColor(Color.GREEN);

				UtilsCommands.replyOrSend(embed, event1, event2, Button.primary("slot_replay", "♻️ Rejouer"));

			} else {
				try {
					money.remove(user, 50);
				} catch (SQLException e) {
					e.printStackTrace();
					return;
				}

				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle(String.format("%1$s Machine à sous %1$s", Emoji.fromMarkdown("\uD83C\uDF40")));
				embed.setDescription(String.format("Dommage ! %1$s, tu viens de perdre la mise de **50** %2$s !", Objects.requireNonNull(user).getAsMention(), Main.getRedstoneEmoji()));
				embed.setColor(Color.RED);

				UtilsCommands.replyOrSend(embed, event1, event2, Button.primary("slot_replay", "♻️ Rejouer"));
			}

		} else UtilsCommands.replyOrSend(String.format("%1$s, tu n'as pas assez de %2$s !", user.getAsMention(), Main.getRedstoneEmoji()), event1, event2);
	}

	public void onButtonInteraction(ButtonInteractionEvent event) {
		if (event.getComponentId().equalsIgnoreCase("slot_replay")) {

			User user = event.getUser();

			int userMoney;
			try {
				userMoney = money.get(user);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}

			if (50 < userMoney) {

				if (Math.random()*(10)+1 < 4) {

					int randomNB = (int) Math.round(Math.random()*(max-min+1)+min);

					try {
						money.add(user, randomNB);
					} catch (SQLException e) {
						e.printStackTrace();
						return;
					}

					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle(String.format("%1$s Machine à sous %1$s", Emoji.fromMarkdown("\uD83C\uDF40")));
					embed.setDescription(String.format("Bravo %1$s ! Tu as gagner une mise est de **%2$s** %3$s !", Objects.requireNonNull(user).getAsMention(), randomNB, Main.getRedstoneEmoji()));
					embed.setColor(Color.GREEN);

					event.replyEmbeds(embed.build()).addActionRow(Button.primary("slot_replay", "♻️ Rejouer")).queue();

				} else {
					try {
						money.remove(user, 50);
					} catch (SQLException e) {
						e.printStackTrace();
						return;
					}

					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle(String.format("%1$s Machine à sous %1$s", Emoji.fromMarkdown("\uD83C\uDF40")));
					embed.setDescription(String.format("Dommage ! %1$s, tu viens de perdre la mise de **50** %2$s !", Objects.requireNonNull(user).getAsMention(), Main.getRedstoneEmoji()));
					embed.setColor(Color.RED);

					event.replyEmbeds(embed.build()).addActionRow(Button.primary("slot_replay", "♻️ Rejouer")).queue();
				}

			} else event.reply(String.format("%1$s, tu n'as pas assez de %2$s !", user.getAsMention(), Main.getRedstoneEmoji())).queue();
		}
	}
}
