package dev.swt.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ColorDialog extends Dialog {

	private Label textLabel, colorField;
	private RGB rgb;
	private Label[] rgbLabels;
	private Spinner[] rgbSpinners;
	private Button[] btns;
	private String subTitle = "Please select text foreground color";
	private String[] btnTitles = { "Ok", "Cancel" };
	private String[] rgbTitles = { "red", "green", "blue" };

	public ColorDialog(Shell shell, int style) {
		super(shell, style);
	}

	public ColorDialog(Shell shell) {
		this(shell, 0);
	}

	public Object open() {
		Shell parent = getParent();
		Display display = parent.getDisplay();
		Shell shellDialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		// create content
		makeShell(shellDialog);
		makeTextLabel(shellDialog);
		makeColorField(shellDialog);
		makeSpinners(display, shellDialog);
		makeButtons(shellDialog);

		// modify listener for each spinner
		for (Spinner s : rgbSpinners) {
			s.addModifyListener(new ModifyListenerSpinner(colorField, rgbSpinners));
		}

		// selection listener ok-button
		btns[0].addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				getColor();
				e.display.getActiveShell().dispose();
			}

			private void getColor() {
				if (rgb == null) {
					rgb = new RGB();
				}

				rgb.setRed(rgbSpinners[0].getSelection());
				rgb.setGreen(rgbSpinners[1].getSelection());
				rgb.setBlue(rgbSpinners[2].getSelection());
			}
		});

		// selection listener cancel-button
		btns[1].addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				e.display.getActiveShell().dispose();
			}
		});

		shellDialog.setText(getText());
		shellDialog.open();
		shellDialog.pack();
		while (!shellDialog.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		return rgb;
	}

	private void makeShell(final Shell shell) {
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 20;
		layout.verticalSpacing = 5;
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		shell.setLayout(layout);
	}

	private void makeTextLabel(final Shell shell) {
		textLabel = new Label(shell, SWT.FILL);
		GridData layoutData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1);
		textLabel.setLayoutData(layoutData);
		textLabel.setText(subTitle);
	}

	private void makeColorField(final Shell shell) {
		colorField = new Label(shell, SWT.LEFT);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		colorField.setLayoutData(layoutData);
	}

	private void makeSpinners(Display display, final Shell shell) {
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
			rgbSpinners[i].setSelection(80);
			rgbSpinners[i].setBackground(new Color(display, 255, 255, 255));
			rgbSpinners[i].setLayoutData(layoutDataSpinners);
		}
	}

	private void makeButtons(final Shell shell) {
		btns = new Button[btnTitles.length];
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);

		for (int i = 0; i < btns.length; i++) {
			btns[i] = new Button(shell, SWT.PUSH);
			btns[i].setText(btnTitles[i]);
			btns[i].setLayoutData(layoutData);
		}
	}
}
