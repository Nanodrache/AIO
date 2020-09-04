package de.aio.commands.types;

public enum Commands
{
	SAY("say", "<Message>", "The bot send the <Message> in the textchannel."),
	CHANNEL("channel", "<create|delete|modify|party> <id>", "Manage channel"),
	LANGUAGE("language", "<language>", "Set the language of the bot messages to the language file <language>.lang"),
	KICK("kick", "<id>", "Kick a user from the server."),
	BAN("ban", "<id> <days>", "Ban a user from the server. Optional <days>"),
	PERMISSION("permission", "<get|give|remove|name|color> <id> <name|permission|r> <g> <b>", "Manage the permisssion system"),
	WELCOME("welcome", "<id|false>", "Edit welcome channel or turn welcome message off."),
	VERIFY("verify", "<setRole|setMsg|setReaction> <id|Reaction>", "Manage the user verification"),
	REACTION("reaction", "<enable|disable|disabledList> <id>", "Manage reactions"),
	MULTITWITCH("multitwitch", "<twitchname>...", "Generate multitwitch-link"),
	NEWSLETTER("newsletter", "<name> <create|set|join|leave> <name> <value>", "Manage newsletter");
	
	private String cmd;
	private String args;
	private String description;
	
	Commands(String cmd, String args, String description)
	{
		this.cmd = cmd;
		this.args = args;
		this.description = description;
	}
	
	public String getCmd()
	{
		return cmd;
	}
	
	public String getArgs()
	{
		return args;
	}
	
	public String getDescription()
	{
		return description;
	}
}
