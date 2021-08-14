package fr.djredstone.botdiscord.listener;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings("deprecation")
public class lostMemberListener extends ListenerAdapter {
	
	public void onNewMember(GuildMemberLeaveEvent event) {
		
		Main.lostMembers = Main.lostMembers + 1;
		
	}

}
