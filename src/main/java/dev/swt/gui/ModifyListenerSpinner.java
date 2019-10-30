package dev.swt.gui;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class ModifyListenerSpinner implements ModifyListener {

	private Label label;
	private Spinner[] rgbs;

	public ModifyListenerSpinner(Label label, Spinner[] rgbs) {
		this.label = label;
		this.rgbs = rgbs;

		updateColor();
	}

	@Override
	public void modifyText(ModifyEvent e) {
		updateColor();
	}

	private void updateColor() {
		Color color;
		RGB rgb;

		rgb = new RGB();
		rgb.setRed(rgbs[0].getSelection());
		rgb.setGreen(rgbs[1].getSelection());
		rgb.setBlue(rgbs[2].getSelection());

		color = new Color(label.getDisplay(), rgb.getRed(), rgb.getGreen(), rgb.getBlue());

		label.setBackground(color);
	}
}
