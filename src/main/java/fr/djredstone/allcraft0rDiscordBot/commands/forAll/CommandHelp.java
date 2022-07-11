package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Nullable;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandHelp {

	final String prefix = Main.getPrefix();
	final Emoji emoji = Emoji.fromMarkdown("◽️");

	final EmbedBuilder embedStaff = new EmbedBuilder()
		.setTitle("Commandes Staff")
		.addField(String.format("%1$s %2$sask", emoji, prefix), "> Demande prise en compte", true)
		.addField(String.format("%1$s %2$snon (message)", emoji, prefix), "> Demande refusée", true)
		.addField(String.format("%1$s %2$soui (message)", emoji, prefix), "> Demande acceptée", true)
		.addField(String.format("%1$s %2$stext (message)", emoji, prefix), "> Message personnalisé", true)
		.addField(String.format("%1$s %2$sfakeban <@membre> (message)", emoji, prefix), "> Faux message de ban", true)
		.addField(String.format("%1$s %2$sreset-xp (<@membre>)", emoji, prefix), "> Faux message de reset d'XP", true)
		.addField(String.format("%1$s %2$sstopP4", emoji, prefix), "> Arrête la partie de puissance 4 en cours", true)
		.addField(String.format("%1$s %2$slock <#channel> (raison)", emoji, prefix), "> Lock un channel", true)
		.addField(String.format("%1$s %2$sunlock <#channel> (raison)", emoji, prefix), "> Unlock un channel", true)
		.addField(String.format("%1$s %2$sblacklist add/remove <@membre>", emoji, prefix), "> Blacklist un membre", true)
		.setColor(Color.RED);

	final EmbedBuilder embedUtils = new EmbedBuilder()
		.setTitle("Commandes Utilitaires")
		.addField(String.format("%1$s %2$saide", emoji, prefix), "> Liste des commandes", true)
		.addField(String.format("%1$s %2$ssend", emoji, prefix), "> Envoie un message aux personnes de puissances", true)
		.addField(String.format("%1$s %2$sping", emoji, prefix), "> Lance une balle de ping pong, voit en combien de temps je la renvoie", true)
		.addField(String.format("%1$s %2$slink", emoji, prefix), "> Affiche des liens en rapport à allcraft0r", true)
		.setColor(Color.GREEN);

	final EmbedBuilder embedFun = new EmbedBuilder()
		.setTitle("Commandes Fun")
		.addField(String.format("%1$s %2$stank", emoji, prefix), "> AMERICA ! F*CK YEAHH !!", true)
		.addField(String.format("%1$s %2$seyes", emoji, prefix), "> I'm watching you...", true)
		.addField(String.format("%1$s %2$s8ball", emoji, prefix), "> Posez une question...", true)
		.setColor(Color.YELLOW);

	final EmbedBuilder embedEconomy = new EmbedBuilder()
		.setTitle("Commandes Economiques")
		.addField(String.format("%1$s %2$smoney", emoji, prefix), "> Affiche son nombre de redstones", true)
		.addField(String.format("%1$s %2$sdashboard", emoji, prefix), "> Affiche le dashboard des 10 premières personnes", true)
		.addField(String.format("%1$s %2$sdaily", emoji, prefix), "> Récupère sa redstone quotidienne", true)
		.addField(String.format("%1$s %2$snumber", emoji, prefix), "> Démarre une partie de find number", true)
		.addField(String.format("%1$s %2$sslot", emoji, prefix), "> Démarre une partie de machine à sous", true)
		.addField(String.format("%1$s %2$sP4", emoji, prefix), "> Démarre une partie de puissance 4", true)
		.setColor(Color.ORANGE);

	final EmbedBuilder embedMusic = new EmbedBuilder()
		.setTitle("Commandes Musicales")
		.addField(String.format("%1$s %2$sdisconnect", emoji, prefix), "> Déconnecte le bot du vocal", true)
		.addField(String.format("%1$s %2$snow", emoji, prefix), "> Affiche les information de la musique actuelle", true)
		.addField(String.format("%1$s %2$spause", emoji, prefix), "> Met en pause / Retire la pause de la musique en cours", true)
		.addField(String.format("%1$s %2$splay", emoji, prefix), "> Joue une musique", true)
		.addField(String.format("%1$s %2$squeue", emoji, prefix), "> Montre la file d'attente des musiques en cours", true)
		.addField(String.format("%1$s %2$srepeat", emoji, prefix), "> Active / Déactive la répétition d'une musique", true)
		.addField(String.format("%1$s %2$sskip", emoji, prefix), "> Passe la musique actuelle", true)
		.addField(String.format("%1$s %2$sstop", emoji, prefix), "> Arrête la musique en cours ainsi que sa file d'attente", true)
		.addField(String.format("%1$s %2$svolume", emoji, prefix), "> Ajuste le volume sonore du bot", true)
		.setColor(Color.BLUE);

	private static final ArrayList<String> names = new ArrayList<>(Arrays.asList("utils", "fun", "economy", "staff", "music"));
	
	public CommandHelp(String arg, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
			
		if (names.contains(arg.toLowerCase())) {
			switch (arg.toLowerCase()) {
				case "utils":
					UtilsCommands.replyOrSend(embedUtils, event1, event2);
					break;
				case "fun":
					UtilsCommands.replyOrSend(embedFun, event1, event2);
					break;
				case "economy":
					UtilsCommands.replyOrSend(embedEconomy, event1, event2);
					break;
				case "staff":
					UtilsCommands.replyOrSend(embedStaff, event1, event2);
					break;
				case "music":
					UtilsCommands.replyOrSend(embedMusic, event1, event2);
					break;
			}
		} else {
			UtilsCommands.replyOrSend("Aucun argument donné (`utils` | `fun` | `economy` | `staff` | `music`)", event1, event2);
		}
		
	}

}
