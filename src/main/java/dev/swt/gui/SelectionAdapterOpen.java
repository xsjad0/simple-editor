package dev.swt.gui;

import java.io.File;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterOpen extends SelectionAdapter {

	private CTabFolder tabFolder;

	public SelectionAdapterOpen(CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}

	public void widgetSelected(SelectionEvent e) {
		Shell shell = (Shell) tabFolder.getShell();
		FileDialog ask = new FileDialog(shell, SWT.OPEN);
		String path = ask.open();

		if (path != null) {
			String[] pathArray = path.split(Pattern.quote(File.separator));
			String filename = pathArray[pathArray.length - 1];

			MyFile myFile = MyFileIO.read(path);
			if (myFile != null) {
				CTabItem newTab = new CTabItem(tabFolder, SWT.CLOSE);
				MyText text = new MyText(tabFolder, SWT.MULTI | SWT.V_SCROLL);
				newTab.setControl(text);
				newTab.setText(filename);
				text.setForeground(new Color(tabFolder.getDisplay(), Integer.valueOf(myFile.getMeta("ForegroundRed")),
						Integer.valueOf(myFile.getMeta("ForegroundGreen")),
						Integer.valueOf(myFile.getMeta("ForegroundBlue"))));
				text.setText(myFile.getContent());
				text.addModifyListener(new ModifyListenerText(tabFolder));
				tabFolder.setSelection(newTab);
			}
		}
	}
}
