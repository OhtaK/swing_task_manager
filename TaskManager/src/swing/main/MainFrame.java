package swing.main;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dto.TaskDto;

public class MainFrame extends JFrame{
	public String[] PanelNames = {"main","register","edit"};//この名前でパネルの指定をする
	
	//各種パネルをフィールドで作成
	MainPanel mainPanel = new MainPanel(this,PanelNames[0]);
    TaskRegisterPanel taskRegisterPanel = new TaskRegisterPanel(this,PanelNames[1]);
    TaskEditPanel taskEditPanel = new TaskEditPanel(this,PanelNames[2]);
    
    public MainFrame(){
        this.add(mainPanel);
        mainPanel.setVisible(true);
        
        this.add(taskRegisterPanel);
        taskRegisterPanel.setVisible(false);
        
        this.add(taskEditPanel);
        taskEditPanel.setVisible(false);
        
        this.setBounds(100, 100, 1200, 800);
    }
    
    public static void main(String[] args) {
    	int r = RandomUtils.nextInt(PER) + 1;
        
        
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    
    //ページの再描画用
    //mainPanelはこれをしないと変更が表示に反映されない
    //一応他の二つのパネル分も…
    public void reloadPage(String reloadPanelName){
    	if(reloadPanelName == PanelNames[0]){
    		this.remove(this.mainPanel);
    		MainPanel mainPanel = new MainPanel(this,PanelNames[0]);
    		this.add(mainPanel);
    	}
    	else if(reloadPanelName == PanelNames[1]){
    		this.remove(this.taskRegisterPanel);
    		TaskRegisterPanel taskRegisterPanel = new TaskRegisterPanel(this,PanelNames[1]);
    		this.add(taskRegisterPanel);
    	}
    	else if(reloadPanelName == PanelNames[2]){
    		this.remove(this.taskEditPanel);
    		TaskEditPanel taskEditPanel = new TaskEditPanel(this,PanelNames[2]);
    		this.add(taskEditPanel);
    	}
    }
    
    //パネル遷移メソッド
    //あらかじめ全パネルを作成して表示、非表示を切り替える方式
    
    //メインパネルを表示
    public void showMainPanel(JPanel nowPanel){
    	nowPanel.setVisible(false);
        mainPanel.setVisible(true);
    }
    
    //タスク登録パネルを表示
    public void showRegisterPanel(JPanel nowPanel){
    	nowPanel.setVisible(false);
        taskRegisterPanel.setVisible(true);
    }
    
    //タスク編集パネルを表示
    public void showEditPanel(JPanel nowPanel, TaskDto task){
    	nowPanel.setVisible(false);
        //初期文字列を設定
        taskEditPanel.setEditString(task);
        taskEditPanel.setVisible(true);
    }
}
