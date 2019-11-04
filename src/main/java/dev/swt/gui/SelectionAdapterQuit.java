package dev.swt.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.MessageBox;

public class SelectionAdapterQuit extends SelectionAdapter {

	private CTabFolder tabFolder;

	public SelectionAdapterQuit(CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		CTabItem[] tabs = tabFolder.getItems();
		boolean isAnyTabModified = false;

		for (CTabItem tab : tabs) {
			MyText text = (MyText) tab.getControl();
			if (text.isModified()) {
				isAnyTabModified = true;
				break;
			}
		}

		if (isAnyTabModified) {
			MessageBox messageBox = new MessageBox(tabFolder.getShell(), SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
			messageBox.setText("Information");
			messageBox.setMessage("Modified tabs found.\nClose application anyway?");
			if (messageBox.open() == SWT.YES) {
				e.display.dispose();
			}
		} else {
			e.display.dispose();
		}
	}

}
