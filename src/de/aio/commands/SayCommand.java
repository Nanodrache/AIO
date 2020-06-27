package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.Options;
import de.aio.PermissionList;
import de.aio.commands.types.Commands;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class SayCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (
				member.hasPermission(Permission.ADMINISTRATOR) ||
				AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_SAY.name())
			)
		{
			String outputMessage = message.getContentDisplay().substring((AIO.INSTANCE.options.getProperty(Options.CommandPrefix.name()).length() + Commands.SAY.getCmd().length()));
			
			channel.sendMessage(outputMessage).queue();
		}
		else
		{
			message.delete().complete();
			channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
		}
	}
}
