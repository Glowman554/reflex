package net.shadew.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.function.BiConsumer;

final class NumberNode extends AbstractPrimitiveNode {
	private final Number number;
	private BigInteger bigInteger;
	private BigDecimal bigDecimal;
	private String string;

	NumberNode(Number number) {
		super(JsonType.NUMBER);
		this.number = number;
	}

	@Override
	public JsonNode ifNumber(BiConsumer<JsonNode, Number> action) {
		action.accept(this, number);
		return this;
	}

	@Override
	public JsonNode ifByte(BiConsumer<JsonNode, Byte> action) {
		action.accept(this, asByte());
		return this;
	}

	@Override
	public JsonNode ifShort(BiConsumer<JsonNode, Short> action) {
		action.accept(this, asShort());
		return this;
	}

	@Override
	public JsonNode ifInt(BiConsumer<JsonNode, Integer> action) {
		action.accept(this, asInt());
		return this;
	}

	@Override
	public JsonNode ifLong(BiConsumer<JsonNode, Long> action) {
		action.accept(this, asLong());
		return this;
	}

	@Override
	public JsonNode ifFloat(BiConsumer<JsonNode, Float> action) {
		action.accept(this, asFloat());
		return this;
	}

	@Override
	public JsonNode ifDouble(BiConsumer<JsonNode, Double> action) {
		action.accept(this, asDouble());
		return this;
	}

	@Override
	public JsonNode ifBigInteger(BiConsumer<JsonNode, BigInteger> action) {
		action.accept(this, asBigInteger());
		return this;
	}

	@Override
	public JsonNode ifBigDecimal(BiConsumer<JsonNode, BigDecimal> action) {
		action.accept(this, asBigDecimal());
		return this;
	}

	@Override
	public String asExactString() {
		throw new IncorrectTypeException(JsonType.NUMBER, JsonType.STRING);
	}

	@Override
	public String asString() {
		return number.toString();
	}

	@Override
	public byte asByte() {
		return number.byteValue();
	}

	@Override
	public short asShort() {
		return number.shortValue();
	}

	@Override
	public int asInt() {
		return number.intValue();
	}

	@Override
	public long asLong() {
		return number.longValue();
	}

	@Override
	public float asFloat() {
		return number.floatValue();
	}

	@Override
	public double asDouble() {
		return number.doubleValue();
	}

	@Override
	public BigInteger asBigInteger() {
		if (bigInteger != null)
			return bigInteger;

		if (number instanceof BigInteger)
			return bigInteger = (BigInteger) number;

		if (number instanceof BigDecimal)
			return bigInteger = ((BigDecimal) number).toBigInteger();

		if (number instanceof UnparsedNumber)
			return bigInteger = ((UnparsedNumber) number).bigIntegerValue();

		if (number instanceof UnparsedHexNumber)
			return bigInteger = ((UnparsedHexNumber) number).bigIntegerValue();

		return bigInteger = asBigDecimal().toBigInteger();
	}

	@Override
	public BigDecimal asBigDecimal() {
		if (bigDecimal != null)
			return bigDecimal;

		if (number instanceof BigInteger)
			return bigDecimal = new BigDecimal((BigInteger) number);

		if (number instanceof BigDecimal)
			return bigDecimal = (BigDecimal) number;

		if (number instanceof UnparsedNumber)
			return bigDecimal = ((UnparsedNumber) number).bigDecimalValue();

		if (number instanceof UnparsedHexNumber)
			return bigDecimal = ((UnparsedHexNumber) number).bigDecimalValue();

		return bigDecimal = BigDecimal.valueOf(asDouble());
	}

	@Override
	public Number asNumber() {
		return number;
	}

	@Override
	public boolean asBoolean() {
		throw new IncorrectTypeException(JsonType.NUMBER, JsonType.BOOLEAN);
	}

	// We use BigDecimal to resemble this number so numbers of different types
	// are equal
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		NumberNode other = (NumberNode) o;
		return asBigDecimal().equals(other.asBigDecimal());
	}

	@Override
	public int hashCode() {
		return Objects.hash(asBigDecimal());
	}

	@Override
	public String toString() {
		if (string != null)
			return string;

		BigDecimal decimal = asBigDecimal();
		try {
			BigInteger integer = decimal.toBigIntegerExact();
			return string = integer.toString();
		} catch (ArithmeticException exc) {
			return string = decimal.toString();
		}
	}
}
