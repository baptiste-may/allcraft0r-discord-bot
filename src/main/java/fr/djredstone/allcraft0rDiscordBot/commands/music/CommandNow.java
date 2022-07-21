package fr.djredstone.allcraft0rDiscordBot.commands.music;

import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import fr.djredstone.allcraft0rDiscordBot.Utils;
import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandNow {

    public CommandNow(SlashCommandInteractionEvent event) {

        if (PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild())).audioPlayer.getPlayingTrack() == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
            embed.setDescription(Emoji.fromMarkdown("\uD83D\uDED1") + " Il n'y a pas de musique en ce moment");
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            return;
        }

        final AudioTrack track = PlayerManager.getInstance().getMusicManager(event.getGuild()).audioPlayer.getPlayingTrack();

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
        embed.setDescription(Emoji.fromMarkdown("\uD83D\uDCCB") + " Informations de la musique en cours :");
        embed.addField("Titre", "**" + track.getInfo().title + "**", true);
        embed.addField("Auteur", "**" + track.getInfo().author + "**", true);
        embed.addField("Durée", "**" + Utils.getTimestamp(track.getInfo().length) + "**", true);
        event.replyEmbeds(embed.build()).setEphemeral(true).queue();

    }

}
