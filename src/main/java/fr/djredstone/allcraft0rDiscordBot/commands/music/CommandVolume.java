package fr.djredstone.allcraft0rDiscordBot.commands.music;

import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.classes.music.GuildMusicManager;
import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandVolume {

    public CommandVolume(SlashCommandInteractionEvent event) {

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild()));

        if (event.getOption("number") == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
            embed.setDescription(String.format("%1$s Le volume de la musique est actuellement à %2$s", Emoji.fromMarkdown("\uD83D\uDD0A"), musicManager.audioPlayer.getVolume()) + "%");
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            return;
        }

        final int volume = Objects.requireNonNull(event.getOption("number")).getAsInt();

        musicManager.audioPlayer.setVolume(volume);

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
        embed.setDescription(String.format("%1$s Le volume de la musique a été mis à jour à %2$s", Emoji.fromMarkdown("\uD83D\uDD0A"), volume) + "%");
        event.replyEmbeds(embed.build()).queue();

    }

}
