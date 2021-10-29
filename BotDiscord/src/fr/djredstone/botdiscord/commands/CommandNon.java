package fr.djredstone.botdiscord.commands;

import java.awt.Color;
import java.util.Objects;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandNon extends ListenerAdapter {
	
	public void onSlashCommand(SlashCommandEvent event) {
		
		if(event.getName().equalsIgnoreCase("non")) {
			
			if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) {
                event.reply(Main.noPermMessage).queue();
                return;
            }
			
			String message = event.getOption("non_message").getAsString();

			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(":warning: Message de la hiérarchie :warning:");
			embed.setDescription("Votre demande a été refusée.");
			embed.setFooter(message);
			embed.setColor(Color.RED);
			embed.setThumbnail("https://images.emojiterra.com/google/android-10/512px/274c.png");
			
			event.replyEmbeds(embed.build()).queue();
			event.getChannel().sendTyping().queue();
			
		}
	}

}
