package gq.glowman554.cfg;

import java.util.HashMap;

public class CfgParser
{
	private HashMap<String, CfgSection> sections = new HashMap<>();

	public void parse(String s)
	{
		String[] lines = s.split("\\n");
		String current_section = "root";

		for (String line : lines)
		{
			if (line.startsWith(":"))
			{
				current_section = line.substring(1).trim();
			}
			else
				if (line.startsWith(";") || line.trim().length() == 0)
				{
				}
				else
				{
					if (!sections.containsKey(current_section))
					{
						sections.put(current_section, new CfgSection());
					}

					sections.get(current_section).addLine(line);
				}
		}
	}
	
	public CfgSection get(String section)
	{
		return sections.get(section);
	}
}
