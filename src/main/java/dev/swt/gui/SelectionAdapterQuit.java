package dev.swt.gui;

import org.eclipse.swt.events.*;

public class SelectionAdapterQuit extends SelectionAdapter {

	public SelectionAdapterQuit() {

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		e.display.dispose();
	}

}
