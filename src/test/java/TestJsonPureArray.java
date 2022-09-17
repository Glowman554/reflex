import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.ReflexField;
import gq.glowman554.reflex.loaders.ReflexJsonLoader;
import gq.glowman554.reflex.types.*;
import net.shadew.json.JsonSyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJsonPureArray {
	public static final String test_json = "[1, 2, 3]";

	@ReflexField
	public ReflexIntArray $;
	
	@org.junit.jupiter.api.Test
	public void test1() throws JsonSyntaxException, IllegalAccessException {
		Reflex.setDebug(true);
		TestJsonPureArray t = (TestJsonPureArray) new Reflex(new ReflexJsonLoader(test_json)).load(new TestJsonPureArray());

		assertEquals(1, t.$.get(0));
		assertEquals(2, t.$.get(1));
		assertEquals(3, t.$.get(2));

		System.out.println(t);
	}
}
