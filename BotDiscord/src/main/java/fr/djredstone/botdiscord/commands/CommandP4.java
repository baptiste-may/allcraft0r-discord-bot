package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class CommandP4 extends ListenerAdapter {

	private static User firstUser;
	private static User secondUser;
	private static Message gameMessage = null;
	private static Boolean tour = false;

	private static ArrayList<ArrayList<Boolean>> game;

	private static ArrayList<String> emojis;

	public static void setGame(ArrayList<ArrayList<Boolean>> game) { CommandP4.game = game; }
	public static void setEmojis(ArrayList<String> emojis) { CommandP4.emojis = emojis; }
	public static void setFirstUser(User firstUser) { CommandP4.firstUser = firstUser; }
	public static void setSecondUser(User secondUser) { CommandP4.secondUser = secondUser; }
	public static void setGameMessage(Message gameMessage) { CommandP4.gameMessage = gameMessage; }
	public static void setTour(Boolean tour) { CommandP4.tour = tour; }

	public static User getFirstUser() { return firstUser; }
	public static User getSecondUser() { return secondUser; }
	public static Message getGameMessage() { return gameMessage; }
	public static Boolean getTour() { return tour; }
	public static ArrayList<ArrayList<Boolean>> getGame() { return game; }
	public static ArrayList<String> getEmojis() { return emojis; }

	public static void setup() {

		setEmojis(new ArrayList<>());
		setGame(new ArrayList<>());

		getEmojis().add("1️⃣");
		getEmojis().add("2️⃣");
		getEmojis().add("3️⃣");
		getEmojis().add("4️⃣");
		getEmojis().add("5️⃣");
		getEmojis().add("6️⃣");
		getEmojis().add("7️⃣");
	}

	private static void createGame() {

		for (int i = 0; i < 7; i++) {
			ArrayList<Boolean> list = new ArrayList<>();
			for (int i2 = 0; i2 < 6; i2++) {
				list.add(null);
			}
			getGame().add(list);
		}

	}

	private static EmbedBuilder gameToEmbed(ArrayList<ArrayList<Boolean>> gameVar, boolean player) {

		EmbedBuilder embed = new EmbedBuilder();
		StringBuilder board = new StringBuilder();

		for (int i = 0; i < 6; i++) {
			for (int i2 = 0; i2 < 7; i2++) {
				try {
					Boolean bool = gameVar.get(i2).get(i);
					if (bool) {
						board.append(":red_circle:");
					} else {
						board.append(":yellow_circle:");
					}
				} catch (NullPointerException ignored) {
					board.append(":black_small_square:");
				}
			}
			board.append("\n");
		}
		for (String emoji : getEmojis()) {
			board.append(emoji);
		}
		if (player) embed.setColor(Color.YELLOW);
		else embed.setColor(Color.RED);
		embed.setAuthor(getFirstUser().getAsTag() + " \uD83D\uDFE1 ⚔ " + getSecondUser().getAsTag() + " \uD83D\uDD34");
		embed.setTitle("Puissance 4");
		embed.setDescription(board);
		if (getTour()) embed.setFooter("C'est au tour de " + getFirstUser().getAsTag() + " \uD83D\uDD34");
		else embed.setFooter("C'est au tour de " + getSecondUser().getAsTag() + " \uD83D\uDFE1");
		return embed;

	}

	private static void addReactions(Message message) {

		for (String emoji : getEmojis()) {
			message.addReaction(emoji).queue();
		}

	}

	private static int getTheLastPlace(ArrayList<Boolean> list) {

		for (int i = 5; i >= 0; i--) {
			if (list.get(i) == null) {
				return i;
			}
		}
		return -1;
	}

	private static boolean checkResult(ArrayList<ArrayList<Boolean>> gameVar, boolean bool) {

		int i,j;

		//horizontal
		for(i=0;i<7;i++)
			for(j=0;j<6-3;j++)
				try {
					if(gameVar.get(i).get(j) == bool && gameVar.get(i).get(j)==gameVar.get(i).get(j+1) && gameVar.get(i).get(j)==gameVar.get(i).get(j+2) && gameVar.get(i).get(j)==gameVar.get(i).get(j+3))
						return true;
				} catch (NullPointerException ignored) {
				}

		//vertical
		for(i=0;i<7-3;i++)
			for(j=0;j<6;j++)
				try {
					if(gameVar.get(i).get(j) == bool && gameVar.get(i).get(j)==gameVar.get(i+1).get(j) && gameVar.get(i).get(j)==gameVar.get(i+2).get(j) && gameVar.get(i).get(j)==gameVar.get(i+3).get(j))
						return true;
				} catch (NullPointerException ignored) {
				}

		//droite diagonal
		for(i=0;i<7-3;i++)
			for(j=0;j<6-3;j++)
				try {
					if(gameVar.get(i).get(j) == bool && gameVar.get(i).get(j)==gameVar.get(i+1).get(j+1) && gameVar.get(i).get(j)==gameVar.get(i+2).get(j+2) && gameVar.get(i).get(j)==gameVar.get(i+3).get(j+3))
						return true;
				} catch (NullPointerException ignored) {
				}

		//gauche diagonal
		for(i=0;i<7-3;i++)
			for(j=0;j<6-3;j++)
				try {
					if(gameVar.get(i).get(j) == bool && gameVar.get(i).get(j)==gameVar.get(i+1).get(j-1) && gameVar.get(i).get(j)==gameVar.get(i+2).get(j-2) && gameVar.get(i).get(j)==gameVar.get(i+3).get(j-3))
						return true;
				} catch (NullPointerException ignored) {
				}

		return false;
	}

	private static void winAndRestart(EmbedBuilder embed, MessageReactionAddEvent event, User winner, User loser) {
		embed.setColor(Color.GREEN);
		embed.setAuthor(winner.getAsTag() + " a gagné contre " + loser.getAsTag() + " !");
		embed.setFooter(null);
		embed.setThumbnail(winner.getAvatarUrl());
		event.getChannel().retrieveMessageById(event.getMessageId()).queue(message -> message.clearReactions().queue());
		event.getChannel().editMessageEmbedsById(event.getMessageId(), embed.build()).queue();
		getGame().clear();
		setFirstUser(null);
		setSecondUser(null);
		setGameMessage(null);
		setTour(false);
	}

	public CommandP4(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

		if (event1 == null && event2 == null) return;

		if (getGameMessage() != null) {
			UtilsCommands.replyOrSend("Une partie est déjà en cours !", event1, event2);
			return;
		}

		User user;
		TextChannel textChannel;
		if (event1 != null) {
			user = event1.getAuthor();
			textChannel = event1.getTextChannel();
		}
		else {
			user = event2.getUser();
			textChannel = event2.getTextChannel();
		}

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("**" + user.getName() + "** commence une partie de puissance 4 !");
		embed.setDescription("Clickez sur l'émoji :crossed_swords: pour l'affronter !");
		embed.setThumbnail("https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Puissance4_01.svg/1200px-Puissance4_01.svg.png");
		embed.setColor(Color.ORANGE);

		setFirstUser(user);
		textChannel.sendMessageEmbeds(embed.build()).setActionRow(Button.success("P4_start", Emoji.fromUnicode("⚔️"))).queue();
		if (event1 != null) event1.getMessage().delete().queue();
	}

	@Override
	public void onButtonInteraction(ButtonInteractionEvent event) {

		if (event.getComponentId().equalsIgnoreCase("P4_start")) {

			if (getFirstUser().equals(event.getUser())) {
				event.reply("Tu ne peux pas te battre contre toi même !").setEphemeral(true).queue();
				return;
			}

			getGame().clear();
			createGame();

			setSecondUser(event.getUser());
			setGameMessage(event.getMessage());
			event.editMessageEmbeds(gameToEmbed(getGame(), true).build()).setActionRows(new ArrayList<>()).queue();
			addReactions(event.getMessage());

		}

	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {

		if (Objects.requireNonNull(event.getUser()).isBot()) return;

		if (getGameMessage() == null) return;
		if (!getGameMessage().getId().equals(event.getMessageId())) return;

		User user = event.getUser();
		for (int i = 0; i < 7; i++) {
			if (event.getReactionEmote().getEmoji().equals(getEmojis().get(i))) {
				assert user != null;
				if (user.equals(getFirstUser()) || user.equals(getSecondUser())) {
					if (getTour() && user.equals(getFirstUser())) {
						int lastPlace = getTheLastPlace(getGame().get(i));
						if (lastPlace == -1) {
							event.getReaction().removeReaction(event.getUser()).queue();
							return;
						}
						getGame().get(i).set(lastPlace, true);
						setTour(false);
						event.getChannel().editMessageEmbedsById(event.getMessageId(), gameToEmbed(getGame(), true).build()).queue();
						event.getReaction().removeReaction(event.getUser()).queue();
						if (checkResult(getGame(), true)) winAndRestart(new EmbedBuilder(gameToEmbed(getGame(), true)), event, getFirstUser(), getSecondUser());
					} else if (!getTour() && user.equals(getSecondUser())) {
						int lastPlace = getTheLastPlace(getGame().get(i));
						if (lastPlace == -1) {
							event.getReaction().removeReaction(event.getUser()).queue();
							return;
						}
						getGame().get(i).set(lastPlace, false);
						setTour(true);
						event.getChannel().editMessageEmbedsById(event.getMessageId(), gameToEmbed(getGame(), false).build()).queue();
						event.getReaction().removeReaction(event.getUser()).queue();
						if (checkResult(getGame(), false)) winAndRestart(new EmbedBuilder(gameToEmbed(getGame(), false)), event, getSecondUser(), getFirstUser());
					} else {
						event.getReaction().removeReaction(event.getUser()).queue();
					}
				} else {
					event.getReaction().removeReaction(event.getUser()).queue();
				}
			}
		}

	}

}
