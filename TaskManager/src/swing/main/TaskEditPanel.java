package swing.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class TaskEditPanel extends JPanel {
    JButton toDoBtn = new JButton("todoに移動");
    JButton doingBtn = new JButton("doingに移動");
    JButton doneBtn = new JButton("doneに移動");
    MainFrame mainFrame;
    Connection con = null;
	Statement smt = null;
	
	JTextField text1 = new JTextField(10);
    JTextField text2 = new JTextField(10);
    JTextField text3 = new JTextField(10);
    
    String nowSelectedString = MainPanel.nowSelectText;
    String[] nowSelectedStrings = new String[4];
    
    public TaskEditPanel(MainFrame mf,String name){
    	mainFrame = mf;
        this.setName(name);
        this.setLayout(null);
        this.setSize(800, 1000);
        if(nowSelectedString != null){
        	nowSelectedStrings = nowSelectedString.split(":", 0);
        }
        
        JLabel label1 = new JLabel("タスク名");
        JLabel label2 = new JLabel("期限");
        JLabel label3 = new JLabel("備考");
        
        toDoBtn.setBounds(150, 150, 200, 40);
        toDoBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try {
          		   // JDBCドライバーの指定
          		   Class.forName("org.sqlite.JDBC");
          		 
          		   // データベースに接続する なければ作成される
          		   con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
          		   smt = con.createStatement();
          		   String sql = "update task set name='"+ text1.getText() + "', limit_date='" + text2.getText() + "', comment='"+ text3.getText() + "', status=0 where id=" + nowSelectedStrings[0]+ ";";
          		  
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
        
        doingBtn.setBounds(350, 150, 200, 40);
        doingBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try {
          		   // JDBCドライバーの指定
          		   Class.forName("org.sqlite.JDBC");
          		 
          		   // データベースに接続する なければ作成される
          		   con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
          		   smt = con.createStatement();
          		   String sql = "update task set name='"+ text1.getText() + "', limit_date='" + text2.getText() + "', comment='"+ text3.getText() + "', status=1 where id=" + nowSelectedStrings[0]+ ";";
          		  
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
        
        doneBtn.setBounds(550, 150, 200, 40);
        doneBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	try {
          		   // JDBCドライバーの指定
          		   Class.forName("org.sqlite.JDBC");
          		 
          		   // データベースに接続する なければ作成される
          		   con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
          		   smt = con.createStatement();
          		   String sql = "update task set name='"+ text1.getText() + "', limit_date='" + text2.getText() + "', comment='"+ text3.getText() + "', status=2 where id=" + nowSelectedStrings[0]+ ";";
          		  
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
        
        this.add(toDoBtn);
        this.add(doingBtn);
        this.add(doneBtn);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(text1);
        this.add(text2);
        this.add(text3);
    }
    public void pc(){
    	mainFrame.reloadPage(mainFrame.PanelNames[0], this);
    	mainFrame.PanelChange((JPanel)this, mainFrame.PanelNames[0], "");
    }
    
    public void setEditString(String setString){
    	String nowSelectedString = setString;
        if(nowSelectedString != null){
        	nowSelectedStrings = nowSelectedString.split(":", 0);
        }
        if(nowSelectedStrings.length > 1){
        	text1.setText(nowSelectedStrings[1]);
        }
        else{
        	text1.setText("");
        }
        
        if(nowSelectedStrings.length > 2){
        	text2.setText(nowSelectedStrings[2]);
        }
        else{
        	text2.setText("");
        }
        
        if(nowSelectedStrings.length > 3){
        	text3.setText(nowSelectedStrings[3]);
        }
        else{
        	text3.setText("");
        }
    }
}