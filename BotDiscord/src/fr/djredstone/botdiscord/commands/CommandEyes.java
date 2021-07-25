package fr.djredstone.botdiscord.commands;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandEyes extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "eyes")) {
			
			event.getChannel().sendMessage(":eye::lips::eye:").queue();
			event.getChannel().sendTyping().queue();
			event.getMessage().delete().queue();
			
		}
	}

}
