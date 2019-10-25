package dev.swt.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ColorDialog {

	private Display display;
	private Shell shell;
	private Label textLabel, colorField;
	private RGB rgb;
	private Label[] rgbLabels;
	private Spinner[] rgbSpinners;
	private Button[] btns;
	private String subTitle = "Please select text foreground color";
	private String[] btnTitles = { "Ok", "Cancel" };
	private String[] rgbTitles = { "red", "green", "blue" };

	public ColorDialog() {
		makeDisplay();
		makeShell();
		makeTextLabel();
		makeColorField();
		makeSpinners();
		makeButtons();
		addListeners();
		shell.pack();
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
		shell = new Shell(display);
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 20;
		layout.verticalSpacing = 5;
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		shell.setLayout(layout);
		// shell.setBackground(new Color(display, 100, 100, 100));
	}

	private void makeTextLabel() {
		textLabel = new Label(shell, SWT.FILL);
		GridData layoutData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1);
		textLabel.setLayoutData(layoutData);
		textLabel.setText(subTitle);
	}

	private void makeColorField() {
		colorField = new Label(shell, SWT.LEFT);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		colorField.setLayoutData(layoutData);
		// color.setFont(fGlobal);
		// color.setBackground(cLabel);
	}

	private void makeSpinners() {
		Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
		GridLayout layout = new GridLayout();
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		GridData layoutDataLabels = new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1);
		GridData layoutDataSpinners = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		rgbSpinners = new Spinner[3];
		rgbLabels = new Label[rgbTitles.length];

		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;
		layout.horizontalSpacing = 15;
		layout.verticalSpacing = 10;
		// layout.marginHeight = 10;
		group.setLayoutData(layoutData);
		group.setLayout(layout);
		group.setText("Text Color");

		for (int i = 0; i < 3; i++) {
			rgbLabels[i] = new Label(group, SWT.FILL);
			rgbLabels[i].setText(rgbTitles[i]);
			rgbLabels[i].setLayoutData(layoutDataLabels);
			rgbSpinners[i] = new Spinner(group, SWT.READ_ONLY | SWT.FILL);
			rgbSpinners[i].setMinimum(0);
			rgbSpinners[i].setMaximum(255);
			rgbSpinners[i].setSelection(100);
			rgbSpinners[i].setBackground(new Color(display, 255, 255, 255));
			rgbSpinners[i].setLayoutData(layoutDataSpinners);
		}

		rgbSpinners[0].setSelection(0);
		rgbSpinners[1].setSelection(0);
		rgbSpinners[2].setSelection(0);
	}

	private void makeButtons() {
		btns = new Button[btnTitles.length];
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);

		for (int i = 0; i < btns.length; i++) {
			btns[i] = new Button(shell, SWT.PUSH);
			btns[i].setText(btnTitles[i]);
			btns[i].setLayoutData(layoutData);
		}
	}

	private void addListeners() {
		for (Spinner s : rgbSpinners) {
			s.addModifyListener(new ModifyListenerSpinner(colorField, rgbSpinners, rgb));
		}
		btns[0].addSelectionListener(new SelectionAdapterOk(rgbSpinners, rgb));
		btns[1].addSelectionListener(new SelectionAdapterCancel());
	}
}
