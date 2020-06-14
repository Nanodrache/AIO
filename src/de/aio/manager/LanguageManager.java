package de.aio.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import de.aio.AIO;
import de.aio.Options;

public class LanguageManager
{
	private String lang = "en";
	private File langFile = new File("/AIO", "en.lang");
	private Properties langList = new Properties();
	
	public LanguageManager()
	{
		lang = AIO.INSTANCE.options.getProperty(Options.Language.name());
		
		if (!langFile.exists()) createDefault();
		
		checkLangFile();
	}

	public void setLang(String language)
	{
		lang = language;
		checkLangFile();
	}

	private void checkLangFile()
	{
		langFile = new File("/AIO", lang + ".lang");
		
		if (langFile.exists())
		{
			loadLang();
		}
		else
		{
			lang = "en";
			langFile = new File("/AIO", lang + ".lang");
		}
	}
	
	private void loadLang()
	{
		try
		{
			langList.load(new BufferedReader(new FileReader(langFile)));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void createDefault()
	{
		File langEn = new File("/AIO", "en.lang");
		File langDe = new File("/AIO", "de.lang");
		
		if (!langEn.exists())
		{
			try
			{
				langEn.mkdirs();
				langEn.createNewFile();

				Properties langEnDefault = new Properties();

				langEnDefault.put("unknownChannelType", "Unknown channel type");
				langEnDefault.put("noPermissionsCommand", "You don't have the permissions to peform this command.");
				langEnDefault.put("onlyVoicechannel", "This command is only for voice channel.");
				langEnDefault.put("onlyTextchannel", "This command is only for text channel.");
				langEnDefault.put("unknownCommand", "Unknown command");
				langEnDefault.put("helpChannelname", "Channel name");
				langEnDefault.put("helpId", "ID");
				langEnDefault.put("helpType", "Type");
				langEnDefault.put("helpCreated", "Created");
				langEnDefault.put("helpParent", "Parent");
				langEnDefault.put("helpPosition", "Position");
				langEnDefault.put("helpGuildName", "Guildname");
				langEnDefault.put("helpGuildId", "Guild ID");
				langEnDefault.put("helpTopic", "Topic");
				langEnDefault.put("helpNSFW", "NSFW");
				langEnDefault.put("helpSlowmode", "Slowmode");
				langEnDefault.put("helpMessageCounter", "Messagecounter");
				langEnDefault.put("helpBitrate", "Bitrate");
				langEnDefault.put("helpUserlimit", "Userlimit");
				langEnDefault.put("updateLanguage", "Update language");
				langEnDefault.put("kickFromServer", "User was kicked from the server.");
				langEnDefault.put("banFromServer", "User was banned from the server.");
				langEnDefault.put("getPermissionInfo", "The role %roleName% has Permission %permmisionName% set to %permissionValue%.");
				langEnDefault.put("updatePermission", "%roleName% : %permmisionName% has updated to %permissionValue%.");
				langEnDefault.put("unknownPermission", "The permission %unknownPermission% is unknown.");
				langEnDefault.put("updateRoleName", "The role %roleNameOld% renamed to %roleNameNew%.");
				langEnDefault.put("updateRoleColor", "The color from role %roleName% has updated.");
				
				langEnDefault.store(new FileWriter(langEn), null);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if (!langDe.exists())
		{
			try
			{
				langDe.mkdirs();
				langDe.createNewFile();

				Properties langDeDefault = new Properties();

				langDeDefault.put("unknownChannelType", "Unbekannter Channeltype");
				langDeDefault.put("noPermissionsCommand", "Du hast nicht die Rechte für diesen Befehl.");
				langDeDefault.put("onlyVoicechannel", "Dieser Befehl ist nur für Sprachchannel.");
				langDeDefault.put("onlyTextchannel", "Dieser Befehl ist nur für Textchannel.");
				langDeDefault.put("unknownCommand", "Unbekannter Befehl");
				langDeDefault.put("helpChannelname", "Channelname");
				langDeDefault.put("helpId", "ID");
				langDeDefault.put("helpType", "Typ");
				langDeDefault.put("helpCreated", "Erstellt am");
				langDeDefault.put("helpParent", "Kategorie");
				langDeDefault.put("helpPosition", "Position");
				langDeDefault.put("helpGuildName", "Servername");
				langDeDefault.put("helpGuildId", "Server ID");
				langDeDefault.put("helpTopic", "Thema");
				langDeDefault.put("helpNSFW", "NSFW");
				langDeDefault.put("helpSlowmode", "Slowmode");
				langDeDefault.put("helpMessageCounter", "Nachrichtenzähler");
				langDeDefault.put("helpBitrate", "Bitrate");
				langDeDefault.put("helpUserlimit", "Benutzerlimit");
				langDeDefault.put("updateLanguage", "Sprache geändert");
				langDeDefault.put("kickFromServer", "User wurde vom Server gekickt.");
				langDeDefault.put("banFromServer", "User wurde vom Server verbannt.");
				langDeDefault.put("getPermissionInfo", "Die Rolle %roleName% hat das Recht %permmisionName% auf %permissionValue% gesetzt.");
				langDeDefault.put("updatePermission", "%roleName% : %permmisionName% wurde auf %permissionValue% gesetzt.");
				langDeDefault.put("unknownPermission", "Das Recht %unknownPermission% ist unbekannt.");
				langDeDefault.put("updateRoleName", "Die Rolle %roleNameOld% wurde zu %roleNameNew% umbennant.");
				
				langDeDefault.store(new FileWriter(langDe), null);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public String getString(String key)
	{
		return langList.getProperty(key);
	}
}
