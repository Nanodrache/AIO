package de.aio.commands.types;

public enum Commands
{
	SAY("say", "<Message>", "The bot send the <Message> in the textchannel.");
	
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
