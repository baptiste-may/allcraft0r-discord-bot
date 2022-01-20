package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandMoney {
	
	public CommandMoney(User user, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {
        	
        UtilsCommands.replyOrSend((user.getAsMention() + ", tu as **" + Main.getMoney(user) + " redstones** " + Main.redstoneEmoji), event1, event2);
		
	}

}
