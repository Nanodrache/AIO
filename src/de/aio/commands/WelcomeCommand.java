package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.Options;
import de.aio.PermissionList;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class WelcomeCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (
				member.hasPermission(Permission.ADMINISTRATOR) ||
				AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_WELCOME.name())
			)
		{
			String id = message.getContentRaw().split(" ")[1];
			
			AIO.INSTANCE.options.setProperty(Options.WELCOME_MESSAGE.name(), id);
			channel.sendMessage(AIO.languageManager.getString("welcomeSet")).complete().delete().delay(5, TimeUnit.SECONDS);
		}
		else
		{
			message.delete().complete();
			channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
		}
	}
}
