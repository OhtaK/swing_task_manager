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
    JButton btn = new JButton("登録");
    MainFrame mf;
    String str;
    Connection con = null;
	Statement smt = null;
	
    public TaskRegisterPanel(MainFrame m,String s){
        mf = m;
        str = s;
        this.setName(s);
        this.setLayout(null);
        this.setSize(800, 1000);
        HashMap<Integer,ArrayList<String>> resultSetForRegister = MainPanel.resultSet;
        Set<Integer> MapSet = resultSetForRegister.keySet();

        JLabel label1 = new JLabel("タスク名");
        JLabel label2 = new JLabel("期限");
        JLabel label3 = new JLabel("備考");
        
        JTextField text1 = new JTextField(10);
        JTextField text2 = new JTextField(10);
        JTextField text3 = new JTextField(10);
        
        btn.setBounds(350, 150, 200, 40);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try {
            		   // JDBCドライバーの指定
            		   Class.forName("org.sqlite.JDBC");
            		 
            		   // データベースに接続する なければ作成される
            		   con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
            		   smt = con.createStatement();
            		   int id = serchLastId(MainPanel.resultSet) + 1;
            		   String sql = "insert into task (id, name, limit_date, comment) values(" + String.valueOf(id) + ", '"+text1.getText() + "', '" + text2.getText() + "', '" + text3.getText() + "');";
            		   smt.executeUpdate(sql);
            		   con.close();
            		  } catch (ClassNotFoundException e1) {
            		   // TODO 自動生成された catch ブロック
            		   e1.printStackTrace();
            		  } catch (SQLException e2) {
            		   // TODO 自動生成された catch ブロック
            		   e2.printStackTrace();
            		  }
                pc();
            }
        });
        
        label1.setBounds(150, 50, 200, 40);
        label2.setBounds(350, 50, 200, 40);
        label3.setBounds(550, 50, 200, 40);
        
        text1.setBounds(150, 100, 200, 40);
        text2.setBounds(350, 100, 200, 40);
        text3.setBounds(550, 100, 200, 40);
        
        this.add(btn);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(text1);
        this.add(text2);
        this.add(text3);
    }
    public void pc(){
    	mf.reloadPage(mf.PanelNames[0], this);
    	//mf.remove(this);
        mf.PanelChange((JPanel)this, mf.PanelNames[0], "");
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