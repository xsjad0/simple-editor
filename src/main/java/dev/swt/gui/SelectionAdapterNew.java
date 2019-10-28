package dev.swt.gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterNew extends SelectionAdapter {

	private CTabFolder tabFolder;
	private ArrayList<CTabItem> tabs;
	private ArrayList<Text> tabTextFields;

	public SelectionAdapterNew(CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}

	public void widgetSelected(SelectionEvent e) {
		CTabItem item = new CTabItem(tabFolder, SWT.CLOSE);
		Text text = new Text(tabFolder, SWT.MULTI | SWT.V_SCROLL);

		item.setText("New Tab (" + tabs.size() + ")");
		item.setControl(text);
		// item.setShowClose(false);

		tabFolder.setSelection(tabFolder.getItemCount() - 1);
	}
}
