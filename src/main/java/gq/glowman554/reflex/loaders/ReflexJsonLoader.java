package gq.glowman554.reflex.loaders;

import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.ReflexDataLoader;
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
	public boolean load_boolean(String field_name) {
		return this.resolve(field_name).asBoolean();
	}

	@Override
	public boolean[] load_boolean_array(String field_name) {
		return this.resolve(field_name).asBooleanArray();
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
