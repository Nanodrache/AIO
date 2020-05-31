package de.aio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AIO
{
	private Properties options;
	
	public static void main(String[] args)
	{
		new AIO();
	}
	
	public AIO()
	{
		options = new Properties();
		
		try
		{
			File optionsFile = new File("options.cfg");
			
			if (optionsFile.exists())
			{
				options.load(new FileReader(optionsFile));
			}
			else
			{
				optionsFile.createNewFile();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
