package gq.glowman554.reflex;

import gq.glowman554.reflex.types.*;

import java.util.HashMap;

public class Reflex {
	private static boolean debug = false;
	private final ReflexDataLoader loader;
	private final HashMap<Class<?>, ReflexTypeHandler> handlers = new HashMap<>();

	public Reflex(ReflexDataLoader loader) {
		this.loader = loader;

		this.handlers.put(String.class, (field, loaders, __) -> loaders.load_string(field));
		this.handlers.put(ReflexStringArray.class, (field, loaders, __) -> new ReflexStringArray(loaders.load_string_array(field)));

		this.handlers.put(int.class, (field, loaders, __) -> loaders.load_int(field));
		this.handlers.put(ReflexIntArray.class, (field, loaders, __) -> new ReflexIntArray(loaders.load_int_array(field)));

		this.handlers.put(double.class, (field, loaders, __) -> loaders.load_double(field));
		this.handlers.put(ReflexDoubleArray.class, (field, loaders, __) -> new ReflexDoubleArray(loaders.loas_double_array(field)));

		this.handlers.put(boolean.class, (field, loaders, __) -> loaders.load_boolean(field));
		this.handlers.put(ReflexBooleanArray.class, (field, loaders, __) -> new ReflexBooleanArray(loaders.load_boolean_array(field)));

		this.handlers.put(ReflexCustomArray.class, (field, loaders, o) -> {
			@SuppressWarnings("unchecked")
			ReflexCustomArray<Object> rca = (ReflexCustomArray<Object>) o;

			rca.array(loaders.load_custom_array(field, rca.getInstantiate()));

			return rca;
		});
	}

	public static boolean getDebug() {
		return Reflex.debug;
	}

	public static void setDebug(boolean debug) {
		Reflex.debug = debug;
	}

	public Object load(Object o, String load_prefix) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = o.getClass();

		for (var f : clazz.getDeclaredFields()) {
			if (f.isAnnotationPresent(ReflexField.class)) {
				var h = this.handlers.get(f.getType());
				String load_name = load_prefix + "." + f.getName();

				if (Reflex.debug)
					System.out.printf("[REFLEX] loading %s@%s\n", load_name, f.getType().getName());

				try {
					if (h != null) {
						f.set(o, h.load(load_name, this.loader, f.get(o)));
					} else {
						f.set(o, this.load(f.get(o), load_name));
					}
				} catch (Exception e) {
					if (!f.getAnnotation(ReflexField.class).optional()) {
						throw e;
					} else {
						f.set(o, null);
						if (Reflex.debug)
							System.out.println("[REFLEX] Ignoring load fail for " + load_name);
					}
				}
			}
		}
		return o;
	}

	public Object load(Object o) throws IllegalArgumentException, IllegalAccessException {
		return this.load(o, "root");
	}
}
