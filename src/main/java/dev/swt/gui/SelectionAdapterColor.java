package dev.swt.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterColor extends SelectionAdapter {

	private Color textColor;

	public SelectionAdapterColor(Color textColor) {
		this.textColor = textColor;
	}

	public void widgetSelected(SelectionEvent e) {
		ColorDialog cd = new ColorDialog();
		RGB rgb = new RGB();
		cd.open();

	}
}
