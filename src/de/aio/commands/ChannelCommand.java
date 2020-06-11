package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.InviteAction;

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
						channel.sendMessage(AIO.languageManager.getString("unknownChannelType")).complete().delete().delay(5, TimeUnit.SECONDS);
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
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
							channel.sendMessage(AIO.languageManager.getString("onlyVoicechannel")).complete().delete().delay(5, TimeUnit.SECONDS);
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
							channel.sendMessage(AIO.languageManager.getString("onlyTextchannel")).complete().delete().delay(5, TimeUnit.SECONDS);
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
							channel.sendMessage(AIO.languageManager.getString("onlyTextchannel")).complete().delete().delay(5, TimeUnit.SECONDS);
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
							channel.sendMessage(AIO.languageManager.getString("onlyTextchannel")).complete().delete().delay(5, TimeUnit.SECONDS);
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
							channel.sendMessage(AIO.languageManager.getString("onlyVoicechannel")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
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
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("invite"))
			{
				if (member.hasPermission(Permission.CREATE_INSTANT_INVITE))
				{
					if (args.length == 2)
					{
						InviteAction ia = channel.createInvite();
						
						ia.setMaxAge(1l, TimeUnit.DAYS);
						ia.setTemporary(true);
						
						channel.sendMessage(ia.complete().getUrl()).complete().delete().delay(1, TimeUnit.DAYS);
					}
					else if (args.length == 3)
					{
						if (args[2].equalsIgnoreCase("unique"))
						{
							channel.sendMessage(channel.getGuild().getVanityUrl()).complete().delete().delay(10, TimeUnit.SECONDS);
						}
					}
					else if (args.length == 5)
					{
						long id = Long.parseLong(args[2]);
						GuildChannel gchannel = AIO.INSTANCE.jda.getGuildChannelById(id);
						InviteAction ia = gchannel.createInvite();
						
						long time = Long.parseLong(args[3]);
						
						if (time > 0)
						{
							ia.setMaxAge(time, TimeUnit.SECONDS);
							ia.setTemporary(true);
						}
						
						int maxUses = Integer.parseInt(args[4]);
						
						if (maxUses > 0) ia.setMaxUses(maxUses);
						
						channel.sendMessage(ia.complete().getUrl()).complete().delete().delay(10, TimeUnit.SECONDS);
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("delete"))
			{
				if (member.hasPermission(Permission.ADMINISTRATOR))
				{
					if (args.length == 2)
					{
						channel.delete().complete();
					}
					else if (args.length == 3)
					{
						long id = Long.parseLong(args[2]);
						
						channel.getGuild().getGuildChannelById(id).delete().complete();
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("party"))
			{
				if (member.hasPermission(Permission.ADMINISTRATOR))
				{
					if (args[2].equalsIgnoreCase("create"))
					{
						long id = Long.parseLong(args[3]);
						VoiceChannel vc = channel.getGuild().getVoiceChannelById(id);
						
						String suffix = "";
						
						for (int i = 4; i < args.length; i++)
						{
							suffix += args[i];
						}
						
						AIO.partyManager.createLobby(vc, suffix);
					}
					else if (args[2].equalsIgnoreCase("delete"))
					{
						long id = Long.parseLong(args[3]);
						
						AIO.partyManager.deleteLobby(id);
					}
				}
			}
		}
	}
}
































