package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class EditServerUi extends JFrame {

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
//					EditServerUi frame = new EditServerUi();
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
	// format port chi nhap duoc so

	public EditServerUi(final Controller controller,int rowSelected,String serverName,String ipServer,int portServer) {
		String portString=Integer.toString(portServer);
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
		
		textField_2 = new JTextField();
		textField_2.setBounds(179, 128, 169, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(portString);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(ipServer);
		textField_1.setBounds(179, 85, 169, 20);
		contentPane.add(textField_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(179, 45, 169, 20);
		textField.setText(serverName);
		contentPane.add(textField);
		
		JButton btnNewButton = new JButton("Edit Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text1=textField.getText();
				String text2=textField_1.getText();
				String text3=textField_2.getText();
				if(text1!=""&&text2!=""&&text3!="")
				{
	
					try {
						List<ServerData> listServer=ServerData.getAllServer();
						FileWriter file = new FileWriter(new File("configServer.txt"));
						
							for(int i=0;i<listServer.size();i++)
							{
								if(i!=rowSelected)
								{
									ServerData data=listServer.get(i);
									if(data!=null)
									{
										file.write(data.getServerName()+","+data.getIp()+","+data.getPort()+"\n");
									}
									
								}
								else {
									file.write(text1+","+text2+","+text3+"\n");
								}
							}
						controller.closeEditServerUi();
						file.close();
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
