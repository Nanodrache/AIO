package de.aio.newsletter;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

import de.aio.AIO;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.sharding.ShardManager;

public class NewsletterFilter
{
	private File dir;
	private Properties options = new Properties();
	private ArrayList<Long> user = new ArrayList<>();
	private EmbedBuilder eb;
	public boolean isCreated = false;
	
	public NewsletterFilter(String name)
	{
		try
		{
			dir = new File("/AIO/Newsletter/" + name);
			dir.mkdirs();
			
			new File(dir, "options.txt").createNewFile();
			
			options.put("Author", AIO.INSTANCE.jda.getSelfUser().getName());
			
			options.store(new BufferedWriter(new FileWriter(new File(dir, "options.txt"))), null);
			
			new File(dir, "user.txt").createNewFile();
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

	public void rename(String newName)
	{
		dir.renameTo(new File("/AIO/Newsletter", newName));
	}
	
	public void addUser(long id)
	{
		user.add(id);
		updateUserFile();
	}
	
	public void removeUser(long id)
	{
		user.remove(id);
		updateUserFile();
	}
	
	private void updateUserFile()
	{
		try
		{
			File fUser = new File(dir, "user.txt");
			
			fUser.delete();
			fUser.createNewFile();
			
			BufferedWriter br = new BufferedWriter(new FileWriter(fUser));
			
			for (Long userId : user)
			{
				br.append(userId + "");
				br.flush();
			}
			
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void createMessage()
	{
		eb = new EmbedBuilder();
		isCreated = true;
	}

	public void setTitle(String title)
	{
		eb.setTitle(title);
	}

	public void setAuthor(String author)
	{
		eb.setAuthor(author);
	}

	public void setColor(String color)
	{
		eb.setColor(Color.getColor(color, Color.ORANGE));
	}

	public void setDescription(String description)
	{
		eb.setDescription(description);
	}

	public void setFooter(String footer)
	{
		eb.setFooter(footer);
	}

	public void setImage(String url)
	{
		eb.setImage(url);
	}

	public void setThumbnail(String url)
	{
		eb.setThumbnail(url);
	}

	public void setTimestamp()
	{
		eb.setTimestamp(LocalDateTime.now());
	}

	public void addBlankField(boolean inline)
	{
		eb.addBlankField(inline);
	}

	public void addField(String name, String value, boolean inline)
	{
		eb.addField(name, value, inline);
	}

	public void send()
	{
		ShardManager sm = AIO.INSTANCE.jda.getShardManager();
		
		for (long id : user)
		{
			PrivateChannel pc = sm.getUserById(id).openPrivateChannel().complete();
			
			pc.sendMessage(eb.build()).queue();
		}
		
		isCreated = false;
	}
}
