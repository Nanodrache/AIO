package de.aio.listeners;

import de.aio.AIO;
import de.aio.Options;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ServerJoinListener extends ListenerAdapter
{
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent e)
	{
		if (AIO.INSTANCE.options.getProperty(Options.WELCOME_MESSAGE.name()).equalsIgnoreCase("false"))
		{
			if (!e.getMember().getUser().isBot())
			{
				Member member = e.getMember();
				
				if (!member.hasTimeJoined())
				{
					TextChannel ch = e.getGuild().getTextChannelById(AIO.INSTANCE.options.getProperty(Options.WELCOME_MESSAGE.name()));
					
					ch.sendMessage(AIO.languageManager.getString("welcomeUser")
						.replace("%userName%", member.getEffectiveName())
						.replace("%serverName%", e.getGuild().getName())
					).queue();
				}
			}
		}
	}
}
