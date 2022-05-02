package fr.djredstone.botdiscord.classes.music;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import fr.djredstone.botdiscord.Utils;
import fr.djredstone.botdiscord.commands.UtilsCommands;

public class PlayerManager {
    private static PlayerManager INSTANCE;

    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager() {
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);

            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    public void loadAndPlay(TextChannel channel, String trackURL) {
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track);

                EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
                embed.setDescription(Emoji.fromMarkdown("\uD83D\uDCBF") + " Une musique a été ajouté à la liste :");
                embed.addField("Titre", "**" + track.getInfo().title + "**", true);
                embed.addField("Auteur", "**" + track.getInfo().author + "**", true);
                embed.addField("Durée", "**" + Utils.getTimestamp(track.getInfo().length) + "**", true);
                channel.sendMessageEmbeds(embed.build()).setActionRow(
                        Button.link(track.getInfo().uri, "Lien de la musique")
                ).queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                if (playlist.isSearchResult()) {
                    AudioTrack track = playlist.getTracks().get(0);
                    EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
                    embed.setDescription(Emoji.fromMarkdown("\uD83D\uDCBF") + " Une musique a été ajouté à la liste :");
                    embed.addField("Titre", "**" + track.getInfo().title + "**", true);
                    embed.addField("Auteur", "**" + track.getInfo().author + "**", true);
                    embed.addField("Durée", "**" + Utils.getTimestamp(track.getInfo().length) + "**", true);
                    channel.sendMessageEmbeds(embed.build()).queue();
                    musicManager.scheduler.queue(track);
                } else {
                    noMatches();
                }
            }

            @Override
            public void noMatches() {
                EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
                embed.setColor(Color.RED);
                embed.setDescription("Aucune musique trouvé " + Emoji.fromMarkdown("⚠️"));
                channel.sendMessageEmbeds(embed.build()).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
                embed.setColor(Color.RED);
                embed.setDescription("Erreur lors du chargement de la chanson " + Emoji.fromMarkdown("⚠️"));
                channel.sendMessageEmbeds(embed.build()).queue();
            }
        });
    }

    public static PlayerManager getInstance() {
        if (INSTANCE == null) INSTANCE = new PlayerManager();
        return INSTANCE;
    }

}
