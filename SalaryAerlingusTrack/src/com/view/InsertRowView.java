package com.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class InsertRowView {

	private static InsertRowView instance;
	private JFrame frmInsertRow;
	private JTextField tfPayslip;
	private JTextField tfActualS;
	private JTextField tfWorkingD;
    private JTextField tfDailyP;
    private JLabel aerLingusLogo;
    private JButton btnSubmit;
    private JComboBox cbPayG;
    private JComboBox cbPayM;
    private JComboBox cbPayY;
    private JComboBox cbStartG;
    private JComboBox cbStartM;
    private JComboBox cbStartY;
    private JComboBox cbEndG;
    private JComboBox cbEndM;
    private JComboBox cbEndY;
    private final Integer[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
    private final Integer[] days = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    private JOptionPane error;
    
    
    
	/**
	 * Create the application.
	 */
	private InsertRowView() {
		initialize();
	}
	
	public static InsertRowView getIntance() {
		if(instance == null) {
			instance = new InsertRowView();
		}
		return instance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInsertRow = new JFrame();
		frmInsertRow.setTitle("Insert Row");
		frmInsertRow.setBounds(100, 100, 550, 300);
		frmInsertRow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmInsertRow.getContentPane().setLayout(null);
		frmInsertRow.setLocationRelativeTo(null);
		frmInsertRow.setResizable(false);
		
		ImageIcon lg = new ImageIcon(getClass().getClassLoader().getResource("logAL.png"));
		frmInsertRow.setIconImage(lg.getImage());
		
		JLabel lblNewLabel = new JLabel("Payslip : ");
		lblNewLabel.setBounds(20, 30, 120, 14);
		frmInsertRow.getContentPane().add(lblNewLabel);
		
		tfPayslip = new JTextField();
		tfPayslip.setHorizontalAlignment(SwingConstants.CENTER);
		tfPayslip.setBounds(184, 27, 86, 20);
		frmInsertRow.getContentPane().add(tfPayslip);
		tfPayslip.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Payday (DD-MM-YY) :");
		lblNewLabel_1.setBounds(20, 61, 154, 14);
		frmInsertRow.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Starting Date (DD-MM-YY) :");
		lblNewLabel_2.setBounds(20, 93, 154, 14);
		frmInsertRow.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ending Date (DD-MM-YY) :");
		lblNewLabel_3.setBounds(20, 124, 154, 14);
		frmInsertRow.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Actual Salary :");
		lblNewLabel_5.setBounds(20, 217, 126, 14);
		frmInsertRow.getContentPane().add(lblNewLabel_5);
		
		tfActualS = new JTextField();
		tfActualS.setHorizontalAlignment(SwingConstants.CENTER);
		tfActualS.setBounds(184, 214, 86, 20);
		frmInsertRow.getContentPane().add(tfActualS);
		tfActualS.setColumns(10);
		
		//The dimension of the logo is 150x150
		aerLingusLogo = new JLabel("");
		ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("AL_Logo.png"));
		aerLingusLogo.setIcon(logo);
		aerLingusLogo.setBounds(374, 53, 150, 150);
		frmInsertRow.getContentPane().add(aerLingusLogo);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(0, 100, 114));
		btnSubmit.setBounds(406, 217, 89, 20);
		btnSubmit.setFocusPainted(false);
		frmInsertRow.getContentPane().add(btnSubmit);
		
		cbPayG = new JComboBox(days);
		cbPayG.setBounds(184, 58, 50, 24);
		frmInsertRow.getContentPane().add(cbPayG);
		
		cbPayM = new JComboBox(months);
		cbPayM.setBounds(244, 58, 50, 24);
		frmInsertRow.getContentPane().add(cbPayM);
		
		cbPayY = new JComboBox();
		cbPayY.setBounds(304, 58, 60, 24);
		frmInsertRow.getContentPane().add(cbPayY);
		
		cbStartG = new JComboBox(days);
		cbStartG.setBounds(184, 88, 50, 24);
		frmInsertRow.getContentPane().add(cbStartG);
		
		cbStartM = new JComboBox(months);
		cbStartM.setBounds(244, 88, 50, 24);
		frmInsertRow.getContentPane().add(cbStartM);
		
		cbStartY = new JComboBox();
		cbStartY.setBounds(304, 88, 60, 24);
		frmInsertRow.getContentPane().add(cbStartY);
		
		cbEndG = new JComboBox(days);
		cbEndG.setBounds(184, 119, 50, 24);
		frmInsertRow.getContentPane().add(cbEndG);
		
		cbEndM = new JComboBox(months);
		cbEndM.setBounds(244, 119, 50, 24);
		frmInsertRow.getContentPane().add(cbEndM);
		
		cbEndY = new JComboBox();
		cbEndY.setBounds(304, 119, 60, 24);
		frmInsertRow.getContentPane().add(cbEndY);
		
		tfWorkingD = new JTextField();
		tfWorkingD.setHorizontalAlignment(SwingConstants.CENTER);
		tfWorkingD.setBounds(184, 154, 86, 20);
		frmInsertRow.getContentPane().add(tfWorkingD);
		tfWorkingD.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Working days :");
		lblNewLabel_6.setBounds(20, 157, 120, 14);
		frmInsertRow.getContentPane().add(lblNewLabel_6);
		
		tfDailyP = new JTextField();
		tfDailyP.setHorizontalAlignment(SwingConstants.CENTER);
		tfDailyP.setBounds(184, 183, 86, 20);
		frmInsertRow.getContentPane().add(tfDailyP);
		tfDailyP.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Daily Pay :");
		lblNewLabel_7.setBounds(20, 186, 120, 14);
		frmInsertRow.getContentPane().add(lblNewLabel_7);
		
	}

	
	//GETTERS
	
	public JFrame getFrmInsertRow() {
		return frmInsertRow;
	}
	
	public JTextField getTfPayslip() {
		return tfPayslip;
	}

	public JTextField getTfActualS() {
		return tfActualS;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JComboBox getCbPayG() {
		return cbPayG;
	}

	public JComboBox getCbPayM() {
		return cbPayM;
	}

	public JComboBox getCbPayY() {
		return cbPayY;
	}

	public JComboBox getCbStartG() {
		return cbStartG;
	}

	public JComboBox getCbStartM() {
		return cbStartM;
	}

	public JComboBox getCbStartY() {
		return cbStartY;
	}

	public JComboBox getCbEndG() {
		return cbEndG;
	}

	public JComboBox getCbEndM() {
		return cbEndM;
	}

	public JComboBox getCbEndY() {
		return cbEndY;
	}

	public Integer[] getDays() {
		return days;
	}

//	public Integer[] getDays30() {
//		return days30;
//	}
//	
//	public Integer[] getDaysFeb() {
//		return daysFeb;
//	}
//
//	public Integer[] getDaysFebLeap() {
//		return daysFebLeap;
//	}

	public JTextField getTfWorkingD() {
		return tfWorkingD;
	}

	public JTextField getTfDailyP() {
		return tfDailyP;
	}
	
	public JOptionPane getError() {
		return error;
	}
	
	public void clearAllTextFields() {
		this.tfActualS.setText("");
		this.tfDailyP.setText("");
		this.tfPayslip.setText("");
		this.tfWorkingD.setText("");
	}

}
