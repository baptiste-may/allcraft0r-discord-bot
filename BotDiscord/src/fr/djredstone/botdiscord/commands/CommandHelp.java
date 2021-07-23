package fr.djredstone.botdiscord.commands;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandHelp extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "aide")) {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("    Liste des commandes :");
			
			embed.addField(":blue_square: **" + Main.prefix + "aide**", "> Liste des commandes", true);
			embed.addField(":blue_square: **" + Main.prefix + "send**", "> Envoye un message aux personnes de puissances", true);
			embed.addField(":blue_square: **" + Main.prefix + "ping**", "> Lance une balle de ping pong, voit en combien de temps de la renvoie", true);
			embed.addField(":blue_square: **" + Main.prefix + "link**", "> Affiche des liens en rapport à allcraft0r", true);
			
			embed.addField(":red_square: **" + Main.prefix + "ask**", "> Demande prise en compte", true);
			embed.addField(":red_square: **" + Main.prefix + "non (message)**", "> Demande refusée", true);
			embed.addField(":red_square: **" + Main.prefix + "oui (message)**", "> Demande acceptée", true);
			embed.addField(":red_square: **" + Main.prefix + "text (message)**", "> Message personnalisé", true);
			embed.addField(":red_square: **" + Main.prefix + "warn @(membre)**", "> Message de prévention", true);
			
			embed.addField(":yellow_square: **" + Main.prefix + "tank**", "> AMERICA ! F*CK YEAHH !!", true);
			embed.addField(":yellow_square: **" + Main.prefix + "eyes**", "> I'm watching you...", true);
			
			embed.setThumbnail("https://images.emojiterra.com/google/android-10/512px/2754.png");
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(""
					+ " :blue_square: : Commandes pour le peuple\n"
					+ " :red_square: : Commandes pour les personnes de puissances\n"
					+ " :yellow_square: : Commandes funs\n").queue();
			event.getChannel().sendMessage(embed.build()).queue();
			embed.clear();
			event.getMessage().delete().queue();
			
		}
		
    }

}
