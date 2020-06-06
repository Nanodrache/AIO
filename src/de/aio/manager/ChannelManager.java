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

		eb.addField("Name", guildChannel.getName(), false);
		eb.addField("ID", guildChannel.getId(), false);
		eb.addField("Type", guildChannel.getType().name(), false);
		eb.addField("Created", guildChannel.getTimeCreated() + "", false);
		eb.addField("Parent", guildChannel.getParent().getName(), false);
		eb.addField("Position", guildChannel.getPosition() + "", false);
		eb.addField("Guildname", guildChannel.getManager().getGuild().getName(), false);
		eb.addField("Guildid", guildChannel.getManager().getGuild().getId(), false);
		
		if (guildChannel.getType() == ChannelType.TEXT)
		{
			TextChannel tchannel = (TextChannel) guildChannel;

			eb.addField("Topic", tchannel.getTopic(), false);
			eb.addField("NSFW", tchannel.isNSFW() + "", false);
			eb.addField("Slowmode", tchannel.getSlowmode() + "", false);
			eb.addField("Messagecounter", tchannel.getHistory().size() + "", false);
		}
		else if (guildChannel.getType() == ChannelType.VOICE)
		{
			VoiceChannel vchannel = (VoiceChannel) guildChannel;

			eb.addField("Bitrate", vchannel.getBitrate() + "", false);
			eb.addField("Userlimit", vchannel.getUserLimit() + "", false);
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
