package dev.swt.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterOpen extends SelectionAdapter {

	private Label label;

	public SelectionAdapterOpen() {

	}

	public void widgetSelected(SelectionEvent e) {
		String content;

		// windows file dialog
		Shell shell = (Shell) this.label.getParent();
		FileDialog ask = new FileDialog(shell, SWT.OPEN);
		String filename = ask.open();
		content = FileIO.read(filename);

		// put content in label of new tab
	}
}
