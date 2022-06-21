package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import java.awt.Color;

import javax.annotation.Nullable;

import fr.djredstone.allcraft0rDiscordBot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
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
		final Emoji emoji = Emoji.fromMarkdown("◻️");

		assert member != null;
		if(member.hasPermission(Permission.NICKNAME_MANAGE)) {
			
			EmbedBuilder embedStaff = new EmbedBuilder();
			embedStaff.setTitle("Commandes privées", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Illuminati-Logo.png/480px-Illuminati-Logo.png");

			embedStaff.addField(String.format("%1$s: **%2$sask**", emoji, prefix), "> Demande prise en compte", true);
			embedStaff.addField(String.format("%1$s: **%2$snon (message)**", emoji, prefix), "> Demande refusée", true);
			embedStaff.addField(String.format("%1$s: **%2$soui (message)**", emoji, prefix), "> Demande acceptée", true);
			embedStaff.addField(String.format("%1$s: **%2$stext (message)**", emoji, prefix), "> Message personnalisé", true);
			embedStaff.addField(String.format("%1$s: **%2$sfakeban <@membre> (message)**", emoji, prefix), "> Faux message de ban", true);
			embedStaff.addField(String.format("%1$s: **%2$sreset-xp (<@membre>)**", emoji, prefix), "> Faux message de reset d'XP", true);
			embedStaff.addField(String.format("%1$s: **%2$sstopP4**", emoji, prefix), "> Arrête la partie de puissance 4 en cours", true);
			embedStaff.addField(String.format("%1$s: **%2$slock <#channel> (raison)", emoji, prefix), "> Lock un channel", true);
			embedStaff.addField(String.format("%1$s: **%2$sblacklist add/remove <@membre>", emoji, prefix), "> Blacklist un membre", true);
			member.getUser().openPrivateChannel().queue(channel -> channel.sendMessageEmbeds(embedStaff.build()).queue());
			
		}
		
		EmbedBuilder embed1 = new EmbedBuilder();
		embed1.setTitle("Commandes");
		
		embed1.addField(String.format("%1$s: **%2$saide**", emoji, prefix), "> Liste des commandes", true);
		embed1.addField(String.format("%1$s: **%2$ssend**", emoji, prefix), "> Envoie un message aux personnes de puissances", true);
		embed1.addField(String.format("%1$s: **%2$sping**", emoji, prefix), "> Lance une balle de ping pong, voit en combien de temps je la renvoie", true);
		embed1.addField(String.format("%1$s: **%2$slink**", emoji, prefix), "> Affiche des liens en rapport à allcraft0r", true);
		
		embed1.setColor(Color.BLUE);
		
		EmbedBuilder embed2 = new EmbedBuilder();
		embed2.addField(String.format("%1$s: **%2$stank**", emoji, prefix), "> AMERICA ! F*CK YEAHH !!", true);
		embed2.addField(String.format("%1$s: **%2$seyes**", emoji, prefix), "> I'm watching you...", true);
		embed2.addField(String.format("%1$s: **%2$s8ball**", emoji, prefix), "> Posez une question...", true);
		
		embed2.setColor(Color.YELLOW);
		
		EmbedBuilder embed3 = new EmbedBuilder();
		embed3.addField(String.format("%1$s: **%2$smoney**", emoji, prefix), "> Affiche son nombre de redstones", true);
		embed3.addField(String.format("%1$s: **%2$sdashboard**", emoji, prefix), "> Affiche le dashboard des 10 premières personnes", true);
		embed3.addField(String.format("%1$s: **%2$sdaily**", emoji, prefix), "> Récupère sa redstone quotidienne", true);
		embed3.addField(String.format("%1$s: **%2$snumber**", emoji, prefix), "> Démarre une partie de find number", true);
		embed3.addField(String.format("%1$s: **%2$sslot**", emoji, prefix), "> Démarre une partie de machine à sous", true);
		embed3.addField(String.format("%1$s: **%2$sP4**", emoji, prefix), "> Démarre une partie de puissance 4", true);
		
		embed3.setColor(Color.ORANGE);
		
		embed1.setThumbnail("https://images.emojiterra.com/google/android-10/512px/2754.png");

		MessageChannel channel;
		if(event1 != null) channel = event1.getChannel();
		else {
			event2.reply("Voici les commandes :").setEphemeral(true).queue();
			channel = event2.getTextChannel();
		}

		channel.sendMessageEmbeds(embed1.build(), embed2.build(), embed3.build()).queue();
		
	}

}
