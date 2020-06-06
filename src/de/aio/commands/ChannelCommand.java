package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ChannelCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		String[] args = message.getContentDisplay().split(" ");
		
		if (args.length == 1)
		{
			//Help for command
		}
		else if (args.length > 2)
		{
			//Channel create
			if (args[1].equalsIgnoreCase("create"))
			{
				//Check permissions
				if (member.hasPermission(Permission.ADMINISTRATOR))
				{
					String channelCreateName = "";
					
					for (int i = 2; i < args.length; i++)
					{
						channelCreateName += args[i];
					}
					
					//Check channel-type
					if (args[2].equalsIgnoreCase("text"))
					{
						AIO.channelManager.createTextChannel(channelCreateName, channel.getParent());
					}
					else if (args[2].equalsIgnoreCase("voice"))
					{
						AIO.channelManager.createVoiceChannel(channelCreateName, channel.getParent());
					}
					else
					{
						message.delete().complete();
						channel.sendMessage("Unknown channel type").complete().delete().delay(5, TimeUnit.SECONDS);
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage("You don't have the permissions to perform this command.").complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("modify"))
			{
				if (member.hasPermission(Permission.ADMINISTRATOR))
				{
					long id = Long.parseLong(args[3]);
					
					if (args[2].equalsIgnoreCase("bitrate"))
					{
						if (AIO.channelManager.isVoicechannel(id))
						{
							int bitrate = Integer.parseInt(args[4]);
							
							AIO.channelManager.modifyBitrate(channel.getGuild().getGuildChannelById(id), bitrate);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage("This command is only for voicechannel").complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[2].equalsIgnoreCase("name"))
					{
						String channelModifyName = "";
						
						for (int i = 4; i < args.length; i++)
						{
							channelModifyName += args[i];
						}
						
						AIO.channelManager.modifyName(channel.getGuild().getGuildChannelById(id), channelModifyName);
					}
					else if (args[2].equalsIgnoreCase("nsfw"))
					{
						if (AIO.channelManager.isTextchannel(id))
						{
							boolean isNSFW = Boolean.parseBoolean(args[4]);
							
							AIO.channelManager.modifyNSFW(channel.getGuild().getGuildChannelById(id), isNSFW);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage("This command is only for textchannel").complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[2].equalsIgnoreCase("parent"))
					{
						long idParent = Long.parseLong(args[4]);
						
						AIO.channelManager.modifyParent(channel.getGuild().getGuildChannelById(id), channel.getGuild().getCategoryById(idParent));
					}
					else if (args[2].equalsIgnoreCase("position"))
					{
						int pos = Integer.parseInt(args[4]);
						
						AIO.channelManager.modifyPosition(channel.getGuild().getGuildChannelById(id), pos);
					}
					else if (args[2].equalsIgnoreCase("slowmode"))
					{
						if (AIO.channelManager.isTextchannel(id))
						{
							int slow = Integer.parseInt(args[4]);
							
							AIO.channelManager.modifySlowmode(channel.getGuild().getGuildChannelById(id), slow);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage("This command is only for textchannel").complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[2].equalsIgnoreCase("topic"))
					{
						if (AIO.channelManager.isTextchannel(id))
						{
							String channelModifyTopic = "";
							
							for (int i = 4; i < args.length; i++)
							{
								channelModifyTopic += args[i];
							}
							
							AIO.channelManager.modifyTopic(channel.getGuild().getGuildChannelById(id), channelModifyTopic);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage("This command is only for textchannel").complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[2].equalsIgnoreCase("userlimit"))
					{
						if (AIO.channelManager.isVoicechannel(id))
						{
							int userlimit = Integer.parseInt(args[4]);
							
							AIO.channelManager.modifyUserlimit(channel.getGuild().getGuildChannelById(id), userlimit);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage("This command is only for voicechannel").complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage("You don't have the permissions to perform this command.").complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("info"))
			{
				if (member.hasPermission(Permission.ADMINISTRATOR))
				{
					long id = 0l;
					
					if (args.length == 2) id = channel.getIdLong();
					else if (args.length == 3) id = Long.parseLong(args[2]);
					
					AIO.channelManager.getChannelInfo(channel.getGuild().getGuildChannelById(id), member);
				}
				else
				{
					message.delete().complete();
					channel.sendMessage("You don't have the permissions to perform this command.").complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
		}
	}
}
































