package containers;

import java.sql.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class BillSystemFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JLabel lblCustomerid;
	private JButton btnSubmit;
	private JTextField txtCusContactNo;
	private JLabel lblCustomerId_1;
	private JLabel lblVegetable;
	private JComboBox<Object> comboBox;
	private JTextField textField;
	private JLabel lblFruits;
	private JComboBox<Object> comboBox_1;
	private JTextField textField_1;
	private JComboBox<Object> comboBox_2;
	private JLabel lblPulsesAndSpices;
	private JTextField textField_2;
	private JLabel lblStationary;
	private JComboBox<?> comboBox_3;
	private JTextField textField_3;
	private JLabel lblOthers;
	private JTextField textField_4;
	private JComboBox<?> comboBox_4;
	private JButton btnReset;
	private JLabel lblBillID;
	private Timestamp timestamp;
	private Connection con;
	private JButton btnAddVegetable;
	private JButton btnAddFruit;
	private JButton btnAddItem;
	private JButton btnAddItem_1;
	private JButton btnAddItem_2;
	private long timeI;
	private JButton btnTotal;
	private JTextField textField_5;
	private JButton btnSubmit_1;
	private long Cus_ID;
	private float iprice = 0;
	private float iQua = 0;
	private float iprice_1 = 0;
	private float iQua_1 = 0;
	private float iprice_2 = 0;
	private float iQua_2 = 0;
	private float iprice_3 = 0;
	private float iQua_3 = 0;
	private float iprice_4 = 0;
	private float iQua_4 = 0;
	private float gTotal = 0;
	private JLabel lblGrandTotal;
	private JScrollPane scrollPane;
	private JTable table;
	private JTable table_1;
	private JButton btnGloceryTransactionDetails;
	private JButton btnSearchCustomer;
	private JLabel lblCusconformation;
	private JLabel lblSubmitedToBill;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillSystemFrame frame = new BillSystemFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	    // @throws Exception 
	public BillSystemFrame() throws Exception, ParseException
	{
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3305/BillingSystem?user=root&password=student");
		setTitle("Billing System");
		addComponents();
		addEvents();
			
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////       Method for EventListener.  //////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addEvents() throws Exception 
    {
    	btnSubmit.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent arg0) {
    			
    			lblCustomerid.setText("Customer ID : "+ txtCusContactNo.getText());
      			lblCustomerid.setVisible(true);
      			lblCustomerId_1.setText("Customer ID : "+ txtCusContactNo.getText());
      			lblCustomerId_1.setVisible(true);
      			
      	        timestamp = new Timestamp(System.currentTimeMillis());
                timeI =  timestamp.getTime();
      	        lblBillID.setText("Bill ID : "+ timestamp.getTime());
    			lblBillID.setVisible(true);
    			try 
				{
					long Cus_ID_1 = Long.parseLong(txtCusContactNo.getText());
					String sql = "SELECT * FROM bill where Cus_ID = '"+ Cus_ID_1 +"'";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						long Cus_ID_2 = Long.parseLong(txtCusContactNo.getText());
		        		PreparedStatement pstmt_21 = null;
		        		String C_Name = null;
						try {
							String sql_1 = " SELECT Cus_Name FROM customer WHERE Cus_ID = '" + Cus_ID_2 + "' ";
							pstmt_21 = con.prepareStatement(sql_1);
							ResultSet rs_11 = pstmt_21.executeQuery();
							if (rs_11.next()) {
								C_Name = rs_11.getString("Cus_Name");
							}
							lblCusconformation.setText("Our Company Welcome You "+ C_Name);
			      			lblCusconformation.setVisible(true);						
			      			} catch ( Exception e ){ System.out.println(e);}
					} else {
						try
		    			{
		                    String query_1 = "insert into Customer values( ?, ?, ?)";
		    				PreparedStatement pstmt_20 = con.prepareStatement(query_1);				
		    				Cus_ID = Long.parseLong(txtCusContactNo.getText());
		    				String Cus_Name = (String)txtFirstName.getText() +" "+(String)txtLastName.getText();
		    				pstmt_20.setLong(1, Cus_ID);  
		    				pstmt_20.setString(2, Cus_Name);
		    				pstmt_20.setString(3, null);
		    				int i = pstmt_20.executeUpdate();
		    				System.out.println(i +" records inserted");
		    				lblCusconformation.setText("Welcome! To Our Company First ");
			      			lblCusconformation.setVisible(true);
		    				}
		    			catch ( SQLException e ){ System.out.println(e);}					}
		            } catch (Exception e) { System.out.println(e);	}
      			}
      		});
    	
    	btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					long Ear_Cus = Long.parseLong(txtCusContactNo.getText());
					PreparedStatement pstmt_16 = con.prepareStatement("insert into bill  values( ?, ?, ?, ?, NOW())");
				 	pstmt_16.setLong(1,timeI);  
				 	pstmt_16.setLong(2,Ear_Cus);
				 	float tQua = iQua + iQua_1 + iQua_2 + iQua_3 + iQua_4;
					pstmt_16.setFloat(3, tQua);
					gTotal = iprice*iQua + iprice_1*iQua_1 + iprice_2*iQua_2 + iprice_3*iQua_3 + iprice_4*iQua_4;
					pstmt_16.setFloat(4, gTotal);
					int i = pstmt_16.executeUpdate();
					System.out.println(i+" records inserted");
					lblSubmitedToBill.setVisible(true);
					} catch ( Exception e ) { System.out.println(e); } 
			}
		});
    	
    	btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblGrandTotal.setText("Grand Total : INR  "+ (iprice*iQua + iprice_1*iQua_1 + iprice_2*iQua_2 + iprice_3*iQua_3 + iprice_4*iQua_4) );
      			lblGrandTotal.setVisible(true);
			}
		});
		
    	btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				txtFirstName.setText("First Name");
				txtLastName.setText("Last Name");
				txtCusContactNo.setText("");
				lblSubmitedToBill.setVisible(false);
				lblCusconformation.setVisible(false);
				lblCustomerid.setVisible(false);
				lblBillID.setVisible(false);
				lblCustomerId_1.setVisible(false);
				lblGrandTotal.setVisible(false);
			Statement st_5 = null;
				try 
					{
						st_5 = con.createStatement();
						ResultSet rs_10 = st_5.executeQuery("SELECT Cus_ID FROM customer");
						Long Long_1=null;
			            while (rs_10.next()) {
			            	Long_1 = rs_10.getLong("Cus_ID");
							System.out.println(Long_1);
							try {
								String sql_10 = " DELETE FROM billdesctemp WHERE Cus_ID = '" + Long_1 + "' ";
								PreparedStatement pstmt_15 = con.prepareStatement(sql_10);  
								int i = pstmt_15.executeUpdate();
								System.out.println(i+" records deleted");
								}
							catch ( Exception e ){ System.out.println(e);} 
						}
			            }catch ( Exception e ){ System.out.println(e);}
				try 
				{
					String sql = "SELECT * FROM billdesctemp";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
		            } catch (Exception e) { System.out.println(e);	}
			}
		});
    	
    	btnSearchCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					long Cus_ID_1 = Long.parseLong(textField_5.getText());
					String sql = "SELECT * FROM bill where Cus_ID = '"+ Cus_ID_1 +"'";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						try 
						{
							String sql_1 = "select * from bill where Cus_ID = '"+ Cus_ID_1 +"'";
							PreparedStatement pstmt_1 = con.prepareStatement(sql_1);
							ResultSet rs_1 = pstmt_1.executeQuery();
							table_1.setModel(DbUtils.resultSetToTableModel(rs_1));
				            } catch (Exception e) { System.out.println(e);	}
					} else {
						textField_5.setText("Not In Database");
					}
					}
						catch (Exception e) { System.out.println(e);	}
			}
		});
    	
    	btnAddVegetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
    		try
    		{
    			String vegetable = null;
    			
    			PreparedStatement pstmt = con.prepareStatement("insert into billdesctemp values( ?, ?, ?, ?, ?, ?, NOW())");  
    			PreparedStatement pstmt_2 = con.prepareStatement("insert into totalbilldesc values( ?, ?, ?, ?, ?, ?, NOW())"); 
    			
    			pstmt.setLong(1,timeI);  //1 specifies the first parameter in the query  
    			pstmt_2.setLong(1, timeI);
    			
    			pstmt.setLong(2, Long.parseLong(txtCusContactNo.getText()));
    			pstmt_2.setLong(2, Long.parseLong(txtCusContactNo.getText()));
    			
    			if(comboBox.getSelectedItem().toString() != null)
        			vegetable = (String)comboBox.getItemAt(comboBox.getSelectedIndex());
        		else
        			System.out.println("nulle");
    			
        		pstmt.setString(3, vegetable);
        		pstmt_2.setString(3, vegetable);
        		
        		PreparedStatement pstmt_1 = null;
    			try {
					String sql_1 = " SELECT Price FROM vegetables WHERE Items = '" + vegetable + "' ";
					pstmt_1 = con.prepareStatement(sql_1);
					ResultSet rs_5 = pstmt_1.executeQuery();
					if (rs_5.next()) {
						iprice = rs_5.getFloat("Price");
					}
				} catch ( Exception e ){ System.out.println(e);}
    			
    			pstmt.setFloat(4, iprice);
    			pstmt_2.setFloat(4, iprice);
    			
    		    iQua = Float.parseFloat(textField.getText());
    			
    			pstmt.setFloat(5, iQua);
    			pstmt_2.setFloat(5, iQua);
    			
    			Float calPrice = iprice * iQua ;
    			
    			pstmt.setFloat(6, calPrice);
    			pstmt_2.setFloat(6, calPrice);
    			
    			int i = pstmt.executeUpdate();
    			int j = pstmt_2.executeUpdate();
    			
    			System.out.println(i+j +" records inserted");
    			}
    		catch ( Exception e ){ System.out.println(e);}
    		try 
			{
				String sql = "SELECT * FROM billdesctemp";
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
	            } catch (Exception e) { System.out.println(e);	}
    	}
    	});
    	
    	btnAddFruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
	    		{
	    			String fruit = null;
	    			
	    			PreparedStatement pstmt_3 = con.prepareStatement("insert into billdesctemp values( ?, ?, ?, ?, ?, ?, NOW())");  
	    			PreparedStatement pstmt_5 = con.prepareStatement("insert into totalbilldesc values( ?, ?, ?, ?, ?, ?, NOW())"); 
	    			
	    			pstmt_3.setLong(1,timeI);  //1 specifies the first parameter in the query  
	    			pstmt_5.setLong(1, timeI);
	    			
	    			pstmt_3.setLong(2, Long.parseLong(txtCusContactNo.getText()));
	    			pstmt_5.setLong(2, Long.parseLong(txtCusContactNo.getText()));
	    			
	    			if(comboBox_1.getSelectedItem().toString() != null)
	        			fruit = (String)comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
	        		else
	        			System.out.println("nulle");
	    			
	        		pstmt_3.setString(3, fruit);
	        		pstmt_5.setString(3, fruit);
	        		
	        		PreparedStatement pstmt_4 = null;
	    			try {
						String sql_1 = " SELECT Price FROM fruits WHERE Items = '" + fruit + "' ";
						pstmt_4 = con.prepareStatement(sql_1);
						ResultSet rs_6 = pstmt_4.executeQuery();
						if (rs_6.next()) {
							iprice_1 = rs_6.getFloat("Price");
						}
					} catch ( Exception e ){ System.out.println(e);}
	    			
	    			pstmt_3.setFloat(4, iprice_1);
	    			pstmt_5.setFloat(4, iprice_1);
	    			
	    			iQua_1 = Float.parseFloat(textField_1.getText());
	    			
	    			pstmt_3.setFloat(5, iQua_1);
	    			pstmt_5.setFloat(5, iQua_1);
	    			
	    			Float calPrice = iprice_1 * iQua_1 ;
	    			
	    			pstmt_3.setFloat(6, calPrice);
	    			pstmt_5.setFloat(6, calPrice);
	    			
	    			int i = pstmt_3.executeUpdate();
	    			int j = pstmt_5.executeUpdate();
	    			
	    			System.out.println(i+j +" records inserted");
	    			}
				catch ( Exception e ){ System.out.println(e);}
				try 
				{
					String sql = "SELECT * FROM billdesctemp";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
		            } catch (Exception e) { System.out.println(e);	}
			}
		    });
    	
    	btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
	    		{
	    			String kitchen_items = null;
	    			
	    			PreparedStatement pstmt_6 = con.prepareStatement("insert into billdesctemp values( ?, ?, ?, ?, ?, ?, NOW())");  
	    			PreparedStatement pstmt_8 = con.prepareStatement("insert into totalbilldesc values( ?, ?, ?, ?, ?, ?, NOW())"); 
	    			
	    			pstmt_6.setLong(1,timeI);  //1 specifies the first parameter in the query  
	    			pstmt_8.setLong(1, timeI);
	    			
	    			pstmt_6.setLong(2, Long.parseLong(txtCusContactNo.getText()));
	    			pstmt_8.setLong(2, Long.parseLong(txtCusContactNo.getText()));
	    			
	    			if(comboBox_2.getSelectedItem().toString() != null)
	        			kitchen_items = (String)comboBox_2.getItemAt(comboBox_2.getSelectedIndex());
	        		else
	        			System.out.println("nulle");
	    			
	        		pstmt_6.setString(3, kitchen_items);
	        		pstmt_8.setString(3, kitchen_items);
	        		
	        		PreparedStatement pstmt_7 = null;
	    			try {
						String sql_2 = " SELECT Price FROM kitchen WHERE Items = '" + kitchen_items + "' ";
						pstmt_7 = con.prepareStatement(sql_2);
						ResultSet rs_7 = pstmt_7.executeQuery();
						if (rs_7.next()) {
							iprice_2 = rs_7.getFloat("Price");
						}
					} catch ( Exception e ){ System.out.println(e);}
	    			
	    			pstmt_6.setFloat(4, iprice_2);
	    			pstmt_8.setFloat(4, iprice_2);
	    			
	    			iQua_2 = Float.parseFloat(textField_2.getText());
	    			
	    			pstmt_6.setFloat(5, iQua_2);
	    			pstmt_8.setFloat(5, iQua_2);
	    			
	    			Float calPrice = iprice_2 * iQua_2 ;
	    			
	    			pstmt_6.setFloat(6, calPrice);
	    			pstmt_8.setFloat(6, calPrice);
	    			
	    			int i = pstmt_6.executeUpdate();
	    			int j = pstmt_8.executeUpdate();
	    			
	    			System.out.println(i+j +" records inserted");
	    			}
				catch ( Exception e ){ System.out.println(e);}
				try 
				{
					String sql = "SELECT * FROM billdesctemp";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
		            } catch (Exception e) { System.out.println(e);	}
				
			}
		    });
    	
    	btnAddItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
	    		{
	    			String stationary_items = null;
	    			
	    			PreparedStatement pstmt_9 = con.prepareStatement("insert into billdesctemp values( ?, ?, ?, ?, ?, ?, NOW())");  
	    			PreparedStatement pstmt_11 = con.prepareStatement("insert into totalbilldesc values( ?, ?, ?, ?, ?, ?, NOW())"); 
	    			
	    			pstmt_9.setLong(1,timeI);  //1 specifies the first parameter in the query 
	    			pstmt_11.setLong(1, timeI);
	    			
	    			pstmt_9.setLong(2, Long.parseLong(txtCusContactNo.getText()));
	    			pstmt_11.setLong(2, Long.parseLong(txtCusContactNo.getText()));
	    			
	    			if(comboBox_3.getSelectedItem().toString() != null)
	        			stationary_items = (String)comboBox_3.getItemAt(comboBox_3.getSelectedIndex());
	        		else
	        			System.out.println("nulle");
	    			
	        		pstmt_9.setString(3, stationary_items);
	        		pstmt_11.setString(3, stationary_items);
	        		
	        		PreparedStatement pstmt_10 = null;
	    			try { 
						String sql_2 = " SELECT Price FROM stationary WHERE Items = '" + stationary_items + "' ";
						pstmt_10 = con.prepareStatement(sql_2);
						ResultSet rs_8 = pstmt_10.executeQuery();
						if (rs_8.next()) {
							iprice_3 = rs_8.getFloat("Price");
						}
					} catch ( Exception e ){ System.out.println(e);}
	    			
	    			pstmt_9.setFloat(4, iprice_3);
	    			pstmt_11.setFloat(4, iprice_3);
	    			
	    			iQua_3 = Float.parseFloat(textField_3.getText());
	    			
	    			pstmt_9.setFloat(5, iQua_3);
	    			pstmt_11.setFloat(5, iQua_3);
	    			
	    			Float calPrice = iprice_3 * iQua_3 ;
	    			
	    			pstmt_9.setFloat(6, calPrice);
	    			pstmt_11.setFloat(6, calPrice);
	    			
	    			int i = pstmt_9.executeUpdate();
	    			int j = pstmt_11.executeUpdate();
	    			
	    			System.out.println(i+j +" records inserted");
	    			}
				catch ( Exception e ){ System.out.println(e);}
				try 
				{
					String sql = "SELECT * FROM billdesctemp";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
		            } catch (Exception e) { System.out.println(e);	}
			}
		    });
    	
    	btnAddItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
	    		{
	    			String others = null;
	    			
	    			PreparedStatement pstmt_12 = con.prepareStatement("insert into billdesctemp values( ?, ?, ?, ?, ?, ?, NOW())");  
	    			PreparedStatement pstmt_14 = con.prepareStatement("insert into totalbilldesc values( ?, ?, ?, ?, ?, ?, NOW())"); 
	    			
	    			pstmt_12.setLong(1,timeI);  //1 specifies the first parameter in the query  
	    			pstmt_14.setLong(1, timeI);
	    			
	    			pstmt_12.setLong(2, Long.parseLong(txtCusContactNo.getText()));
	    			pstmt_14.setLong(2, Long.parseLong(txtCusContactNo.getText()));
	    			
	    			if(comboBox_4.getSelectedItem().toString() != null)
	        			others = (String)comboBox_4.getItemAt(comboBox_4.getSelectedIndex());
	        		else
	        			System.out.println("nulle");
	    			
	        		pstmt_12.setString(3, others);
	        		pstmt_14.setString(3, others);
	        		
	        		PreparedStatement pstmt_13 = null;
	    			try {
						String sql_1 = " SELECT Price FROM others WHERE Items = '" + others + "' ";
						pstmt_13 = con.prepareStatement(sql_1);
						ResultSet rs_9 = pstmt_13.executeQuery();
						if (rs_9.next()) {
							iprice_4 = rs_9.getFloat("Price");
						}
					} catch ( Exception e ){ System.out.println(e);}
	    			
	    			pstmt_12.setFloat(4, iprice_4);
	    			pstmt_14.setFloat(4, iprice_4);
	    			
	    			iQua_4 = Float.parseFloat(textField_4.getText());
	    			
	    			pstmt_12.setFloat(5, iQua_4);
	    			pstmt_14.setFloat(5, iQua_4);
	    			
	    			Float calPrice = iprice_4 * iQua_4 ;
	    			
	    			pstmt_12.setFloat(6, calPrice);
	    			pstmt_14.setFloat(6, calPrice);
	    			
	    			int i = pstmt_12.executeUpdate();
	    			int j = pstmt_14.executeUpdate();
	    			
	    			System.out.println(i+j +" records inserted");
	    			}
				catch ( Exception e ){ System.out.println(e);}
				try 
				{
					String sql = "SELECT * FROM billdesctemp";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
		            } catch (Exception e) { System.out.println(e);	}
			}
		});
    	btnGloceryTransactionDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					String sql = "SELECT * FROM totalbilldesc";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
		            } catch (Exception e) { System.out.println(e);	}
			}
		});
		
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////   Method to Initialize components ////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addComponents() throws Exception, ParseException
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Customer", null, panel, null);
		
		JLabel lblCostumerName = new JLabel("Costumer Name :");
		lblCostumerName.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Cambria", Font.ITALIC, 17));
		txtFirstName.setText("First Name");
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Cambria", Font.ITALIC, 17));
		txtLastName.setText("Last Name");
		txtLastName.setColumns(10);
		
		JLabel lblCostumerContactNo = new JLabel("Costumer Contact No :");
		lblCostumerContactNo.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		
		txtCusContactNo = new JTextField();
		txtCusContactNo.setFont(new Font("Cambria", Font.ITALIC, 17));
		txtCusContactNo.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		
		btnSubmit.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		
		lblCustomerid = new JLabel("CustomerID");
		lblCustomerid.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		lblCustomerid.setVisible(false);
		
		btnReset = new JButton("Reset");
		
		btnReset.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		
		lblCusconformation = new JLabel("CusConformation");
		lblCusconformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblCusconformation.setFont(new Font("Cambria", Font.ITALIC, 17));
		lblCusconformation.setVisible(false);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(280)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCustomerid)
							.addGap(541)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(160, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCostumerContactNo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(77)
									.addComponent(txtCusContactNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(51))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCostumerName, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(txtFirstName, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(txtLastName, 142, 142, 142)))
							.addGap(613))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(416)
					.addComponent(lblCusconformation, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(418, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(173)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCostumerName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCostumerContactNo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtCusContactNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addComponent(lblCusconformation)
					.addGap(251)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(btnReset)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblCustomerid))
						.addComponent(btnSubmit))
					.addGap(61))
		);
		panel.setLayout(gl_panel);		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Bill Calculator", null, panel_1, null);
		
		lblCustomerId_1 = new JLabel("Customer ID_1");
		lblCustomerId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerId_1.setVerticalAlignment(SwingConstants.TOP);
		lblCustomerId_1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		lblCustomerId_1.setVisible(false);
		
		lblVegetable = new JLabel("Vegetables :");
		lblVegetable.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		
		Statement st = null;
		try 
		{
			st = con.createStatement();
			String sql = "SELECT *FROM vegetables";
			ResultSet rs = st.executeQuery(sql);
			String str=null;
			ArrayList<String> arr =new ArrayList<>();
            while (rs.next()) {
            	str = rs.getString("Items");
				arr.add(str);
			}
            comboBox = new JComboBox<Object>(arr.toArray());
    		comboBox.setFont(new Font("Cambria", Font.ITALIC, 15));
            }catch (Exception e) {
			System.out.println(e);
			}
		    finally {
		    	st.close();
		}
		
		btnAddVegetable = new JButton("Add Vegetable");
		
		btnAddVegetable.setFont(new Font("Cambria", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("Cambria", Font.BOLD, 15));
		textField.setColumns(10);
		
		lblFruits = new JLabel("Fruits : ");
		lblFruits.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		
		Statement st_1 = null;
		try 
		{
			st_1 = con.createStatement();
			String sql = "SELECT *FROM fruits";
			ResultSet rs_1 = st_1.executeQuery(sql);
			String str_1 = null;
			ArrayList<String> arr_1 =new ArrayList<>();
            while (rs_1.next()) {
            	str_1 = rs_1.getString("Items");
				arr_1.add(str_1);
			}
            comboBox_1 = new JComboBox<Object>(arr_1.toArray());
    		comboBox_1.setFont(new Font("Cambria", Font.ITALIC, 15));
            }catch (Exception e) {
			System.out.println(e);
			}
		    finally {
		    	st_1.close();
		}
		
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Cambria", Font.BOLD, 15));
		textField_1.setColumns(10);
		
		btnAddFruit = new JButton("Add Fruit");
		btnAddFruit.setFont(new Font("Cambria", Font.BOLD, 15));
		
		Statement st_2 = null;
		try 
		{
			st_2 = con.createStatement();
			String sql = "SELECT *FROM kitchen";
			ResultSet rs_2 = st_2.executeQuery(sql);
			String str_2 = null;
			ArrayList<String> arr_2 =new ArrayList<>();
            while (rs_2.next()) {
            	str_2 = rs_2.getString("Items");
				arr_2.add(str_2);
			}
            comboBox_2 = new JComboBox<Object>(arr_2.toArray());
    		comboBox_2.setFont(new Font("Cambria", Font.ITALIC, 15));
            }catch (Exception e) {
			System.out.println(e);
			}
		    finally {
		    	st_2.close();
		}
		
		
		lblPulsesAndSpices = new JLabel("Pulses And Spices :");
		lblPulsesAndSpices.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Cambria", Font.BOLD, 15));
		textField_2.setColumns(10);
		
		btnAddItem = new JButton("Add Item");
		btnAddItem.setFont(new Font("Cambria", Font.BOLD, 15));
		
		lblStationary = new JLabel("Stationary : ");
		lblStationary.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		
		Statement st_3 = null;
		try 
		{
			st_3 = con.createStatement();
			String sql = "SELECT *FROM stationary";
			ResultSet rs_3 = st_3.executeQuery(sql);
			String str_3 = null;
			ArrayList<String> arr_3 =new ArrayList<>();
            while (rs_3.next()) {
            	str_3 = rs_3.getString("Items");
				arr_3.add(str_3);
			}
            comboBox_3 = new JComboBox(arr_3.toArray());
    		comboBox_3.setFont(new Font("Cambria", Font.ITALIC, 15));
            }catch (Exception e) {
			System.out.println(e);
			}
		    finally {
		    	st_3.close();
		}
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Cambria", Font.BOLD, 15));
		textField_3.setColumns(10);
		
		btnAddItem_1 = new JButton("Add Item");
		btnAddItem_1.setFont(new Font("Cambria", Font.BOLD, 15));
		
		lblOthers = new JLabel("Others : ");
		lblOthers.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Cambria", Font.BOLD, 15));
		textField_4.setColumns(10);
		
		Statement st_4 = null;
		try 
		{
			st_4 = con.createStatement();
			String sql = "SELECT *FROM others";
			ResultSet rs_4 = st_4.executeQuery(sql);
			String str_4 = null;
			ArrayList<String> arr_4 =new ArrayList<>();
            while (rs_4.next()) {
            	str_4 = rs_4.getString("Items");
				arr_4.add(str_4);
			}
            comboBox_4 = new JComboBox(arr_4.toArray());
    		comboBox_4.setFont(new Font("Cambria", Font.ITALIC, 15));
            }catch (Exception e) {
			System.out.println(e);
			}
		    finally {
		    	st_4.close();
		}
		
		btnAddItem_2 = new JButton("Add Item");
		btnAddItem_2.setFont(new Font("Cambria", Font.BOLD, 15));
		
		btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		
		lblBillID = new JLabel("BillID");
		lblBillID.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillID.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		lblBillID.setVisible(false);
		
		btnTotal = new JButton("Total");
		btnTotal.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		
		lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		lblGrandTotal.setVisible(false);
		
		scrollPane = new JScrollPane();
		
		lblSubmitedToBill = new JLabel("Submited to Bill Database");
		lblSubmitedToBill.setFont(new Font("Cambria", Font.ITALIC, 17));
		lblSubmitedToBill.setVisible(false);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(463)
							.addComponent(lblSubmitedToBill, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(77)
							.addComponent(lblGrandTotal, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
							.addGap(83)
							.addComponent(btnTotal)
							.addGap(62)
							.addComponent(btnSubmit_1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(270)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCustomerId_1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVegetable, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFruits, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPulsesAndSpices)
										.addComponent(lblStationary)
										.addComponent(lblOthers))
									.addGap(53)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboBox_4, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox_3, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox_2, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(54)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_4, 0, 0, Short.MAX_VALUE)
										.addComponent(textField_3, 0, 0, Short.MAX_VALUE)
										.addComponent(textField_2, 0, 0, Short.MAX_VALUE)
										.addComponent(textField_1, 0, 0, Short.MAX_VALUE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
									.addGap(88)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAddItem_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAddItem_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAddItem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAddFruit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAddVegetable)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(36)
									.addComponent(lblBillID, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(28)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1270, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerId_1)
						.addComponent(lblBillID))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVegetable)
						.addComponent(btnAddVegetable)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAddFruit))
						.addComponent(lblFruits))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPulsesAndSpices)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAddItem)))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStationary)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddItem_1))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblOthers)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAddItem_2))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubmitedToBill)
						.addComponent(lblGrandTotal)
						.addComponent(btnTotal)
						.addComponent(btnSubmit_1))
					.addGap(21))
		);
		
		table = new JTable();
		table.setFont(new Font("Cambria", Font.ITALIC, 17));
		scrollPane.setViewportView(table);
		
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Glocery Transition Details", null, panel_2, null);
		
		JLabel lblSearchcus = new JLabel("Customer ID :");
		lblSearchcus.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Cambria", Font.ITALIC, 17));
		textField_5.setColumns(10);
		
		btnSearchCustomer = new JButton("Show Customer Bill Detail");
		
		btnSearchCustomer.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
		
		btnGloceryTransactionDetails = new JButton("Glocery Transaction Details");
		
		btnGloceryTransactionDetails.setFont(new Font("Cambria", Font.ITALIC, 17));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(33)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 1263, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(328)
							.addComponent(lblSearchcus, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(77)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(btnSearchCustomer, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(506)
							.addComponent(btnGloceryTransactionDetails, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchCustomer)
						.addComponent(lblSearchcus))
					.addGap(35)
					.addComponent(btnGloceryTransactionDetails)
					.addGap(29)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Cambria", Font.ITALIC, 15));
		scrollPane_1.setViewportView(table_1);
		panel_2.setLayout(gl_panel_2);
	}
}