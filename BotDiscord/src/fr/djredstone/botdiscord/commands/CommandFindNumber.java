package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandFindNumber extends ListenerAdapter {
	
	private String channelID = null;
	private int randomNB;
	private HashMap<User, Integer> coups = new HashMap<>();
	private int nbDeBase;

	private final Random random = new Random();
	
	public void onSlashCommand(SlashCommandEvent event) {

		int max = 1000;
		
		if(event.getName().equalsIgnoreCase("number")) {
			
			if(channelID == null) {
				
				try {
					if(event.getOption("nb_max") != null) max = (int) event.getOption("nb_max").getAsLong();
				} catch (NumberFormatException ignored) {
				}
				
				if(max <= 500) {
					event.reply("Le nombre maximum doit être supérieur à 500 !").queue();
					return;
				}
				
				nbDeBase = max;
				
				channelID = event.getChannel().getId();
				randomNB = random.nextInt(max - 1 + 1) + 1;
				
				coups.clear();
				
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("Un nombre aléatoire a été génréré entre 1 et " + max + " :game_die:");
				embed.setDescription("Tout le monde peut chercher mon nombre :eyes:");
				embed.setFooter("| Commandé par " + event.getMember().getUser().getAsTag(), event.getMember().getUser().getAvatarUrl());
				embed.setColor(Color.RED);
				
				event.replyEmbeds(embed.build()).queue();
				
			}
			
		}
		
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if(channelID != null) {
			
			if(channelID.equalsIgnoreCase(event.getChannel().getId())) {
				
				try {
				    int proposition = Integer.parseInt(event.getMessage().getContentDisplay());
				    
				    if(coups.get(event.getAuthor()) == null) coups.put(event.getAuthor(), 0);
				    coups.put(event.getAuthor(), coups.get(event.getAuthor()) + 1);
				    
				    if(proposition < randomNB) {
				    	new BukkitRunnable() {
							@Override
							public void run() {
								event.getMessage().delete().queue();
							}
						}.runTaskLater(Main.main, 100);
				    	event.getMessage().addReaction("⬆️").queue();
				    } else if(proposition > randomNB) {
				    	new BukkitRunnable() {
							@Override
							public void run() {
								event.getMessage().delete().queue();
							}
						}.runTaskLater(Main.main, 100);
				    	event.getMessage().addReaction("⬇️").queue();
				    } else {
				    	channelID = null;
				    	
				    	EmbedBuilder embed = new EmbedBuilder();
						embed.setTitle("Quelqu'un a trouvé le nombre ! :clap:");
						embed.setDescription("__**" + event.getAuthor().getAsTag() + "**__ a découvert le nombre **" + randomNB + "** !");
						embed.setColor(Color.YELLOW);
						
						event.getChannel().sendMessage(embed.build()).queue();
						
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
							event.getChannel().sendMessage("Avec " + coups.get(event.getAuthor()) + " coups, tu gagnes **" + nb + " redstones** " + event.getAuthor().getAsMention() + " !").queue();
						} else {
							event.getChannel().sendMessage("Comme tu as fait trop de coups, tu ne vas pas récupérer de redstones !").queue();
						}
						
						Main.setMoney(event.getAuthor(), Main.getMoney(event.getAuthor()) + nb);
						
						event.getMessage().delete().queue();
						
				    }
				    
				} catch (NumberFormatException ignored) {
				}
				
			}
			
		}
		
	}

}
