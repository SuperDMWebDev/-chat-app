package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddServerUi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddServerUi frame = new AddServerUi();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AddServerUi(final Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server name : ");
		lblNewLabel.setBounds(47, 41, 122, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblIpServer = new JLabel("Ip Server :");
		lblIpServer.setBounds(47, 81, 107, 29);
		contentPane.add(lblIpServer);
		
		JLabel lblNewLabel_1 = new JLabel("Port server :");
		lblNewLabel_1.setBounds(44, 121, 125, 34);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(179, 128, 169, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 85, 169, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(179, 45, 169, 20);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Add Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text1=textField_2.getText();
				String text2=textField_1.getText();
				String text3=textField.getText();
				if(text1!=""&&text2!=""&&text3!="")
				{
					try {
						FileWriter file = new FileWriter(new File("configServer.txt"),true);
						String textWrite=text1+","+text2+","+text3;
						file.write(textWrite);
						file.close();
						controller.closeAddServerUi();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnNewButton.setBounds(148, 176, 116, 23);
		contentPane.add(btnNewButton);
	}
}
