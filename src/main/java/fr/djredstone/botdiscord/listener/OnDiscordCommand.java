package fr.djredstone.botdiscord.listener;

import java.util.Objects;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;
import fr.djredstone.botdiscord.commands.forAll.*;
import fr.djredstone.botdiscord.commands.economy.CommandDaily;
import fr.djredstone.botdiscord.commands.economy.CommandDashboard;
import fr.djredstone.botdiscord.commands.economy.CommandFindNumber;
import fr.djredstone.botdiscord.commands.economy.CommandMoney;
import fr.djredstone.botdiscord.commands.economy.CommandP4;
import fr.djredstone.botdiscord.commands.economy.CommandQuitteOuDouble;
import fr.djredstone.botdiscord.commands.music.CommandDisconnect;
import fr.djredstone.botdiscord.commands.music.CommandNow;
import fr.djredstone.botdiscord.commands.music.CommandPause;
import fr.djredstone.botdiscord.commands.music.CommandPlay;
import fr.djredstone.botdiscord.commands.music.CommandQueue;
import fr.djredstone.botdiscord.commands.music.CommandRepeat;
import fr.djredstone.botdiscord.commands.music.CommandSkip;
import fr.djredstone.botdiscord.commands.music.CommandStop;
import fr.djredstone.botdiscord.commands.music.CommandVolume;

public class OnDiscordCommand extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

		switch (event.getName().toLowerCase()) {
			case "money" -> new CommandMoney(event.getUser(), null, event);
			case "daily" -> new CommandDaily(event.getUser(), null, event);
			case "number" -> new CommandFindNumber(null, event.getUser(), null, event);
			case "p4" -> new CommandP4(null, event);
			case "dashboard" -> new CommandDashboard(null, event);
			case "quitteoudouble" -> {
				if (!UtilsCommands.getCommandUsed().containsKey(event.getUser())) UtilsCommands.getCommandUsed().put(event.getUser(), 0);
				if (UtilsCommands.getCommandUsed().get(event.getUser()) < 20) {
					UtilsCommands.getCommandUsed().put(event.getUser(), UtilsCommands.getCommandUsed().get(event.getUser()) + 1);
					if (event.getOption("nb_depart_mise") != null) new CommandQuitteOuDouble(Objects.requireNonNull(event.getOption("nb_depart_mise")).getAsString(), null, event);
					else new CommandQuitteOuDouble(null, null, event);
				} else UtilsCommands.replyOrSend("Vous avez atteint votre limite de commande pour aujourd'hui !", null, event);
			}

			case "send" -> new CommandSend(event.getUser(), null, null, event);
			case "eyes" -> new CommandEyes(null, event);
			case "tank" -> new CommandTank(null, event);
			case "aide" -> new CommandHelp(null, event);
			case "link" -> new CommandLink(null, event);
			case "ping" -> new CommandPing(null, event);

			case "play" -> {
				if (event.getOption("play_search") != null) new CommandPlay(Objects.requireNonNull(event.getOption("play_search")).getAsString(), null, event);
				else new CommandPlay(null, null, event);
			}
			case "stop" -> new CommandStop(null, event);
			case "skip" -> new CommandSkip(null, event);
			case "disconnect" -> new CommandDisconnect(null, event);
			case "pause" -> new CommandPause(null, event);
			case "repeat" -> new CommandRepeat(null, event);
			case "queue" -> new CommandQueue(null, event);
			case "now" -> new CommandNow(null, event);
			case "volume" -> {
				if (event.getOption("volume") != null) new CommandVolume(Objects.requireNonNull(event.getOption("volume")).getAsString(), null, event);
				else new CommandVolume(null, null, event);
			}
			case "8ball" -> new Command8Ball(null, event, Objects.requireNonNull(event.getOption("8ball")).getAsString());
		}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(!args[0].startsWith("!")) return;
		
		String cmd = args[0].replace(Main.getPrefix(), "");
		
		switch(cmd.toLowerCase()) {
			case "number" -> {
				if (args.length > 1) new CommandFindNumber(args[1], event.getAuthor(), event, null);
				else new CommandFindNumber(null, event.getAuthor(), event, null);
			}
			case "quitteoudouble" -> {
				if (!UtilsCommands.getCommandUsed().containsKey(event.getAuthor())) UtilsCommands.getCommandUsed().put(event.getAuthor(), 0);
				if (UtilsCommands.getCommandUsed().get(event.getAuthor()) < 5) {
					UtilsCommands.getCommandUsed().put(event.getAuthor(), UtilsCommands.getCommandUsed().get(event.getAuthor()) + 1);
					if (args.length > 1) new CommandQuitteOuDouble(args[1], event, null);
					else new CommandQuitteOuDouble(null, event, null);
				} else UtilsCommands.replyOrSend("Afin de garder une économie stable, le quitte ou double est limité à 5 utilisations par jours.", event, null);
			}
			case "money" -> new CommandMoney(event.getAuthor(), event, null);
			case "daily" -> new CommandDaily(event.getAuthor(), event, null);
			case "send" -> new CommandSend(event.getAuthor(), args[1], event, null);
			case "eyes" -> new CommandEyes(event, null);
			case "tank" -> new CommandTank(event, null);
			case "aide" -> new CommandHelp(event, null);
			case "link" -> new CommandLink(event, null);
			case "ping" -> new CommandPing(event, null);
			case "dashboard" -> new CommandDashboard(event, null);
			case "p4" -> new CommandP4(event, null);
			case "play" -> {
				if (args.length > 1) {
					StringBuilder builder = new StringBuilder();
					for (int i = 1; i < args.length; i++) {
						builder.append(args[i]);
					}
					new CommandPlay(builder.toString(), event, null);
				}
				else new CommandPlay(null, event, null);
			}
			case "stop" -> new CommandStop(event, null);
			case "skip" -> new CommandSkip(event, null);
			case "disconnect" -> new CommandDisconnect(event, null);
			case "pause" -> new CommandPause(event, null);
			case "repeat" -> new CommandRepeat(event, null);
			case "queue" -> new CommandQueue(event, null);
			case "now" -> new CommandNow(event, null);
			case "volume" -> {
				if (args.length > 1) new CommandVolume(args[1], event, null);
				else new CommandVolume(null, event, null);
			}
			case "8ball" -> {
				StringBuilder builder = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					builder.append(args[i]).append(" ");
				}
				new Command8Ball(event, null, builder.toString());
			}

		}
	}
}
