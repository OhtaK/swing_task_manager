package swing.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import dto.TaskDto;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
    MainFrame mainFrame;
    //各タスク表示用のリスト
    DefaultListModel<String> toDoListModel = new DefaultListModel<String>();
    JList<String> toDoList = new JList<String>();
    
    DefaultListModel<String> doingListModel = new DefaultListModel<String>();
    JList<String> doingList = new JList<String>();
    
    DefaultListModel<String> doneListModel = new DefaultListModel<String>();
    JList<String> doneList = new JList<String>();
    
    JLabel notSelectedErrorLabel = new JLabel("何も選択されていません。選択してからもう一度押してください。");
    JLabel deletedAnnotationLabel = new JLabel("タスクを消去しました。どこかのボタンを押せば表示に反映されます。");
    boolean isNotSelectedError = false;
    
    //panel作成
    MainPanel(MainFrame mf,String  name){
    	mainFrame = mf;
    	this.setName(name);
    	this.setLayout(null);

    	this.setBounds(100, 100, 1000, 800);
    	this.setLayout(new FlowLayout());

    	//TODOタスク表示
    	makeTaskPanel("TODO", this);

    	//DOINGタスク表示
    	makeTaskPanel("DOING", this);

    	//DONEタスク表示
    	makeTaskPanel("DONE", this);

    	//ボタン配置
    	JPanel buttonPanel = new JPanel();
    	JButton createButton = new JButton("新規作成");
    	createButton.setPreferredSize(new Dimension(100,50));
    	createButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			panelChangeToRegister(mainFrame.PanelNames[1]);
    		}
    	});
    	buttonPanel.add(createButton);

    	JButton editButton = new JButton("タスクを編集");
    	editButton.setPreferredSize(new Dimension(100,50));
    	editButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			//ボタンを押したとき選択中の要素をdtoにセット
    			TaskDto task = setTaskDtoFromSelectedString();
    			if(!isNotSelectedError){
    				panelChangeToEdit(mainFrame.PanelNames[2], task);
    			}
    		}
    	});
    	buttonPanel.add(editButton);
    	
    	JButton deleteButton = new JButton("タスクを消去");
    	deleteButton.setPreferredSize(new Dimension(100,50));
    	deleteButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			//ボタンを押したとき選択中の要素をdtoにセット
    			TaskDto task = setTaskDtoFromSelectedString();
            	DBAccesser dbAccesser = new DBAccesser();
            	dbAccesser.delete(task.getId());
            	deletedAnnotationLabel.setVisible(true);
    		}
    	});
    	buttonPanel.add(deleteButton);
    	this.add(buttonPanel);
    	
    	notSelectedErrorLabel.setVisible(false);
    	deletedAnnotationLabel.setVisible(false);
    	this.add(notSelectedErrorLabel);
    	this.add(deletedAnnotationLabel);
    }
        
    public void makeTaskPanel(String panelTitle, MainPanel mainPanel){

    	//titleに指定したタイトルテキストをつけてタスクパネルを作ります
    	JPanel taskPanel = new JPanel();
    	JPanel titlePanel = new JPanel();
    	JLabel titleLabel = new JLabel(panelTitle);
    	JPanel taskListPanel = new JPanel();
    	LineBorder border = new LineBorder(Color.BLACK, 2, true);

    	//タスク表示領域配置
    	taskPanel.setPreferredSize(new Dimension(300, 600));
    	taskPanel.setBackground(Color.WHITE);

    	//タイトルを置く用のパネル
    	titlePanel.setPreferredSize(new Dimension(300, 60));
    	titlePanel.setBackground(Color.WHITE);
    	titlePanel.setAlignmentY(0.0f);
    	taskPanel.add(titlePanel);

    	// 一覧を生成
    	taskListPanel.setPreferredSize(new Dimension(290, 540));
    	taskListPanel.setBackground(Color.WHITE);
    	
    	DBAccesser dbAccesser = new DBAccesser();
    	List<TaskDto> tasks = dbAccesser.selectAll();
    	
    	for(TaskDto task : tasks) {
			String listElement = String.valueOf(task.getId()) + ":" + task.getTitle() + ":" + task.getDiscription() + ":" + task.getLimitDate();
			if(panelTitle == "TODO" && task.getStatus() == 0){
				toDoListModel.addElement(listElement);
			}
			else if(panelTitle == "DOING" && task.getStatus() == 1){
				doingListModel.addElement(listElement);
			}
			else if(panelTitle == "DONE" && task.getStatus() == 2){
				doneListModel.addElement(listElement);
			}
		}
    	
    	if(panelTitle == "TODO"){
    		toDoList.setModel(toDoListModel);
    		taskListPanel.add(toDoList);
    	}
    	else if(panelTitle == "DOING"){
    		doingList.setModel(doingListModel);
    		taskListPanel.add(doingList);
    	}
    	else if(panelTitle == "DONE"){
    		doneList.setModel(doneListModel);
    		taskListPanel.add(doneList);
    	}

    	taskPanel.add(taskListPanel);

    	//タイトル配置
    	titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 32));
    	titleLabel.setAlignmentX(0.5f);
    	titlePanel.add(titleLabel);

    	//枠線
    	taskPanel.setBorder(border);
    	mainPanel.add(taskPanel);
    }
    
    public TaskDto setTaskDtoFromSelectedString(){
    	isNotSelectedError = false;
    	TaskDto task = new TaskDto();
		String nowSelectText;
		if(!toDoList.isSelectionEmpty()){
			nowSelectText = toDoList.getSelectedValue();
			task.setStatus(0);
		}
		else if(!doingList.isSelectionEmpty()){
			nowSelectText = doingList.getSelectedValue();
			task.setStatus(1);
		}
		else if(!doneList.isSelectionEmpty()){
			nowSelectText = doneList.getSelectedValue();
			task.setStatus(2);
		}
		else{
			notSelectedErrorLabel.setVisible(true);
			isNotSelectedError = true;
			return null;
		}
		
		String[] nowSelectTexts = new String[4];
		nowSelectTexts = nowSelectText.split(":", 0);
		
		task.setId(Integer.parseInt(nowSelectTexts[0]));
		//配列の末尾に空文字が入っていると配列の長さが変わってしまうみたいなのでこう場合分けします。
		//配列の途中の空文字は空文字として認識されてるっぽい…
		if(nowSelectTexts.length > 1){
			task.setTitle(nowSelectTexts[1]);
		}
		if(nowSelectTexts.length > 2){
			task.setLimitDate(nowSelectTexts[2]);
		}
		if(nowSelectTexts.length > 3){
			task.setDiscription(nowSelectTexts[3]);
		}
    	
    	return task;
    }
    
    public void panelChangeToRegister(String toPanelName){
    	mainFrame.showRegisterPanel((JPanel)this);
    }
    
    public void panelChangeToEdit(String toPanelName, TaskDto task){
    	mainFrame.showEditPanel((JPanel)this, task);
    }
} 