package swing.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.TaskDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class TaskEditPanel extends JPanel {
    
    MainFrame mainFrame;
    Connection con = null;
	Statement smt = null;
	
	JTextField taskTitle = new JTextField(10);
    JTextField taskLimit = new JTextField(10);
    JTextField taskDiscription = new JTextField(10);
    
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
        
        JButton toDoBtn = new JButton("todoに移動");
        toDoBtn.setBounds(150, 150, 200, 40);
        
        toDoBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	DBAccesser dbAccesser = new DBAccesser();
            	TaskDto task = new TaskDto();
            	task.setId(Integer.parseInt(nowSelectedStrings[0]));
            	task.setTitle(taskTitle.getText());
            	task.setLimitDate(taskLimit.getText());
            	task.setDiscription(taskDiscription.getText());
            	task.setStatus(0);
            	dbAccesser.update(task);
            	
            	panelChange();
            	//mainFrame.showMainPanel();
            }
        });
        
        JButton doingBtn = new JButton("doingに移動");
        doingBtn.setBounds(350, 150, 200, 40);
        
        doingBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	DBAccesser dbAccesser = new DBAccesser();
            	TaskDto task = new TaskDto();
            	task.setId(Integer.parseInt(nowSelectedStrings[0]));
            	task.setTitle(taskTitle.getText());
            	task.setLimitDate(taskLimit.getText());
            	task.setDiscription(taskDiscription.getText());
            	task.setStatus(1);
            	dbAccesser.update(task);
            	
            	panelChange();
            }
        });
        
        JButton doneBtn = new JButton("doneに移動");
        doneBtn.setBounds(550, 150, 200, 40);
        
        doneBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	DBAccesser dbAccesser = new DBAccesser();
            	TaskDto task = new TaskDto();
            	task.setId(Integer.parseInt(nowSelectedStrings[0]));
            	task.setTitle(taskTitle.getText());
            	task.setLimitDate(taskLimit.getText());
            	task.setDiscription(taskDiscription.getText());
            	task.setStatus(2);
            	dbAccesser.update(task);
            	
            	panelChange();
            }
        });
        
        JLabel taskTitleLabel = new JLabel("タスク名");
        JLabel taskLimitLabel = new JLabel("期限");
        JLabel taskDixcriptionLabel = new JLabel("備考");
        
        taskTitleLabel.setBounds(150, 50, 200, 40);
        taskLimitLabel.setBounds(350, 50, 200, 40);
        taskDixcriptionLabel.setBounds(550, 50, 200, 40);
        
        taskTitle.setBounds(150, 100, 200, 40);
        taskLimit.setBounds(350, 100, 200, 40);
        taskDiscription.setBounds(550, 100, 200, 40);
        
//        taskTitle.setText(selectedTask.getTitle());
//        taskLimit.setText(selectedTask.getLimitDate());
//        taskDiscription.setText(selectedTask.getDiscription());
        
        this.add(toDoBtn);
        this.add(doingBtn);
        this.add(doneBtn);
        this.add(taskTitleLabel);
        this.add(taskLimitLabel);
        this.add(taskDixcriptionLabel);
        this.add(taskTitle);
        this.add(taskLimit);
        this.add(taskDiscription);
    }
    public void panelChange(){
    	mainFrame.reloadPage(mainFrame.PanelNames[0]);
    	mainFrame.panelChange((JPanel)this, mainFrame.PanelNames[0], "");
    }
    
    public void setEditString(String setString){
    	String nowSelectedString = setString;
        if(nowSelectedString != null){
        	nowSelectedStrings = nowSelectedString.split(":", 0);
        }
        if(nowSelectedStrings.length > 1){
        	taskTitle.setText(nowSelectedStrings[1]);
        }
        else{
        	taskTitle.setText("");
        }
        
        if(nowSelectedStrings.length > 2){
        	taskLimit.setText(nowSelectedStrings[2]);
        }
        else{
        	taskLimit.setText("");
        }
        
        if(nowSelectedStrings.length > 3){
        	taskDiscription.setText(nowSelectedStrings[3]);
        }
        else{
        	taskDiscription.setText("");
        }
    }
}