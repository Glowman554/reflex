package gq.glowman554.reflex.loaders;

import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.ReflexDataLoader;
import gq.glowman554.reflex.types.ReflexCustomArray;
import net.shadew.json.Json;
import net.shadew.json.JsonNode;
import net.shadew.json.JsonSyntaxException;

public class ReflexJsonLoader implements ReflexDataLoader {
	private final JsonNode json;

	public ReflexJsonLoader(String json) throws JsonSyntaxException {
		this.json = Json.json().parse(json);
	}

	public ReflexJsonLoader(JsonNode json) {
		this.json = json;
	}

	@Override
	public String load_string(String field_name) {
		return this.resolve(field_name).asString();
	}

	@Override
	public String[] load_string_array(String field_name) {
		return this.resolve(field_name).asStringArray();
	}

	@Override
	public int load_int(String field_name) {
		return this.resolve(field_name).asInt();
	}

	@Override
	public int[] load_int_array(String field_name) {
		return this.resolve(field_name).asIntArray();
	}

	@Override
	public double load_double(String field_name) {
		return this.resolve(field_name).asDouble();
	}

	@Override
	public double[] loas_double_array(String field_name) {
		return this.resolve(field_name).asDoubleArray();
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
	public boolean load_boolean(String field_name) {
		return this.resolve(field_name).asBoolean();
	}

	@Override
	public boolean[] load_boolean_array(String field_name) {
		return this.resolve(field_name).asBooleanArray();
	}

	@Override
	public Object[] load_custom_array(String field, ReflexCustomArray.Instantiate instantiate) {
		JsonNode arr = this.resolve(field);
		Object[] o = new Object[arr.length()];

		for (int i = 0; i < o.length; i++) {
			try {
				o[i] = new Reflex(new ReflexJsonLoader(arr.get(i))).load(instantiate.init());
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		return o;
	}

	private JsonNode resolve(String field) {
		if (Reflex.getDebug())
			System.out.printf("[REFLEX JSON] Resolving %s...\n", field);
		String[] path = field.split("\\.");

		JsonNode current = this.json;

		for (int i = 1; i < path.length; i++) {
			if (Reflex.getDebug())
				System.out.printf("[REFLEX JSON] Load step %s\n", path[i]);
			current = current.get(path[i]);
		}

		return current;
	}
}
