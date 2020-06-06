package de.aio.manager;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.GuildChannel;
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

	public void modifyBitrate(GuildChannel guildChannel, int bitrate)
	{
		guildChannel.getManager().setBitrate(bitrate);
	}

	public void modifyName(GuildChannel guildChannel, String channelName)
	{
		guildChannel.getManager().setName(channelName);
	}

	public void modifyNSFW(GuildChannel guildChannel, boolean isNSFW)
	{
		guildChannel.getManager().setNSFW(isNSFW);
	}

	public void modifyParent(GuildChannel guildChannel, Category parent)
	{
		guildChannel.getManager().setParent(parent);
	}

	public void modifyPosition(GuildChannel guildChannel, int pos)
	{
		guildChannel.getManager().setPosition(pos);
	}

	public void modifySlowmode(GuildChannel guildChannel, int slow)
	{
		guildChannel.getManager().setSlowmode(slow);
	}

	public void modifyTopic(GuildChannel guildChannel, String channelTopic)
	{
		guildChannel.getManager().setTopic(channelTopic);
	}
}
