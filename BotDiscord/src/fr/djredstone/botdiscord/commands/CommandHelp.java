package fr.djredstone.botdiscord.commands;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandHelp {
	
	public CommandHelp(String cmd, User user, @Nullable GuildMessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
		
		if(cmd.equalsIgnoreCase(Main.prefix + "aide")) {
			
			Member member;
			if(event1 != null) {
				member = event1.getMember();
			} else {
				member = event2.getMember();
			}
			
			if(member.hasPermission(Permission.NICKNAME_MANAGE)) {
				
				EmbedBuilder embedStaff = new EmbedBuilder();
				embedStaff.setTitle("Commandes privées", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Illuminati-Logo.png/480px-Illuminati-Logo.png");
				
				embedStaff.addField(":white_medium_small_square: **" + Main.prefix + "ask**", "> Demande prise en compte", true);
				embedStaff.addField(":white_medium_small_square: **" + Main.prefix + "non (message)**", "> Demande refusée", true);
				embedStaff.addField(":white_medium_small_square: **" + Main.prefix + "oui (message)**", "> Demande acceptée", true);
				embedStaff.addField(":white_medium_small_square: **" + Main.prefix + "text (message)**", "> Message personnalisé", true);
				embedStaff.addField(":white_medium_small_square: **" + Main.prefix + "fakeban <@membre> (message)**", "> Faux message de ban", true);
				embedStaff.addField(":white_medium_small_square: **" + Main.prefix + "fakeban <membre> (message)** + image en attachment", "> Faux message de ban (personnalisé)", true);
				embedStaff.addField(":white_medium_small_square: **" + Main.prefix + "reset-xp (<@membre>)**", "> Faux message de reset d'XP", true);
				
				user.openPrivateChannel().queue(channel -> {
		            channel.sendMessage(embedStaff.build()).queue();
		        });
				
			}
			
			EmbedBuilder embed1 = new EmbedBuilder();
			embed1.setTitle("Commandes");
			
			embed1.addField(":white_medium_small_square: **" + Main.prefix + "aide**", "> Liste des commandes", true);
			embed1.addField(":white_medium_small_square: **" + Main.prefix + "send**", "> Envoie un message aux personnes de puissances", true);
			embed1.addField(":white_medium_small_square: **" + Main.prefix + "ping**", "> Lance une balle de ping pong, voit en combien de temps je la renvoie", true);
			embed1.addField(":white_medium_small_square: **" + Main.prefix + "link**", "> Affiche des liens en rapport à allcraft0r", true);
			
			embed1.setColor(Color.BLUE);
			
			EmbedBuilder embed2 = new EmbedBuilder();
			embed2.addField(":white_medium_small_square: **" + Main.prefix + "tank**", "> AMERICA ! F*CK YEAHH !!", true);
			embed2.addField(":white_medium_small_square: **" + Main.prefix + "eyes**", "> I'm watching you...", true);
			
			embed2.setColor(Color.YELLOW);
			
			EmbedBuilder embed3 = new EmbedBuilder();
			embed3.addField(":white_medium_small_square: **" + Main.prefix + "money**", "> Affiche son nombre de redstones", true);
			embed3.addField(":white_medium_small_square: **" + Main.prefix + "daily**", "> Récupère sa redstone quotidienne", true);
			embed3.addField(":white_medium_small_square: **" + Main.prefix + "number**", "> Démarre une partie de find number", true);
			embed3.addField(":white_medium_small_square: **" + Main.prefix + "quitteoudouble**", "> Démarre une partie de quitte ou double", true);
			
			embed3.setColor(Color.ORANGE);
			
			embed1.setThumbnail("https://images.emojiterra.com/google/android-10/512px/2754.png");
			
			UtilsCommands.replyOrSend(embed1, event1, event2);
			TextChannel channel;
			if(event1 != null) {
				channel = event1.getChannel();
			} else {
				channel = event2.getTextChannel();
			}
			channel.sendMessage(embed2.build()).queue();
			channel.sendMessage(embed3.build()).queue();
			
		}
		
	}

}
