package fr.djredstone.allcraft0rDiscordBot.listener;

import java.util.Objects;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.*;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandBlacklist;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandFakeBan;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandFakeResetXP;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandHask;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandLock;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandNon;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandOui;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandStopP4;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandText;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandUnlock;
import org.jetbrains.annotations.NotNull;

public class OnDiscordOPCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (!args[0].startsWith("!")) return;

        String cmd = args[0].replace(Main.getPrefix(), "");

        switch(cmd.toLowerCase()) {

            case "ask" -> {
                if (testPerm(event)) return;
                new CommandHask(event);
            }
            case "non" -> {
                if (testPerm(event)) return;
                if (args.length > 1) new CommandNon(args[1], event);
                else new CommandNon(null, event);
            }
            case "oui" -> {
                if (testPerm(event)) return;
                if (args.length > 1) new CommandOui(args[1], event);
                else new CommandOui(null, event);
            }
            case "text" -> {
                if (testPerm(event)) return;
                if (args.length > 1) new CommandText(args[2], event);
                else new CommandText(null, event);
            }
            case "lock" -> {
                if (testPerm(event)) return;
                new CommandLock(event);
            }
            case "unlock" -> {
                if (testPerm(event)) return;
                new CommandUnlock(event);
            }
            case "fakeban" -> {
                if (testPerm(event)) return;
                new CommandFakeBan(event);
            }
            case "fakeresetxp" -> {
                if (testPerm(event)) return;
                new CommandFakeResetXP(event);
            }
            case "stopp4" -> {
                if (testPerm(event)) return;
                new CommandStopP4(event);
            }
            case "blacklist"-> {
                if (testPerm(event)) return;
                new CommandBlacklist(event);
            }

        }

    }

    private static boolean testPerm(MessageReceivedEvent event) {
        if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) {
            UtilsCommands.replyOrSend(Main.getNoPermMessage(), event, null);
            return true;
        }
        return false;
    }

}
