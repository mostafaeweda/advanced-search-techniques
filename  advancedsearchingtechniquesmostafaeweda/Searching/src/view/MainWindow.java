package view;

import list.AbstractList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
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

public class MainWindow {

	private static String column = "Integers";
	private static final int UNSORTED_SIZE = 5000;
	private static final int SORTED_SIZE = 5000;

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
		shell.setBounds(dispBounds.width / 8, dispBounds.height / 8, 3 * dispBounds.width / 4, 3 * dispBounds.height /4);
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
		sequential.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				try {
					int element = Integer.parseInt(keyText.getText());
					Integer found = controller.sequentialSearch(folder.getSelection()[0].getText(), element);
					if (found != null) {
						String wanted = found.toString();
						Table current = (Table) shell.getData("table");
						TableItem[] items = current.getItems();
						TableItem item;
						for (int i = 0, n = items.length; i < n; i++) {
							item = items[i];
							if (item.getText().equals(wanted)) {
								current.setTopIndex(i < 15 ? i : i - 15);
//								current.showItem(item);
//								current.showSelection();
								current.select(i);
								break;
							}
						}
					}
					runningTime.setText(controller.runningTime().toString());
					comparisons.setText(controller.getComparisons().toString());
				} catch (NumberFormatException e1) {
					// do nothing
				}
			}
		});
		binary.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				Integer found = controller.binarySearch(folder.getSelection()[0].getText(), Integer.parseInt(keyText.getText()));
				if (found != null) {
					String wanted = found.toString();
					Table current = (Table) shell.getData("table");
					TableItem[] items = current.getItems();
					TableItem item;
					for (int i = 0, n = items.length; i < n; i++) {
						item = items[i];
						if (item.getText().equals(wanted)) {
							current.setTopIndex(i < 15 ? i : i - 15);
//							current.showItem(item);
//							current.showSelection();
							current.select(i);
							break;
						}
					}
				}
				runningTime.setText(controller.runningTime().toString());
				comparisons.setText(controller.getComparisons().toString());
			}
		});
		interpolation.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				Integer found = controller.interpolationSearch(folder.getSelection()[0].getText(), Integer.parseInt(keyText.getText()));
				if (found != null) {
					String wanted = found.toString();
					Table current = (Table) shell.getData("table");
					TableItem[] items = current.getItems();
					TableItem item;
					for (int i = 0, n = items.length; i < n; i++) {
						item = items[i];
						if (item.getText().equals(wanted)) {
							current.setTopIndex(i < 15 ? i : i - 15);
//							current.showItem(item);
//							current.showSelection();
							current.select(i);
							break;
						}
					}
				}
				runningTime.setText(controller.runningTime().toString());
				comparisons.setText(controller.getComparisons().toString());
			}
		});
		bst.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				Integer found = controller.BSTSearch(folder.getSelection()[0].getText(), Integer.parseInt(keyText.getText()));
				if (found != null) {
					String wanted = found.toString();
					Table current = (Table) shell.getData("table");
					TableItem[] items = current.getItems();
					TableItem item;
					for (int i = 0, n = items.length; i < n; i++) {
						item = items[i];
						if (item.getText().equals(wanted)) {
							current.select(i);
							current.setTopIndex(i < 15 ? i : i - 15);
//							current.showItem(item);
//							current.showSelection();
							break;
						}
					}
				}
				runningTime.setText(controller.runningTime().toString());
				comparisons.setText(controller.getComparisons().toString());
			}
		});
		avl.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				Integer found = controller.AVLSearch(folder.getSelection()[0].getText(), Integer.parseInt(keyText.getText()));
				if (found != null) {
					String wanted = found.toString();
					Table current = (Table) shell.getData("table");
					TableItem[] items = current.getItems();
					TableItem item;
					for (int i = 0, n = items.length; i < n; i++) {
						item = items[i];
						if (item.getText().equals(wanted)) {
							current.select(i);
							current.setTopIndex(i < 15 ? i : i - 15);
//							current.showItem(item);
//							current.showSelection();
							break;
						}
					}
				}
				runningTime.setText(controller.runningTime().toString());
				comparisons.setText(controller.getComparisons().toString());
			}
		});
		folder.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table current = (Table) folder.getSelection()[0].getData("table");
				shell.setData("table", current);
				String tab = folder.getSelection()[0].getText();
				if (tab.equals("Manual") || tab.equals("Unsorted")) {
					binary.setEnabled(false);
					interpolation.setEnabled(false);
				}
				else if (tab.equals("Sorted")) {
					binary.setEnabled(true);
					interpolation.setEnabled(true);
				}
			}});
	}

	private void init() {
		font = new Font(display, "Monotype Corsiva", 18, SWT.BOLD);
	}

	private void createUnsorted(TabFolder folder) {
		TabItem manualItem = new TabItem(folder, SWT.NONE);
		manualItem.setText("Unsorted");
		AbstractList<Integer> list = controller.generateUnsortedList(UNSORTED_SIZE);
		Composite composite = new Composite(folder, SWT.NONE);
		composite.setLayout(new GridLayout());
		Table table = new Table(composite, SWT.FULL_SELECTION | SWT.MULTI | SWT.VIRTUAL);
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
		AbstractList<Integer> list = controller.generateManualList(1000);
		Composite composite = new Composite(folder, SWT.NONE);
		composite.setLayout(new GridLayout());
		Table table = new Table(composite, SWT.FULL_SELECTION | SWT.MULTI | SWT.VIRTUAL);
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
		table.setRedraw(true);
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
