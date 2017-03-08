import java.util.logging.Level;
import java.util.logging.Logger;

public class MasterServer {
	private static int PORT = 8080;
	private static Logger log;
	
	public static void main(String[] args) {
		log = Logger.getLogger(MasterServer.class.toString());
		log.log(Level.INFO, "Starting up Master Server");
	}
}