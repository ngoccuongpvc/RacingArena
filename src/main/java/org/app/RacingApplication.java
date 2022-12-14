package org.app;

import org.app.controllers.Controller;
import org.app.controllers.GameController;
import org.app.controllers.PingController;
import org.app.model.GameModel;
import org.app.socket.ConnectionManager;
import org.app.socket.TCPServer;

import java.io.IOException;
import java.util.logging.Logger;


public class RacingApplication {
    static Logger logger = Logger.getLogger("root");
    static int APPLICATION_PORT = 6969;

    public static void main(String[] args) throws InterruptedException {
        logger.info(String.format("Starting TCP Server at port %d", APPLICATION_PORT));
        TCPServer server = null;
        GameModel gameModel = new GameModel(logger);
        try {
            server = new TCPServer(APPLICATION_PORT, logger, gameModel);
        } catch (IOException exception) {
            logger.severe(exception.getMessage());
            return;
        }

        GameController gameController = new GameController(logger, gameModel);

        logger.info("Successfully!");

        server.start();
        gameController.start();
    }
}