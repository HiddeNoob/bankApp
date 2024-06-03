package gui;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class TwoInputPanel extends JDialog {
	private JTextField inputFirstRow;
	private JTextField inputSecondRow;
	private int clickedButtonId = -1;

	private JButton leftButton;
	private JButton rightButton;
	
	TwoInputPanel(String title,String row1,String row2,String leftButtonText,String rightButtonText){
		setSize(new Dimension(399, 272));
		setPreferredSize(new Dimension(335, 235));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.SOUTH);
		
		leftButton = new JButton(leftButtonText);

		panel_4.add(leftButton);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_4.add(horizontalGlue);
		
		rightButton = new JButton(rightButtonText);

		panel_4.add(rightButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{178, 161, 0};
		gbl_panel_1.rowHeights = new int[]{67, 25, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel textFirstRow = new JLabel(row1);
		textFirstRow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFirstRow.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textFirstRow = new GridBagConstraints();
		gbc_textFirstRow.insets = new Insets(0, 0, 5, 5);
		gbc_textFirstRow.gridx = 0;
		gbc_textFirstRow.gridy = 0;
		panel_1.add(textFirstRow, gbc_textFirstRow);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10,10,10,10));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		panel_1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{161, 0};
		gbl_panel.rowHeights = new int[]{50, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		inputFirstRow = new JTextField();
		GridBagConstraints gbc_inputFirstRow = new GridBagConstraints();
		gbc_inputFirstRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputFirstRow.gridx = 0;
		gbc_inputFirstRow.gridy = 0;
		panel.add(inputFirstRow, gbc_inputFirstRow);
		inputFirstRow.setColumns(4);
		
		JLabel textSecondRow = new JLabel(row2);
		GridBagConstraints gbc_textSecondRow = new GridBagConstraints();
		gbc_textSecondRow.insets = new Insets(0, 0, 0, 5);
		gbc_textSecondRow.gridx = 0;
		gbc_textSecondRow.gridy = 1;
		panel_1.add(textSecondRow, gbc_textSecondRow);
		textSecondRow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(10,10,10,10));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 1;
		panel_1.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{161, 0};
		gbl_panel_3.rowHeights = new int[]{25, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		inputSecondRow = new JTextField();
		GridBagConstraints gbc_inputSecondRow = new GridBagConstraints();
		gbc_inputSecondRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputSecondRow.gridx = 0;
		gbc_inputSecondRow.gridy = 0;
		panel_3.add(inputSecondRow, gbc_inputSecondRow);
		inputSecondRow.setColumns(8);
		
		JLabel textTitle = new JLabel(title);
		textTitle.setMaximumSize(new Dimension(52, 50));
		textTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textTitle.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(textTitle, BorderLayout.NORTH);
		setVisible(true);
	}

	public String getFirstRowText() {
		return inputFirstRow.getText();
	}
	
	public String getSecondRowText() {
		return inputSecondRow.getText();
	}
	
	public int getClickedButtonId() {
		return this.clickedButtonId;
	}

	public void setLeftButtonFunction(ActionListener a) {

		leftButton.addActionListener(a);
	}
	
	public void setRightButtonFunction(ActionListener a) {

		rightButton.addActionListener(a);
	}
}
