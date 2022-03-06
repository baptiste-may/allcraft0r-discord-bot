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

public class CommandStop {

    public CommandStop(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) {

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

        musicManager.scheduler.player.stopTrack();
        musicManager.scheduler.queue.clear();

        EmbedBuilder embed = UtilsCommands.getEmbedBuilderMusic(user);
        embed.setDescription("⏹ Les musiques ont été arrêté !");
        UtilsCommands.replyOrSend(embed, event1, event2);

    }

}
