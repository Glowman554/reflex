package gq.glowman554.reflex;

public interface ReflexDataLoader {
	String load_string(String field_name);

	String[] load_string_array(String field_name);

	int load_int(String field_name);

	int[] load_int_array(String field_name);

	double load_double(String field_name);

	double[] loas_double_array(String field_name);

	boolean load_boolean(String field_name);

	boolean[] load_boolean_array(String field_name);
}
