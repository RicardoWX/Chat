import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class ChatServer {

	/**
	 * @param args
	 */
	ServerSocket ss = null;
	private boolean isRunning = false;
	
	List<Client> clients=new ArrayList<Client>();
	
	public static void main(String[] args){ 
		
		new ChatServer().start();
		
	}
		
	
	public void start(){
		try {
			ss = new ServerSocket(7777);
			isRunning=true;
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			while(isRunning){
				Socket s = ss.accept();
				Client c = new Client(s);
System.out.println("a client is connect!");
				new Thread(c).start();
				clients.add(c);
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
		private boolean isConnected = false;
		private DataOutputStream dos = null;
		
		
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
	
		public void send(String str){
			
			try {
				dos=new DataOutputStream(s.getOutputStream());
				dos.writeUTF(str);
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
					for(int i=0;i<clients.size();i++){
						Client c=clients.get(i);
						c.send(str);
					}
				}
			} catch (EOFException e) {
				System.out.println("Client closed");
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try {
						
					if(dis != null)dis.close();
					if(dos != null)dos.close();
					if(s != null) s.close();
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		}
		
	}
	
	
}
