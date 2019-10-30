package dev.swt.gui;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;

public class SelectionAdapterColor extends SelectionAdapter {

	private CTabFolder tabFolder;

	public SelectionAdapterColor(CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}

	public void widgetSelected(SelectionEvent e) {
		CTabItem selected = tabFolder.getSelection();
		ColorDialog cd = new ColorDialog(tabFolder.getShell());
		RGB rgb = (RGB) cd.open();

		if (rgb != null) {
			selected.getControl()
					.setForeground(new Color(tabFolder.getDisplay(), rgb.getRed(), rgb.getGreen(), rgb.getBlue()));
		}
	}
}
