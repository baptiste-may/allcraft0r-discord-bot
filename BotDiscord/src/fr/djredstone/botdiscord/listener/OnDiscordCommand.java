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
import fr.djredstone.botdiscord.commands.CommandSend;
import fr.djredstone.botdiscord.commands.CommandTank;
import fr.djredstone.botdiscord.commands.CommandText;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnDiscordCommand extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
        
		new CommandMoney(Main.prefix + event.getName(), event.getUser(), null, event);
		new CommandDaily(Main.prefix + event.getName(), event.getUser(), null, event);
		
		new CommandFindNumber(Main.prefix + event.getName(), null, event.getUser(), null, event);
		
		new CommandHask(Main.prefix + event.getName(), event.getUser(), null, event);
		new CommandNon(Main.prefix + event.getName(), null, event.getUser(), null, event);
		new CommandOui(Main.prefix + event.getName(), null, event.getUser(), null, event);
        new CommandText(Main.prefix + event.getName(), null, null, event.getUser(), null, event);
        
        new CommandSend(Main.prefix + event.getName(), null, event.getUser(), null, event);
        
        new CommandEyes(Main.prefix + event.getName(), event.getUser(), null, event);
        new CommandTank(Main.prefix + event.getName(), event.getUser(), null, event);
        
        new CommandHelp(Main.prefix + event.getName(), event.getUser(), null, event);
        new CommandLink(Main.prefix + event.getName(), event.getUser(), null, event);
        new CommandPing(Main.prefix + event.getName(), event.getUser(), null, event);
        
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].startsWith(Main.prefix)) {
			new CommandMoney(args[0], event.getAuthor(), event, null);
			new CommandDaily(args[0], event.getAuthor(), event, null);
			
			if(args.length > 1) {
				new CommandFindNumber(args[0], args[1], event.getAuthor(), event, null);
			} else {
				new CommandFindNumber(args[0], null, event.getAuthor(), event, null);
			}
			
			new CommandHask(args[0], event.getAuthor(), event, null);
			if(args.length > 1) {
				new CommandNon(args[0], args[1], event.getAuthor(), event, null);
			} else {
				new CommandNon(args[0], null, event.getAuthor(), event, null);
			}
			if(args.length > 1) {
				new CommandOui(args[0], args[1], event.getAuthor(), event, null);
			} else {
				new CommandOui(args[0], null, event.getAuthor(), event, null);
			}
			if(args.length == 2) {
				new CommandText(args[0], args[1], null, event.getAuthor(), event, null);
			} else if(args.length > 2) {
				new CommandText(args[0], args[1], args[2], event.getAuthor(), event, null);
			}
			
			if(args.length > 1) {
				new CommandSend(args[0], args[1], event.getAuthor(), event, null);
			}
			
			new CommandEyes(args[0], event.getAuthor(), event, null);
			new CommandTank(args[0], event.getAuthor(), event, null);
			
			new CommandHelp(args[0], event.getAuthor(), event, null);
			new CommandLink(args[0], event.getAuthor(), event, null);
			new CommandPing(args[0], event.getAuthor(), event, null);
			
		}
		
	}

}
