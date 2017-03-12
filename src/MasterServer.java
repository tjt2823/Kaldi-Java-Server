import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import org.glassfish.grizzly.websockets.WebSocketEngine;

public class MasterServer {
	private static int PORT = 8080;
	private static Logger log;
	
	public static void main(String[] args) {
		log = Logger.getLogger(MasterServer.class.toString());
		log.log(Level.INFO, "Starting up Master Server");
		
		if(args.length < 1) {
			System.err.println("No port provided." + "\n\tUsage: java MasterServer <port>\n");
			log.log(Level.SEVERE, "No port number specified. Exiting.");
			System.exit(-1);
		}
		
		log.log(Level.INFO, "Using port = " + PORT);
		
		log.log(Level.INFO, "Creating Grizzly Http server at localhost:" + PORT);
		final HttpServer server = HttpServer.createSimpleServer(null, PORT);
		
		log.log(Level.INFO, "Attaching WebSocket add on to Http Server");
		server.getListener("grizzly").registerAddOn(new WebSocketAddOn());
		
		final WebSocketApplication wsDecoderHandler = new WSDecoderHandler();
		final WebSocketApplication statusHandler = new StatusHandler();
		final WebSocketApplication workerHandler = new WorkerHandler();
		
		log.log(Level.INFO, "Registering WSDecoderHandler with WebSocketEngine");
		WebSocketEngine.getEngine().register("/client/ws", "/speech", wsDecoderHandler);
		log.log(Level.INFO, "Registering StatusHandler with WebSocketEngine");
		WebSocketEngine.getEngine().register("/client/ws", "/status", statusHandler);
		log.log(Level.INFO, "Registering WorkerHandler with WebSocketEngine");
		WebSocketEngine.getEngine().register("/worker/ws", "/speech", workerHandler);
	}
	
	private static class WSDecoderHandler extends WebSocketApplication {
		
	}
	
	private static class StatusHandler extends WebSocketApplication {
		
	}
	
	private static class WorkerHandler extends WebSocketApplication {
		
	}
}