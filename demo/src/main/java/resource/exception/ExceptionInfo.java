package resource.exception;

public class ExceptionInfo {
	private int status;
	private String msg, desc;

	public ExceptionInfo(int status, String msg, String desc) {
		super();
		this.status = status;
		this.msg = msg;
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}