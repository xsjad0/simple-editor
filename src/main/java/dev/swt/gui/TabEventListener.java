package dev.swt.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder2Listener;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Listener;

public class TabEventListener implements CTabFolder2Listener {

    @Override
    public void close(CTabFolderEvent event) {
        /* ModifyListenerText listener = (ModifyListenerText) event.item.getListeners(SWT.Modify)[0];

        if (listener.isModified()) {
            Shell shell = (Shell) 
            FileDialog ask = new FileDialog(shell, SWT.SAVE);
            String path = ask.open();

            if (path != null) {
                String[] pathArray = path.split(Pattern.quote(File.separator));
                String filename = pathArray[pathArray.length - 1];
                selectedTab.setText(filename);
                FileIO.write(path, text.getText());
            }
        } */
        event.item.dispose();
    }

    @Override
    public void minimize(CTabFolderEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void maximize(CTabFolderEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void restore(CTabFolderEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showList(CTabFolderEvent event) {
        // TODO Auto-generated method stub

    }

}