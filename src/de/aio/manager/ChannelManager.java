package de.aio.manager;

import java.awt.Color;

import de.aio.AIO;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
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

	public void getChannelInfo(GuildChannel guildChannel, Member member)
	{
		EmbedBuilder eb = new EmbedBuilder();
		eb.setAuthor(AIO.INSTANCE.jda.getSelfUser().getName());
		eb.setColor(Color.ORANGE);
		eb.setTitle("Channelinfo : " + guildChannel.getName());
		eb.setThumbnail(guildChannel.getGuild().getBannerUrl());

		eb.addField(AIO.languageManager.getString("helpName"), guildChannel.getName(), false);
		eb.addField(AIO.languageManager.getString("helpId"), guildChannel.getId(), false);
		eb.addField(AIO.languageManager.getString("helpType"), guildChannel.getType().name(), false);
		eb.addField(AIO.languageManager.getString("helpCreated"), guildChannel.getTimeCreated() + "", false);
		eb.addField(AIO.languageManager.getString("helpParent"), guildChannel.getParent().getName(), false);
		eb.addField(AIO.languageManager.getString("helpPosition"), guildChannel.getPosition() + "", false);
		eb.addField(AIO.languageManager.getString("helpGuildName"), guildChannel.getManager().getGuild().getName(), false);
		eb.addField(AIO.languageManager.getString("helpGuildId"), guildChannel.getManager().getGuild().getId(), false);
		
		if (guildChannel.getType() == ChannelType.TEXT)
		{
			TextChannel tchannel = (TextChannel) guildChannel;

			eb.addField(AIO.languageManager.getString("helpTopic"), tchannel.getTopic(), false);
			eb.addField(AIO.languageManager.getString("helpNSFW"), tchannel.isNSFW() + "", false);
			eb.addField(AIO.languageManager.getString("helpSlowmode"), tchannel.getSlowmode() + "", false);
			eb.addField(AIO.languageManager.getString("helpMessageCounter"), tchannel.getHistory().size() + "", false);
		}
		else if (guildChannel.getType() == ChannelType.VOICE)
		{
			VoiceChannel vchannel = (VoiceChannel) guildChannel;

			eb.addField(AIO.languageManager.getString("helpBitrate"), vchannel.getBitrate() + "", false);
			eb.addField(AIO.languageManager.getString("helpUserlimit"), vchannel.getUserLimit() + "", false);
		}
		
		member.getUser().openPrivateChannel().complete().sendMessage(eb.build()).queue();
	}
	
	public boolean isTextchannel(long id)
	{
		if (AIO.INSTANCE.jda.getGuildChannelById(id).getType() == ChannelType.TEXT) return true;
		else return false;
	}
	
	public boolean isVoicechannel(long id)
	{
		if (AIO.INSTANCE.jda.getGuildChannelById(id).getType() == ChannelType.VOICE) return true;
		else return false;
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

	public void modifyUserlimit(GuildChannel guildChannel, int userlimit)
	{
		guildChannel.getManager().setUserLimit(userlimit);
	}
}
