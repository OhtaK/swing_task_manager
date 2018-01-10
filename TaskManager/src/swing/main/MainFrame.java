package swing.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	public String[] PanelNames = {"main","register","edit"};
	MainPanel mainPanel = new MainPanel(this,PanelNames[0]);
    TaskRegisterPanel taskRegisterPanel = new TaskRegisterPanel(this,PanelNames[1]);
    TaskEditPanel taskEditPanel = new TaskEditPanel(this,PanelNames[2]);
    public MainFrame mf;
    
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
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    
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
    public void showEditPanel(JPanel nowPanel, String EditString){
    	nowPanel.setVisible(false);
        //初期文字列を設定
        taskEditPanel.setEditString(EditString);
        taskEditPanel.setVisible(true);
    }
}
