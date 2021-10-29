package fr.djredstone.botdiscord.commands;

import java.util.HashSet;
import java.util.Set;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandDaily extends ListenerAdapter {
	
	private Set<User> hadGet = new HashSet<>();
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("daily")) {
			
			if(!hadGet.contains(event.getMember().getUser())) {
				
				Main.setMoney(event.getMember().getUser(), Main.getMoney(event.getMember().getUser()) + 200);
				
				event.reply("Tu as reçu **200 redstones** " + event.getMember().getUser().getAsMention()).queue();
				
				hadGet.add(event.getMember().getUser());
				
			} else {
				
				event.reply("Vous avez déjà récupérer votre redstone quotidienne, " + event.getMember().getUser().getAsMention()).queue();
				
			}
			
		}
	}
}
