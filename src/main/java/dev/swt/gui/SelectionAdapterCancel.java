package dev.swt.gui;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterCancel extends SelectionAdapter {

	public SelectionAdapterCancel() {

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		e.display.dispose();
	}

}
