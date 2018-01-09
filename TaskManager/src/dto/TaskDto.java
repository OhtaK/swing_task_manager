package dto;

public class TaskDto {

	//タスクID
	Integer id;
	
	//タスクの名前
	String title;
	
	//タスクの備考
	String discription;
	
	//タスクの期限
	String limitDate;
	
	//タスクのステータス
	Integer status;
	
	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return discription
	 */
	public String getDiscription() {
		return discription;
	}

	/**
	 * @param discription セットする discription
	 */
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	/**
	 * @return title
	 */
	public String getLimitDate() {
		return limitDate;
	}

	/**
	 * @param title セットする title
	 */
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}
	
	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status セットする status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
