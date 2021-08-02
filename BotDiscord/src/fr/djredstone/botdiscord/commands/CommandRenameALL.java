package fr.djredstone.botdiscord.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.djredstone.botdiscord.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandRenameALL extends ListenerAdapter {
	
	Main main;
	
	public CommandRenameALL(Main main) {
		this.main = main;
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        
        File file = new File(main.getDataFolder(), "renameAll.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		if(args[0].equalsIgnoreCase(Main.prefix + "renameAll")) {
			
			String result = null;
			
			if(args[1].equalsIgnoreCase("reset")) {
				
				result = "";
				
			} else if(args[1].equalsIgnoreCase("backup")) {
				
				@SuppressWarnings("unchecked")
				ArrayList<String> list = (ArrayList<String>) config.get("backup");
				for(String user : list) {
					event.getGuild().getMember(Main.jda.getUserById(user)).modifyNickname(config.getString("backup." + user));
				}
				
			}else {
				
				StringBuilder message = new StringBuilder();
				for(String arg : args) {
					if(!arg.equalsIgnoreCase(args[0])) {
						message.append(arg);
						message.append(" ");
					}
				}
				if(message.toString() == null) {
					message.append("");
				}
				
				result = message.toString();
				
				for(Member member : event.getGuild().getMembers()) {
					
					config.set("backup." + member.getId().toString(), member.getNickname());
					
				}
				
				try {
					config.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			event.getChannel().sendMessage("Tous les pseudos ont été mis à jours !").queue();
			
			for(Member member : event.getGuild().getMembers()) {
				
				if(event.getGuild().getMember(Main.jda.getUserById("290399813697142786")) != member) {
					
					member.modifyNickname(result).queue();
					
				}
				
				/*
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				
			}
			
			event.getChannel().sendTyping().queue();
			event.getMessage().delete().queue();
			
		}
	}

}
