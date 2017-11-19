package swing.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	public String[] PanelNames = {"top","Sub", "Edit"};
	MainPanel mainPanel = new MainPanel(this,PanelNames[0]);
    TaskRegisterPanel taskRegisterPanel = new TaskRegisterPanel(this,PanelNames[1]);
    TaskEditPanel taskEditPanel = new TaskEditPanel(this,PanelNames[2]);
    MainFrame mf;
    
    public MainFrame(){
    	MainPanel mainPanel = new MainPanel(this,PanelNames[0]);
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
    
    public void reloadPage(String panelName, JPanel jp){
    	
    	MainFrame mainFrame = new MainFrame();
    	mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	mainFrame.setVisible(true);
        
    	if(panelName == PanelNames[0]){
    		mainFrame.remove((MainPanel)jp);
    		MainPanel mainPanel = new MainPanel(this,PanelNames[0]);
    		mainFrame.add(mainPanel);
    	}
    	else if(panelName == PanelNames[1]){
    		mainFrame.remove((TaskRegisterPanel)jp);
    		TaskRegisterPanel taskRegisterPanel = new TaskRegisterPanel(this,PanelNames[1]);
    		mainFrame.add(taskRegisterPanel);
    	}
    	else if(panelName == PanelNames[2]){
    		mainFrame.remove((TaskEditPanel)jp);
    		TaskEditPanel taskEditPanel = new TaskEditPanel(this,PanelNames[2]);
    		mainFrame.add(taskEditPanel);
    	}
    }
    
    //パネル遷移メソッド
    public void panelChange(JPanel nowPanel, String toPanelName, String EditString){
    	String nowPanelName = nowPanel.getName();
        
        //遷移元のパネルを非表示
        if(nowPanelName == PanelNames[0]){
        	mainPanel = (MainPanel)nowPanel;
        	nowPanel.setVisible(false);
        }
        else if(nowPanelName == PanelNames[1]){
        	taskRegisterPanel = (TaskRegisterPanel)nowPanel;
        	taskRegisterPanel.setVisible(false);
        }
        else if(nowPanelName == PanelNames[2]){
        	taskEditPanel = (TaskEditPanel)nowPanel;
        	taskEditPanel.setVisible(false);
        }
        
        //遷移先のパネルを表示
        if(toPanelName == PanelNames[0]){
        	mainPanel.setVisible(true);
        }
        else if(toPanelName == PanelNames[1]){
        	taskRegisterPanel.setVisible(true);
        }
        else if(toPanelName == PanelNames[2]){
        	//編集パネルだけ初期文字列を設定
        	taskEditPanel.setEditString(EditString);
        	taskEditPanel.setVisible(true);
        }
    }
}
