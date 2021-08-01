package fr.djredstone.botdiscord.commands;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandRenameALL extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "renameAll")) {
			
			String result;
			
			if(args[1].equalsIgnoreCase("reset")) {
				
				result = "";
				
			} else {
				
				result = args[1];
				
			}
			
			for(Member member : event.getGuild().getMembers()) {
				
				member.modifyNickname(result);
				
			}
			
		}
	}

}
