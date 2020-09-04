package de.aio.manager;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;

import de.aio.newsletter.NewsletterFilter;

public class NewsletterManager
{
	private HashMap<String, NewsletterFilter> newsletter = new HashMap<>();
	
	public NewsletterManager()
	{
		for(File dirNewsletter : new File("/AIO/Newsletter/").listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				if (pathname.isDirectory()) return true;
				return false;
			}
		}))
		{
			newsletter.put(dirNewsletter.getName(), new NewsletterFilter(dirNewsletter.getName()));
		}
	}
	
	public void createNewsletter(String name)
	{
		newsletter.put(name, new NewsletterFilter(name));
	}

	public void deleteNewsletter(String name)
	{
		newsletter.remove(name);
	}

	public void rename(String oldName, String newName)
	{
		NewsletterFilter nf = newsletter.get(oldName);
		nf.rename(newName);
		newsletter.remove(oldName);
		newsletter.put(newName, nf);
	}

	public void joinNewsletter(String name, long id)
	{
		newsletter.get(name).addUser(id);
	}

	public void leaveNewsletter(String name, long id)
	{
		newsletter.get(name).removeUser(id);
	}

	public NewsletterFilter get(String name)
	{
		return newsletter.get(name);
	}
}
