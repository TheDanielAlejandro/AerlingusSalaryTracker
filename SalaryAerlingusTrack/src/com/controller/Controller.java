package com.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import com.model.*;
import com.view.*;

public class Controller {

	private Model model;
	private final SalaryCalculatorView S_C_VIEW;
	private InsertRowView insRowView;
	private EditRowView editRowView;
	private CalculatorView calculatorView;
	private static Controller instance;

	
	private Controller() {
		model = Model.getInstance();
		S_C_VIEW = SalaryCalculatorView.getInstance();
		insRowView = InsertRowView.getIntance();
		editRowView = EditRowView.getInstance();
		calculatorView = CalculatorView.getInstance();
		initializeInsRowView();
		initializeSCView();
		initializeEditRowView();
		initializeCalculatorView();
		model.createConnection();
		S_C_VIEW.getBtnLoadFromDB().doClick();
		
	}
	
	
	/**
	 * Method that retrieves the instance of the Controller if existent if not, it creates it.
	 * @return instance Controller
	 */
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	private void initializeSCView() {
		
		S_C_VIEW.getBtnInsertRow().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insRowView.clearAllTextFields();
				
				insRowView.getCbPayG().setModel(new DefaultComboBoxModel<>(model.getDays()));
				insRowView.getCbStartG().setModel(new DefaultComboBoxModel<>(model.getDays()));
				insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.getDays()));
				
				insRowView.getCbPayM().setModel(new DefaultComboBoxModel<>(model.getMonths()));
				insRowView.getCbStartM().setModel(new DefaultComboBoxModel<>(model.getMonths()));
				insRowView.getCbEndM().setModel(new DefaultComboBoxModel<>(model.getMonths()));
				
				insRowView.getCbPayY().setModel(new DefaultComboBoxModel<>(model.getYears()));
				insRowView.getCbStartY().setModel(new DefaultComboBoxModel<>(model.getYears()));
				insRowView.getCbEndY().setModel(new DefaultComboBoxModel<>(model.getYears()));
				
				insRowView.getFrmInsertRow().setVisible(true);
				}
		
		});
		
		S_C_VIEW.getBtnLoadFromDB().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				((DefaultTableModel) S_C_VIEW.getTable().getModel()).setRowCount(0);
				
				try {
					Statement stmt = model.getCon().createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM PROVA");
					if(!rs.next()) {
						insRowView.getError().showMessageDialog(insRowView.getCbEndG(), "The database is empty, to start working insert some data!");
					}else {
						do {
							Integer payslip = rs.getInt("Payslip");
							Date payday = rs.getDate("Payday");
							Date startingDate = rs.getDate("StartingDate");
							Date endingDate = rs.getDate("EndingDate");
							Integer daysWorked = rs.getInt("DaysWorked");
							Double dailyPay = rs.getDouble("DailyPay");
							Double expectedSalary = rs.getDouble("ExpectedSalary");
							Double actualSalary = rs.getDouble("ActualSalary");
							((DefaultTableModel)S_C_VIEW.getTable().getModel()).addRow(new Object[] {payslip, payday.toString(), startingDate.toString(), endingDate.toString(), daysWorked, dailyPay, expectedSalary, actualSalary});
						}while(rs.next());
					}

					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		S_C_VIEW.getBtnEditRow().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					int row = S_C_VIEW.getTable().getSelectedRow();
					if(row == -1) {
						insRowView.getError().showMessageDialog(insRowView.getCbEndG(), "You have to select first a row to edit!");
					}else {
						String payslip = S_C_VIEW.getTable().getValueAt(row, 0).toString();
						String payday = S_C_VIEW.getTable().getValueAt(row, 1).toString();
						String startingD = S_C_VIEW.getTable().getValueAt(row, 2).toString();
						String endingD = S_C_VIEW.getTable().getValueAt(row, 3).toString();
						String daysW = S_C_VIEW.getTable().getValueAt(row, 4).toString();
						String dailyP = S_C_VIEW.getTable().getValueAt(row, 5).toString();
						String expectedS = S_C_VIEW.getTable().getValueAt(row, 6).toString();
						String actualS = S_C_VIEW.getTable().getValueAt(row, 7).toString();
						
						editRowView.setTextFields(payslip, payday, startingD, endingD, daysW, dailyP, expectedS, actualS);
						editRowView.getFrame().setVisible(true);
						
					}
			}
		});
		
		S_C_VIEW.getBtnDeleteRow().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int row = S_C_VIEW.getTable().getSelectedRow();
					if(row == -1) {
						insRowView.getError().showMessageDialog(insRowView.getCbEndG(), "You have to select first a row to delete!");
					}else {
						String paySlip = S_C_VIEW.getTable().getValueAt(row, 0).toString();
						
						String query = "DELETE FROM PROVA WHERE PAYSLIP=?";
						PreparedStatement stmt = model.getCon().prepareStatement(query);
						stmt.setString(1, paySlip);
						stmt.executeUpdate();
						System.out.println("Removed!");
						stmt.close();
						
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				S_C_VIEW.getBtnLoadFromDB().doClick();
			}
				
		});
	
		S_C_VIEW.getBtnCalculator().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				calculatorView.getFrame().setVisible(true);
				
				calculatorView.getCbStartYear().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(model.getMinYear(), model.getMaxYear())));
				calculatorView.getCbEndYear().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(model.getMinYear(), model.getMaxYear())));
				
				calculatorView.getCbStartMonth().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(model.getMinMonth((Integer)calculatorView.getCbStartYear().getSelectedItem()), model.getMaxMonth((Integer)calculatorView.getCbStartYear().getSelectedItem()))));
				calculatorView.getCbEndMonth().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(model.getMinMonth((Integer)calculatorView.getCbEndYear().getSelectedItem()), model.getMaxMonth((Integer)calculatorView.getCbEndYear().getSelectedItem()))));
				
			}
		});
		
	}
	
	private void initializeInsRowView() {
		
		insRowView.getCbPayM().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				Integer daySelected = (Integer) insRowView.getCbPayG().getSelectedItem();
				
				switch((Integer)insRowView.getCbPayM().getSelectedItem()) {
					case 2:
						if((Integer)insRowView.getCbPayY().getSelectedItem() %4 == 0) {
							insRowView.getCbPayG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(29)));
							if(daySelected<= 29)insRowView.getCbPayG().setSelectedItem(daySelected);
						}else {
							insRowView.getCbPayG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(28)));
							if(daySelected<= 28)insRowView.getCbPayG().setSelectedItem(daySelected);
						}
						break;
					case 4:
						insRowView.getCbPayG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbPayG().setSelectedItem(daySelected);
						break;
					case 6:
						insRowView.getCbPayG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbPayG().setSelectedItem(daySelected);
						break;
					case 9:
						insRowView.getCbPayG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbPayG().setSelectedItem(daySelected);
						break;
					case 11:
						insRowView.getCbPayG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbPayG().setSelectedItem(daySelected);
						break;
					default:
						insRowView.getCbPayG().setModel(new DefaultComboBoxModel<>(insRowView.getDays()));
						insRowView.getCbPayG().setSelectedItem(daySelected);
						System.out.println(daySelected);
						break;
				}

			}
		});
		
		
		insRowView.getCbStartG().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if((Integer)insRowView.getCbEndM().getSelectedItem() == (Integer)insRowView.getCbStartM().getSelectedItem() && (Integer)insRowView.getCbEndY().getSelectedItem() == (Integer)insRowView.getCbStartY().getSelectedItem()) {
					
					switch((Integer)insRowView.getCbStartM().getSelectedItem()) {
						case 2:
							if((Integer)insRowView.getCbEndY().getSelectedItem() %4 == 0) {
								insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((Integer)insRowView.getCbStartG().getSelectedItem(),29)));
							}else {
								insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((Integer)insRowView.getCbStartG().getSelectedItem(),28)));
							}
							break;
						case 4:
							insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((Integer)insRowView.getCbStartG().getSelectedItem(),30)));
							break;
						case 6:
							insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((Integer)insRowView.getCbStartG().getSelectedItem(),30)));
							break;
						case 9:
							insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((Integer)insRowView.getCbStartG().getSelectedItem(),30)));
							break;
						case 11:
							insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((Integer)insRowView.getCbStartG().getSelectedItem(),30)));
							break;
						default:
							insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((Integer)insRowView.getCbStartG().getSelectedItem(),31)));
							break;
					}
				}
			}
		});
		
		
		
		
		insRowView.getCbStartM().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Integer daySelected = (Integer) insRowView.getCbStartG().getSelectedItem();
				
				if((Integer)insRowView.getCbEndM().getSelectedItem() < (Integer)insRowView.getCbStartM().getSelectedItem() && (Integer)insRowView.getCbEndY().getSelectedItem() == (Integer)insRowView.getCbStartY().getSelectedItem()) {
					insRowView.getCbEndM().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((Integer)insRowView.getCbStartM().getSelectedItem(),12)));
				}
				
				switch((Integer)insRowView.getCbStartM().getSelectedItem()) {
					case 2:
						if((Integer)insRowView.getCbStartY().getSelectedItem() %4 == 0) {
							insRowView.getCbStartG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(29)));
							if(daySelected<= 29)insRowView.getCbStartG().setSelectedItem(daySelected);
						}else {
							insRowView.getCbStartG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(28)));
							if(daySelected<= 28)insRowView.getCbStartG().setSelectedItem(daySelected);
						}
						break;
					case 4:
						insRowView.getCbStartG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbStartG().setSelectedItem(daySelected);
						break;
					case 6:
						insRowView.getCbStartG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbStartG().setSelectedItem(daySelected);
						break;
					case 9:
						insRowView.getCbStartG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbStartG().setSelectedItem(daySelected);
						break;
					case 11:
						insRowView.getCbStartG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbStartG().setSelectedItem(daySelected);
						break;
					default:
						insRowView.getCbStartG().setModel(new DefaultComboBoxModel<>(insRowView.getDays()));
						insRowView.getCbStartG().setSelectedItem(daySelected);
						break;
				}
			}
		});
		
		insRowView.getCbEndM().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Integer daySelected = (Integer) insRowView.getCbEndG().getSelectedItem();
				
				switch((Integer)insRowView.getCbEndM().getSelectedItem()) {
					case 2:
						if((Integer)insRowView.getCbEndY().getSelectedItem() %4 == 0) {
							insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(29)));
							if(daySelected<= 29)insRowView.getCbEndG().setSelectedItem(daySelected);
						}else {
							insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(28)));
							if(daySelected<= 28)insRowView.getCbEndG().setSelectedItem(daySelected);
						}
						break;
					case 4:
						insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbEndG().setSelectedItem(daySelected);
						break;
					case 6:
						insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbEndG().setSelectedItem(daySelected);
						break;
					case 9: 
						insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbEndG().setSelectedItem(daySelected);
						break;
					case 11:
						insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers(30)));
						if(daySelected<= 30)insRowView.getCbEndG().setSelectedItem(daySelected);
						break;
					default:
						insRowView.getCbEndG().setModel(new DefaultComboBoxModel<>(insRowView.getDays()));
						insRowView.getCbEndG().setSelectedItem(daySelected);
						break;
				}
			}
		});
		
		
		//NB there will be a problem if there is a facturing period that starts in a a year and end in another
		//for example last week of december.
		
		
		insRowView.getCbPayY().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				insRowView.getCbStartY().setSelectedItem(insRowView.getCbPayY().getSelectedItem());
				insRowView.getCbEndY().setSelectedItem(insRowView.getCbPayY().getSelectedItem());
			}
		});
		
		insRowView.getCbStartY().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				insRowView.getCbPayY().setSelectedItem(insRowView.getCbStartY().getSelectedItem());
				insRowView.getCbEndY().setSelectedItem(insRowView.getCbStartY().getSelectedItem());
			}
		});
		
		insRowView.getCbEndY().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				insRowView.getCbPayY().setSelectedItem(insRowView.getCbEndY().getSelectedItem());
				insRowView.getCbStartY().setSelectedItem(insRowView.getCbEndY().getSelectedItem());
				
				
			}
		});
		
		
		insRowView.getBtnSubmit().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				int numCols = S_C_VIEW.getTable().getModel().getColumnCount();

				try {
					Integer payslip = Integer.parseInt(insRowView.getTfPayslip().getText());
					
					String dayPay = model.dateBuilder(insRowView.getCbPayG().getSelectedItem().toString());
					String monthPay = model.dateBuilder(insRowView.getCbPayM().getSelectedItem().toString());
					String yearPay = insRowView.getCbPayY().getSelectedItem().toString();
					String datePay = yearPay + "-" + monthPay + "-" + dayPay; 
					
					String dayStart = model.dateBuilder(insRowView.getCbStartG().getSelectedItem().toString());
					String monthStart = model.dateBuilder(insRowView.getCbStartM().getSelectedItem().toString());
					String yearStart = insRowView.getCbStartY().getSelectedItem().toString();
					String dateStart = yearStart +"-" + monthStart + "-" + dayStart;
					
					String dayEnd = model.dateBuilder(insRowView.getCbEndG().getSelectedItem().toString());
					String monthEnd = model.dateBuilder(insRowView.getCbEndM().getSelectedItem().toString());
					String yearEnd = insRowView.getCbEndY().getSelectedItem().toString();
					String dateEnd = yearEnd + "-" + monthEnd + "-" + dayEnd;
					
					Integer daysWorked = Integer.parseInt(insRowView.getTfWorkingD().getText());
					
					Double dailyPay = Double.parseDouble(insRowView.getTfDailyP().getText());
					
					Double expectedSalary = Double.parseDouble(insRowView.getTfDailyP().getText()) * Integer.parseInt(insRowView.getTfWorkingD().getText());
					
					Double actualSalary = Double.parseDouble(insRowView.getTfActualS().getText()); 
					
					PreparedStatement stmt = model.getCon().prepareStatement("INSERT INTO PROVA VALUES(?,?,?,?,?,?,?,?)");
					stmt.setInt(1, payslip);
					stmt.setDate(2, Date.valueOf(datePay));
					stmt.setDate(3, Date.valueOf(dateStart));
					stmt.setDate(4, Date.valueOf(dateEnd));
					stmt.setInt(5, daysWorked);
					stmt.setDouble(6, dailyPay);
					stmt.setDouble(7, expectedSalary);
					stmt.setDouble(8, actualSalary);
					
					stmt.execute();
					stmt.close();
					
				}catch(SQLException | NumberFormatException ex) {
					
					insRowView.getError().showMessageDialog(insRowView.getCbEndG(), "All the boxes needs to be filled and with the right values!");
					//ex.printStackTrace();
				}
						
				S_C_VIEW.getBtnLoadFromDB().doClick();
			}
		});
	}
	
	private void initializeEditRowView() {

		editRowView.getBtnUpdate().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Integer payslip = Integer.parseInt(editRowView.getTfPayslip().getText());
				String datePay = editRowView.getTfPayday().getText();
				String dateStart = editRowView.getTfStartD().getText();
				String dateEnd = editRowView.getTfEndD().getText();
				Integer daysWorked = Integer.parseInt(editRowView.getTfDaysWorked().getText());
				Double dailyPay = Double.parseDouble(editRowView.getTfDailyP().getText());
				Double expectedS = Double.parseDouble(editRowView.getTfExpectedS().getText());
				Double actualS = Double.parseDouble(editRowView.getTfActualS().getText());
				
				try {
					String query = "UPDATE PROVA SET PAYDAY=?, STARTINGDATE=?, ENDINGDATE=?, DAYSWORKED=?, DAILYPAY=?, EXPECTEDSALARY=?, ACTUALSALARY=? WHERE PAYSLIP=?";
					PreparedStatement stmt = model.getCon().prepareStatement(query);
					stmt.setDate(1, Date.valueOf(datePay));
					stmt.setDate(2, Date.valueOf(dateStart));
					stmt.setDate(3, Date.valueOf(dateEnd));
					stmt.setInt(4, daysWorked);
					stmt.setDouble(5, dailyPay);
					stmt.setDouble(6, expectedS);
					stmt.setDouble(7, actualS);
					stmt.setInt(8, payslip);
					stmt.executeUpdate();
					System.out.println("Update Completed!");
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				S_C_VIEW.getBtnLoadFromDB().doClick();
			}
		});
		
		
	}
	
	
	private void initializeCalculatorView() {
		
		calculatorView.getCbStartMonth().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calculatorView.getCbEndMonth().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((int) calculatorView.getCbStartMonth().getSelectedItem(), model.getMaxMonth((Integer)calculatorView.getCbEndYear().getSelectedItem()))));
			}
		});
		
		calculatorView.getCbStartYear().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calculatorView.getCbEndYear().setModel(new DefaultComboBoxModel<>(model.buildArrayOfIntegers((int) calculatorView.getCbStartYear().getSelectedItem(), model.getMaxYear())));
				
			}
		});
			
		calculatorView.getBtnCalculateM().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String start = calculatorView.getCbStartYear().getSelectedItem().toString() + "-" + calculatorView.getCbStartMonth().getSelectedItem().toString() + "-1";
				String end;
				
				switch((int)calculatorView.getCbEndMonth().getSelectedItem()) {
				
					case 2:
						if((int)calculatorView.getCbEndYear().getSelectedItem()%4 == 0) {
							end = calculatorView.getCbEndYear().getSelectedItem().toString() + "-" + calculatorView.getCbEndMonth().getSelectedItem().toString() + "-29";
						}else {
							end = calculatorView.getCbEndYear().getSelectedItem().toString() + "-" + calculatorView.getCbEndMonth().getSelectedItem().toString() + "-28";
						}
						break;
					case 4:
						end = calculatorView.getCbEndYear().getSelectedItem().toString() + "-" + calculatorView.getCbEndMonth().getSelectedItem().toString() + "-30";
						break;
					case 6:
						end = calculatorView.getCbEndYear().getSelectedItem().toString() + "-" + calculatorView.getCbEndMonth().getSelectedItem().toString() + "-30";
						break;
					case 9:
						end = calculatorView.getCbEndYear().getSelectedItem().toString() + "-" + calculatorView.getCbEndMonth().getSelectedItem().toString() + "-30";
						break;
					case 11:
						end = calculatorView.getCbEndYear().getSelectedItem().toString() + "-" + calculatorView.getCbEndMonth().getSelectedItem().toString() + "-30";
						break;
					default:
						end = calculatorView.getCbEndYear().getSelectedItem().toString() + "-" + calculatorView.getCbEndMonth().getSelectedItem().toString() + "-31";
						break;
	
				}
				
				calculatorView.getTfExpected().setText(model.getExpectedSalary(start, end).toString());
				calculatorView.getTfActual().setText(model.getActualSalary(start, end).toString());
			
			}
		});
		
				
	}

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller c1 = new Controller();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
}
	//insRowView.getError().showMessageDialog(insRowView.getCbEndG(), "The ending month can't be earlier than the starting month for the same year!");