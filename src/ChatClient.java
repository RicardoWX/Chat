import java.awt.*;

public class ChatClient extends Frame{

	/**
	 * @param args
	 */
	TextField taText=new TextField();
	TextArea taContent=new TextArea();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatClient().lanuchFrame();
	}
	
	public void lanuchFrame(){
		setLocation(400,300);
		setSize(300,300);
		this.setVisible(true);
		add(taText,BorderLayout.SOUTH);
		add(taContent,BorderLayout.NORTH);
		pack();
	}

}
