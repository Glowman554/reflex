package gq.glowman554.reflex.types;

public class ReflexIntArray implements ReflexArray<Integer> {
	private final int[] arr;

	public ReflexIntArray(int[] arr) {
		this.arr = arr;
	}

	@Override
	public Integer get(int idx) {
		return this.arr[idx];
	}
}
