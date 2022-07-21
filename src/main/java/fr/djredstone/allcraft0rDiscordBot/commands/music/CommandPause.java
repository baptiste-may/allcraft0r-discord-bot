package fr.djredstone.allcraft0rDiscordBot.commands.music;

import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandPause {

    public CommandPause(SlashCommandInteractionEvent event) {

        final AudioPlayer player = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild())).audioPlayer;
        if (player.getPlayingTrack() == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
            embed.setDescription(Emoji.fromMarkdown("\uD83D\uDED1") + " Il n'y a pas de musique en ce moment");
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            return;
        }

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
        player.setPaused(!player.isPaused());
        if (player.isPaused()) embed.setDescription(Emoji.fromMarkdown("⏸") + " La musique en cours a été mise en pause !");
        else embed.setDescription(Emoji.fromMarkdown("▶️") + " La musique en cours a été remise en route !");
        event.replyEmbeds(embed.build()).queue();

    }

}
