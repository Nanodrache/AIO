package de.aio.manager;

import java.awt.Color;

import de.aio.AIO;
import net.dv8tion.jda.api.Permission;

public class PermissionManager
{
	public PermissionManager()
	{
		
	}
	
	public boolean givePermission(long roleId, String permission)
	{
		if (getPermissionFromString(permission) != Permission.UNKNOWN)
		{
			AIO.INSTANCE.jda.getRoleById(roleId).getManager().givePermissions(getPermissionFromString(permission));
			return true;
		}
		else return false;
	}
	
	public boolean removePermission(long roleId, String permission)
	{
		if (getPermissionFromString(permission) != Permission.UNKNOWN)
		{
			AIO.INSTANCE.jda.getRoleById(roleId).getManager().revokePermissions(getPermissionFromString(permission));
			return true;
		}
		else return false;
	}
	
	public boolean hasPermission(long roleId, String permission)
	{
		return AIO.INSTANCE.jda.getRoleById(roleId).hasPermission(getPermissionFromString(permission));
	}
	
	public void setRoleName(long roleId, String name)
	{
		AIO.INSTANCE.jda.getRoleById(roleId).getManager().setName(name);
	}
	
	public void setRoleColor(long roleId, int r, int g, int b)
	{
		AIO.INSTANCE.jda.getRoleById(roleId).getManager().setColor(new Color(r, g, b));
	}
	
	public Permission getPermissionFromString(String s)
	{
		if (s.equalsIgnoreCase("ADMINISTRATOR")) return Permission.ADMINISTRATOR;
		else if (s.equalsIgnoreCase("BAN_MEMBERS")) return Permission.BAN_MEMBERS;
		else if (s.equalsIgnoreCase("CREATE_INSTANT_INVITE")) return Permission.CREATE_INSTANT_INVITE;
		else if (s.equalsIgnoreCase("KICK_MEMBERS")) return Permission.KICK_MEMBERS;
		else if (s.equalsIgnoreCase("MANAGE_CHANNEL")) return Permission.MANAGE_CHANNEL;
		else if (s.equalsIgnoreCase("MANAGE_EMOTES")) return Permission.MANAGE_EMOTES;
		else if (s.equalsIgnoreCase("MANAGE_PERMISSIONS")) return Permission.MANAGE_PERMISSIONS;
		else if (s.equalsIgnoreCase("MANAGE_ROLES")) return Permission.MANAGE_ROLES;
		else if (s.equalsIgnoreCase("MANAGE_SERVER")) return Permission.MANAGE_SERVER;
		else if (s.equalsIgnoreCase("MANAGE_WEBHOOKS")) return Permission.MANAGE_WEBHOOKS;
		else if (s.equalsIgnoreCase("MESSAGE_ADD_REACTION")) return Permission.MESSAGE_ADD_REACTION;
		else if (s.equalsIgnoreCase("MESSAGE_ATTACH_FILES")) return Permission.MESSAGE_ATTACH_FILES;
		else if (s.equalsIgnoreCase("MESSAGE_EMBED_LINKS")) return Permission.MESSAGE_EMBED_LINKS;
		else if (s.equalsIgnoreCase("MESSAGE_EXT_EMOJI")) return Permission.MESSAGE_EXT_EMOJI;
		else if (s.equalsIgnoreCase("MESSAGE_HISTORY")) return Permission.MESSAGE_HISTORY;
		else if (s.equalsIgnoreCase("MESSAGE_MANAGE")) return Permission.MESSAGE_MANAGE;
		else if (s.equalsIgnoreCase("MESSAGE_MENTION_EVERYONE")) return Permission.MESSAGE_MENTION_EVERYONE;
		else if (s.equalsIgnoreCase("MESSAGE_READ")) return Permission.MESSAGE_READ;
		else if (s.equalsIgnoreCase("MESSAGE_TTS")) return Permission.MESSAGE_TTS;
		else if (s.equalsIgnoreCase("MESSAGE_WRITE")) return Permission.MESSAGE_WRITE;
		else if (s.equalsIgnoreCase("NICKNAME_CHANGE")) return Permission.NICKNAME_CHANGE;
		else if (s.equalsIgnoreCase("NICKNAME_MANAGE")) return Permission.NICKNAME_MANAGE;
		else if (s.equalsIgnoreCase("PRIORITY_SPEAKER")) return Permission.PRIORITY_SPEAKER;
		else if (s.equalsIgnoreCase("VIEW_AUDIT_LOGS")) return Permission.VIEW_AUDIT_LOGS;
		else if (s.equalsIgnoreCase("VIEW_CHANNEL")) return Permission.VIEW_CHANNEL;
		else if (s.equalsIgnoreCase("VIEW_GUILD_INSIGHTS")) return Permission.VIEW_GUILD_INSIGHTS;
		else if (s.equalsIgnoreCase("VOICE_CONNECT")) return Permission.VOICE_CONNECT;
		else if (s.equalsIgnoreCase("VOICE_DEAF_OTHERS")) return Permission.VOICE_DEAF_OTHERS;
		else if (s.equalsIgnoreCase("VOICE_MOVE_OTHERS")) return Permission.VOICE_MOVE_OTHERS;
		else if (s.equalsIgnoreCase("VOICE_MUTE_OTHERS")) return Permission.VOICE_MUTE_OTHERS;
		else if (s.equalsIgnoreCase("VOICE_SPEAK")) return Permission.VOICE_SPEAK;
		else if (s.equalsIgnoreCase("VOICE_STREAM")) return Permission.VOICE_STREAM;
		else if (s.equalsIgnoreCase("VOICE_USE_VAD")) return Permission.VOICE_USE_VAD;
		else return Permission.UNKNOWN;
	}
}
