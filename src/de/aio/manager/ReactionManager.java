package de.aio.manager;

public class ReactionManager
{
	private long verifyRoleId;
	private long verifyMessageId;
	private String verifyReactionName;
	
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
}
