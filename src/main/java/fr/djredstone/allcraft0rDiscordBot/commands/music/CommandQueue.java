package fr.djredstone.allcraft0rDiscordBot.commands.music;

import java.util.Objects;
import java.util.Queue;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import fr.djredstone.allcraft0rDiscordBot.classes.music.GuildMusicManager;
import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandQueue {

    public CommandQueue(SlashCommandInteractionEvent event) {

        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild()));
        Queue<AudioTrack> queue = musicManager.scheduler.queue;
        synchronized (queue) {
            if (queue.isEmpty()) {
                EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
                embed.setDescription(Emoji.fromMarkdown("❔") + " Il n'y a pas de musique dans la liste");
                event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            } else {
                int trackCount = (int) musicManager.scheduler.player.getPlayingTrack().getPosition();
                EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
                embed.setDescription(Emoji.fromMarkdown("\uD83D\uDCC3") + " Liste des 10 prochaines musiques :");
                for (AudioTrack track : queue) {
                    if (trackCount < musicManager.scheduler.player.getPlayingTrack().getPosition() + 10) {
                        embed.addField("**" + track.getInfo().title + "**", track.getInfo().author, false);
                        trackCount++;
                    }
                }
                event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            }
        }
    }

}
