package com.view.systemAdm;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.bean.Building;
import com.bean.DormAdmin;
import com.dao.SysAdminDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class DormAdmManage extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	private JTextField name;
	private JTextField building;
	private JTable dormAdmTable;
	private DefaultTableModel dtm=null;
	@SuppressWarnings("unused")
	private AddDormAdm addDormAdm=null;
	@SuppressWarnings("rawtypes")
	private JComboBox sexTypeComb;
	private JButton changeButton;
	private JButton deleteButton;
	private static DormAdmin selectDormAdm=new DormAdmin();
	private static final String[] sexType={"","��","Ů"};
	private JButton dormWoman;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
	public DormAdmManage() {
		setTitle("\u5BBF\u820D\u7BA1\u7406\u5458\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 679, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u5BBF\u7BA1\u8D26\u53F7");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblNewLabel.setBounds(50, 21, 108, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BBF\u7BA1\u540D\u5B57");
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(372, 21, 108, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7BA1\u7406\u5BBF\u820D\u697C");
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(35, 81, 108, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u6027\u522B");
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(393, 81, 108, 29);
		contentPane.add(lblNewLabel_3);
		
		ID = new JTextField();
		ID.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		ID.setBounds(143, 18, 130, 30);
		contentPane.add(ID);
		ID.setColumns(10);
		
		name = new JTextField();
		name.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		name.setColumns(10);
		name.setBounds(464, 18, 130, 30);
		contentPane.add(name);
		
		building = new JTextField();
		building.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		building.setColumns(10);
		building.setBounds(143, 78, 130, 30);
		contentPane.add(building);
		
		sexTypeComb = new JComboBox();
		sexTypeComb.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		sexTypeComb.setBounds(464, 78, 130, 30);
		sexTypeComb.setModel(new DefaultComboBoxModel(sexType));
		contentPane.add(sexTypeComb);
		
		JButton queryButton = new JButton("\u67E5\u8BE2");
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryDormAdm(e);
			}
		});
		queryButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		queryButton.setFocusable(false);
		queryButton.setBounds(255, 135, 89, 32);
		contentPane.add(queryButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 191, 611, 211);
		contentPane.add(scrollPane);
		
		dormAdmTable = new JTable();
		dormAdmTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectRow(e);
			}
		});
		//�����û������϶�
		dormAdmTable.getTableHeader().setReorderingAllowed(false);
		//���ñ���߶�
		dormAdmTable.setRowHeight(20);
		//���ñ��������ʾ
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		dormAdmTable.setDefaultRenderer(Object.class, r);
		
		dormAdmTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5BBF\u7BA1\u8D26\u53F7", "\u5BBF\u7BA1\u540D\u5B57", "\u7BA1\u7406\u5BBF\u820D\u697C", "\u6027\u522B"
			}
		){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		});
		
		dormAdmTable.setFont(new Font("SimSun", Font.PLAIN, 12));
		scrollPane.setViewportView(dormAdmTable);
		
		JButton insertButton = new JButton("\u589E\u52A0");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDormAdm(e);
			}
		});
		insertButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		insertButton.setFocusable(false);
		insertButton.setBounds(350, 135, 89, 32);
		contentPane.add(insertButton);
		
		changeButton = new JButton("\u4FEE\u6539");
		changeButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		changeButton.setFocusable(false);
		changeButton.setBounds(445, 135, 89, 32);
		contentPane.add(changeButton);
		
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDormAdm(e);
			}
		});
		deleteButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		deleteButton.setFocusable(false);
		deleteButton.setBounds(540, 135, 89, 32);
		contentPane.add(deleteButton);
		
		this.deleteButton.setFocusable(false);
		this.changeButton.setFocusable(false);
		queryButton.setFocusable(false);
		insertButton.setFocusable(false);
		
		dormWoman = new JButton("\u7BA1\u7406\u5973\u5BDD\u7684\u5BBF\u7BA1");
		dormWoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				womanBuilding(e);
			}
		});
		dormWoman.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		dormWoman.setFocusable(false);
		dormWoman.setBounds(86, 135, 166, 32);
		contentPane.add(dormWoman);
		
		dtm=(DefaultTableModel)dormAdmTable.getModel();
		queryAllDormAdm();
	}

	//��ͼ
	@SuppressWarnings("unchecked")
	protected void womanBuilding(ActionEvent e) {
		dtm.setRowCount(0);
		SysAdminDao sysAdmDao=new SysAdminDao();
		List<DormAdmin> DormAdmList=sysAdmDao.womanBuilding();
		for(DormAdmin da:DormAdmList){
			@SuppressWarnings("rawtypes")
			Vector v=new Vector();
			v.add(da.getDormAdmID());
			v.add(da.getName());
			v.add(da.getBuildingName());
			v.add(da.getSex());
			dtm.addRow(v);
		}
	}

	protected void insertDormAdm(ActionEvent e) {
		// �����޹�
		if(addDormAdm==null){
			addDormAdm=new AddDormAdm(this);
		}
		addDormAdm.setVisible(true);
	}

	//ɾ���޹�
	protected void deleteDormAdm(ActionEvent e) {
		boolean confirmDelete=JOptionPane.showConfirmDialog(this, "ȷ��Ҫɾ�����޹���","����ɾ������",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION;
		if(confirmDelete){
			SysAdminDao sysAdmDao=new SysAdminDao();
			boolean success=sysAdmDao.deleteDormAdm(selectDormAdm.getDormAdmID(),selectDormAdm.getBuildingName());
			if(success){
				JOptionPane.showMessageDialog(this, "ɾ���ɹ���");
				queryAllDormAdm();
			}else{
				JOptionPane.showMessageDialog(this, "ɾ��ʧ��");
			}
		}
	}

	//�����ѡ��һ���޹�
	protected void selectRow(MouseEvent e) {
		selectDormAdm.setDormAdmID(dtm.getValueAt(this.dormAdmTable.getSelectedRow(), 0).toString());
		selectDormAdm.setName(dtm.getValueAt(this.dormAdmTable.getSelectedRow(), 1).toString());
		selectDormAdm.setBuildingName(dtm.getValueAt(this.dormAdmTable.getSelectedRow(), 2).toString());
		selectDormAdm.setSex(dtm.getValueAt(this.dormAdmTable.getSelectedRow(), 3).toString());
		this.changeButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
	}

	public boolean isEmpty(String s){
		if("".equals(s)||s==null)
			return true;
		return false;
	}
	
	//��ѯ�޹���Ϣ
	@SuppressWarnings("unchecked")
	protected void queryDormAdm(ActionEvent e) {
		String dormAdmID=this.ID.getText();
		String dormAdmName=this.name.getText();
		String dormAdmBuildingID=this.building.getText();
		String sexType=(String)this.sexTypeComb.getSelectedItem();
		if(dormAdmID.isEmpty()&&dormAdmName.isEmpty()&&dormAdmBuildingID.isEmpty()&&sexType.isEmpty()){
			queryAllDormAdm();
			return;
		}
		DormAdmin queryDormAdm = new DormAdmin();
		queryDormAdm.setDormAdmID(dormAdmID);
		queryDormAdm.setName(dormAdmName);
		queryDormAdm.setBuildingName(dormAdmBuildingID);
		queryDormAdm.setSex(sexType);
		dtm.setRowCount(0);
		SysAdminDao sysAdmDao=new SysAdminDao();
		List<DormAdmin> DormAdmList=sysAdmDao.queryDormAdm(queryDormAdm);
		for(DormAdmin da:DormAdmList){
			@SuppressWarnings("rawtypes")
			Vector v=new Vector();
			v.add(da.getDormAdmID());
			v.add(da.getName());
			v.add(da.getBuildingName());
			v.add(da.getSex());
			dtm.addRow(v);
		}
	}

	//��ѯ�����޹�
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryAllDormAdm() {
		dtm.setRowCount(0);
		SysAdminDao sysAdmDao=new SysAdminDao();
		List<DormAdmin> DormAdmList=sysAdmDao.queryAllDormAdm();
		for(DormAdmin da:DormAdmList){
			@SuppressWarnings("rawtypes")
			Vector v=new Vector();
			v.add(da.getDormAdmID());
			v.add(da.getName());
			v.add(da.getBuildingName());
			v.add(da.getSex());
			dtm.addRow(v);
		}
		this.changeButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
	}
}
