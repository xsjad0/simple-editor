package dev.swt.gui;

import java.io.File;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class SelectionAdapterOpen extends SelectionAdapter {

	private CTabFolder tabFolder;

	public SelectionAdapterOpen(CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}

	public void widgetSelected(SelectionEvent e) {
		CTabItem newTab = new CTabItem(tabFolder, SWT.CLOSE);
		MyText text = new MyText(tabFolder, SWT.MULTI | SWT.V_SCROLL);
		Shell shell = (Shell) tabFolder.getShell();
		FileDialog ask = new FileDialog(shell, SWT.OPEN);
		String path = ask.open();
		String[] pathArray = path.split(Pattern.quote(File.separator));
		String filename = pathArray[pathArray.length - 1];
		String content = FileIO.read(path);

		newTab.setControl(text);
		newTab.setText(filename);
		text.setText(content);
		text.addModifyListener(new ModifyListenerText());
		tabFolder.setSelection(newTab);
	}
}
