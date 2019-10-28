package dev.swt.gui;

import java.io.File;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterSave extends SelectionAdapter {

	private CTabFolder tabFolder;

	public SelectionAdapterSave(CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}

	public void widgetSelected(SelectionEvent e) {
		CTabItem selectedTab = tabFolder.getSelection();

		if (selectedTab != null) {
			Text text = (Text) selectedTab.getControl();
			Shell shell = (Shell) tabFolder.getShell();
			FileDialog ask = new FileDialog(shell, SWT.SAVE);
			String path = ask.open();

			if (path != null) {
				String[] pathArray = path.split(Pattern.quote(File.separator));
				String filename = pathArray[pathArray.length - 1];
				selectedTab.setText(filename);
				FileIO.write(path, text.getText());
			}
		}
	}
}
