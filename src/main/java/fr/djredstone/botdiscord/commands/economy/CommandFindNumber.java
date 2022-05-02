package fr.djredstone.botdiscord.commands.economy;

import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import javax.annotation.Nullable;

import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;
import fr.djredstone.botdiscord.classes.money;
import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandFindNumber extends ListenerAdapter {
	
	private static String channelID = null;
	private static int randomNB;
	private static final HashMap<User, Integer> coups = new HashMap<>();
	private static int nbDeBase;

	public CommandFindNumber(@Nullable String option, @Nullable User user, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
		
		if(event1 == null && event2 == null) return;
		
		int max = 1000;
			
		if(channelID == null) {
			
			if(event2 != null) {
				try {
					if(event2.getOption("nb_max") != null) max = (int) Objects.requireNonNull(event2.getOption("nb_max")).getAsLong();
				} catch (NumberFormatException ignored) {
				}
			} else {
				try {
					if(option != null) max = Integer.parseInt(option);
				} catch (NumberFormatException ignored) {
				}
			}
			
			if(max < 1000) {
				UtilsCommands.replyOrSend("Le nombre maximum doit être supérieur à 1000 !", event1, event2);
				return;
			}
			
			nbDeBase = max;
			
			if(event1 != null) {
				channelID = event1.getChannel().getId();
			} else {
				channelID = event2.getChannel().getId();
			}
			Random random = new Random();
			randomNB = random.nextInt(max - 1 + 1) + 1;
			
			coups.clear();
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(String.format("Un nombre aléatoire a été génréré entre 1 et %1$s :game_die:", max));
			embed.setDescription(String.format("Tout le monde peut chercher mon nombre %1$s", Emoji.fromMarkdown("\uD83D\uDC40")));
			assert user != null;
			embed.setFooter("Commandé par " + user.getAsTag(), user.getAvatarUrl());
			embed.setColor(Color.RED);
			
			UtilsCommands.replyOrSend(embed, event1, event2);
			
		}
		
	}

	public void onMessageReceived(@NotNull MessageReceivedEvent event) {

		if(channelID != null) {

			if(channelID.equalsIgnoreCase(event.getChannel().getId())) {

				try {
					int proposition = Integer.parseInt(event.getMessage().getContentDisplay());

					coups.putIfAbsent(event.getAuthor(), 0);
					coups.put(event.getAuthor(), coups.get(event.getAuthor()) + 1);

					if(proposition < randomNB) {
						new BukkitRunnable() {
							@Override
							public void run() {
								event.getMessage().delete().queue();
							}
						}.runTaskLater(Main.getInstance(), 100);
						event.getMessage().addReaction("⬆️").queue();
					} else if(proposition > randomNB) {
						new BukkitRunnable() {
							@Override
							public void run() {
								event.getMessage().delete().queue();
							}
						}.runTaskLater(Main.getInstance(), 100);
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
