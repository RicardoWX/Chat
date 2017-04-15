import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class CharServer {

	/**
	 * @param args
	 */
	public static void main(String[] args){ 
		
		ServerSocket ss=null;
		Socket s=null;
		boolean isRunning=false;
		DataInputStream  dis=null;
		try {
			ss=new ServerSocket(8888);
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			isRunning=true;
			
			while(isRunning){
				boolean isConnected=false;
				s=ss.accept();
				isConnected=true;
System.out.println("a client is connect!");
				dis=new DataInputStream(s.getInputStream());
				while(isConnected){
				String str=dis.readUTF();
				System.out.println(str);
				}
				//dis.close();
			}
		} catch (IOException e) {
			
			//e.printStackTrace();
			System.out.println("client is close!");
			
		}finally{
			try {
				if(dis != null)dis.close();
				if(s != null) s.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}
