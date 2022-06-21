package fr.djredstone.allcraft0rDiscordBot.commands.forAll;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import fr.djredstone.allcraft0rDiscordBot.commands.UtilsCommands;

public class CommandTank { public CommandTank(@Nullable MessageReceivedEvent event1, @Nullable SlashCommandInteractionEvent event2) { UtilsCommands.replyOrSend("https://tenor.com/view/tank-gif-10952763", event1, event2); } }
