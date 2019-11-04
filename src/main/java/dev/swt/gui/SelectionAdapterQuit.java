package dev.swt.gui;

import org.eclipse.swt.events.*;

public class SelectionAdapterQuit extends SelectionAdapter {

	public SelectionAdapterQuit() {
		// TODO: check if any tab is checked modified
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		e.display.dispose();
	}

}
