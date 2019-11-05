package dev.swt.gui;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.MessageBox;

public class SelectionAdapterQuit extends SelectionAdapter {

	private ResourceBundle msg;

	private CTabFolder tabFolder;

	public SelectionAdapterQuit(ResourceBundle msg, CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
		this.msg = msg;
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
			messageBox.setMessage(msg.getString("stopClosing"));
			if (messageBox.open() == SWT.YES) {
				e.display.dispose();
			}
		} else {
			e.display.dispose();
		}
	}

}
