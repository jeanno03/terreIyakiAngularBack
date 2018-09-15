package terreIyaki.tool;

public class ComentTool {
	
	private String comment;

	public ComentTool() {
		super();
	}

	public ComentTool(String comment) {
		this();
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ComentTool [comment=" + comment + "]";
	}
	

	
}
