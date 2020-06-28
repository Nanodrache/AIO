package de.aio.listeners;

import de.aio.AIO;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter
{
	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e)
	{
		if (!e.getMember().getUser().isBot())
		{
			if (e.getMessageIdLong() == AIO.reactionManager.getVerifyMessageId())
			{
				if (e.getReactionEmote().getName() == AIO.reactionManager.getVerifyReactionName())
				{
					e.getMember().getRoles().add(e.getGuild().getRoleById(AIO.reactionManager.getVerifyRoleId()));
				}
				else
				{
					e.getReaction().removeReaction().complete();
				}
			}
		}
	}
}
