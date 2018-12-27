package code.math;

import code.graphics.RenderBuffer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VectorF {
	private static final int DEFAULT_DIM = 3;
	private int m = DEFAULT_DIM;
	private float[] vector;

	public VectorF(int m) {
		this.m = m;
		vector = new float[m];
	}

	public VectorF(float[] vector) {
		m = vector.length;
		this.vector = vector;
	}

	public VectorF(@JsonProperty("x") float x, @JsonProperty("y") float y) {
		this.vector = new float[] { x, y, 1 };
	}

	public float getValue(int i) {
		return vector[i];
	}

	public void setValue(int i, float value) {
		vector[i] = value;
	}

	public float dotProduct(VectorF vec) {
		float sol = 0;
		for (int i = 0; i < m; i++) {
			sol += (vector[i] * vec.getValue(i));
		}
		return sol;
	}

	/*
	 * only works on 2d vectors
	 */
	public float crossProduct(VectorF vec) {
		return vector[0] * vec.getValue(1) - vector[1] * vec.getValue(0);
	}

	public VectorF multiplicate(float a) {
		VectorF sol = new VectorF(m);
		for (int i = 0; i < m; i++) {
			sol.setValue(i, vector[i] * a);
		}
		return sol;
	}

	public VectorF sum(VectorF vec) {
		VectorF sol = new VectorF(m);
		for (int i = 0; i < m; i++) {
			sol.setValue(i, vector[i] + vec.getValue(i));
		}
		return sol;
	}

	public VectorF diff(VectorF vec) {
		VectorF sol = new VectorF(m);
		for (int i = 0; i < m; i++) {
			sol.setValue(i, vector[i] - vec.getValue(i));
		}
		return sol;
	}

	/*
	 * only works with 2d vectors!!
	 */
	public VectorF normal() {
		VectorF sol = new VectorF(m);
		sol.setValue(0, vector[1]);
		sol.setValue(1, -vector[0]);
		return sol;
	}

	public void render(int xOffset, int yOffset, RenderBuffer render) {
		throw new UnsupportedOperationException();
	}

	public void print() {
		for (int i = 0; i < m; i++) {
			System.out.print(vector[i] + " ");
		}
	}

	// only works with 2d vectors
	public float length() {
		return (float) Math.sqrt((vector[0] * vector[0]) + (vector[1] * vector[1]));
	}

	public VectorF clone() {
		return new VectorF(vector.clone());
	}

}
