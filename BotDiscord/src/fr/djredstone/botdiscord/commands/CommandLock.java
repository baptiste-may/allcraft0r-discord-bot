package fr.djredstone.botdiscord.commands;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandLock extends ListenerAdapter {
	
	public void onGuildMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Main.prefix + "lock")) {
        	
        	if (!event.getMember().hasPermission(Permission.NICKNAME_MANAGE)) {
                event.getChannel().sendMessage(Main.noPermMessage).queue();
                return;
            }
        	
        	if(event.getMessage().getMentionedChannels().isEmpty() || args[1].equalsIgnoreCase(event.getMessage().getMentionedChannels().get(0).getAsMention())) {
        		event.getMessage().reply("Utilisation : " + Main.prefix + "lock <#channel>").queue();
        		return;
        	}
        	
        	TextChannel channel = event.getMessage().getMentionedChannels().get(0);
        	channel.getRolePermissionOverrides();
        	
        }
	}

}
