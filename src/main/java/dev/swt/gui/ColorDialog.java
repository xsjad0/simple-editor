package dev.swt.gui;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ColorDialog extends Dialog {

	private ResourceBundle msg;

	private Label textLabel, colorField;
	private RGB rgb;
	private Label[] rgbLabels;
	private Spinner[] rgbSpinners;
	private Button[] btns;

	private ColorDialog(Shell shell, int style) {
		super(shell, style);
	}

	public ColorDialog(ResourceBundle msg, Shell shell) {
		this(shell, 0);
		this.msg = msg;
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
		layout.horizontalSpacing = 10;
		layout.verticalSpacing = 5;
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		shell.setLayout(layout);
		shell.setText(msg.getString("colorTitle"));
	}

	private void makeTextLabel(final Shell shell) {
		textLabel = new Label(shell, SWT.FILL);
		GridData layoutData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1);
		textLabel.setLayoutData(layoutData);
		textLabel.setText(msg.getString("colorInfo"));
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
		GridData layoutDataLabels = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		GridData layoutDataSpinners = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		rgbSpinners = new Spinner[3];
		rgbLabels = new Label[3];

		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 10;

		group.setLayoutData(layoutData);
		group.setLayout(layout);
		group.setText(msg.getString("colorGroup"));

		// RGB Red
		rgbLabels[0] = new Label(group, SWT.FILL);
		rgbLabels[0].setText(msg.getString("colorRed"));
		rgbLabels[0].setLayoutData(layoutDataLabels);

		rgbSpinners[0] = new Spinner(group, /* SWT.READ_ONLY | */ SWT.FILL);
		rgbSpinners[0].setMinimum(0);
		rgbSpinners[0].setMaximum(255);
		rgbSpinners[0].setSelection(80);
		rgbSpinners[0].setBackground(new Color(display, 255, 255, 255));
		rgbSpinners[0].setLayoutData(layoutDataSpinners);

		// RGB Green
		rgbLabels[1] = new Label(group, SWT.FILL);
		rgbLabels[1].setText(msg.getString("colorGreen"));
		rgbLabels[1].setLayoutData(layoutDataLabels);
		rgbSpinners[1] = new Spinner(group, /* SWT.READ_ONLY | */ SWT.FILL);
		rgbSpinners[1].setMinimum(0);
		rgbSpinners[1].setMaximum(255);
		rgbSpinners[1].setSelection(80);
		rgbSpinners[1].setBackground(new Color(display, 255, 255, 255));
		rgbSpinners[1].setLayoutData(layoutDataSpinners);

		// RGB Blue
		rgbLabels[2] = new Label(group, SWT.FILL);
		rgbLabels[2].setText(msg.getString("colorBlue"));
		rgbLabels[2].setLayoutData(layoutDataLabels);
		rgbSpinners[2] = new Spinner(group, /* SWT.READ_ONLY | */ SWT.FILL);
		rgbSpinners[2].setMinimum(0);
		rgbSpinners[2].setMaximum(255);
		rgbSpinners[2].setSelection(80);
		rgbSpinners[2].setBackground(new Color(display, 255, 255, 255));
		rgbSpinners[2].setLayoutData(layoutDataSpinners);
	}

	private void makeButtons(final Shell shell) {
		btns = new Button[2];
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);

		btns[0] = new Button(shell, SWT.PUSH);
		btns[0].setText(msg.getString("colorOkay"));
		btns[0].setLayoutData(layoutData);

		btns[1] = new Button(shell, SWT.PUSH);
		btns[1].setText(msg.getString("colorCancel"));
		btns[1].setLayoutData(layoutData);
	}
}
