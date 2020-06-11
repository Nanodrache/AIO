package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class LanguageCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (member.hasPermission(Permission.ADMINISTRATOR))
		{
			AIO.languageManager.setLang(message.getContentDisplay().split(" ")[1]);
			channel.sendMessage(AIO.languageManager.getString("updateLanguage")).complete();
		}
		else
		{
			message.delete().complete();
			channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
		}
	}
}
