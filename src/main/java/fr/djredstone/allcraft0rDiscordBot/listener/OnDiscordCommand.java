package fr.djredstone.allcraft0rDiscordBot.listener;

import java.sql.SQLException;
import java.util.Objects;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.Setup;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;
import fr.djredstone.allcraft0rDiscordBot.commands.forAll.*;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandDaily;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandDashboard;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandFindNumber;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandMoney;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandP4;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandSlot;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandDisconnect;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandNow;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandPause;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandPlay;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandQueue;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandRepeat;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandSkip;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandStop;
import fr.djredstone.allcraft0rDiscordBot.commands.music.CommandVolume;
import org.jetbrains.annotations.NotNull;

public class OnDiscordCommand extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

		if (UtilsCommands.chekBlacklist(null, event)) return;

		try {
			switch (event.getName().toLowerCase()) {
				case "money":
					new CommandMoney(event.getUser(), null, event);
					break;
				case "daily":
					new CommandDaily(event.getUser(), null, event);
					break;
				case "number":
					new CommandFindNumber(null, event.getUser(), null, event);
					break;
				case "p4":
					new CommandP4(null, event);
					break;
				case "dashboard":
					new CommandDashboard(null, event);
					break;
				case "slot":
					new CommandSlot(null, event);
					break;

				case "send":
					new CommandSend(event.getUser(), null, null, event);
					break;
				case "eyes":
					new CommandEyes(null, event);
					break;
				case "tank":
					new CommandTank(null, event);
					break;
				case "aide":
					new CommandHelp(Objects.requireNonNull(event.getOption("help_arg")).getAsString(), null, event);
					break;
				case "link":
					new CommandLink(null, event);
					break;
				case "ping":
					new CommandPing(null, event);
					break;

				case "play":
					if (event.getOption("play_search") != null) new CommandPlay(Objects.requireNonNull(event.getOption("play_search")).getAsString(), null, event);
					else new CommandPlay(null, null, event);
					break;
				case "stop":
					new CommandStop(null, event);
					break;
				case "skip":
					new CommandSkip(null, event);
					break;
				case "disconnect":
					new CommandDisconnect(null, event);
					break;
				case "pause":
					new CommandPause(null, event);
					break;
				case "repeat":
					new CommandRepeat(null, event);
					break;
				case "queue":
					new CommandQueue(null, event);
					break;
				case "now":
					new CommandNow(null, event);
					break;
				case "volume":
					if (event.getOption("volume") != null) new CommandVolume(Objects.requireNonNull(event.getOption("volume")).getAsString(), null, event);
					else new CommandVolume(null, null, event);
					break;
				case "8ball":
					new Command8Ball(null, event, Objects.requireNonNull(event.getOption("8ball")).getAsString());
					break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			UtilsCommands.replyOrSend(UtilsCommands.getSQLErrorMessage(), null, event);
			Setup.DBConnect();
		}
	}
	
	@Override
	public void onMessageReceived(@NotNull MessageReceivedEvent event) {

		if (UtilsCommands.chekBlacklist(event, null)) return;

		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(!args[0].startsWith("!")) return;
		
		String cmd = args[0].replace(Main.getPrefix(), "");

		try {
			switch(cmd.toLowerCase()) {
				case "number":
					if (args.length > 1) new CommandFindNumber(args[1], event.getAuthor(), event, null);
					else new CommandFindNumber(null, event.getAuthor(), event, null);
					break;

				case "slot":
					new CommandSlot(event, null);
					break;

				case "money":
					new CommandMoney(event.getAuthor(), event, null);
					break;

				case "daily":
					new CommandDaily(event.getAuthor(), event, null);
					break;

				case "send":
					new CommandSend(event.getAuthor(), args[1], event, null);
					break;

				case "eyes":
					new CommandEyes(event, null);
					break;

				case "tank":
					new CommandTank(event, null);
					break;

				case "aide":
					new CommandHelp(args[1], event, null);
					break;

				case "link":
					new CommandLink(event, null);
					break;

				case "ping":
					new CommandPing(event, null);
					break;

				case "dashboard":
					new CommandDashboard(event, null);
					break;

				case "p4":
					new CommandP4(event, null);
					break;

				case "play":
					if (args.length > 1) {
						StringBuilder builder = new StringBuilder();
						for (int i = 1; i < args.length; i++) {
							builder.append(args[i]);
						}
						new CommandPlay(builder.toString(), event, null);
					}
					else new CommandPlay(null, event, null);
					break;

				case "stop":
					new CommandStop(event, null);
					break;

				case "skip":
					new CommandSkip(event, null);
					break;

				case "disconnect":
					new CommandDisconnect(event, null);
					break;

				case "pause":
					new CommandPause(event, null);
					break;

				case "repeat":
					new CommandRepeat(event, null);
					break;

				case "queue":
					new CommandQueue(event, null);
					break;

				case "now":
					new CommandNow(event, null);
					break;

				case "volume":
					if (args.length > 1) new CommandVolume(args[1], event, null);
					else new CommandVolume(null, event, null);
					break;

				case "8ball":
					StringBuilder builder = new StringBuilder();
					for (int i = 1; i < args.length; i++) {
						builder.append(args[i]).append(" ");
					}
					new Command8Ball(event, null, builder.toString());
					break;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			UtilsCommands.replyOrSend(UtilsCommands.getSQLErrorMessage(), event, null);
		}
	}
}
