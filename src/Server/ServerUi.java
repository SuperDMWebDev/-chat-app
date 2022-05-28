package Server;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


// them serverName
public class ServerUi extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private Boolean isOpen = false;
	
	public static JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ServerUi frame = new ServerUi();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 **/
	public void updateClientTable()
	{
		Object[][] tableContent = new Object[Main.socketController.client.size()][2];
		for (int i = 0; i < Main.socketController.client.size(); i++) {
			tableContent[i][0] = Main.socketController.client.get(i).getUserName();
			tableContent[i][1] = Main.socketController.client.get(i).getPort();
		}
		table.setModel(new DefaultTableModel(tableContent,new String[] {"Client name","Client port"}));
	}
	
	public ServerUi(final Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP :");
		lblNewLabel.setBounds(38, 37, 41, 23);
		contentPane.add(lblNewLabel);
		
		JLabel textField = new JLabel();
		textField.setBounds(99, 40, 93, 20);
		String IpReceive = SocketController.getServerIp();
		textField.setText(IpReceive);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Port");
		lblNewLabel_1.setBounds(231, 37, 56, 23);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(282, 38, 113, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		// khong can serverName
//		JLabel lblNewLabel_2 = new JLabel("Server name");
//		lblNewLabel_2.setBounds(86, 91, 106, 23);
//		contentPane.add(lblNewLabel_2);
//		
//		textField_2 = new JTextField();
//		textField_2.setBounds(209, 92, 134, 20);
//		contentPane.add(textField_2);
//		textField_2.setColumns(10);
		
		String [] RowName= {"Client name", "Client port"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Client Name");
		dtm.addColumn("Client Port");
		table = new JTable(dtm);
		table.revalidate();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(38, 150, 2, 2);
		scrollPane.setSize(244,100);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Open Server");
		btnNewButton.setBounds(306, 147, 118, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("List connected client");
		lblNewLabel_3.setBounds(38, 125, 128, 14);
		contentPane.add(lblNewLabel_3);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isOpen==false)
				{					
					String text1=textField_1.getText();
//					String text2=textField_2.getText();
					if(text1!="")
					{
//						JOptionPane.showMessageDialog(this,"Please fill all blanks",JOptionPane.WARNING_MESSAGE);
						int portFinal = Integer.parseInt(text1); 
						SocketController.OpenSocket(portFinal);
						btnNewButton.setText("Close Server");
						isOpen=true;
				
						
					}
				}
				else {
					btnNewButton.setText("Open Server");
					isOpen=false;
					SocketController.closeSocket();
				}
			}
		});

	
	}
}
