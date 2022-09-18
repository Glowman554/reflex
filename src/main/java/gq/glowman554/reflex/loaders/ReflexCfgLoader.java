package gq.glowman554.reflex.loaders;

import gq.glowman554.cfg.CfgParser;
import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.ReflexDataLoader;
import gq.glowman554.reflex.types.ReflexCustomArray.Instantiate;
import net.shadew.json.Json;
import net.shadew.json.JsonNode;

public class ReflexCfgLoader implements ReflexDataLoader
{
	private final CfgParser cfg;

	public ReflexCfgLoader(String cfg)
	{
		this.cfg = new CfgParser();
		this.cfg.parse(cfg);
	}

	public ReflexCfgLoader(CfgParser cfg)
	{
		this.cfg = cfg;
	}

	@Override
	public String load_string(String field_name)
	{
		return this.resolve(field_name).asString();
	}

	@Override
	public String[] load_string_array(String field_name)
	{
		return this.resolve(field_name).asStringArray();
	}

	@Override
	public int load_int(String field_name)
	{
		return this.resolve(field_name).asInt();
	}

	@Override
	public int[] load_int_array(String field_name)
	{
		return this.resolve(field_name).asIntArray();
	}

	@Override
	public long load_long(String field_name)
	{
		return this.resolve(field_name).asLong();
	}

	@Override
	public long[] load_long_array(String field_name)
	{
		return this.resolve(field_name).asLongArray();
	}

	@Override
	public double load_double(String field_name)
	{
		return this.resolve(field_name).asDouble();
	}

	@Override
	public double[] loas_double_array(String field_name)
	{
		return this.resolve(field_name).asDoubleArray();
	}

	@Override
	public boolean load_boolean(String field_name)
	{
		return this.resolve(field_name).asBoolean();
	}

	@Override
	public boolean[] load_boolean_array(String field_name)
	{
		return this.resolve(field_name).asBooleanArray();
	}

	@Override
	public Object[] load_custom_array(String field, Instantiate instantiate)
	{
		throw new IllegalStateException("Not supported!");
	}

	private JsonNode resolve(String field)
	{
		if (Reflex.getDebug())
		{
			System.out.printf("[REFLEX CFG] Resolving %s...\n", field);
		}

		String[] path = field.split("\\.");

		if (path.length != 3)
		{
			throw new IllegalArgumentException("Expected root.<section>.<key>");
		}

		String value = this.cfg.get(path[1]).get(path[2]);
		JsonNode node;

		try
		{
			node = Json.json().parse(value);
		}
		catch (Exception e)
		{
			try
			{
				node = JsonNode.number(Long.parseLong(value));
			}
			catch (Exception e2)
			{
				try
				{
					node = JsonNode.number(Double.parseDouble(value));
				}
				catch (Exception e3)
				{
					if (value.equals("true") || value.equals("false"))
					{
						node = JsonNode.bool(Boolean.parseBoolean(value));
					}
					else
					{
						if (value.charAt(0) == '"')
						{
							value = value.substring(1);

							if (value.charAt(value.length() - 1) == '"')
							{
								value = value.substring(0, value.length() - 1);
							}
							else
							{
								throw new IllegalStateException("String must start and end with \" or just remove the \" altogether!");
							}
						}

						node = JsonNode.string(value);
					}
				}
			}
		}

		return node;
	}

}
