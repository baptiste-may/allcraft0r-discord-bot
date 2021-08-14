package fr.djredstone.botdiscord.listener;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class newMemberListener extends ListenerAdapter {

	public void onNewMember(GuildMemberJoinEvent event) {
		
		Main.newMembers = Main.newMembers + 1;
		
	}
	
}
