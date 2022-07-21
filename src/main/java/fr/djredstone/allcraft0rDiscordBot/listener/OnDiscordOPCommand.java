package fr.djredstone.allcraft0rDiscordBot.listener;

import java.util.Objects;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.allcraft0rDiscordBot.Main;
import fr.djredstone.allcraft0rDiscordBot.commands.economy.CommandStopP4;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandBlacklist;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandFakeBan;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandFakeResetXP;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandHask;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandLock;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandNon;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandOui;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandText;
import fr.djredstone.allcraft0rDiscordBot.commands.hPrivate.CommandUnlock;
import org.jetbrains.annotations.NotNull;

public class OnDiscordOPCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (testPerm(event)) return;

        switch (event.getName().toLowerCase()) {
            case "ask" -> new CommandHask(event);
            case "non" -> new CommandNon(event);
            case "oui" -> new CommandOui(event);
            case "text" -> new CommandText(event);
            case "lock" -> new CommandLock(event);
            case "unlock" -> new CommandUnlock(event);
            case "ban" -> new CommandFakeBan(event);
            case "reset-xp" -> new CommandFakeResetXP(event);
            case "stopp4" -> new CommandStopP4(event);
            case "blacklist" -> new CommandBlacklist(event);
        }

    }

    private static boolean testPerm(SlashCommandInteractionEvent event) {
        if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) {
            event.reply(Main.getNoPermMessage()).setEphemeral(true).queue();
            return true;
        }
        return false;
    }

}
