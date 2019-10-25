package dev.swt.gui;

import org.eclipse.swt.events.*;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterOk extends SelectionAdapter {

	private RGB rgb;
	private Spinner[] rgbs;

	public SelectionAdapterOk(Spinner[] rgbs, RGB rgb) {
		this.rgbs = rgbs;
		this.rgb = rgb;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		getColor();
	}

	private void getColor() {
		rgb.setRed(rgbs[0].getSelection());
		rgb.setGreen(rgbs[1].getSelection());
		rgb.setBlue(rgbs[2].getSelection());
	}
}
