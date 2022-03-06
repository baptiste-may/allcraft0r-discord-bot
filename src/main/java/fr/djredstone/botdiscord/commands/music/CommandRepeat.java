package fr.djredstone.botdiscord.commands.music;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.botdiscord.commands.UtilsCommands;
import fr.djredstone.botdiscord.music.GuildMusicManager;
import fr.djredstone.botdiscord.music.PlayerManager;

public class CommandRepeat {

    public CommandRepeat(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

        Guild guild;
        User user;
        if (event1 != null) {
            guild = event1.getGuild();
            user = event1.getAuthor();
        }
        else {
            assert event2 != null;
            guild = event2.getGuild();
            user = event2.getUser();
        }
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        if (musicManager.audioPlayer.getPlayingTrack() == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
            embed.setDescription("\uD83D\uDED1 Il n'y a pas de musique en ce moment");
            UtilsCommands.replyOrSend(embed, event1, event2);
            return;
        }

        musicManager.scheduler.repeating = !musicManager.scheduler.repeating;

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
        if (musicManager.scheduler.repeating) embed.setDescription("\uD83D\uDD01 La liste se répéte à présent");
        else embed.setDescription("➡️ La liste ne se répéte plus");
        UtilsCommands.replyOrSend(embed, event1, event2);

    }

}
