package dev.swt.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.*;

public class SelectionAdapterNew extends SelectionAdapter {

	private static int tabCount = 1;

	private CTabFolder tabFolder;

	public SelectionAdapterNew(CTabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}

	public void widgetSelected(SelectionEvent e) {
		CTabItem item = new CTabItem(tabFolder, SWT.CLOSE);
		MyText text = new MyText(tabFolder, SWT.MULTI | SWT.V_SCROLL);

		SelectionAdapterNew.tabCount++;

		item.setText("untitled-" + SelectionAdapterNew.tabCount);
		item.setControl(text);
		// item.setShowClose(false);

		text.addModifyListener(new ModifyListenerText(tabFolder));
		tabFolder.setSelection(tabFolder.getItemCount() - 1);
	}
}
