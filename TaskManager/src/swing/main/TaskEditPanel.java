package swing.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.TaskDto;
 
public class TaskEditPanel extends JPanel {
    
    MainFrame mainFrame;
	
	JLabel taskId = new JLabel();
	JTextField taskTitle = new JTextField(10);
    JTextField taskLimit = new JTextField(10);
    JTextField taskDiscription = new JTextField(10);
    
    public TaskEditPanel(MainFrame mf, String name){
    	mainFrame = mf;
        this.setName(name);
        this.setLayout(null);
        this.setSize(800, 1000);
        
        JButton toDoBtn = new JButton("todoに移動");
        toDoBtn.setBounds(150, 150, 200, 40);
        
        toDoBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	updateData(0);
            	panelChange();
            }
        });
        
        JButton doingBtn = new JButton("doingに移動");
        doingBtn.setBounds(350, 150, 200, 40);
        
        doingBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	updateData(1);
            	panelChange();
            }
        });
        
        JButton doneBtn = new JButton("doneに移動");
        doneBtn.setBounds(550, 150, 200, 40);
        
        doneBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	updateData(2);
            	panelChange();
            }
        });
        
        JLabel taskIdLabel = new JLabel("ID");
        JLabel taskTitleLabel = new JLabel("タスク名");
        JLabel taskLimitLabel = new JLabel("期限");
        JLabel taskDixcriptionLabel = new JLabel("備考");
        
        taskIdLabel.setBounds(50, 50, 200, 40);
        taskTitleLabel.setBounds(150, 50, 200, 40);
        taskLimitLabel.setBounds(350, 50, 200, 40);
        taskDixcriptionLabel.setBounds(550, 50, 200, 40);
        
        taskId.setBounds(50, 100, 200, 40);
        taskTitle.setBounds(150, 100, 200, 40);
        taskLimit.setBounds(350, 100, 200, 40);
        taskDiscription.setBounds(550, 100, 200, 40);
        
        this.add(toDoBtn);
        this.add(doingBtn);
        this.add(doneBtn);
        this.add(taskIdLabel);
        this.add(taskTitleLabel);
        this.add(taskLimitLabel);
        this.add(taskDixcriptionLabel);
        this.add(taskId);
        this.add(taskTitle);
        this.add(taskLimit);
        this.add(taskDiscription);
    }
    
    public void panelChange(){
    	mainFrame.reloadPage(mainFrame.PanelNames[0]);
    	mainFrame.showMainPanel((JPanel)this);
    }
    
    public void setEditString(TaskDto task){
    	taskId.setText(String.valueOf(task.getId()));
    	if(task.getTitle() != null){
    		taskTitle.setText(task.getTitle());
    	}
    	if(task.getLimitDate() != null){
    		taskLimit.setText(task.getLimitDate());
    	}
    	if(task.getDiscription() != null){
    		taskDiscription.setText(task.getDiscription());
    	}
    }
    
    public void updateData(int status){
    	DBAccesser dbAccesser = new DBAccesser();
    	TaskDto task = new TaskDto();
    	task.setId(Integer.parseInt(taskId.getText()));
    	task.setTitle(taskTitle.getText());
    	task.setLimitDate(taskLimit.getText());
    	task.setDiscription(taskDiscription.getText());
    	task.setStatus(status);
    	dbAccesser.update(task);
    }
}