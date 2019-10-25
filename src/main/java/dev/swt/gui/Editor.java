package dev.swt.gui;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class Editor {

	private Display display;
	private Shell shell;
	private Menu menuBar;
	private MenuItem[] menuItems;
	private Menu[] menus;
	private MenuItem[][] subMenuItems;
	private Color textColor;

	private String[] menuTitles = { "&File", "&Edit", "&Help" };
	private String[][] subMenuTitles = { { "&New", "&Open...", "&Save...", "&Quit" }, { "Text &Color", "", "", "" },
			{ "&Version", "", "", "" } };
	/* private String[][] subMenuToolTips = {
			{ "Create new file", "Open existing file", "Save text to file", "Exit	program" },
			{ "Select text color", "", "", "" }, { "Show version informations", "", "", "" } }; */

	public Editor() {
		makeDisplay();
		makeShell();
		makeMenu();
		makeCoolBar();
		makeTabs();
		addListeners();
	}

	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void makeDisplay() {
		display = new Display();
	}

	private void makeShell() {
		shell = new Shell();
		GridLayout layout = new GridLayout();

		layout.numColumns = 1;
		layout.makeColumnsEqualWidth = true;
		layout.verticalSpacing = 5;

		shell.setLayout(layout);
	}

	private void makeMenu() {
		menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);

		// make menu items
		menuItems = new MenuItem[menuTitles.length];
		menus = new Menu[menuTitles.length];
		subMenuItems = new MenuItem[subMenuTitles.length][subMenuTitles[1].length];
		for (int i = 0; i < menuItems.length; i++) {
			menuItems[i] = new MenuItem(menuBar, SWT.CASCADE);
			menuItems[i].setText(menuTitles[i]);

			// make drop down menu for each menu item
			menus[i] = new Menu(shell, SWT.DROP_DOWN);
			menuItems[i].setMenu(menus[i]);

			// make submenu items for each menu item
			for (int k = 0; k < subMenuTitles[1].length; k++) {
				if (!subMenuTitles[i][k].equals("")) {
					subMenuItems[i][k] = new MenuItem(menus[i], SWT.PUSH);
					subMenuItems[i][k].setText(subMenuTitles[i][k]);
					// subMenuItems[i][k].setToolTipText(subMenuToolTips[i][k]);
				}
			}
		}
	}

	private void makeCoolBar() {
		// TODO: implement coolbar
	}

	private void makeTabs() {
		// TODO: implement tabbar
	}

	private void addListeners() {
		// File-Menu
		subMenuItems[0][0].addSelectionListener(new SelectionAdapterNew());
		subMenuItems[0][1].addSelectionListener(new SelectionAdapterSave());
		subMenuItems[0][2].addSelectionListener(new SelectionAdapterOpen());
		subMenuItems[0][3].addSelectionListener(new SelectionAdapterQuit());

		// Edit-Menu
		subMenuItems[1][0].addSelectionListener(new SelectionAdapterColor(textColor));

		// Help-Menu
		subMenuItems[1][0].addSelectionListener(new SelectionAdapterColor(textColor));
	}
}
