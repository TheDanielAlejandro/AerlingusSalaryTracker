package com.view;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class EditRowView {

	private static EditRowView instance;
	private JFrame frmEditRow;
	private JTextField tfPayslip;
	private JTextField tfPayday;
	private JTextField tfStartD;
	private JTextField tfEndD;
	private JTextField tfDaysWorked;
	private JTextField tfDailyP;
	private JTextField tfExpectedS;
	private JTextField tfActualS;
	private JButton btnUpdate;

	

	/**
	 * Create the application.
	 */
	private EditRowView() {
		initialize();
	}

	public static EditRowView getInstance() {
		if(instance==null) {
			instance = new EditRowView();
		}
		return instance;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditRow = new JFrame();
		frmEditRow.setTitle("Edit Row");
		frmEditRow.setBounds(100, 100, 935, 178);
		frmEditRow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmEditRow.getContentPane().setLayout(null);
		frmEditRow.setLocationRelativeTo(null);
		frmEditRow.setResizable(false);
		
		ImageIcon lg = new ImageIcon(getClass().getClassLoader().getResource("logAL.png"));
		frmEditRow.setIconImage(lg.getImage());
		
		tfPayslip = new JTextField();
		tfPayslip.setBackground(Color.WHITE);
		tfPayslip.setHorizontalAlignment(SwingConstants.CENTER);
		tfPayslip.setEditable(false);
		tfPayslip.setBounds(20, 39, 101, 29);
		frmEditRow.getContentPane().add(tfPayslip);
		tfPayslip.setColumns(10);
		
		tfPayday = new JTextField();
		tfPayday.setHorizontalAlignment(SwingConstants.CENTER);
		tfPayday.setColumns(10);
		tfPayday.setBounds(131, 39, 101, 29);
		frmEditRow.getContentPane().add(tfPayday);
		
		tfStartD = new JTextField();
		tfStartD.setHorizontalAlignment(SwingConstants.CENTER);
		tfStartD.setColumns(10);
		tfStartD.setBounds(242, 39, 101, 29);
		frmEditRow.getContentPane().add(tfStartD);
		
		tfEndD = new JTextField();
		tfEndD.setHorizontalAlignment(SwingConstants.CENTER);
		tfEndD.setColumns(10);
		tfEndD.setBounds(353, 39, 101, 29);
		frmEditRow.getContentPane().add(tfEndD);
		
		tfDaysWorked = new JTextField();
		tfDaysWorked.setHorizontalAlignment(SwingConstants.CENTER);
		tfDaysWorked.setColumns(10);
		tfDaysWorked.setBounds(464, 39, 101, 29);
		frmEditRow.getContentPane().add(tfDaysWorked);
		
		tfDailyP = new JTextField();
		tfDailyP.setHorizontalAlignment(SwingConstants.CENTER);
		tfDailyP.setColumns(10);
		tfDailyP.setBounds(575, 39, 101, 29);
		frmEditRow.getContentPane().add(tfDailyP);
		
		tfExpectedS = new JTextField();
		tfExpectedS.setHorizontalAlignment(SwingConstants.CENTER);
		tfExpectedS.setColumns(10);
		tfExpectedS.setBounds(686, 39, 101, 29);
		frmEditRow.getContentPane().add(tfExpectedS);
		
		JLabel lblNewLabel = new JLabel("Payslip");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 14, 101, 14);
		frmEditRow.getContentPane().add(lblNewLabel);
		
		tfActualS = new JTextField();
		tfActualS.setHorizontalAlignment(SwingConstants.CENTER);
		tfActualS.setColumns(10);
		tfActualS.setBounds(797, 39, 101, 29);
		frmEditRow.getContentPane().add(tfActualS);
		
		JLabel lblPayday = new JLabel("Payday");
		lblPayday.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayday.setBounds(131, 14, 101, 14);
		frmEditRow.getContentPane().add(lblPayday);
		
		JLabel lblStartingDate = new JLabel("Starting Date");
		lblStartingDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartingDate.setBounds(242, 14, 101, 14);
		frmEditRow.getContentPane().add(lblStartingDate);
		
		JLabel lblEndingDate = new JLabel("Ending Date");
		lblEndingDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndingDate.setBounds(353, 14, 101, 14);
		frmEditRow.getContentPane().add(lblEndingDate);
		
		JLabel lblDaysworked = new JLabel("Days Worked");
		lblDaysworked.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaysworked.setBounds(464, 14, 101, 14);
		frmEditRow.getContentPane().add(lblDaysworked);
		
		JLabel lblDailyPay = new JLabel("Daily Pay");
		lblDailyPay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDailyPay.setBounds(575, 14, 101, 14);
		frmEditRow.getContentPane().add(lblDailyPay);
		
		JLabel lblExpectedSalary = new JLabel("Expected Salary");
		lblExpectedSalary.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpectedSalary.setBounds(686, 14, 101, 14);
		frmEditRow.getContentPane().add(lblExpectedSalary);
		
		JLabel lblActualSalary = new JLabel("Actual Salary");
		lblActualSalary.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualSalary.setBounds(797, 14, 101, 14);
		frmEditRow.getContentPane().add(lblActualSalary);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(0, 128, 128));
		btnUpdate.setBounds(394, 92, 130, 36);
		frmEditRow.getContentPane().add(btnUpdate);
	}

	
	public void setTextFields(String payslip, String payday, String startD, String endD, String daysWorked, String dailyP, String expectedS, String actualS ) {
		this.tfPayslip.setText(payslip);
		this.tfPayday.setText(payday);
		this.tfStartD.setText(startD);
		this.tfEndD.setText(endD);
		this.tfDaysWorked.setText(daysWorked);
		this.tfDailyP.setText(dailyP);
		this.tfExpectedS.setText(expectedS);
		this.tfActualS.setText(actualS);
	}
	

	public JFrame getFrame() {
		return frmEditRow;
	}

	public JTextField getTfPayslip() {
		return tfPayslip;
	}

	public JTextField getTfPayday() {
		return tfPayday;
	}

	public JTextField getTfStartD() {
		return tfStartD;
	}

	public JTextField getTfEndD() {
		return tfEndD;
	}

	public JTextField getTfDaysWorked() {
		return tfDaysWorked;
	}

	public JTextField getTfDailyP() {
		return tfDailyP;
	}

	public JTextField getTfExpectedS() {
		return tfExpectedS;
	}

	public JTextField getTfActualS() {
		return tfActualS;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}
	
	
}
