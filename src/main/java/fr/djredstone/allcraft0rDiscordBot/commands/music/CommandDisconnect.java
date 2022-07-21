package fr.djredstone.allcraft0rDiscordBot.commands.music;

import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import fr.djredstone.allcraft0rDiscordBot.classes.music.GuildMusicManager;
import fr.djredstone.allcraft0rDiscordBot.classes.music.PlayerManager;
import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandDisconnect {

    public CommandDisconnect(SlashCommandInteractionEvent event) {

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild()));
        musicManager.scheduler.player.stopTrack();
        musicManager.scheduler.queue.clear();

        event.getGuild().getAudioManager().closeAudioConnection();

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic();
        embed.setDescription("Déconnecté du salon vocal " + Emoji.fromMarkdown("✅"));
        event.replyEmbeds(embed.build()).queue();

    }

}
