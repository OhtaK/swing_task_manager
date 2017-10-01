package swing.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame{
	public String[] PanelNames = {"top","Sub", "Edit"};
	MainPanel mp = new MainPanel(this,PanelNames[0]);
    TaskRegisterPanel trp = new TaskRegisterPanel(this,PanelNames[1]);
    TaskEditPanel tep = new TaskEditPanel(this,PanelNames[2]);
    MainFrame mf;
    
    public MainFrame(){
    	MainPanel mp = new MainPanel(this,PanelNames[0]);
        this.add(mp);mp.setVisible(true);
        this.add(trp);trp.setVisible(false);
        this.add(tep);tep.setVisible(false);
        this.setBounds(100, 100, 1200, 800);
    }
    
    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
    
    public void reloadPage(String panelName, JPanel jp){
    	
    	MainFrame mf = new MainFrame();
        mf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mf.setVisible(true);
        
    	if(panelName==PanelNames[0]){
    		mf.remove((MainPanel)jp);
    		MainPanel mp = new MainPanel(this,PanelNames[0]);
    		mf.add(mp);
    	}
    	else if(panelName==PanelNames[1]){
    		mf.remove((TaskRegisterPanel)jp);
    		TaskRegisterPanel trp = new TaskRegisterPanel(this,PanelNames[1]);
    		mf.add(trp);
    	}
    	else if(panelName==PanelNames[2]){
    		mf.remove((TaskEditPanel)jp);
    		TaskEditPanel tep = new TaskEditPanel(this,PanelNames[2]);
    		mf.add(tep);
    	}
    }
    
    public void PanelChange(JPanel jp, String str, String EditString){
        String name = jp.getName();
        if(name==PanelNames[0]){
            mp = (MainPanel)jp;
            jp.setVisible(false);
        }else if(name==PanelNames[1]){
            trp = (TaskRegisterPanel)jp;
            trp.setVisible(false);
        }
        else if(name==PanelNames[2]){
            tep = (TaskEditPanel)jp;
            tep.setVisible(false);
        }
        if(str==PanelNames[0]){
            mp.setVisible(true);
        }else if(str==PanelNames[1]){
            trp.setVisible(true);
        }
        else if(str==PanelNames[2]){
        	tep.setEditString(EditString);
            tep.setVisible(true);
        }
    }
}
