package de.aio.listeners;

import de.aio.AIO;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter
{
	private long messageIdVerify = 0;
	private long roleId = 0;
	private String reaction = "";
	
	public ReactionListener()
	{
		setRole(Long.parseLong(AIO.INSTANCE.options.getProperty("VerificationRole")));
		setMessage(Long.parseLong(AIO.INSTANCE.options.getProperty("VerificationMessage")));
		setReaction(AIO.INSTANCE.options.getProperty("VerificationReaction"));
	}
	
	public void setRole(long roleId)
	{
		this.roleId = roleId;
	}
	
	public void setMessage(long messageId)
	{
		this.messageIdVerify = messageId;
	}
	
	public void setReaction(String reaction)
	{
		this.reaction = reaction;
	}
	
	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e)
	{
		if (!e.getMember().getUser().isBot())
		{
			if (e.getMessageIdLong() == messageIdVerify)
			{
				if (e.getReactionEmote().getName() == reaction)
				{
					e.getMember().getRoles().add(e.getGuild().getRoleById(roleId));
				}
				else
				{
					e.getReaction().removeReaction().complete();
				}
			}
		}
	}
}
