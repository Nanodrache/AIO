package de.aio.commands.types;

public enum Commands
{
	SAY("say", "<Message>", "The bot send the <Message> in the textchannel."),
	CHANNEL("channel", "<create|delete|modify|party> <id>", "Manage channel"),
	LANGUAGE("language", "<language>", "Set the language of the bot messages to the language file <language>.lang"),
	KICK("kick", "<id>", "Kick a user from the server.");
	
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
