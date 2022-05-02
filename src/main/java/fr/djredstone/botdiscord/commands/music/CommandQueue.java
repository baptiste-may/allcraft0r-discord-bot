package fr.djredstone.botdiscord.commands.music;

import javax.annotation.Nullable;

import java.util.Queue;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import fr.djredstone.botdiscord.commands.UtilsCommands;
import fr.djredstone.botdiscord.classes.music.GuildMusicManager;
import fr.djredstone.botdiscord.classes.music.PlayerManager;

public class CommandQueue {

    public CommandQueue(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

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
        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        Queue<AudioTrack> queue = musicManager.scheduler.queue;
        synchronized (queue) {
            if (queue.isEmpty()) {
                EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
                embed.setDescription(Emoji.fromMarkdown("❔") + " Il n'y a pas de musique dans la liste");
                UtilsCommands.replyOrSend(embed, event1, event2);
            } else {
                int trackCount = (int) musicManager.scheduler.player.getPlayingTrack().getPosition();
                EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
                embed.setDescription(Emoji.fromMarkdown("\uD83D\uDCC3") + " Liste des 10 prochaines musiques :");
                for (AudioTrack track : queue) {
                    if (trackCount < musicManager.scheduler.player.getPlayingTrack().getPosition() + 10) {
                        embed.addField("**" + track.getInfo().title + "**", track.getInfo().author, false);
                        trackCount++;
                    }
                }
                UtilsCommands.replyOrSend(embed, event1, event2);
            }
        }
    }

}
