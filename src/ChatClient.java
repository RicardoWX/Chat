import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ChatClient extends Frame{

	/**
	 * @param args
	 */
	TextField taText=new TextField();	
	TextArea taContent=new TextArea();
    Socket s = null;	
    DataOutputStream dos = null;
    DataInputStream dis =null;
	private boolean isConnected=false;
	
	
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
				disconnect();
				System.exit(0);
				
			}
        	
        });
		taText.addActionListener(new TFListener());
        pack();
		connect();
		new Thread(new RecvThread()).start();
	}
	
	public void connect(){
		try{
	     s=new Socket("localhost",7777);
	 	 dos=new DataOutputStream(s.getOutputStream());
	 	 dis=new DataInputStream(s.getInputStream());
	 	 isConnected=true;
	     //dos=new DataOutputStream(s.getOutputStream());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void disconnect(){
		try {
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
    }
	
	
	
	private class TFListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String str=taText.getText().trim();
			//taContent.setText(str);
			taText.setText("");
			try {
			
				dos.writeUTF(str);
				dos.flush();
				
			} catch (IOException e1) {
			
				e1.printStackTrace();		
				
			}		
		}
		
		
	}
	
	private class RecvThread implements Runnable{
		@Override
		public void run() {
			try {
				while(isConnected){
					String str=dis.readUTF();
					//System.out.println(str);
					taContent.setText(taContent.getText()+str+"\n");
				}
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("client close");
			}
		}
	}

	

}
