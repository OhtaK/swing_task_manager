package swing.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import dto.TaskDto;

public class DBAccesser {
	public Connection con = null;
	public Statement smt = null;
	
	public List<TaskDto> selectAll(){
		try {
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
	
	public int selectLastId(){
		try {
    		// データベースに接続する なければ作成される
    		con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
    		smt = con.createStatement();
    		String sql = "select id from task order by id desc limit 1";
    		ResultSet rs = smt.executeQuery(sql);
    		int id = rs.getInt(1);
    		con.close();
    		return id;
    	}
		catch (SQLException e) {
    		// TODO 自動生成された catch ブロック
    		e.printStackTrace();
    		return 0;
    	}
	}
	
	public void insert(TaskDto task){
		try {
    		// データベースに接続する なければ作成される
    		con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
    		smt = con.createStatement();
    		String sql = "insert into task (id, name, limit_date, comment) values(" + String.valueOf(task.getId()) + ", '" + task.getTitle() + "', '" + task.getLimitDate() + "', '" + task.getDiscription() + "');";
    		smt.executeUpdate(sql);
    		con.close();
    	} catch (SQLException e) {
    		// TODO 自動生成された catch ブロック
    		e.printStackTrace();
    	}
	}
	
	public void update(TaskDto task){
		try {
			// データベースに接続する なければ作成される
			con = DriverManager.getConnection("jdbc:sqlite:/Users/keisuke-ota/taskManager.sqlite");
			smt = con.createStatement();
			String sql = "update task set name='"+ task.getTitle() + "', limit_date='" + task.getLimitDate() + "', comment='"+ task.getDiscription() + "', status = " + task.getStatus() + " where id=" + task.getId() + ";";
			smt.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
