package fr.djredstone.botdiscord.listener;

import java.sql.SQLException;
import java.util.Objects;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.classes.blacklist;
import fr.djredstone.botdiscord.commands.forAll.*;
import fr.djredstone.botdiscord.commands.economy.CommandDaily;
import fr.djredstone.botdiscord.commands.economy.CommandDashboard;
import fr.djredstone.botdiscord.commands.economy.CommandFindNumber;
import fr.djredstone.botdiscord.commands.economy.CommandMoney;
import fr.djredstone.botdiscord.commands.economy.CommandP4;
import fr.djredstone.botdiscord.commands.economy.CommandSlot;
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

		try {
			if (blacklist.contains(event.getUser())) {
				event.reply("Vous êtes actuellement blacklist !").setEphemeral(true).queue();
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		switch (event.getName().toLowerCase()) {
			case "money" -> new CommandMoney(event.getUser(), null, event);
			case "daily" -> new CommandDaily(event.getUser(), null, event);
			case "number" -> new CommandFindNumber(null, event.getUser(), null, event);
			case "p4" -> new CommandP4(null, event);
			case "dashboard" -> new CommandDashboard(null, event);
			case "slot" -> new CommandSlot(null, event);

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
				if (chekBlacklist(event)) return;
				if (args.length > 1) new CommandFindNumber(args[1], event.getAuthor(), event, null);
				else new CommandFindNumber(null, event.getAuthor(), event, null);
			}
			case "slot" -> {
				if (chekBlacklist(event)) return;
				new CommandSlot(event, null);
			}
			case "money" -> {
				if (chekBlacklist(event)) return;
				new CommandMoney(event.getAuthor(), event, null);
			}
			case "daily" -> {
				if (chekBlacklist(event)) return;
				new CommandDaily(event.getAuthor(), event, null);
			}
			case "send" -> {
				if (chekBlacklist(event)) return;
				new CommandSend(event.getAuthor(), args[1], event, null);
			}
			case "eyes" -> {
				if (chekBlacklist(event)) return;
				new CommandEyes(event, null);
			}
			case "tank" -> {
				if (chekBlacklist(event)) return;
				new CommandTank(event, null);
			}
			case "aide" -> {
				if (chekBlacklist(event)) return;
				new CommandHelp(event, null);
			}
			case "link" -> {
				if (chekBlacklist(event)) return;
				new CommandLink(event, null);
			}
			case "ping" -> {
				if (chekBlacklist(event)) return;
				new CommandPing(event, null);
			}
			case "dashboard" -> {
				if (chekBlacklist(event)) return;
				new CommandDashboard(event, null);
			}
			case "p4" -> {
				if (chekBlacklist(event)) return;
				new CommandP4(event, null);
			}
			case "play" -> {
				if (chekBlacklist(event)) return;
				if (args.length > 1) {
					StringBuilder builder = new StringBuilder();
					for (int i = 1; i < args.length; i++) {
						builder.append(args[i]);
					}
					new CommandPlay(builder.toString(), event, null);
				}
				else new CommandPlay(null, event, null);
			}
			case "stop" -> {
				if (chekBlacklist(event)) return;
				new CommandStop(event, null);
			}
			case "skip" -> {
				if (chekBlacklist(event)) return;
				new CommandSkip(event, null);
			}
			case "disconnect" -> {
				if (chekBlacklist(event)) return;
				new CommandDisconnect(event, null);
			}
			case "pause" -> {
				if (chekBlacklist(event)) return;
				new CommandPause(event, null);
			}
			case "repeat" -> {
				if (chekBlacklist(event)) return;
				new CommandRepeat(event, null);
			}
			case "queue" -> {
				if (chekBlacklist(event)) return;
				new CommandQueue(event, null);
			}
			case "now" -> {
				if (chekBlacklist(event)) return;
				new CommandNow(event, null);
			}
			case "volume" -> {
				if (chekBlacklist(event)) return;
				if (args.length > 1) new CommandVolume(args[1], event, null);
				else new CommandVolume(null, event, null);
			}
			case "8ball" -> {
				if (chekBlacklist(event)) return;
				StringBuilder builder = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					builder.append(args[i]).append(" ");
				}
				new Command8Ball(event, null, builder.toString());
			}

		}
	}

	private static boolean chekBlacklist(MessageReceivedEvent event) {
		try {
			if (blacklist.contains(event.getAuthor())) {
				event.getMessage().delete().queue();
				event.getAuthor().openPrivateChannel().queue(channel -> channel.sendMessage("Vous êtes actuellement blacklist !").queue());
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
