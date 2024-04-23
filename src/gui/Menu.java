package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import data.User;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.List;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import java.awt.Choice;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.Dimension;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Menu(User currentUser) {
		setPreferredSize(new Dimension(600, 600));
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Hoşgeldin " + currentUser.getName() + "!");
		panel.add(lblNewLabel, BorderLayout.WEST);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Mevcut Bakiyeniz " + currentUser.getTotalMoney() + "₺");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel_1, BorderLayout.EAST);
	}
}
