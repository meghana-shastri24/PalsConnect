package meghana.model;

import java.util.Date;

public class OutputMessage extends Messagem {
	
private Date time;
	
	public OutputMessage(Messagem original,Date time) {
		super(original.getId(),original.getMessage());
		this.time = time;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}

}
