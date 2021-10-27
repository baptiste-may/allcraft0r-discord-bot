package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandFindNumber extends ListenerAdapter {
	
	private String channelID = null;
	private int randomNB;
	private int coups;
	private int nbDeBase;

	private final Random random = new Random();
	
	Logger log = Bukkit.getLogger();
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");

		int max = 1000;
		
		if(args[0].equalsIgnoreCase(Main.prefix + "number")) {
			
			if(channelID == null) {
				
				try {
					if(args.length > 1) max = Integer.parseInt(args[1]);
				} catch (NumberFormatException ignored) {
				}
				
				nbDeBase = max;
				
				channelID = event.getChannel().getId();
				randomNB = random.nextInt(max - 1 + 1) + 1;
				
				log.warning("Le nombre aléatoire est " + randomNB + " 👀");
				
				coups = 0;
				
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("Un nombre aléatoire a été génréré entre 1 et " + max + " :game_die:");
				embed.setDescription("Tout le monde peut chercher mon nombre :eyes:");
				embed.setFooter("| Commandé par " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl());
				embed.setColor(Color.RED);
				
				event.getChannel().sendMessage(embed.build()).queue();
				
				event.getMessage().delete().queue();
				
			}
			
		} else if(channelID != null) {
			
			if(channelID.equalsIgnoreCase(event.getChannel().getId())) {
				
				try {
				    int proposition = Integer.parseInt(event.getMessage().getContentDisplay());
				    
				    coups ++;
				    
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
						
						int nb = nbDeBase - coups;
						if(coups > 0) {
							event.getChannel().sendMessage("Avec " + coups + " coups, tu gagnes **" + nb + " redstones** " + event.getAuthor().getAsMention() + " !").queue();
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
