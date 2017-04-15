import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class CharServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss=new ServerSocket(8888);
			
			while(true){
				Socket s=ss.accept();
System.out.println("a client is connect!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
