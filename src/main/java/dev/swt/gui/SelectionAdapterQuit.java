package dev.swt.gui;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterQuit extends SelectionAdapter {

	public SelectionAdapterQuit() {

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		e.display.dispose();
	}

}
