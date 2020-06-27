package de.aio.manager;

import java.awt.Color;
import java.util.HashMap;

import de.aio.AIO;
import de.aio.PermissionList;
import net.dv8tion.jda.api.Permission;

public class PermissionManager
{
	private HashMap<String, Boolean> permissionListRole = new HashMap<>();
	private HashMap<String, Boolean> permissionListUser = new HashMap<>();
	
	public PermissionManager()
	{
		
	}
	
	public boolean giveRolePermission(long roleId, String permission)
	{
		if (getPermissionFromString(permission) != Permission.UNKNOWN)
		{
			AIO.INSTANCE.jda.getRoleById(roleId).getManager().givePermissions(getPermissionFromString(permission));
			return true;
		}
		else return false;
	}
	
	public boolean removeRolePermission(long roleId, String permission)
	{
		if (getPermissionFromString(permission) != Permission.UNKNOWN)
		{
			AIO.INSTANCE.jda.getRoleById(roleId).getManager().revokePermissions(getPermissionFromString(permission));
			return true;
		}
		else return false;
	}
	
	public boolean giveUserPermission(long guildId, long userId, String permission)
	{
		if (isAioPermission(permission))
		{
			permissionListUser.put(guildId + "_" + userId + "_" + permission, true);
			return true;
		}
		else return false;
	}

	public boolean removeUserPermission(long guildId, long userId, String permission)
	{
		if (isAioPermission(permission))
		{
			permissionListUser.remove(guildId + "_" + userId + "_" + permission);
			return true;
		}
		else return false;
	}
	
	public boolean isAioPermission(String permission)
	{
		return (PermissionList.valueOf(permission) != null);
	}
	
	public boolean hasRolePermission(long roleId, String permission)
	{
		if (getPermissionFromString(permission) != Permission.UNKNOWN)
		{
			return AIO.INSTANCE.jda.getRoleById(roleId).hasPermission(getPermissionFromString(permission));
		}
		else
		{
			if (permission.equalsIgnoreCase(PermissionList.AIO_BAN.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_CREATE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_CREATE_TEXT.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_CREATE_VOICE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_DELETE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_INFO.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_INVITE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_INVITE_TEMP.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_BITRATE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_NAME.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_NSFW.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_PARENT.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_POSITION.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_SLOWMODE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_TOPIC.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_USERLIMIT.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_PARTY.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_PARTY_CREATE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_PARTY_DELETE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_KICK.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_LANGUAGE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_COLOR.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_GET.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_GIVE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_NAME.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_REMOVE.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_SAY.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_WELCOME.name())) return hasRoleAioPermission(roleId, PermissionList.AIO_BAN.name());
		}
		
		return false;
	}
	
	public boolean hasUserPermission(long guildId, long userId, String permission)
	{
		if (getPermissionFromString(permission) != Permission.UNKNOWN)
		{
			return AIO.INSTANCE.jda.getGuildById(guildId).getMemberById(userId).hasPermission(getPermissionFromString(permission));
		}
		else
		{
			if (permission.equalsIgnoreCase(PermissionList.AIO_BAN.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_CREATE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_CREATE_TEXT.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_CREATE_VOICE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_DELETE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_INFO.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_INVITE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_INVITE_TEMP.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_BITRATE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_NAME.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_NSFW.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_PARENT.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_POSITION.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_SLOWMODE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_TOPIC.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_MODIFY_USERLIMIT.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_PARTY.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_PARTY_CREATE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_CHANNEL_PARTY_DELETE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_KICK.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_LANGUAGE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_COLOR.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_GET.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_GIVE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_NAME.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_PERMISSION_REMOVE.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_SAY.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
			else if (permission.equalsIgnoreCase(PermissionList.AIO_WELCOME.name())) return hasUserAioPermission(guildId, userId, PermissionList.AIO_BAN.name());
		}
		
		return false;
	}
	
	private boolean hasRoleAioPermission(long roleId, String permission)
	{
		if (permissionListRole.containsKey(roleId + "_" + permission))
		{
			return permissionListRole.get(roleId + "_" + permission);
		}
		else return false;
	}
	
	private boolean hasUserAioPermission(long guildId, long userId, String permission)
	{
		if (permissionListUser.containsKey(guildId + "_" + userId + "_" + permission))
		{
			return permissionListUser.get(guildId + "_" + userId + "_" + permission);
		}
		else return false;
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
