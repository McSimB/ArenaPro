package javax.microedition.lcdui;

public class AlertType {

	private AlertType(int type) {
		this.type = type;
	}

	static final int ALERT_INFO = 1;
	static final int ALERT_WARN = 2;
	static final int ALERT_ERR = 3;
	static final int ALERT_ALRM = 4;
	static final int ALERT_CFM = 5;
	public static final AlertType INFO = new AlertType(1);
	public static final AlertType WARNING = new AlertType(2);
	public static final AlertType ERROR = new AlertType(3);
	public static final AlertType ALARM = new AlertType(4);
	public static final AlertType CONFIRMATION = new AlertType(5);
	private int type;

}