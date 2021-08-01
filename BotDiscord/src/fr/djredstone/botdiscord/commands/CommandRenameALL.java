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
				
				StringBuilder message = new StringBuilder();
				for(String arg : args) {
					if(!arg.equalsIgnoreCase(args[0])) {
						message.append(arg);
						message.append(" ");
					}
				}
				if(message.toString() == null) {
					message.append("");
				}
				
				result = message.toString();
				
			}
			
			event.getChannel().sendMessage("Tous les pseudos ont été mis à jours !").queue();
			
			for(Member member : event.getGuild().getMembers()) {
				
				member.modifyNickname(result).queue();
				
			}
			
			event.getChannel().sendTyping().queue();
			event.getMessage().delete().queue();
			
		}
	}

}
