package fr.djredstone.botdiscord.listener;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.botdiscord.Main;
import fr.djredstone.botdiscord.commands.*;

public class OnDiscordOPCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        Member member = event.getMember();
        assert member != null;
        if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
            UtilsCommands.replyOrSend(Main.getNoPermMessage(), null, event);
            return;
        }

        switch (event.getName().toLowerCase()) {

            case "ask" -> new CommandHask(null, event);
            case "non" -> new CommandNon(null, null, event);
            case "oui" -> new CommandOui(null, null, event);
            case "text" -> new CommandText(null, null, event);
            case "lock" -> new CommandLock(null, event);
            case "unlock" -> new CommandUnlock(null, event);
            case "fakeban" -> new CommandFakeBan(null, event);
            case "fakeresetxp" -> new CommandFakeResetXP(null, event);
            case "stopp4" -> new CommandStopP4(null, event);

        }

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Member member = event.getMember();
        assert member != null;
        if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
            UtilsCommands.replyOrSend(Main.getNoPermMessage(), event, null);
            return;
        }

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (!args[0].startsWith("!")) return;

        String cmd = args[0].replace(Main.getPrefix(), "");

        switch(cmd.toLowerCase()) {

            case "ask" -> new CommandHask(event, null);
            case "non" -> {
                if (args.length > 1) new CommandNon(args[1], event, null);
                else new CommandNon(null, event, null);
            }
            case "oui" -> {
                if (args.length > 1) new CommandOui(args[1], event, null);
                else new CommandOui(null, event, null);
            }
            case "text" -> {
                if (args.length > 1) new CommandText(args[2], event, null);
                else new CommandText(null, event, null);
            }
            case "lock" -> new CommandLock(event, null);
            case "unlock" -> new CommandUnlock(event, null);
            case "fakeban" -> new CommandFakeBan(event, null);
            case "fakeresetxp" -> new CommandFakeResetXP(event, null);
            case "stopp4" -> new CommandStopP4(event, null);

        }

    }

}
