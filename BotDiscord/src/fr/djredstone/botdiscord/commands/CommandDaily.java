package fr.djredstone.botdiscord.commands;

import java.util.HashSet;
import java.util.Set;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandDaily extends ListenerAdapter {
	
	private Set<User> hadGet = new HashSet<>();
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.prefix + "daily")) {
			
			if(!hadGet.contains(event.getAuthor())) {
				
				Main.setMoney(event.getAuthor(), Main.getMoney(event.getAuthor()) + 200);
				
				event.getChannel().sendMessage("Tu as reçu **200 redstones** " + event.getAuthor().getAsMention()).queue();
				event.getMessage().delete().queue();
				
				hadGet.add(event.getAuthor());
				
			} else {
				
				event.getChannel().sendMessage("Vous avez déjà récupérer votre redstone quotidienne, " + event.getAuthor().getAsMention()).queue();
				event.getMessage().delete().queue();
				
			}
			
		}
	}
}
