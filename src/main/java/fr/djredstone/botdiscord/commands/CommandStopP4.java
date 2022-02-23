package fr.djredstone.botdiscord.commands;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.Main;

public class CommandStopP4 {

    public CommandStopP4(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

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

        CommandP4.getGame().clear();
        CommandP4.setFirstUser(null);
        CommandP4.setSecondUser(null);
        CommandP4.setGameMessage(null);
        CommandP4.setTour(false);

        UtilsCommands.replyOrSend("La partie en cours a été arrêtée !", event1, event2);

    }

}
