package fr.djredstone.botdiscord.commands;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class CommandFakeResetXP extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        List<Member> mentionedMembers = event.getMessage().getMentionedMembers();

        if(args[0].equalsIgnoreCase(Main.prefix + "reset-xp")) {
        	
        	Member member = event.getMember();
        	
            if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
                event.getChannel().sendMessage(Main.noPermMessage).queue();
                return;
            }

            if(args.length > 1 && !mentionedMembers.isEmpty()) {

                Member target = mentionedMembers.get(0);
                User userTarget = target.getUser();

                event.getChannel().sendMessage("L'xp de **" + userTarget.getAsTag() + "** a bien été réinitialisé ! ✅").queue();
                event.getMessage().delete().queue();

            } else {

                event.getMessage().reply("Utilisation : " + Main.prefix + "reset-xp <@membre>").queue();

            }

        }

    }

}
