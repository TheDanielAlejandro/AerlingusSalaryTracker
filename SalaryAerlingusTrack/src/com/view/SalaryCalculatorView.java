package com.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.Icon;

public class SalaryCalculatorView {

	private static SalaryCalculatorView instance; 
	
	private JFrame frmAerlingusPaysliptracker;
	private JPanel panel;
	private JButton btnInsertRow;
	private JButton btnLoadFromDB;
	private JButton btnDeleteRow;
	private JButton btnEditRow;
	private JButton btnCalculator;
	private JTable table;
//	private JMenuBar menuBar;
//	private JMenu mnNewMenu;
	
	/**
	 * Create the application.
	 */
	private SalaryCalculatorView() {
		initialize();
	}

	/**
	 * Method that retrieves the instance of the view if existent if not, it creates it.
	 * @return instance SalaryCalculatorView
	 */
	public static SalaryCalculatorView getInstance() {
		if(instance == null) {
			instance = new SalaryCalculatorView();
		}
		return instance;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAerlingusPaysliptracker = new JFrame();
		frmAerlingusPaysliptracker.getContentPane().setBackground(new Color(0, 100, 114));
		frmAerlingusPaysliptracker.setTitle("Aerlingus PayslipTracker");
		frmAerlingusPaysliptracker.setBounds(100, 100, 800, 600);
		frmAerlingusPaysliptracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAerlingusPaysliptracker.getContentPane().setLayout(null);
		frmAerlingusPaysliptracker.setLocationRelativeTo(null);
		frmAerlingusPaysliptracker.setResizable(false);
		
		ImageIcon lg = new ImageIcon(getClass().getClassLoader().getResource("logAL.png"));
		frmAerlingusPaysliptracker.setIconImage(lg.getImage());
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 764, 483);
		frmAerlingusPaysliptracker.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Payslip", "Payday", "Starting Date", "Ending Date", "Days Worked", "Daily Pay", "Expected Salary", "Actual Salary"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 784, 57);
		frmAerlingusPaysliptracker.getContentPane().add(panel);
		panel.setLayout(null);
		
		ImageIcon add = new ImageIcon(getClass().getClassLoader().getResource("addRow.png"));
		
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		btnInsertRow = new JButton(add);
		btnInsertRow.setBackground(new Color(255, 255, 255));
		btnInsertRow.setBounds(10, 5, 41, 41);
		btnInsertRow.setBorder(emptyBorder);
		btnInsertRow.setFocusPainted(false);
		btnInsertRow.setContentAreaFilled(false);
		panel.add(btnInsertRow);
		
		ImageIcon refresh = new ImageIcon(getClass().getClassLoader().getResource("refresh.png"));
		
		btnLoadFromDB = new JButton(refresh);
		btnLoadFromDB.setBackground(new Color(255, 255, 255));
		btnLoadFromDB.setBounds(163, 5, 41, 41);
		btnLoadFromDB.setBorder(emptyBorder);
		btnLoadFromDB.setFocusPainted(false);
		btnLoadFromDB.setContentAreaFilled(false);
		panel.add(btnLoadFromDB);
		
		ImageIcon remove = new ImageIcon(getClass().getClassLoader().getResource("deleteRow.png"));
		
		btnDeleteRow = new JButton(remove);
		btnDeleteRow.setBackground(new Color(255, 255, 255));
		btnDeleteRow.setBounds(61, 5, 41, 41);
		btnDeleteRow.setBorder(emptyBorder);
		btnDeleteRow.setFocusPainted(false);
		btnDeleteRow.setContentAreaFilled(false);
		panel.add(btnDeleteRow);
		
		ImageIcon edit = new ImageIcon(getClass().getClassLoader().getResource("editRow.png"));
		
		btnEditRow = new JButton(edit);
		btnEditRow.setBackground(new Color(255, 255, 255));
		btnEditRow.setBounds(112, 5, 41, 41);
		btnEditRow.setBorder(emptyBorder);
		btnEditRow.setFocusPainted(false);
		btnEditRow.setContentAreaFilled(false);
		panel.add(btnEditRow);
		
		ImageIcon calculator = new ImageIcon(getClass().getClassLoader().getResource("calculator.png"));
		
		btnCalculator = new JButton(calculator);
		btnCalculator.setBorder(emptyBorder);
		btnCalculator.setFocusPainted(false);
		btnCalculator.setContentAreaFilled(false);
		btnCalculator.setBackground(Color.WHITE);
		btnCalculator.setBounds(214, 5, 41, 41);
		
		panel.add(btnCalculator);
		
		
		ImageIcon alLogoName = new ImageIcon(getClass().getClassLoader().getResource("ALname.png"));
		
		JLabel alName = new JLabel("");
		alName.setIcon(alLogoName);
		alName.setBounds(482, 0, 302, 57);
		panel.add(alName);
		
		
		
//		menuBar = new JMenuBar();
//		frmAerlingusPaysliptracker.setJMenuBar(menuBar);
//		
//		mnNewMenu = new JMenu("File");
//		menuBar.add(mnNewMenu);
//		
		frmAerlingusPaysliptracker.setVisible(true);
	}
	
	//Getters
	
	public JButton getBtnInsertRow() {
		return btnInsertRow;
	}

	public JButton getBtnLoadFromDB() {
		return btnLoadFromDB;
	}
	
	public JButton getBtnDeleteRow() {
		return btnDeleteRow;
	}

	public JButton getBtnEditRow() {
		return btnEditRow;
	}
	
	public JButton getBtnCalculator() {
		return btnCalculator;
	}
	
	public JTable getTable() {
		return table;
	}

}
