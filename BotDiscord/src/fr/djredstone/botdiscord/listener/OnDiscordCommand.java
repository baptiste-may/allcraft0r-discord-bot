package fr.djredstone.botdiscord.listener;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.CommandDaily;
import fr.djredstone.botdiscord.commands.CommandEyes;
import fr.djredstone.botdiscord.commands.CommandFindNumber;
import fr.djredstone.botdiscord.commands.CommandHask;
import fr.djredstone.botdiscord.commands.CommandHelp;
import fr.djredstone.botdiscord.commands.CommandLink;
import fr.djredstone.botdiscord.commands.CommandMoney;
import fr.djredstone.botdiscord.commands.CommandNon;
import fr.djredstone.botdiscord.commands.CommandOui;
import fr.djredstone.botdiscord.commands.CommandPing;
import fr.djredstone.botdiscord.commands.CommandQuitteOuDouble;
import fr.djredstone.botdiscord.commands.CommandSend;
import fr.djredstone.botdiscord.commands.CommandTank;
import fr.djredstone.botdiscord.commands.CommandText;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnDiscordCommand extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		switch(event.getName()) {
		
		case "money":
			new CommandMoney(event.getUser(), null, event);
			break;
		
		case "daily":
			new CommandDaily(event.getUser(), null, event);
			break;
			
		case "number":
			new CommandFindNumber(null, event.getUser(), null, event);
			break;
			
		case "quitteoudouble":
			new CommandQuitteOuDouble(event.getUser(), null, null, event);
			break;
			
		case "ask":
			new CommandHask(null, event);
			break;
			
		case "non":
			new CommandNon(null, null, event);
			break;
			
		case "oui":
			new CommandOui(null, null, event);
			break;
			
		case "text":
			new CommandText(null, null, event);
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
			new CommandHelp(null, event);
			break;
			
		case "link":
			new CommandLink(null, event);
			break;
			
		case "ping":
			new CommandPing(null, event);
			break;
			
		default:
			break;
		
		}
        
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(!args[0].startsWith("!")) return;
		
		String cmd = args[0].replace(Main.prefix, "");
		
		switch(cmd) {
		
		case "money":
			new CommandMoney(event.getAuthor(), event, null);
			break;
			
		case "daily":
			new CommandDaily(event.getAuthor(), event, null);
			break;
		
		case "number":
			if(args.length > 1) {
				new CommandFindNumber(args[1], event.getAuthor(), event, null);
			} else {
				new CommandFindNumber(null, event.getAuthor(), event, null);
			}
			break;
			
		case "quitteoudouble":
			if(args.length > 1) new CommandQuitteOuDouble(event.getAuthor(), args[1], event, null);
			break;
			
		case "ask":
			new CommandHask(event, null);
			break;
			
		case "non":
			if(args.length > 1) {
				new CommandNon(args[1], event, null);
			} else {
				new CommandNon(null, event, null);
			}
			break;
			
		case "oui":
			if(args.length > 1) {
				new CommandNon(args[1], event, null);
			} else {
				new CommandNon(null, event, null);
			}
			break;
			
		case "text":
			if(args.length > 1) {
				new CommandText(args[2], event, null);
			} else {
				new CommandText(null, event, null);
			}
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
			new CommandHelp(event, null);
			break;
			
		case "link":
			new CommandLink(event, null);
			break;
			
		case "ping":
			new CommandPing(event, null);
			break;
			
		default:
			break;
		
		}
		
	}

}
