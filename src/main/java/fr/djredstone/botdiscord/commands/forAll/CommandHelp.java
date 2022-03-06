package fr.djredstone.botdiscord.commands.forAll;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandHelp {
	
	public CommandHelp(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
			
		Member member;
		if(event1 != null) member = event1.getMember();
		else {
			assert event2 != null;
			member = event2.getMember();
		}

		final String prefix = Main.getPrefix();

		assert member != null;
		if(member.hasPermission(Permission.NICKNAME_MANAGE)) {
			
			EmbedBuilder embedStaff = new EmbedBuilder();
			embedStaff.setTitle("Commandes privées", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Illuminati-Logo.png/480px-Illuminati-Logo.png");
			
			embedStaff.addField(":white_medium_small_square: **" + prefix + "ask**", "> Demande prise en compte", true);
			embedStaff.addField(":white_medium_small_square: **" + prefix + "non (message)**", "> Demande refusée", true);
			embedStaff.addField(":white_medium_small_square: **" + prefix + "oui (message)**", "> Demande acceptée", true);
			embedStaff.addField(":white_medium_small_square: **" + prefix + "text (message)**", "> Message personnalisé", true);
			embedStaff.addField(":white_medium_small_square: **" + prefix + "fakeban <@membre> (message)**", "> Faux message de ban", true);
			embedStaff.addField(":white_medium_small_square: **" + prefix + "reset-xp (<@membre>)**", "> Faux message de reset d'XP", true);
			embedStaff.addField(":white_medium_small_square: **" + prefix + "stopP4**", "> Arrête la partie de puissance 4 en cours", true);
			
			member.getUser().openPrivateChannel().queue(channel -> channel.sendMessageEmbeds(embedStaff.build()).queue());
			
		}
		
		EmbedBuilder embed1 = new EmbedBuilder();
		embed1.setTitle("Commandes");
		
		embed1.addField(":white_medium_small_square: **" + prefix + "aide**", "> Liste des commandes", true);
		embed1.addField(":white_medium_small_square: **" + prefix + "send**", "> Envoie un message aux personnes de puissances", true);
		embed1.addField(":white_medium_small_square: **" + prefix + "ping**", "> Lance une balle de ping pong, voit en combien de temps je la renvoie", true);
		embed1.addField(":white_medium_small_square: **" + prefix + "link**", "> Affiche des liens en rapport à allcraft0r", true);
		
		embed1.setColor(Color.BLUE);
		
		EmbedBuilder embed2 = new EmbedBuilder();
		embed2.addField(":white_medium_small_square: **" + prefix + "tank**", "> AMERICA ! F*CK YEAHH !!", true);
		embed2.addField(":white_medium_small_square: **" + prefix + "eyes**", "> I'm watching you...", true);
		
		embed2.setColor(Color.YELLOW);
		
		EmbedBuilder embed3 = new EmbedBuilder();
		embed3.addField(":white_medium_small_square: **" + prefix + "money**", "> Affiche son nombre de redstones", true);
		embed3.addField(":white_medium_small_square: **" + prefix + "daily**", "> Récupère sa redstone quotidienne", true);
		embed3.addField(":white_medium_small_square: **" + prefix + "number**", "> Démarre une partie de find number", true);
		embed3.addField(":white_medium_small_square: **" + prefix + "quitteoudouble**", "> Démarre une partie de quitte ou double", true);
		embed3.addField(":white_medium_small_square: **" + prefix + "P4**", "> Démarre une partie de puissance 4", true);
		
		embed3.setColor(Color.ORANGE);
		
		embed1.setThumbnail("https://images.emojiterra.com/google/android-10/512px/2754.png");
		
		UtilsCommands.replyOrSend(embed1, event1, event2);
		MessageChannel channel;
		if(event1 != null) channel = event1.getChannel();
		else channel = event2.getTextChannel();

		channel.sendMessageEmbeds(embed2.build()).queue();
		channel.sendMessageEmbeds(embed3.build()).queue();
		
	}

}
