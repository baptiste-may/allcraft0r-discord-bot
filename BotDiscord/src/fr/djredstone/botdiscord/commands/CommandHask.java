package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Objects;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandHask extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("ask")) {
			
			if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) {
                event.getChannel().sendMessage(Main.noPermMessage).queue();
                return;
            }
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":warning: Message de la hiérarchie :warning:");
			embed.setDescription("Votre demande a été prise en compte. :thumbsup:");
			embed.setFooter("Nous vous informerons lorsque nous aurons plus d'informations. :receipt:");
			embed.setColor(Color.ORANGE);
			
			event.replyEmbeds(embed.build()).queue();
			event.getChannel().sendTyping().queue();
			
		}
	}

}
