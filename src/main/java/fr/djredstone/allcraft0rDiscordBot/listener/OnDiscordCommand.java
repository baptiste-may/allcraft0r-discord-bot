package fr.djredstone.allcraft0rDiscordBot.listener;

import java.sql.SQLException;
import java.util.Objects;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.allcraft0rDiscordBot.Setup;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandDaily;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandDashboard;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandFindNumber;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandMoney;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandP4;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandSlot;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.apero.CommandAperoCreate;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.apero.CommandAperoStop;
import fr.djredstone.allcraft0rDiscordBot.commands.forAll.Command8Ball;
import fr.djredstone.allcraft0rDiscordBot.commands.forAll.CommandEyes;
import fr.djredstone.allcraft0rDiscordBot.commands.forAll.CommandHelp;
import fr.djredstone.allcraft0rDiscordBot.commands.forAll.CommandLink;
import fr.djredstone.allcraft0rDiscordBot.commands.forAll.CommandPing;
import fr.djredstone.allcraft0rDiscordBot.commands.forAll.CommandSend;
import fr.djredstone.allcraft0rDiscordBot.commands.forAll.CommandTank;
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

		if (UtilsCommands.chekBlacklist(event)) return;

		try {
			switch (event.getName().toLowerCase()) {
				case "money" -> new CommandMoney(event);
				case "daily" -> new CommandDaily(event);
				case "number" -> new CommandFindNumber(event);
				case "p4" -> new CommandP4(event);
				case "dashboard" -> new CommandDashboard(event);
				case "slot" -> new CommandSlot(event);
				case "send" -> new CommandSend(event);
				case "eyes" -> new CommandEyes(event);
				case "tank" -> new CommandTank(event);
				case "aide" -> new CommandHelp(event);
				case "link" -> new CommandLink(event);
				case "ping" -> new CommandPing(event);
				case "play" -> new CommandPlay(event);
				case "stop" -> new CommandStop(event);
				case "skip" -> new CommandSkip(event);
				case "disconnect" -> new CommandDisconnect(event);
				case "pause" -> new CommandPause(event);
				case "repeat" -> new CommandRepeat(event);
				case "queue" -> new CommandQueue(event);
				case "now" -> new CommandNow(event);
				case "volume" -> new CommandVolume(event);
				case "8ball" -> new Command8Ball(event);
				case "apero" -> {
					switch (Objects.requireNonNull(event.getSubcommandName()).toLowerCase()) {
						case "create" -> new CommandAperoCreate(event);
						case "stop" -> new CommandAperoStop(event);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			event.reply(UtilsCommands.getSQLErrorMessage()).setEphemeral(true).queue();
			Setup.DBConnect();
		}
	}
}
