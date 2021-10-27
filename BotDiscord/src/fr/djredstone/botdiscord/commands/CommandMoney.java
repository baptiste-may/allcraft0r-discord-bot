package fr.djredstone.botdiscord.commands;

import org.jetbrains.annotations.NotNull;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandMoney extends ListenerAdapter {
	
	public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        User user = event.getAuthor();
        
        if(args[0].equalsIgnoreCase(Main.prefix + "money")) {
        	
        	event.getChannel().sendMessage(event.getAuthor().getAsMention() + ", tu as **" + Main.getMoney(user) + " redstones**").queue();
        	event.getMessage().delete().queue();
        	
        }
        
	}

}
