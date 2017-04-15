import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer {

	/**
	 * @param args
	 */
	ServerSocket ss=null;
	boolean isRunning=false;
	
	public static void main(String[] args){ 
		
		new ChatServer().start();
		
	}
		
	
	public void start(){
		try {
			ss=new ServerSocket(8888);
			isRunning=true;
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			while(isRunning){
				Socket s=ss.accept();
				Client c=new Client(s);
System.out.println("a client is connect!");
				new Thread(c).start();
				//dis.close();
			}
		} catch (IOException e) {
			
			//e.printStackTrace();
			System.out.println("client is close!");
			
		}finally{
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class Client implements Runnable{
		private Socket s;
		private DataInputStream dis = null;
		private boolean isConnected=false;
		
		
		public Client(Socket s){
			this.s=s;
			try {
				dis=new DataInputStream(s.getInputStream());
				isConnected=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public void run() {
			// TODO Auto-generated method stub
			
			try {
				while (isConnected) {
					String str = dis.readUTF();
					System.out.println(str);
				}
			} catch (EOFException e) {
				System.out.println("Client closed");
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try {
						
					if(dis != null)dis.close();
					if(s!=null) s.close();
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		}
		
	}
	
	
}
