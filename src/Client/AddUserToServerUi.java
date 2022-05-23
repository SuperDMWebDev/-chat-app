package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddUserToServerUi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddUserToServerUi frame = new AddUserToServerUi();
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
	public ServerData readToChooseServer(String ipServer,int portServer)
	{
		List<ServerData> listServer=ServerData.getAllServer();
		ServerData findServer=null;
		if(listServer!=null)
		{
			for(ServerData server:listServer)
			{
				if(server.getIp().equals(ipServer)&& server.getPort()==portServer)
				{
					findServer=server;
					break;
				}
			}
		}
		return findServer;
	}
	public AddUserToServerUi(final Controller controller, String serverName, String ipServer, int portServer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server Name : ");
		lblNewLabel.setBounds(35, 30, 97, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblPleaseInputYour = new JLabel("Please input your name: ");
		lblPleaseInputYour.setBounds(35, 76, 173, 27);
		contentPane.add(lblPleaseInputYour);
		
		JTextArea txtrNoteYourName = new JTextArea();
		txtrNoteYourName.setText("Note: Your name will be shown in server\n for other people");
		txtrNoteYourName.setBounds(34, 179, 317, 49);
		contentPane.add(txtrNoteYourName);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(230, 36, 115, 14);
		lblNewLabel_1.setText(serverName);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(233, 79, 118, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("JOIN NOW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Main.socketController = new SocketController()
				String userName=textField.getText();
				if(userName!="")
				{
					ServerData findServer=readToChooseServer(ipServer,portServer);
					Main.socketController = new SocketController(userName,findServer);
					Main.socketController.createNewUser();
					controller.openClientChatUi(userName);
					controller.closeAddUserToServerUi();
				}
				
			}
		});
		btnNewButton.setBounds(158, 130, 135, 23);
		contentPane.add(btnNewButton);
	}
}
