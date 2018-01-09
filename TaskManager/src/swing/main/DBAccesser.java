package swing.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.TaskDto;

public class DBAccesser {
	Connection con = null;
	Statement smt = null;
	
	public void insert(String data){
		
	}
	
	public List<TaskDto> selectAll(){
		try {
    		// JDBCドライバーの指定
    		//Class.forName("org.sqlite.JDBC");

    		// データベースに接続する なければ作成される
    		con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
    		smt = con.createStatement();
    		String sql = "select * from task";
    		ResultSet rs = smt.executeQuery(sql);
    		List<TaskDto> tasks = new ArrayList<TaskDto>();
    		while( rs.next() ) {
    			TaskDto task = new TaskDto();
    			task.setId(rs.getInt(1));
        		task.setTitle(rs.getString(2));
        		task.setDiscription(rs.getString(3));
        		task.setLimitDate(rs.getString(4));
        		task.setStatus(rs.getInt(5));
        		tasks.add(task);
    		}
    		con.close();
    		return tasks;
    	}
		catch (SQLException e) {
    		// TODO 自動生成された catch ブロック
    		e.printStackTrace();
    		return null;
    	}
	}
	
	public void update(String data){
		
	}
}
