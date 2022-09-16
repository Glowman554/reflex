package gq.glowman554.reflex.types;

public class ReflexBooleanArray implements ReflexArray<Boolean> {
	private final boolean[] arr;

	public ReflexBooleanArray(boolean[] arr) {
		this.arr = arr;
	}

	@Override
	public Boolean get(int idx) {
		return this.arr[idx];
	}
}
