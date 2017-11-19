package swing.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class TaskRegisterPanel extends JPanel {
    MainFrame mainFrame;
    Connection con = null;
	Statement smt = null;
	
    public TaskRegisterPanel(MainFrame mf,String name){
    	mainFrame = mf;
        this.setName(name);
        this.setLayout(null);
        this.setSize(800, 1000);
        
        JButton registerBtn = new JButton("登録");
        JTextField taskTitle = new JTextField(10);
        JTextField taskLimit = new JTextField(10);
        JTextField taskDiscription = new JTextField(10);
        
        registerBtn.setBounds(350, 150, 200, 40);
        registerBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try {
            		// JDBCドライバーの指定
            		Class.forName("org.sqlite.JDBC");

            		// データベースに接続する なければ作成される
            		con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
            		smt = con.createStatement();
            		int id = serchLastId(MainPanel.taskSet) + 1;
            		String sql = "insert into task (id, name, limit_date, comment) values(" + String.valueOf(id) + ", '"+taskTitle.getText() + "', '" + taskLimit.getText() + "', '" + taskDiscription.getText() + "');";
            		smt.executeUpdate(sql);
            		con.close();
            	} catch (ClassNotFoundException e1) {
            		// TODO 自動生成された catch ブロック
            		e1.printStackTrace();
            	} catch (SQLException e2) {
            		// TODO 自動生成された catch ブロック
            		e2.printStackTrace();
            	}
            	panelChange();
            }
        });
        
        JLabel taskTitleLabel = new JLabel("タスク名");
        JLabel taskLimitLabel = new JLabel("期限");
        JLabel taskDiscriptionLabel = new JLabel("備考");
        
        taskTitleLabel.setBounds(150, 50, 200, 40);
        taskLimitLabel.setBounds(350, 50, 200, 40);
        taskDiscriptionLabel.setBounds(550, 50, 200, 40);
        
        taskTitle.setBounds(150, 100, 200, 40);
        taskLimit.setBounds(350, 100, 200, 40);
        taskDiscription.setBounds(550, 100, 200, 40);
        
        this.add(registerBtn);
        this.add(taskTitleLabel);
        this.add(taskLimitLabel);
        this.add(taskDiscriptionLabel);
        this.add(taskTitle);
        this.add(taskLimit);
        this.add(taskDiscription);
    }
    public void panelChange(){
    	mainFrame.reloadPage(mainFrame.PanelNames[0], this);
    	mainFrame.panelChange((JPanel)this, mainFrame.PanelNames[0], "");
    }
    
    public int serchLastId(HashMap<Integer,ArrayList<String>> resultSet){
    	Set<Integer> keySet = resultSet.keySet();
    	int lastId=0;
    	for(int key : keySet){
    		if(lastId < key){
    			lastId = key;
    		}
    	}
    	return lastId;
    }
}