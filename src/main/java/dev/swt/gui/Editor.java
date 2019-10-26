package dev.swt.gui;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

public class Editor {

	private static final String IMAGE_PATH = "images" + File.separator;

	private Display display;
	private Shell shell;

	private Menu menuBar;
	private MenuItem[] menuItems;
	private Menu[] menus;
	private MenuItem[][] subMenuItems;

	private CoolBar coolBar;
	private CoolItem[] coolItems;
	private Button[] coolButtons;
	private Image[] coolButtonImages;
	private String[] pathImages = { "icon_OpenFolder.gif", "home_nav.gif" };

	private CTabFolder tabFolder;
	private ArrayList<CTabItem> tabs;
	private ArrayList<Text> tabsText;
	private String newTabText = "New Tab";

	private Color textColor;

	private String[] menuTitles = { "&File", "&Edit", "&Help" };
	private String[][] subMenuTitles = { { "&New\tCtrl+N", "&Open...\tCtrl+O", "&Save...\tCtrl+S", "&Quit\tCtrl+Q" },
			{ "Text &Color\tCtrl+C", "", "", "" }, { "&Version\tCtrl+V", "", "", "" } };
	private Character[] menuMnemonics = { 'F', 'E', 'H' };
	private Character[][] subMenuMnemonics = { { 'N', 'O', 'S', 'Q' }, { 'C' }, { 'V' } };
	/* private String[][] subMenuToolTips = {
			{ "Create new file", "Open existing file", "Save text to file", "Exit	program" },
			{ "Select text color", "", "", "" }, { "Show version informations", "", "", "" } }; */

	public Editor() {
		makeDisplay();
		makeShell();
		makeMenu();
		makeImages();
		makeCoolBar();
		makeTabContainer();
		addListeners();
	}

	public void run() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		disposeImages();
		display.dispose();
	}

	/**
	* Creates the images	
	*/
	private void makeImages() {
		coolButtonImages = new Image[pathImages.length];
		try {
			for (int i = 0; i < pathImages.length; i++) {
				coolButtonImages[i] = new Image(display, Editor.class.getResourceAsStream(IMAGE_PATH + pathImages[i]));
			}
		} catch (Exception e) {
			// Images not found; handle gracefully
			System.out.println("image not found");
		}
	}

	/**
	* Disposes the images
	*/
	private void disposeImages() {
		if (coolButtonImages[0] != null)
			coolButtonImages[0].dispose();
		if (coolButtonImages[1] != null)
			coolButtonImages[1].dispose();
	}

	/**
	* Create display
	*/
	private void makeDisplay() {
		display = new Display();
	}

	/**
	* Create shell with grid layout
	*/
	private void makeShell() {
		shell = new Shell();
		GridLayout layout = new GridLayout();

		layout.numColumns = 1;
		layout.makeColumnsEqualWidth = true;
		layout.verticalSpacing = 5;

		shell.setLayout(layout);
	}

	/**
	* Create menu bar
	*/
	private void makeMenu() {
		menuBar = new Menu(shell, SWT.BAR | SWT.LEFT_TO_RIGHT);
		shell.setMenuBar(menuBar);

		// init arrays for menu items, drop down menus for each item,
		// and sub menu items
		menuItems = new MenuItem[menuTitles.length];
		menus = new Menu[menuTitles.length];
		subMenuItems = new MenuItem[subMenuTitles.length][subMenuTitles[1].length];

		for (int i = 0; i < menuItems.length; i++) {
			menuItems[i] = new MenuItem(menuBar, SWT.CASCADE);
			menuItems[i].setText(menuTitles[i]);
			menuItems[i].setAccelerator(SWT.CTRL + menuMnemonics[i]);

			// make drop down menu for each menu item
			menus[i] = new Menu(shell, SWT.DROP_DOWN);

			// link menu to menu item
			menuItems[i].setMenu(menus[i]);

			// make sub menu items for each menu item
			// (equal buttons)
			for (int k = 0; k < subMenuTitles[1].length; k++) {
				if (!subMenuTitles[i][k].equals("")) {
					subMenuItems[i][k] = new MenuItem(menus[i], SWT.PUSH);
					subMenuItems[i][k].setText(subMenuTitles[i][k]);
					subMenuItems[i][k].setAccelerator(SWT.CTRL + subMenuMnemonics[i][k]);
					// subMenuItems[i][k].setToolTipText(subMenuToolTips[i][k]);
				}
			} // for (int k = 0; k < subMenuTitles[1].length; k++) {
		} // for (int i = 0; i < menuItems.length; i++) {
	}

	/**
	* Create cool bar with specified items
	*/
	private void makeCoolBar() {
		coolBar = new CoolBar(shell, SWT.HORIZONTAL);
		coolBar.setLayoutData(new GridData(SWT.LEFT_TO_RIGHT, SWT.CENTER, true, false, 1, 1));
		coolItems = new CoolItem[pathImages.length];
		coolButtons = new Button[pathImages.length];

		for (int i = 0; i < pathImages.length; i++) {
			coolItems[i] = new CoolItem(coolBar, SWT.NONE);
			coolButtons[i] = new Button(coolBar, SWT.PUSH);
			coolButtons[i].setImage(coolButtonImages[i]);
			coolItems[i]
					.setPreferredSize(coolItems[i].computeSize(coolButtons[i].computeSize(SWT.DEFAULT, SWT.DEFAULT).x,
							coolButtons[i].computeSize(SWT.DEFAULT, SWT.DEFAULT).y));
			coolItems[i].setControl(coolButtons[i]);
		}
	}

	/**
	* Create tab container
	*/
	private void makeTabContainer() {
		CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER);
		CTabItem item = new CTabItem(tabFolder, SWT.CLOSE);
		Text text = new Text(tabFolder, SWT.MULTI);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tabs = new ArrayList<>();
		tabsText = new ArrayList<>();

		tabFolder.setLayoutData(layoutData);

		item.setText(newTabText);
		item.setControl(text);
		item.setShowClose(false);

		tabs.add(item);

		tabFolder.setSelection(0);
	}

	/**
	* Add event listeners
	*/
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

		// CoolBarItem-Open
		coolItems[0].addSelectionListener(new SelectionAdapterOpen());
		coolItems[0].addSelectionListener(new SelectionAdapterSave());

		// 
	}
}
