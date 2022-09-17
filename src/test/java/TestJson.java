import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.ReflexField;
import gq.glowman554.reflex.loaders.ReflexJsonLoader;
import gq.glowman554.reflex.types.*;
import net.shadew.json.JsonSyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJson {
	public static final String test_json = "{\"test_string\":\"hello world\",\"test_string_array\":[\"hello\",\"world\"],\"test_int\":123,\"test_int_array\":[1,2,3],\"test_long\":123,\"test_long_array\":[1,2,3],\"inner\":{\"test_inner\":\"inner\"},\"test_double\":1.2,\"test_double_array\":[1.2,3.4,5.6],\"test_boolean\":true,\"test_boolean_array\":[true,false,true],\"inner_array\":[{\"test_inner\":\"test1\"},{\"test_inner\":\"test2\"},{\"test_inner\":\"test3\"}]}";
	@ReflexField
	public String test_string;
	@ReflexField
	public ReflexStringArray test_string_array;

	@ReflexField
	public int test_int;
	@ReflexField
	public ReflexIntArray test_int_array;
	
	@ReflexField
	public long test_long;
	@ReflexField
	public ReflexLongArray test_long_array;

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

	@SuppressWarnings("unchecked")
	@ReflexField
	public ReflexCustomArray<TestInner> inner_array = ReflexCustomArray.from(() -> new TestInner());
	
	@ReflexField(optional = true)
	public String not_in_json;

	@org.junit.jupiter.api.Test
	public void test1() throws JsonSyntaxException, IllegalAccessException {
		Reflex.setDebug(true);
		TestJson t = (TestJson) new Reflex(new ReflexJsonLoader(test_json)).load(new TestJson());

		assertEquals(null, t.not_in_json);
		assertEquals("hello world", t.test_string);
		assertEquals(123, t.test_int);
		assertEquals(123, t.test_long);
		assertEquals(1.2, t.test_double);
		assertEquals(true, t.test_boolean);
		assertEquals("inner", t.inner.test_inner);

		System.out.println(t);
	}

	@Override
	public String toString() {
		return "Test{" +
				"test_string='" + test_string + '\'' +
				", test_string_array=" + test_string_array +
				", test_int=" + test_int +
				", test_int_array=" + test_int_array +
				", test_double=" + test_double +
				", test_double_array=" + test_double_array +
				", test_boolean=" + test_boolean +
				", test_boolean_array=" + test_boolean_array +
				", inner=" + inner +
				", inner_array=" + inner_array +
				", not_in_json='" + not_in_json + '\'' +
				'}';
	}

	public class TestInner {
		@ReflexField
		public String test_inner;
	}
}
