package dev.swt.gui;

import java.util.ResourceBundle;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;

public class SelectionAdapterVersion extends SelectionAdapter {

	private ResourceBundle msg;

	private CTabFolder tabFolder;

	public SelectionAdapterVersion(ResourceBundle msg, CTabFolder tabFolder) {
		this.msg = msg;
		this.tabFolder = tabFolder;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		MessageBox messageBox = new MessageBox(tabFolder.getShell(), SWT.APPLICATION_MODAL | SWT.OK);
		messageBox.setText("Information");
		messageBox.setMessage(msg.getString("version") + "\n\u00a9 " + msg.getString("owner"));
		messageBox.open();
	}

}
