import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

public class ChatClient extends Frame{

	/**
	 * @param args
	 */
	TextField taText=new TextField();
	
	TextArea taContent=new TextArea();
	
	public static void main(String[] args) {
		new ChatClient().lanuchFrame();
	}
	
	public void lanuchFrame(){
		setLocation(400,300);
		this.setSize(300,300);
		this.setVisible(true);
		add(taText,BorderLayout.SOUTH);
		add(taContent,BorderLayout.NORTH);
        this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
        	
        });
		taText.addActionListener(new TFListener());
        pack();
		connect();
	}
	
	public void connect(){
		try{
		Socket s=new Socket("localhost",8888);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private class TFListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String s=taText.getText().trim();
			taContent.setText(s);
			taText.setText("");

		}
		
		
	}

}
