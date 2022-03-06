package fr.djredstone.botdiscord.commands.forAll;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandDaily {
	
	private static final Set<String> hadGet = new HashSet<>();
	
	public CommandDaily(User user, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
			
		if(!hadGet.contains(user.getId())) {
			
			try {
				Main.setMoney(user, Main.getMoney(user) + 200);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			UtilsCommands.replyOrSend("Tu as reçu **200 redstones** " + Main.getRedstoneEmoji() + user.getAsMention(), event1, event2);
			
			hadGet.add(user.getId());
			
		} else {
			
			UtilsCommands.replyOrSend("Vous avez déjà récupéré votre redstone quotidienne, " + user.getAsMention(), event1, event2);
			
		}
		
	}
	
}
