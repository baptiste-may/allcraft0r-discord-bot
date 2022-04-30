package fr.djredstone.botdiscord.commands.hPrivate;

import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.UtilsCommands;

public class CommandFakeResetXP extends ListenerAdapter {

    final String helpMessage = String.format("Utilisation : %1$sfakeresetxp <@user>", Main.getPrefix());

    public CommandFakeResetXP(MessageReceivedEvent event) {

        List<String> args = Arrays.asList(event.getMessage().getContentDisplay().split(" "));
        if (args.size() < 2) {
            UtilsCommands.replyOrSend(helpMessage, event, null);
            return;
        }

        User target;
        target = event.getMessage().getMentionedMembers().get(0).getUser();

        UtilsCommands.replyOrSend(String.format("L'xp de **%1$s** a bien été réinitialisé ! %2$s", target.getAsTag(), Emoji.fromMarkdown("✅")), event, null);

    }

}
