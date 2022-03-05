package fr.djredstone.botdiscord.listener;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.*;

public class OnDiscordCommand extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

		switch (event.getName().toLowerCase()) {
			case "money" -> new CommandMoney(event.getUser(), null, event);
			case "daily" -> new CommandDaily(event.getUser(), null, event);
			case "number" -> new CommandFindNumber(null, event.getUser(), null, event);
			case "quitteoudouble" -> new CommandQuitteOuDouble(event.getUser(), null, null, event);
			case "p4" -> new CommandP4(null, event);
			case "send" -> new CommandSend(event.getUser(), null, null, event);
			case "eyes" -> new CommandEyes(null, event);
			case "tank" -> new CommandTank(null, event);
			case "aide" -> new CommandHelp(null, event);
			case "link" -> new CommandLink(null, event);
			case "ping" -> new CommandPing(null, event);
			case "dashboard" -> new CommandDashboard(null, event);
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
				if(args.length > 1) new CommandQuitteOuDouble(event.getAuthor(), args[1], event, null);
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

		}
	}
}
