package dev.swt.gui;

import java.io.File;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

public class Editor {

	private static final String IMAGE_PATH = "images" + File.separator;

	private ResourceBundle msg;
	private ResourceBundle mnemonics;

	private Display display;
	private Shell shell;

	private Menu menuBar;
	private MenuItem[] menuItems;
	private Menu[] menus;
	private MenuItem[] fileMenuItems;
	private MenuItem[] editMenuItems;
	private MenuItem[] helpMenuItems;

	private CoolBar coolBar;
	private CoolItem[] coolItems;
	private Button[] coolButtons;
	private Image[] coolButtonImages;
	// private String[] pathImages = { "icon_OpenFolder.gif", "save_edit.gif" };

	private CTabFolder tabFolder;

	public Editor() {
		this(ResourceBundle.getBundle("dev.swt.gui.MessageBundle"));
	}

	public Editor(ResourceBundle msg) {
		this.msg = msg;

		getMnemonics();
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
	 * Import Mnemonics
	 */
	private void getMnemonics() {
		mnemonics = ResourceBundle.getBundle("dev.swt.gui.MnemonicBundle");
	}

	/**
	 * Creates the images
	 */
	private void makeImages() {
		coolButtonImages = new Image[2];
		try {
			coolButtonImages[0] = new Image(display,
					Editor.class.getResourceAsStream(IMAGE_PATH + "icon_OpenFolder.gif"));
			coolButtonImages[1] = new Image(display, Editor.class.getResourceAsStream(IMAGE_PATH + "save_edit.gif"));

		} catch (Exception e) {
			// Images not found; handle gracefully
			System.out.println("image not found");
		}
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
		shell.setText(msg.getString("editorTitle"));
	}

	/**
	 * Create menu bar
	 */
	private void makeMenu() {
		menuBar = new Menu(shell, SWT.BAR | SWT.LEFT_TO_RIGHT);
		shell.setMenuBar(menuBar);

		// init arrays for menu items, drop down menus for each item,
		// and sub menu items
		menuItems = new MenuItem[3];
		menus = new Menu[3];
		fileMenuItems = new MenuItem[4];
		editMenuItems = new MenuItem[1];
		helpMenuItems = new MenuItem[1];

		/**
		* make file - menu
		*/
		menuItems[0] = new MenuItem(menuBar, SWT.CASCADE);
		menuItems[0].setText(msg.getString("editorFile"));
		menuItems[0].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorFile"));
		// make drop down menu for each menu item
		menus[0] = new Menu(shell, SWT.DROP_DOWN);
		// link menu to menu item
		menuItems[0].setMenu(menus[0]);
		// make sub menu items for each menu item
		// (equal buttons)
		// ==> New
		fileMenuItems[0] = new MenuItem(menus[0], SWT.PUSH);
		fileMenuItems[0].setText(msg.getString("editorNew"));
		fileMenuItems[0].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorNew"));
		// ==> Open...
		fileMenuItems[1] = new MenuItem(menus[0], SWT.PUSH);
		fileMenuItems[1].setText(msg.getString("editorOpen"));
		fileMenuItems[1].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorOpen"));
		// ==> Save...
		fileMenuItems[2] = new MenuItem(menus[0], SWT.PUSH);
		fileMenuItems[2].setText(msg.getString("editorSave"));
		fileMenuItems[2].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorSave"));
		// ==> Quit
		fileMenuItems[3] = new MenuItem(menus[0], SWT.PUSH);
		fileMenuItems[3].setText(msg.getString("editorQuit"));
		fileMenuItems[3].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorQuit"));

		/**
		* make edit - menu
		*/
		menuItems[1] = new MenuItem(menuBar, SWT.CASCADE);
		menuItems[1].setText(msg.getString("editorEdit"));
		menuItems[1].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorEdit"));
		menus[1] = new Menu(shell, SWT.DROP_DOWN);
		menuItems[1].setMenu(menus[1]);
		// ==> Text Color
		editMenuItems[0] = new MenuItem(menus[1], SWT.PUSH);
		editMenuItems[0].setText(msg.getString("editorColor"));
		editMenuItems[0].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorEdit"));

		/**
		* make help - menu
		*/
		menuItems[2] = new MenuItem(menuBar, SWT.CASCADE);
		menuItems[2].setText(msg.getString("editorHelp"));
		menuItems[2].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorHelp"));
		menus[2] = new Menu(shell, SWT.DROP_DOWN);
		menuItems[2].setMenu(menus[2]);
		// ==> Version
		helpMenuItems[0] = new MenuItem(menus[2], SWT.PUSH);
		helpMenuItems[0].setText(msg.getString("editorVersion"));
		helpMenuItems[0].setAccelerator(SWT.CTRL + (Character) mnemonics.getObject("editorVersion"));
	}

	/**
	 * Create cool bar with specified items
	 */
	private void makeCoolBar() {
		coolBar = new CoolBar(shell, SWT.HORIZONTAL);
		coolBar.setLayoutData(new GridData(SWT.LEFT_TO_RIGHT, SWT.CENTER, true, false, 1, 1));
		coolItems = new CoolItem[2];
		coolButtons = new Button[2];

		for (int i = 0; i < 2; i++) {
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
		tabFolder = new CTabFolder(shell, SWT.BORDER);
		CTabItem item = new CTabItem(tabFolder, SWT.CLOSE);
		MyText text = new MyText(tabFolder, SWT.MULTI | SWT.V_SCROLL);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);

		tabFolder.setLayoutData(layoutData);

		item.setText(msg.getString("editorTabText") + "-1");
		item.setControl(text);

		tabFolder.setSelection(0);
	}

	/**
	 * Add event listeners
	 */
	private void addListeners() {
		// File-Menu
		fileMenuItems[0].addSelectionListener(new SelectionAdapterNew(tabFolder));
		fileMenuItems[1].addSelectionListener(new SelectionAdapterOpen(tabFolder));
		fileMenuItems[2].addSelectionListener(new SelectionAdapterSave(tabFolder));
		fileMenuItems[3].addSelectionListener(new SelectionAdapterQuit(msg, tabFolder));

		// Edit-Menu
		editMenuItems[0].addSelectionListener(new SelectionAdapterColor(msg, tabFolder));

		// Help-Menu
		helpMenuItems[0].addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				MessageBox messageBox = new MessageBox(tabFolder.getShell(), SWT.APPLICATION_MODAL | SWT.OK);
				messageBox.setText("Information");
				messageBox.setMessage(msg.getString("version") + "\n\u00a9" + msg.getString("owner"));
				messageBox.open();
			}
		});

		// CoolBarItem-Open
		coolButtons[0].addSelectionListener(new SelectionAdapterOpen(tabFolder));
		coolButtons[1].addSelectionListener(new SelectionAdapterSave(tabFolder));

		// TabFolder
		tabFolder.addCTabFolder2Listener(new TabEventListener());

		CTabItem[] items = tabFolder.getItems();
		for (CTabItem item : items) {
			MyText text = (MyText) item.getControl();
			text.addModifyListener(new ModifyListenerText(tabFolder));
		}

		// Application
		shell.addListener(SWT.Close, new Listener() {
			@Override
			public void handleEvent(Event event) {
				CTabItem[] tabs = tabFolder.getItems();
				boolean isAnyTabModified = false;

				for (CTabItem tab : tabs) {
					MyText text = (MyText) tab.getControl();
					if (text.isModified()) {
						isAnyTabModified = true;
						break;
					}
				}

				if (isAnyTabModified) {
					MessageBox messageBox = new MessageBox(tabFolder.getShell(),
							SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
					messageBox.setText("Information");
					messageBox.setMessage(msg.getString("stopClosing"));
					event.doit = messageBox.open() == SWT.YES;
				}
			}
		});
	}

	/**
	 * Disposes the images
	 */
	private void disposeImages() {
		for (Image image : coolButtonImages) {
			if (image != null) {
				image.dispose();
			}
		}
	}
}
