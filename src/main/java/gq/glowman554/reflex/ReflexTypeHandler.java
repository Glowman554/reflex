package gq.glowman554.reflex;

public interface ReflexTypeHandler {
	Object load(String field_name, ReflexDataLoader loader, Object o);
}
