package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandFakeBan extends ListenerAdapter {
	
	public void onGuildMessageReceived(MessageReceivedEvent event) {
		
		String msg = event.getMessage().getContentRaw();
        if (msg.startsWith(Main.prefix + "fakeban")) {
            MessageChannel channel = event.getChannel();
            Member member = event.getMember();
            List<Member> mentionedMembers = event.getMessage().getMentionedMembers();

            List<String> args = Arrays.asList(msg.split(" "));
            if (args.size() < 2) {
            	event.getMessage().reply("Utilisation : " + Main.prefix + "fakeban <@membre> (raison)\n").queue();
                return;
            }
            
            String name;
            String avatarURL;
            
            if(mentionedMembers.isEmpty()) {
            	
            	if(event.getMessage().getAttachments().isEmpty()) {
            		
            		event.getMessage().reply("Utilisation : " + Main.prefix + "fakeban <@membre> (raison)\n").queue();
            		return;
            		
            	} else if(Objects.requireNonNull(event.getMessage().getAttachments().get(0).getContentType()).startsWith("image")) {
            		
            		name = args.get(1);
                	avatarURL = event.getMessage().getAttachments().get(0).getUrl();
            		
            	} else {
            		
            		event.getMessage().reply("Utilisation : " + Main.prefix + "fakeban <@membre> (raison)\n").queue();
            		return;
            		
            	}
            	
            } else {
            	
            	Member target = mentionedMembers.get(0);
                User userTarget = target.getUser();
                
                name = userTarget.getAsTag();
                avatarURL = userTarget.getAvatarUrl();
            	
            }

            String reason = String.join(" ", args.subList(2, args.size()));
            
            if(reason.equalsIgnoreCase("")) {
            	reason = "Non spécifiée";
            }

            assert member != null;
            if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
                channel.sendMessage(Main.noPermMessage).queue();
                return;
            }
            
            EmbedBuilder embed = new EmbedBuilder();
            embed.setAuthor(name + " a été banni", null, avatarURL);
            embed.setDescription("**Raison :** " + reason);
			embed.setColor(Color.DARK_GRAY);

            event.getChannel().sendMessageEmbeds(embed.build()).queue();
            event.getChannel().sendTyping().queue();
			event.getMessage().delete().queue();
            
        }
		
	}

}
