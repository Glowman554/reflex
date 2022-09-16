package gq.glowman554.reflex.types;

public class ReflexDoubleArray implements ReflexArray<Double> {
	private final double[] arr;

	public ReflexDoubleArray(double[] arr) {
		this.arr = arr;
	}

	@Override
	public Double get(int idx) {
		return this.arr[idx];
	}

}
