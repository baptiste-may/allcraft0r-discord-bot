package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.djredstone.botdiscord.Main;

public class CommandFakeResetXP extends ListenerAdapter {

    final String helpMessage = "Utilisation : " + Main.prefix + "fakeresetxp <@user>";

    public CommandFakeResetXP(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandEvent event2) {

        if (event1 != null) {
            List<String> args = Arrays.asList(event1.getMessage().getContentDisplay().split(" "));
            if (args.size() < 2) {
                UtilsCommands.replyOrSend(helpMessage, event1, event2);
                return;
            }
        }

        Member member;
        if (event1 != null) member = event1.getMember();
        else {
            assert event2 != null;
            member = event2.getMember();
        }

        assert member != null;
        if (!member.hasPermission(Permission.NICKNAME_MANAGE)) {
            UtilsCommands.replyOrSend(Main.noPermMessage, event1, event2);
            return;
        }

        User target;
        if (event1 != null) target = event1.getMessage().getMentionedMembers().get(0).getUser();
        else target = Objects.requireNonNull(event2.getOption("fakeresetxp_user")).getAsUser();

        UtilsCommands.replyOrSend("L'xp de **" + target.getAsTag() + "** a bien été réinitialisé ! ✅", event1, event2);

    }

}
