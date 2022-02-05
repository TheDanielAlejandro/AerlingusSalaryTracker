package com.view;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalculatorView {

	private static CalculatorView instance;
	private JFrame frame;
	private JButton btnCalculateM;
	private JTextField tfActual;
	private JComboBox cbStartMonth;
	private JComboBox cbStartYear;
	private JComboBox cbEndMonth;
	private JComboBox cbEndYear;
	private JTextField tfExpected;


	/**
	 * Create the application.
	 */
	private CalculatorView() {
		initialize();
	}

	public static CalculatorView getInstance() {
		if(instance==null) {
			instance = new CalculatorView();
		}
		return instance;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(241,241,241));
		frame.setBounds(100, 100, 514, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		cbStartMonth = new JComboBox();
		cbStartMonth.setBounds(82, 25, 60, 36);
		frame.getContentPane().add(cbStartMonth);
		
		cbStartYear = new JComboBox();
		cbStartYear.setBounds(152, 25, 60, 36);
		frame.getContentPane().add(cbStartYear);
	
	    cbEndMonth = new JComboBox();
		cbEndMonth.setBounds(291, 25, 60, 36);
		frame.getContentPane().add(cbEndMonth);
		
		cbEndYear = new JComboBox();
		cbEndYear.setBounds(361, 25, 60, 36);
		frame.getContentPane().add(cbEndYear);
		
		
		btnCalculateM = new JButton("Calculate");
		btnCalculateM.setForeground(new Color(255, 255, 255));
		btnCalculateM.setBackground(new Color(0, 128, 128));
		btnCalculateM.setBounds(181, 133, 130, 36);
		frame.getContentPane().add(btnCalculateM);
		
		tfActual = new JTextField();
		tfActual.setBackground(Color.WHITE);
		tfActual.setHorizontalAlignment(SwingConstants.CENTER);
		tfActual.setEditable(false);
		tfActual.setBounds(291, 72, 130, 36);
		frame.getContentPane().add(tfActual);
		tfActual.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("From : ");
		lblNewLabel.setBounds(26, 36, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("To : ");
		lblNewLabel_1.setBounds(246, 36, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		ImageIcon aircraft = new ImageIcon(getClass().getClassLoader().getResource("Aircraft.png"));
		
		JLabel lblAircraft = new JLabel("");
		lblAircraft.setBounds(0, 180, 501, 279);
		lblAircraft.setIcon(aircraft);
		frame.getContentPane().add(lblAircraft);
		
		tfExpected = new JTextField();
		tfExpected.setBackground(Color.WHITE);
		tfExpected.setForeground(Color.BLACK);
		tfExpected.setEditable(false);
		tfExpected.setHorizontalAlignment(SwingConstants.CENTER);
		tfExpected.setColumns(10);
		tfExpected.setBounds(82, 72, 130, 36);
		frame.getContentPane().add(tfExpected);
		
		JLabel lblNewLabel_2 = new JLabel("Expected :");
		lblNewLabel_2.setBounds(10, 83, 62, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Actual :");
		lblNewLabel_3.setBounds(235, 83, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
	
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBtnCalculateM() {
		return btnCalculateM;
	}

	public JTextField getTfActual() {
		return tfActual;
	}
	
	public JTextField getTfExpected() {
		return tfExpected;
	}

	public JComboBox getCbStartMonth() {
		return cbStartMonth;
	}

	public JComboBox getCbStartYear() {
		return cbStartYear;
	}

	public JComboBox getCbEndMonth() {
		return cbEndMonth;
	}

	public JComboBox getCbEndYear() {
		return cbEndYear;
	}

	public void setCbStartMonth(JComboBox cbStartMonth) {
		this.cbStartMonth = cbStartMonth;
	}
}
