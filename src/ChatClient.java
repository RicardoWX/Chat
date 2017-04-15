import java.awt.*;

public class ChatClient extends Frame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatClient().lanuchFrame();
	}
	
	public void lanuchFrame(){
		setLocation(400,300);
		setSize(300,300);
		this.setVisible(true);
	}

}
