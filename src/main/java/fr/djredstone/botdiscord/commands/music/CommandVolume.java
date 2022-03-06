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

public class CommandVolume {

    public CommandVolume(@Nullable String option, @Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

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

        if (option == null) {
            EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
            embed.setDescription("\uD83D\uDD0A Le volume de la musique est actuellement à " + musicManager.audioPlayer.getVolume() + "%");
            UtilsCommands.replyOrSend(embed, event1, event2);
            return;
        }

        int volume;
        try {
            volume = Integer.parseInt(option);
        } catch (NumberFormatException ignored) {
            UtilsCommands.replyOrSend("Le volume spécifié ne corespond pas. Il doit être compris entre 0 et 100.", event1, event2);
            return;
        }

        musicManager.audioPlayer.setVolume(volume);

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
        embed.setDescription("\uD83D\uDD0A Le volume de la musique a été mis à jour à " + volume + "%");
        UtilsCommands.replyOrSend(embed, event1, event2);

    }

}
