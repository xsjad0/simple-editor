package dev.swt.gui;

import java.util.ResourceBundle;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;

public class SelectionAdapterColor extends SelectionAdapter {

	private ResourceBundle msg;

	private CTabFolder tabFolder;

	public SelectionAdapterColor(ResourceBundle msg, CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
		this.msg = msg;
	}

	public void widgetSelected(SelectionEvent e) {
		CTabItem selected = tabFolder.getSelection();

		if (selected != null) {
			ColorDialog cd = new ColorDialog(msg, tabFolder.getShell());
			RGB rgb = (RGB) cd.open();

			if (rgb != null) {
				selected.getControl().getForeground().dispose();
				selected.getControl()
						.setForeground(new Color(tabFolder.getDisplay(), rgb.getRed(), rgb.getGreen(), rgb.getBlue()));
			}
		}
	}
}
