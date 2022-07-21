package fr.djredstone.allcraft0rDiscordBot.commands.music;

import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.classes.music.GuildMusicManager;
import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandRepeat {

    public CommandRepeat(SlashCommandInteractionEvent event) {

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild()));
        if (musicManager.audioPlayer.getPlayingTrack() == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
            embed.setDescription(Emoji.fromMarkdown("\uD83D\uDED1") + " Il n'y a pas de musique en ce moment");
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            return;
        }

        musicManager.scheduler.repeating = !musicManager.scheduler.repeating;

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
        if (musicManager.scheduler.repeating) embed.setDescription(Emoji.fromMarkdown("\uD83D\uDD01") + " La liste se répéte à présent");
        else embed.setDescription(Emoji.fromMarkdown("➡️") + " La liste ne se répéte plus");
        event.replyEmbeds(embed.build()).queue();

    }

}
