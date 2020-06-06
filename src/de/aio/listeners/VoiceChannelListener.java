package de.aio.listeners;

import de.aio.AIO;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceChannelListener extends ListenerAdapter
{
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent e)
	{
		if (!e.getEntity().getUser().isBot())
		{
			Member member = e.getMember();
			VoiceChannel vc = e.getChannelJoined();
			
			if (AIO.partyManager.isLobby(vc.getIdLong()))
			{
				AIO.partyManager.createParty(vc, member);
			}
		}
	}
	
	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent e)
	{
		if (!e.getEntity().getUser().isBot())
		{
			Member member = e.getMember();
			VoiceChannel vc = e.getChannelJoined();
			
			if (AIO.partyManager.isLobby(vc.getIdLong()))
			{
				AIO.partyManager.createParty(vc, member);
			}
		}
	}
	
	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent e)
	{
		if (!e.getEntity().getUser().isBot())
		{
			VoiceChannel vc = e.getChannelLeft();
			
			if (AIO.partyManager.isParty(vc.getIdLong()))
			{
				if (vc.getMembers().isEmpty())
				{
					AIO.partyManager.deleteParty(vc);
				}
			}
		}
	}
}
