import static org.junit.jupiter.api.Assertions.assertEquals;

import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.ReflexField;
import gq.glowman554.reflex.loaders.ReflexCfgLoader;
import gq.glowman554.reflex.types.ReflexBooleanArray;
import gq.glowman554.reflex.types.ReflexDoubleArray;
import gq.glowman554.reflex.types.ReflexIntArray;
import gq.glowman554.reflex.types.ReflexLongArray;
import gq.glowman554.reflex.types.ReflexStringArray;
import net.shadew.json.JsonSyntaxException;

public class TestCfg
{
	public static String test_cfg = "\ntest_string=hello world\ntest_string_array=[\"hello\", \"world\"]\n\ntest_int=123\ntest_int_array=[1,2,3]\n\ntest_long=123\ntest_long_array=[1,2,3]\n\ntest_double=1.2\ntest_double_array=[1.2,3.4,5.6]\n\ntest_boolean=true\ntest_boolean_array=[true, false, true]\n\n:other_section\nin_section=hewoo";

	@ReflexField
	public RootSection root = new RootSection();
	
	@ReflexField
	public OtherSection other_section = new OtherSection();

	@org.junit.jupiter.api.Test
	public void test1() throws JsonSyntaxException, IllegalAccessException
	{
		Reflex.setDebug(true);
		TestCfg t = (TestCfg) new Reflex(new ReflexCfgLoader(test_cfg)).load(new TestCfg());

		
		assertEquals(null, t.root.loool);
		assertEquals("hello world", t.root.test_string);
		assertEquals(123, t.root.test_int);
		assertEquals(123, t.root.test_long);
		assertEquals(1.2, t.root.test_double);
		assertEquals(true, t.root.test_boolean);
		assertEquals("hewoo", t.other_section.in_section);

		
		System.out.println(t);
	}

	public static class RootSection
	{
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
		
		@ReflexField(optional = true)
		public String loool;
	}

	public static class OtherSection
	{
		@ReflexField
		public String in_section;
	}

}
