package dev.swt.gui;

import org.eclipse.swt.layout.*;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

import org.eclipse.swt.graphics.Image;

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

	private void makeCoolBar() {
		coolBar = new CoolBar(shell, SWT.HORIZONTAL);
		coolBar.setLayoutData(new GridData(SWT.LEFT_TO_RIGHT, SWT.CENTER, true, false, 1, 1));
		coolItems = new CoolItem[2];
		coolButtons = new Button[pathImages.length];
		for (int i = 0; i < 2; i++) {
			coolItems[i] = new CoolItem(coolBar, SWT.NONE);
			coolButtons[i] = new Button(coolBar, SWT.PUSH);
			coolButtons[i].setImage(coolButtonImages[i]);
			// coolItems[i].setImage(image);
			coolItems[i]
					.setPreferredSize(coolItems[i].computeSize(coolButtons[i].computeSize(SWT.DEFAULT, SWT.DEFAULT).x,
							coolButtons[i].computeSize(SWT.DEFAULT, SWT.DEFAULT).y));
			// coolItems[i].setPreferredSize(50, 50);
			coolItems[i].setControl(coolButtons[i]);
			// coolItems[i].setSize(100, 100);

		}
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
