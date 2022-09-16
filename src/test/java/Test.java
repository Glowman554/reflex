import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.ReflexField;
import gq.glowman554.reflex.loaders.ReflexJsonLoader;
import gq.glowman554.reflex.types.ReflexBooleanArray;
import gq.glowman554.reflex.types.ReflexDoubleArray;
import gq.glowman554.reflex.types.ReflexIntArray;
import gq.glowman554.reflex.types.ReflexStringArray;
import net.shadew.json.JsonSyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {
	public static final String test_json = "{\"test_string\":\"hello world\",\"test_string_array\":[\"hello\",\"world\"],\"test_int\":123,\"test_int_array\":[1,2,3],\"inner\":{\"test_inner\":\"inner\"},\"test_double\":1.2,\"test_double_array\":[1.2,3.4,5.6],\"test_boolean\":true,\"test_boolean_array\":[true,false,true]}";
	@ReflexField
	public String test_string;
	@ReflexField
	public ReflexStringArray test_string_array;

	@ReflexField
	public int test_int;
	@ReflexField
	public ReflexIntArray test_int_array;

	@ReflexField
	public double test_double;
	@ReflexField
	public ReflexDoubleArray test_double_array;

	@ReflexField
	public boolean test_boolean;
	@ReflexField
	public ReflexBooleanArray test_boolean_array;

	@ReflexField
	public TestInner inner = new TestInner(); // Subclasses need to be
	// instantiated and not null
	@ReflexField(optional = true)
	public String not_in_json;

	@org.junit.jupiter.api.Test
	public void test1() throws JsonSyntaxException, IllegalAccessException {
		Test t = (Test) new Reflex(new ReflexJsonLoader(test_json)).load(new Test());

		assertEquals(null, t.not_in_json);
		assertEquals("hello world", t.test_string);
		assertEquals(123, t.test_int);
		assertEquals(1.2, t.test_double);
		assertEquals(true, t.test_boolean);
		assertEquals("inner", t.inner.test_inner);
	}

	public class TestInner {
		@ReflexField
		public String test_inner;
	}
}
