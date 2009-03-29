package view;

import list.AbstractList;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import controller.MainController;

/**
 *User interface design and implementation that supports required operation
 *on different kinds of lists
 * 
 * @author Mostafa Mahmoud Mahmoud Eweda
 * @version 1.0
 * @since JDK 1.6
 * 
 * @see ConsoleTesting
 * for analytical resulting methods --> suggested for charts drawing using Excel
 *
 */
public class MainWindow {

	private static String column = "Integers";
	private static final int UNSORTED_SIZE = 5000;
	private static final int SORTED_SIZE = 5000;
	protected static int MANUAL_SIZE = 0;

	private Display display;
	private Shell shell;
	private MainController controller;
	private Font font;

	public void run() {
		controller = new MainController();
		display = new Display();
		shell = new Shell(display);
		shell.setText("Searching Techniques");
		createContents();
		Rectangle dispBounds = display.getBounds();
		shell.setBounds(dispBounds.width / 16, dispBounds.height / 16,
				7 * dispBounds.width / 8, 7 * dispBounds.height / 8);
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		dispose();
	}

	private void createContents() {
		init();
		shell.setLayout(new FormLayout());
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 0);
		formData.top = new FormAttachment(0, 0);
		formData.right = new FormAttachment(70, 0);
		formData.bottom = new FormAttachment(100, 0);
		final TabFolder folder = new TabFolder(shell, SWT.NONE);
		folder.setLayoutData(formData);
		createSorted(folder);
		createUnsorted(folder);
		createManual(folder);

		formData = new FormData();
		formData.left = new FormAttachment(folder, 5);
		formData.top = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		Composite controls = new Composite(shell, SWT.NONE);
		controls.setLayoutData(formData);
		controls.setLayout(new GridLayout());

		GridData gridData = new GridData(GridData.FILL_BOTH);

		final Text keyText = new Text(controls, SWT.BORDER | SWT.CENTER);
		keyText.setFont(font);
		keyText.setLayoutData(gridData);
		final Button sequential = new Button(controls, SWT.PUSH);
		sequential.setText("Sequential");
		sequential.setFont(font);
		sequential.setLayoutData(gridData);
		final Button binary = new Button(controls, SWT.PUSH);
		binary.setText("Binary");
		binary.setFont(font);
		binary.setLayoutData(gridData);
		final Button interpolation = new Button(controls, SWT.PUSH);
		interpolation.setText("Interpolation");
		interpolation.setFont(font);
		interpolation.setLayoutData(gridData);
		final Button bst = new Button(controls, SWT.PUSH);
		bst.setText("Binary Serach Tree");
		bst.setFont(font);
		bst.setLayoutData(gridData);
		Button avl = new Button(controls, SWT.PUSH);
		avl.setText("AVL Tree");
		avl.setFont(font);
		avl.setLayoutData(gridData);
		final CLabel showTime = new CLabel(controls, SWT.NONE);
		showTime.setText("Time elapsed 'nanos'");
		showTime.setFont(font);
		showTime.setLayoutData(gridData);
		final CLabel runningTime = new CLabel(controls, SWT.NONE);
		runningTime.setFont(font);
		runningTime.setLayoutData(gridData);
		final CLabel showComparisons = new CLabel(controls, SWT.NONE);
		showComparisons.setText("# of comparisons made");
		showComparisons.setFont(font);
		showComparisons.setLayoutData(gridData);
		final CLabel comparisons = new CLabel(controls, SWT.NONE);
		comparisons.setFont(font);
		comparisons.setLayoutData(gridData);
		final Button insert = new Button(controls, SWT.PUSH);
		insert.setText("Insert");
		insert.setFont(font);
		insert.setLayoutData(gridData);
		final Button find = new Button(controls, SWT.RADIO);
		find.setText("find ");
		find.setFont(font);
		find.setSelection(true);
		find.setLayoutData(gridData);
		final Button findAll = new Button(controls, SWT.RADIO);
		findAll.setText("find all ");
		findAll.setFont(font);
		findAll.setLayoutData(gridData);
		final Button delete = new Button(controls, SWT.RADIO);
		delete.setText("remove ");
		delete.setFont(font);
		insert.setLayoutData(gridData);
		final Button deleteAll = new Button(controls, SWT.RADIO);
		deleteAll.setText("remove all ");
		deleteAll.setFont(font);
		deleteAll.setLayoutData(gridData);
		final CLabel showSize = new CLabel(controls, SWT.NONE);
		showSize.setFont(font);
		showSize.setText("List Size ");
		showSize.setLayoutData(gridData);
		final CLabel size = new CLabel(controls, SWT.NONE);
		size.setFont(font);
		size.setText("" + SORTED_SIZE);
		size.setLayoutData(gridData);
		sequential.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					Table current = (Table) shell.getData("table");
					current.deselectAll();
					int element = Integer.parseInt(keyText.getText());
					TableItem[] items = current.getItems();
					TableItem item;
					String wanted = null;
					if (find.getSelection()) {
						Integer found = controller.sequentialSearch(folder
								.getSelection()[0].getText(), element);
						if (found != null) {
							wanted = found.toString();
							for (int i = 0, n = items.length; i < n; i++) {
								item = items[i];
								if (item.getText().equals(wanted)) {
									current.setTopIndex(i < 15 ? i : i - 15);
									// current.showItem(item);
									// current.showSelection();
									current.select(i);
									break;
								}
							}
						}
					} else if (findAll.getSelection()) {
						Object[] result = controller.sequentialSearchAll(folder
								.getSelection()[0].getText(), element);
						if (result.length != 0) {
							wanted = result[0].toString();
							for (int i = 0, n = items.length; i < n; i++) {
								item = items[i];
								if (item.getText().equals(wanted)) {
									current.setTopIndex(i < 15 ? i : i - 15);
									// current.showItem(item);
									// current.showSelection();
									current.select(i);
								}
							}
						}
					}
					/*// TODO
					else if (delete.getSelection()) {
						controllder.delete();
					} */
					runningTime.setText(controller.runningTime().toString());
					comparisons.setText(controller.getComparisons().toString());
				} catch (NumberFormatException e1) {
					// do nothing
				}
			}
		});
		binary.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Integer found = controller.binarySearch(
						folder.getSelection()[0].getText(), Integer
								.parseInt(keyText.getText()));
				if (found != null) {
					String wanted = found.toString();
					Table current = (Table) shell.getData("table");
					current.deselectAll();
					TableItem[] items = current.getItems();
					TableItem item;
					for (int i = 0, n = items.length; i < n; i++) {
						item = items[i];
						if (item.getText().equals(wanted)) {
							current.setTopIndex(i < 15 ? i : i - 15);
							// current.showItem(item);
							// current.showSelection();
							current.select(i);
							break;
						}
					}
				}
				runningTime.setText(controller.runningTime().toString());
				comparisons.setText(controller.getComparisons().toString());
			}
		});
		interpolation.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Integer found = controller.interpolationSearch(folder
						.getSelection()[0].getText(), Integer.parseInt(keyText
						.getText()));
				if (found != null) {
					String wanted = found.toString();
					Table current = (Table) shell.getData("table");
					current.deselectAll();
					TableItem[] items = current.getItems();
					TableItem item;
					for (int i = 0, n = items.length; i < n; i++) {
						item = items[i];
						if (item.getText().equals(wanted)) {
							current.setTopIndex(i < 15 ? i : i - 15);
							// current.showItem(item);
							// current.showSelection();
							current.select(i);
							break;
						}
					}
				}
				runningTime.setText(controller.runningTime().toString());
				comparisons.setText(controller.getComparisons().toString());
			}
		});
		bst.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Integer found = controller.BSTSearch(folder.getSelection()[0]
						.getText(), Integer.parseInt(keyText.getText()));
				if (found != null) {
					String wanted = found.toString();
					Table current = (Table) shell.getData("table");
					current.deselectAll();
					TableItem[] items = current.getItems();
					TableItem item;
					for (int i = 0, n = items.length; i < n; i++) {
						item = items[i];
						if (item.getText().equals(wanted)) {
							current.select(i);
							current.setTopIndex(i < 15 ? i : i - 15);
							// current.showItem(item);
							// current.showSelection();
							break;
						}
					}
				}
				runningTime.setText(controller.runningTime().toString());
				comparisons.setText(controller.getComparisons().toString());
			}
		});
		avl.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Integer found = controller.AVLSearch(folder.getSelection()[0]
						.getText(), Integer.parseInt(keyText.getText()));
				if (found != null) {
					String wanted = found.toString();
					Table current = (Table) shell.getData("table");
					TableItem[] items = current.getItems();
					current.deselectAll();
					TableItem item;
					for (int i = 0, n = items.length; i < n; i++) {
						item = items[i];
						if (item.getText().equals(wanted)) {
							current.select(i);
							current.setTopIndex(i < 15 ? i : i - 15);
							// current.showItem(item);
							// current.showSelection();
							break;
						}
					}
				}
				runningTime.setText(controller.runningTime().toString());
				comparisons.setText(controller.getComparisons().toString());
			}
		});
		insert.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table manual = (Table) folder.getSelection()[0]
						.getData("table");
				String init;
				if (manual.getItemCount() > 0)
					init = manual.getItems()[manual.getItemCount() - 1]
							.getText();
				else
					init = "0";
				IInputValidator validator = new IInputValidator() {
					@Override
					public String isValid(String newText) {
						try {
							Integer.parseInt(newText);
							return null;
						} catch (NumberFormatException e1) {
							return newText;
						}
					}
				};
				InputDialog dialog = new InputDialog(shell, "Input",
						"Enter value", init, validator);
				if (dialog.open() == InputDialog.OK) {
					TableItem item = new TableItem(manual, SWT.CENTER);
					item.setText(dialog.getValue());
					TableColumn[] columns = manual.getColumns();
					for (TableColumn tableColumn : columns) {
						tableColumn.pack();
					}
					controller.addManual(Integer.parseInt(dialog.getValue()));
					MANUAL_SIZE++;
					size.setText("" + MANUAL_SIZE);
				}
			}
		});
		folder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table current = (Table) folder.getSelection()[0]
						.getData("table");
				shell.setData("table", current);
				String tab = folder.getSelection()[0].getText();
				if (tab.equals("Unsorted")) {
					binary.setEnabled(false);
					interpolation.setEnabled(false);
					insert.setEnabled(false);
					size.setText("" + UNSORTED_SIZE);
				} else if (tab.equals("Sorted")) {
					binary.setEnabled(true);
					interpolation.setEnabled(true);
					insert.setEnabled(false);
					size.setText("" + SORTED_SIZE);
					;
				} else { // tab.equals("Manual");
					binary.setEnabled(false);
					interpolation.setEnabled(false);
					insert.setEnabled(true);
					size.setText("" + MANUAL_SIZE);
				}
			}
		});
	}

	private void init() {
		font = new Font(display, "Monotype Corsiva", 18, SWT.BOLD);
	}

	private void createUnsorted(TabFolder folder) {
		TabItem manualItem = new TabItem(folder, SWT.NONE);
		manualItem.setText("Unsorted");
		AbstractList<Integer> list = controller
				.generateUnsortedList(UNSORTED_SIZE);
		Composite composite = new Composite(folder, SWT.NONE);
		composite.setLayout(new GridLayout());
		Table table = new Table(composite, SWT.FULL_SELECTION | SWT.MULTI
				| SWT.VIRTUAL);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setRedraw(false);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setText(column);
		for (int i = 0, n = list.size(); i < n; i++) {
			TableItem tableItem = new TableItem(table, SWT.CENTER);
			tableItem.setText(list.get(i).toString());
		}
		tableColumn.pack();
		table.setRedraw(true);
		manualItem.setControl(composite);
		manualItem.setData("table", table);
	}

	private void createSorted(TabFolder folder) {
		TabItem manualItem = new TabItem(folder, SWT.NONE);
		manualItem.setText("Sorted");
		AbstractList<Integer> list = controller.generateSortedList(SORTED_SIZE);
		Composite composite = new Composite(folder, SWT.NONE);
		composite.setLayout(new GridLayout());
		Table table = new Table(composite, SWT.FULL_SELECTION | SWT.MULTI);
		shell.setData("table", table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setRedraw(false);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setText(column);
		for (int i = 0, n = list.size(); i < n; i++) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(list.get(i).toString());
		}
		table.setRedraw(true);
		tableColumn.pack();
		manualItem.setControl(composite);
		manualItem.setData("table", table);
	}

	private void createManual(TabFolder folder) {
		TabItem manualItem = new TabItem(folder, SWT.NONE);
		manualItem.setText("Manual");
		controller.generateManualList(1000);
		Composite composite = new Composite(folder, SWT.NONE);
		composite.setLayout(new GridLayout());
		Table table = new Table(composite, SWT.FULL_SELECTION | SWT.MULTI
				| SWT.VIRTUAL);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setRedraw(false);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setText(column);
		table.setRedraw(true);
		tableColumn.pack();
		manualItem.setControl(composite);
		manualItem.setData("table", table);
	}

	private void dispose() {
		font.dispose();
		display.dispose();
	}

	public static void main(String[] args) {
		new MainWindow().run();
	}
}
