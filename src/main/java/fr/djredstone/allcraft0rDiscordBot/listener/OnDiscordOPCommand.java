package fr.djredstone.allcraft0rDiscordBot.listener;

import javax.annotation.Nullable;
import java.util.Objects;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.allcraft0rDiscordBot.Main;
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
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (testPerm(null, event)) return;

        switch (event.getName().toLowerCase()) {
            case "ask":
                new CommandHask(null, event);
                break;

            case "non":
                if (event.getOption("non_message") != null) new CommandNon(Objects.requireNonNull(event.getOption("non_message")).getAsString(), null, event);
                else new CommandNon(null, null, event);
                break;

            case "oui":
                if (event.getOption("oui_message") != null) new CommandOui(Objects.requireNonNull(event.getOption("oui_message")).getAsString(), null, event);
                else new CommandOui(null, null, event);
                break;

            case "text":
                if (event.getOption("text_channel") != null) new CommandText(Objects.requireNonNull(event.getOption("text_message")).getAsString(), Objects.requireNonNull(event.getOption("text_channel")).getAsTextChannel(), null, event);
                else new CommandText(Objects.requireNonNull(event.getOption("text_message")).getAsString(), null, null, event);
                break;

            case "lock":
                if (event.getOption("lock_message") != null) new CommandLock(Objects.requireNonNull(event.getOption("lock_channel")).getAsTextChannel(), Objects.requireNonNull(event.getOption("lock_message")).getAsString(), null, event);
                else new CommandLock(Objects.requireNonNull(event.getOption("lock_channel")).getAsTextChannel(), null, null, event);
                break;

            case "unlock":
                if (event.getOption("unlock_message") != null) new CommandUnlock(Objects.requireNonNull(event.getOption("unlock_channel")).getAsTextChannel(), Objects.requireNonNull(event.getOption("unlock_message")).getAsString(), null, event);
                else new CommandUnlock(Objects.requireNonNull(event.getOption("unlock_channel")).getAsTextChannel(), null, null, event);
                break;

            case "ban":
                if (event.getOption("fakeban_message") != null) new CommandFakeBan(Objects.requireNonNull(event.getOption("fakeban_user")).getAsUser(), Objects.requireNonNull(event.getOption("fakeban_message")).getAsString(), null, event);
                new CommandFakeBan(Objects.requireNonNull(event.getOption("fakeban_user")).getAsUser(), null, null, event);
                break;

            case "reset-xp":
                new CommandFakeResetXP(Objects.requireNonNull(event.getOption("reset_xp_user")).getAsUser(), null, event);
                break;

            case "stopp4":
                new CommandStopP4(null, event);
                break;

            case "blacklist":
                new CommandBlacklist(Objects.requireNonNull(event.getOption("blacklist_action")).getAsString(), Objects.requireNonNull(event.getOption("blacklist_user")).getAsUser(), null, event);
                break;
        }

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (testPerm(event, null)) return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (!args[0].startsWith("!")) return;

        String cmd = args[0].replace(Main.getPrefix(), "");

        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < args.length; i++) builder.append(args[i]).append(" ");

        switch(cmd.toLowerCase()) {

            case "ask":
                new CommandHask(event, null);
                break;

            case "non":
                if (args.length > 1) new CommandNon(args[1], event, null);
                else new CommandNon(null, event, null);
                break;

            case "oui":
                if (args.length > 1) new CommandOui(args[1], event, null);
                else new CommandOui(null, event, null);
                break;

            case "text":
                if (!event.getMessage().getMentions().getChannels().isEmpty()) new CommandText(builder.toString(), (TextChannel) event.getMessage().getMentions().getChannels().get(0), event, null);
                else new CommandText(null, null, event, null);
                break;

            case "lock":
                new CommandLock((TextChannel) event.getMessage().getMentions().getChannels().get(0), builder.toString(), event, null);
                break;

            case "unlock":
                new CommandUnlock((TextChannel) event.getMessage().getMentions().getChannels().get(0), builder.toString(), event, null);
                break;

            case "ban":
                new CommandFakeBan(event.getMessage().getMentions().getUsers().get(0), builder.toString(), event, null);
                break;

            case "reset-xp":
                new CommandFakeResetXP(event.getMessage().getMentions().getUsers().get(0), event, null);
                break;

            case "stopp4":
                new CommandStopP4(event, null);
                break;

            case "blacklist":
                new CommandBlacklist(args[1], event.getMessage().getMentions().getUsers().get(0), event, null);
                break;

        }

    }

    private static boolean testPerm(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {
        if(event1 != null) {
            if (!Objects.requireNonNull(event1.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) return true;
        }

        if(event2 != null) {
            if (!Objects.requireNonNull(event2.getMember()).hasPermission(Permission.NICKNAME_MANAGE)) {
                event2.reply(Main.getNoPermMessage()).setEphemeral(true).queue();
                return true;
            }
        }
        return false;
    }

}
