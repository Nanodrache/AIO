package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.PermissionList;
import de.aio.commands.types.TextCommand;
import de.aio.manager.NewsletterManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class NewsletterCommand implements TextCommand
{
	NewsletterManager nm = AIO.newsletterManager;
	
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (!member.getUser().isBot())
		{
			String[] args = message.getContentRaw().split(" ");
			String name = args[1];
			
			if (args[2].equalsIgnoreCase("set"))
			{
				if (args[3].equalsIgnoreCase("name"))
				{
					if (AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_NEWSLETTER_SET_NAME.name()))
					{
						nm.rename(name, args[4]);
					}
					else
					{
						message.delete().complete();
						channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
					}
				}
			}
			else if (args[2].equalsIgnoreCase("join"))
			{
				nm.joinNewsletter(name, member.getIdLong());
			}
			else if (args[2].equalsIgnoreCase("leave"))
			{
				nm.leaveNewsletter(name, member.getIdLong());
			}
			else if (args[2].equalsIgnoreCase("create"))
			{
				if (AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_NEWSLETTER_CREATE.name()))
				{
					nm.createNewsletter(name);
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[2].equalsIgnoreCase("delete"))
			{
				if (AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_NEWSLETTER_DELETE.name()))
				{
					nm.deleteNewsletter(name);
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[2].equalsIgnoreCase("send"))
			{
				if (AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_NEWSLETTER_SEND.name()))
				{
					if (args[3].equalsIgnoreCase("create"))
					{
						nm.get(name).createMessage();
					}
					else if (args[3].equalsIgnoreCase("title"))
					{
						if (nm.get(name).isCreated)
						{
							String title = "";
							
							for (int i = 4; i < args.length; i++)
							{
								title += args[i] + " ";
							}
							
							nm.get(name).setTitle(title);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[3].equalsIgnoreCase("author"))
					{
						if (nm.get(name).isCreated)
						{
							nm.get(name).setAuthor(args[4]);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[3].equalsIgnoreCase("color"))
					{
						if (nm.get(name).isCreated)
						{
							nm.get(name).setColor(args[4]);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[3].equalsIgnoreCase("description"))
					{
						if (nm.get(name).isCreated)
						{
							String description = "";
							
							for (int i = 4; i < args.length; i++)
							{
								description += args[i] + " ";
							}
							
							nm.get(name).setDescription(description);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[3].equalsIgnoreCase("footer"))
					{
						if (nm.get(name).isCreated)
						{
							String footer = "";
							
							for (int i = 4; i < args.length; i++)
							{
								footer += args[i] + " ";
							}
							
							nm.get(name).setFooter(footer);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[3].equalsIgnoreCase("image"))
					{
						if (nm.get(name).isCreated)
						{
							nm.get(name).setImage(args[4]);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[3].equalsIgnoreCase("thumbnail"))
					{
						if (nm.get(name).isCreated)
						{
							nm.get(name).setThumbnail(args[4]);
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[3].equalsIgnoreCase("timestamp"))
					{
						if (nm.get(name).isCreated)
						{
							nm.get(name).setTimestamp();
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
					else if (args[3].equalsIgnoreCase("add"))
					{
						if (args[4].equalsIgnoreCase("blank"))
						{
							if (nm.get(name).isCreated)
							{
								nm.get(name).addBlankField(Boolean.getBoolean(args[5]));
							}
							else
							{
								message.delete().complete();
								channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
							}
						}
						else if (args[4].equalsIgnoreCase("field"))
						{
							if (nm.get(name).isCreated)
							{
								String fieldName = "";
								boolean isFieldName = false;
								
								for (int i = 6; i < args.length; i++)
								{
									if (isFieldName) fieldName += args[i] + " ";
									if (args[i].equalsIgnoreCase("--Name")) isFieldName = true;
									else if (args[i].equalsIgnoreCase("--Value")) isFieldName = false;
								}
								
								String fieldValue = "";
								boolean isFieldValue = false;
								
								for (int i = 6; i < args.length; i++)
								{
									if (isFieldValue) fieldValue += args[i] + " ";
									if (args[i].equalsIgnoreCase("--Value")) isFieldValue = true;
								}
								
								nm.get(name).addField(fieldName, fieldValue, Boolean.getBoolean(args[5]));
							}
							else
							{
								message.delete().complete();
								channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
							}
						}
					}
					else if (args[3].equalsIgnoreCase("build"))
					{
						if (nm.get(name).isCreated)
						{
							nm.get(name).send();
						}
						else
						{
							message.delete().complete();
							channel.sendMessage(AIO.languageManager.getString("newsletterFirstCreate")).complete().delete().delay(5, TimeUnit.SECONDS);
						}
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
		}
	}
}
