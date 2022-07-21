package fr.djredstone.allcraft0rDiscordBot.commands.economy;

import javax.annotation.Nullable;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.classes.money;
import org.jetbrains.annotations.NotNull;

public class CommandFindNumber extends ListenerAdapter {
	
	private static String channelID = null;
	private static int randomNB;
	private static final HashMap<User, Integer> coups = new HashMap<>();
	private static int nbDeBase;

	public CommandFindNumber(@Nullable SlashCommandInteractionEvent event) {
		
		if(event == null) return;
		
		int max = 1000;
			
		if(channelID == null) {

			try {
				if(event.getOption("number") != null) max = (int) Objects.requireNonNull(event.getOption("number")).getAsLong();
			} catch (NumberFormatException ignored) {
			}
			
			if(max < 1000) {
				event.reply("Le nombre maximum doit être supérieur à 1000 !").setEphemeral(true).queue();
				return;
			}
			
			nbDeBase = max;

			Random random = new Random();
			randomNB = random.nextInt(max - 1 + 1) + 1;
			
			coups.clear();
			channelID = event.getTextChannel().getId();
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(String.format("Un nombre aléatoire a été génréré entre 1 et %1$s :game_die:", max));
			embed.setDescription(String.format("Tout le monde peut chercher mon nombre %1$s", Emoji.fromMarkdown("\uD83D\uDC40")));
			embed.setColor(Color.RED);
			
			event.replyEmbeds(embed.build()).queue();
			
		} else event.reply("Une partie est déjà en cours dans ce salon").setEphemeral(true).queue();
		
	}

	public void onMessageReceived(@NotNull MessageReceivedEvent event) {

		if(channelID != null) {

			if(channelID.equalsIgnoreCase(event.getChannel().getId())) {

				try {
					int proposition = Integer.parseInt(event.getMessage().getContentDisplay());

					coups.putIfAbsent(event.getAuthor(), 0);
					coups.put(event.getAuthor(), coups.get(event.getAuthor()) + 1);

					if(proposition < randomNB) {
						new Timer().schedule(new TimerTask() {
							@Override
							public void run() {
								event.getMessage().delete().queue();
							}
						}, 5*1000);
						event.getMessage().addReaction("⬆️").queue();
					} else if(proposition > randomNB) {
						new Timer().schedule(new TimerTask() {
							@Override
							public void run() {
								event.getMessage().delete().queue();
							}
						}, 5*1000);
						event.getMessage().addReaction("⬇️").queue();
					} else {
						channelID = null;

						EmbedBuilder embed = new EmbedBuilder();
						embed.setTitle(String.format("Quelqu'un a trouvé le nombre ! %1$s", Emoji.fromMarkdown("\uD83D\uDC4F")));
						embed.setDescription(String.format("__**%1$s**__ a découvert le nombre **%2$s** !", event.getAuthor().getAsTag(), randomNB));
						embed.setColor(Color.YELLOW);

						event.getChannel().sendMessageEmbeds(embed.build()).queue();

						int x = 1;
						int essaisMax = 0;
						while (x < nbDeBase) {
							x *= 2;
							essaisMax += 1;
						}
						essaisMax *= 1.5;

						int nb = essaisMax - coups.get(event.getAuthor());
						if (nb < 0) {
							nb = 0;
						}

						nb = (nb * 100) / essaisMax;

						if(nb > 0) {
							event.getChannel().sendMessage(String.format("Avec %1$s coups, tu gagnes **%2$s** %3$s !", coups.get(event.getAuthor()), nb, Main.getRedstoneEmoji())).queue();
						} else {
							event.getChannel().sendMessage("Comme tu as fait trop de coups, tu ne vas pas récupérer de redstones !").queue();
						}

						try {
							money.add(event.getAuthor(), nb);
						} catch (SQLException e) {
							e.printStackTrace();
						}

						event.getMessage().delete().queue();

					}

				} catch (NumberFormatException ignored) {
				}

			}

		}

	}

}
