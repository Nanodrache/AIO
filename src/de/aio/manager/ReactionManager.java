package de.aio.manager;

import java.util.ArrayList;
import java.util.List;

public class ReactionManager
{
	private long verifyRoleId;
	private long verifyMessageId;
	private String verifyReactionName;
	
	private List<Long> disabledReactionOnMessages = new ArrayList<>();
	
	public void setVerifyRole(long roleId)
	{
		verifyRoleId = roleId;
	}
	
	public void setVerifyMessage(long messageId)
	{
		verifyMessageId = messageId;
	}
	
	public void setVerifyReaction(String reactionName)
	{
		verifyReactionName = reactionName;
	}

	public long getVerifyMessageId()
	{
		return verifyMessageId;
	}

	public String getVerifyReactionName()
	{
		return verifyReactionName;
	}

	public long getVerifyRoleId()
	{
		return verifyRoleId;
	}
	
	public void disableReactionOnMessage(long messageId)
	{
		disabledReactionOnMessages.add(messageId);
	}
	
	public void enableReactionOnMessage(long messageId)
	{
		disabledReactionOnMessages.remove(messageId);
	}
	
	public List<Long> getDisabledReactionOnMessages()
	{
		return disabledReactionOnMessages;
	}
}
