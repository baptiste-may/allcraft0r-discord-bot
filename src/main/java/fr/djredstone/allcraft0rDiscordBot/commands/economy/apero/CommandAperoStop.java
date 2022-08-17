package fr.djredstone.allcraft0rDiscordBot.commands.economy.apero;

import java.sql.SQLException;
import java.util.HashMap;

import net.dv8tion.jda.api.entities.ThreadChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandAperoStop {

    public CommandAperoStop(SlashCommandInteractionEvent event) throws SQLException {

        final User user = event.getUser();
        final HashMap<User, ThreadChannel> aperos = CommandAperoCreate.getAperos();

        if (aperos.containsKey(user)) {
            ThreadChannel thread = aperos.get(user);
            thread.sendMessage("L'apéro est terminé !").queue();
            thread.getManager().setArchived(true).queue();
            event.reply("L'apéro a été terminé !").setEphemeral(true).queue();
        } else
            event.reply("Aucun apéro de ta part n'est en cours !").setEphemeral(true).queue();

    }

}
