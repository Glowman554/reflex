package gq.glowman554.cfg;

import java.util.HashMap;

public class CfgSection
{
	private HashMap<String, String> cfg_lines = new HashMap<>();
	
	public void addLine(String line)
	{
		String[] line_split = line.split("=");
		
		if (line_split.length != 2)
		{
			throw new IllegalArgumentException("Format error. Needs to be key=value!");
		}
		
		cfg_lines.put(line_split[0], line_split[1]);
	}
	
	public String get(String key)
	{
		return cfg_lines.get(key);
	}
}
