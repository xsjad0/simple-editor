package dev.swt.gui;

public class RGB {

	private int rgb[] = { 0, 0, 0 };

	public RGB() {

	}

	public void setRGB(int[] rgb) {
		this.rgb = rgb;
	}

	public int[] getRGB() {
		return rgb;
	}

	public void setRed(int value) {
		this.rgb[0] = value;
	}

	public void setGreen(int value) {
		this.rgb[1] = value;
	}

	public void setBlue(int value) {
		this.rgb[2] = value;
	}

	public int getRed() {
		return this.rgb[0];
	}

	public int getGreen() {
		return this.rgb[1];
	}

	public int getBlue() {
		return this.rgb[2];
	}

	public String toString() {
		return "(" + Integer.toString(rgb[0]) + "/" + Integer.toString(rgb[1]) + "/" + Integer.toString(rgb[2]) + ")";
	}
}
