package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandHelp {

	final Emoji emoji = Emoji.fromMarkdown("◽️");

	final EmbedBuilder embedStaff = new EmbedBuilder()
		.setTitle("Commandes Staff")
		.addField(emoji + " ask", "> Demande prise en compte", true)
		.addField(emoji + " non (message)", "> Demande refusée", true)
		.addField(emoji + " oui (message)", "> Demande acceptée", true)
		.addField(emoji + " text (message)", "> Message personnalisé", true)
		.addField(emoji + " fakeban <@membre> (message)", "> Faux message de ban", true)
		.addField(emoji + " reset-xp (<@membre>)", "> Faux message de reset d'XP", true)
		.addField(emoji + " stopP4", "> Arrête la partie de puissance 4 en cours", true)
		.addField(emoji + " lock <#channel> (raison)", "> Lock un channel", true)
		.addField(emoji + " unlock <#channel> (raison)", "> Unlock un channel", true)
		.addField(emoji + " blacklist add/remove <@membre>", "> Blacklist un membre", true)
		.setColor(Color.RED);

	final EmbedBuilder embedUtils = new EmbedBuilder()
		.setTitle("Commandes Utilitaires")
		.addField(emoji + " aide", "> Liste des commandes", true)
		.addField(emoji + " send", "> Envoie un message aux personnes de puissances", true)
		.addField(emoji + " ping", "> Lance une balle de ping pong, voit en combien de temps je la renvoie", true)
		.addField(emoji + " link", "> Affiche des liens en rapport à allcraft0r", true)
		.setColor(Color.GREEN);

	final EmbedBuilder embedFun = new EmbedBuilder()
		.setTitle("Commandes Fun")
		.addField(emoji + " tank", "> AMERICA ! F*CK YEAHH !!", true)
		.addField(emoji + " eyes", "> I'm watching you...", true)
		.addField(emoji + " 8ball", "> Posez une question...", true)
		.setColor(Color.YELLOW);

	final EmbedBuilder embedEconomy = new EmbedBuilder()
		.setTitle("Commandes Economiques")
		.addField(emoji + " money", "> Affiche son nombre de redstones", true)
		.addField(emoji + " dashboard", "> Affiche le dashboard des 10 premières personnes", true)
		.addField(emoji + " daily", "> Récupère sa redstone quotidienne", true)
		.addField(emoji + " number", "> Démarre une partie de find number", true)
		.addField(emoji + " slot", "> Démarre une partie de machine à sous", true)
		.addField(emoji + " P4", "> Démarre une partie de puissance 4", true)
		.setColor(Color.ORANGE);

	final EmbedBuilder embedMusic = new EmbedBuilder()
		.setTitle("Commandes Musicales")
		.addField(emoji + " disconnect", "> Déconnecte le bot du vocal", true)
		.addField(emoji + " now", "> Affiche les information de la musique actuelle", true)
		.addField(emoji + " pause", "> Met en pause / Retire la pause de la musique en cours", true)
		.addField(emoji + " play", "> Joue une musique", true)
		.addField(emoji + " queue", "> Montre la file d'attente des musiques en cours", true)
		.addField(emoji + " repeat", "> Active / Déactive la répétition d'une musique", true)
		.addField(emoji + " skip", "> Passe la musique actuelle", true)
		.addField(emoji + " stop", "> Arrête la musique en cours ainsi que sa file d'attente", true)
		.addField(emoji + " volume", "> Ajuste le volume sonore du bot", true)
		.setColor(Color.BLUE);

	private static final ArrayList<String> names = new ArrayList<>(Arrays.asList("utils", "fun", "economy", "staff", "music"));
	
	public CommandHelp(SlashCommandInteractionEvent event) {
			
		if (names.contains(Objects.requireNonNull(event.getOption("text")).getAsString().toLowerCase())) {
			EmbedBuilder embed = switch (Objects.requireNonNull(event.getOption("text")).getAsString().toLowerCase()) {
				case "utils" -> embedUtils;
				case "fun" -> embedFun;
				case "economy" -> embedEconomy;
				case "staff" -> embedStaff;
				case "music" -> embedMusic;
				default -> new EmbedBuilder().setTitle("Erreur");
			};
			event.replyEmbeds(embed.build()).setEphemeral(true).queue();
		} else {
			event.reply("Aucun argument donné (`utils` | `fun` | `economy` | `staff` | `music`)").setEphemeral(true).queue();
		}
		
	}

}
