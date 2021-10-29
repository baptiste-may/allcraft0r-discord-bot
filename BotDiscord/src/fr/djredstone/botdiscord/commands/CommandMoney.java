package fr.djredstone.botdiscord.commands;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandMoney extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
        
        if(event.getName().equalsIgnoreCase("money")) {
        	
        	event.reply(event.getUser().getAsMention() + ", tu as **" + Main.getMoney(event.getUser()) + " redstones** " + Main.redstoneEmoji).queue();
        	
        }
        
	}

}
