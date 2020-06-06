package de.aio.manager;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class ChannelManager
{
	public void createTextChannel(String channelName, Category parent)
	{
		ChannelAction<TextChannel> channel = parent.getGuild().createTextChannel(channelName);
		channel.setParent(parent);
		channel.complete();
	}
	
	public void createVoiceChannel(String channelName, Category parent)
	{
		ChannelAction<VoiceChannel> channel = parent.getGuild().createVoiceChannel(channelName);
		channel.setParent(parent);
		channel.complete();
	}
}
