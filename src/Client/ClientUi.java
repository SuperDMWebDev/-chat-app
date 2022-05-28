package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientUi extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClientUi frame = new ClientUi();
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
	public void readFile(DefaultTableModel dtm)
	{
		List<ServerData> listServer=ServerData.getAllServer();
		for(ServerData serverData: listServer)
		{
			boolean condition = ConnectionController.getServerOnline(serverData.getIp(), serverData.getPort());
			if(condition==true)
			{
				serverData.setStatus("Working");
			}
		}
		dtm.setRowCount(0);
		if(listServer!=null)
		{
			for(ServerData server:listServer)
			{
				
				dtm.addRow(new Object[] {server.getServerName(),server.getIp(),server.getPort(),server.getStatus(),server.getConnect()});
			}
		}
	}
	public ClientUi(final Controller controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Server name");
		dtm.addColumn("IP Server");
		dtm.addColumn("Port Server");
		dtm.addColumn("Status");
		dtm.addColumn("Users Online");
		JTable table = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder("List server to connect"));
		scrollPane.setBounds(10, 11, 426, 200);
		scrollPane.setSize(500,200);
		contentPane.add(scrollPane);
		
		
		
		readFile(dtm);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openAddServerUi();
			}
		});
		btnNewButton.setActionCommand("Add");
		btnNewButton.setBounds(554, 23, 106, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row=table.getSelectedRow();
				
				if(row!=-1)
				{
					// port co the la sring
					String serverNameSelected=table.getModel().getValueAt(row,0).toString();
					String ipSelected=table.getModel().getValueAt(row,1).toString();
					String portSelected=table.getModel().getValueAt(row, 2).toString();
					int portIntegerSelected=Integer.parseInt(portSelected);
				
					controller.openEditServerUi(row,serverNameSelected, ipSelected, portIntegerSelected);
					
					
				}
				
			}
		});
		btnNewButton_1.setActionCommand("Edit");
		btnNewButton_1.setBounds(554, 57, 106, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remove");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row!=-1)
				{
					try {
						List<ServerData> listServer=ServerData.getAllServer();
						FileWriter file = new FileWriter(new File("configServer.txt"));
						
						for(int i=0;i<listServer.size();i++)
						{
							if(i!=row)
							{
								ServerData data=listServer.get(i);
								if(data!=null)
								{
									file.write(data.getServerName()+","+data.getIp()+","+data.getPort()+"\n");
								}
								
							}
							
						}
						file.close();
						dtm.setRowCount(0);
						readFile(dtm);
					}catch(IOException e1)
					{
						e1.printStackTrace();
					}
				
				}
			}
		});
		btnNewButton_2.setActionCommand("Remove");
		btnNewButton_2.setBounds(554, 91, 106, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readFile(dtm);
			}
		});
		btnNewButton_3.setActionCommand("Remove");
		btnNewButton_3.setBounds(675, 23, 106, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Join Server");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row!=-1)
				{
					String serverName=table.getModel().getValueAt(row, 0).toString();
					String ipServer=table.getModel().getValueAt(row, 1).toString();
					int portServer=Integer.parseInt(table.getModel().getValueAt(row, 2).toString());
					String status=table.getModel().getValueAt(row,3).toString();
					if(status=="Working")
					{						
						controller.openAddUserToServerUi(serverName,ipServer,portServer);
						controller.closeClientUi();
					}
					
				}
			}
		});
		btnNewButton_4.setBounds(219, 213, 113, 23);
		contentPane.add(btnNewButton_4);
		
		
	}
}
