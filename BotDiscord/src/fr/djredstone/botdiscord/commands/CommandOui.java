package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Objects;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandOui extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("oui")) {
			
			if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) {
                event.reply(Main.noPermMessage).queue();
                return;
            }
			
			String message = event.getOption("oui_message").getAsString();

			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":warning: Message de la hiérarchie :warning:");
			embed.setDescription("Votre demande a été acceptée.");
			embed.setFooter(message.toString());
			embed.setColor(Color.GREEN);
			embed.setThumbnail("https://images.emojiterra.com/twitter/v13.0/512px/2705.png");
			
			event.replyEmbeds(embed.build()).queue();
			event.getChannel().sendTyping().queue();
			
		}
	}

}
