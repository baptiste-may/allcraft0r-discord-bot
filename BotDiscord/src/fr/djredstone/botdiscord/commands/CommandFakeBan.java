package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandFakeBan extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String msg = event.getMessage().getContentRaw();
        if (msg.startsWith(Main.prefix + "fakeban")) {
            TextChannel channel = event.getChannel();
            Member member = event.getMember();
            List<Member> mentionedMembers = event.getMessage().getMentionedMembers();

            List<String> args = Arrays.asList(msg.split(" "));
            if (args.size() < 2) {
            	event.getMessage().reply("Utilisation : " + Main.prefix + "fakeban <@membre> (raison)\n").queue();
                return;
            }
            
            String name = null;
            String avatarURL = null;
            
            if(mentionedMembers.isEmpty()) {
            	
            	if(event.getMessage().getAttachments().isEmpty()) {
            		
            		event.getMessage().reply("Utilisation : " + Main.prefix + "fakeban <@membre> (raison)\n").queue();
            		return;
            		
            	} else if(event.getMessage().getAttachments().get(0).getContentType().equalsIgnoreCase("image/png")) {
            		
            		name = args.get(1);
                	avatarURL = event.getMessage().getAttachments().get(0).getUrl();
            		
            	} else {
            		
            		event.getMessage().reply("Utilisation : " + Main.prefix + "fakeban <@membre> (raison)\n").queue();
            		return;
            		
            	}
            	
            } else {
            	
            	Member target = mentionedMembers.get(0);
                User userTarget = target.getUser();
                
                name = userTarget.getAsTag().toString();
                avatarURL = userTarget.getAvatarUrl();
            	
            }

            String reason = String.join(" ", args.subList(2, args.size()));
            
            if(reason.equalsIgnoreCase("")) {
            	reason = "Non spécifiée";
            }

            if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
                channel.sendMessage(Main.noPermMessage).queue();
                return;
            }
            
            EmbedBuilder embed = new EmbedBuilder();
            embed.setAuthor(name + " a été banni", null, avatarURL);
			embed.setDescription("**Raison :** " + reason);
			embed.setColor(Color.DARK_GRAY);

            event.getChannel().sendMessage(embed.build()).queue();
            event.getChannel().sendTyping().queue();
			event.getMessage().delete().queue();
            
        }
		
	}

}
