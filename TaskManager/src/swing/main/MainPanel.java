package swing.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import dto.TaskDto;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
    MainFrame mainFrame;
    
    public static HashMap<Integer,ArrayList<String>> taskSet = new HashMap<Integer,ArrayList<String>>();
    public static String nowSelectText;
    
    //各タスク表示用のリスト
    DefaultListModel<String> toDoListModel = new DefaultListModel<String>();
    JList<String> toDoList = new JList<String>();
    
    DefaultListModel<String> doingListModel = new DefaultListModel<String>();
    JList<String> doingList = new JList<String>();
    
    DefaultListModel<String> doneListModel = new DefaultListModel<String>();
    JList<String> doneList = new JList<String>();
    
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
    			panelChange(mainFrame.PanelNames[1]);
    		}
    	});
    	buttonPanel.add(createButton);

    	JButton editButton = new JButton("タスクを編集");
    	editButton.setPreferredSize(new Dimension(100,50));
    	editButton.addActionListener(new ActionListener(){
    		//編集ボタンを押したとき選択中の要素をパブリック変数にセット
    		//タスク編集パネルで使う
    		public void actionPerformed(ActionEvent e){
    			if(!toDoList.isSelectionEmpty()){
    				nowSelectText = toDoList.getSelectedValue();
    			}
    			else if(!doingList.isSelectionEmpty()){
    				nowSelectText = doingList.getSelectedValue();
    			}
    			else if(!doneList.isSelectionEmpty()){
    				nowSelectText = doneList.getSelectedValue();
    			}
    			else{
    				System.out.println("何も選択されていません");
    				return;
    			}
    			panelChange(mainFrame.PanelNames[2]);
    		}
    	});
    	buttonPanel.add(editButton);
    	this.add(buttonPanel);
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

    public void panelChange(String toPanelName){
    	if(toPanelName == mainFrame.PanelNames[1]){
    		mainFrame.showRegisterPanel((JPanel)this);
    	}
    	else if(toPanelName == mainFrame.PanelNames[2]){
    		mainFrame.showEditPanel((JPanel)this, nowSelectText);
    	}
    	else{
    		System.out.println("パネルの名前が不正です。");
    	}
    }
} 