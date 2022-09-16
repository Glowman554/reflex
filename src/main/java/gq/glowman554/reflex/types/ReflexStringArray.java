package gq.glowman554.reflex.types;

public class ReflexStringArray implements ReflexArray<String> {
	private final String[] arr;

	public ReflexStringArray(String[] arr) {
		this.arr = arr;
	}

	@Override
	public String get(int idx) {
		return this.arr[idx];
	}
}
