package com.acuity.api.applet;

import com.acuity.api.Events;
import com.acuity.api.applet.input.KeyboardMiddleMan;
import com.acuity.api.applet.input.MouseMiddleMan;
import com.acuity.api.applet.loader.ClientConfig;
import com.acuity.api.applet.loader.ClientEnviroment;
import com.acuity.api.applet.loader.ClientStub;
import com.acuity.api.applet.loader.RSClassLoader;
import com.acuity.api.rs.events.GameStateChangeEvent;
import com.acuity.api.rs.utils.Game;
import com.acuity.api.rs.wrappers.engine.Client;
import com.acuity.rs.api.RSClient;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Zach on 6/17/2017.
 */
public class AppletManager {

    private static final Logger logger = LoggerFactory.getLogger(AppletManager.class);


    private RSClassLoader rsClassLoader;

    private ClientConfig clientConfig;
    private ClientEnviroment<Client> clientEnviroment;
    private ClientStub clientStub;

    private MouseMiddleMan mouseMiddleMan = new MouseMiddleMan();
    private KeyboardMiddleMan keyboardMiddleMan = new KeyboardMiddleMan();


    public AppletManager() throws Exception {
        Events.getRsEventBus().register(this);
    }

    @SuppressWarnings("unchecked")
    public void load() throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.info("RSClient loading started.");
        clientConfig = new ClientConfig(1);
        rsClassLoader = new RSClassLoader(new File(getClass().getClassLoader().getResource("Injected Gamepack.jar").getFile()));
        Class<?> client = rsClassLoader.loadClass("client");
        clientEnviroment = new ClientEnviroment(((RSClient) client.newInstance()).getWrapper());
        clientStub = new ClientStub(clientConfig);
    }

    public void boot() {
        clientEnviroment.boot(clientStub);
    }

    @Subscribe
    public void gameStateChanged(GameStateChangeEvent changeEvent){
        if (changeEvent.getPreviousGameState() == Game.CLIENT_LOADING && changeEvent.getGamestate() == Game.LOGIN_SCREEN){
            mouseMiddleMan.replace(getClient().getCanvas());
            keyboardMiddleMan.replace(getClient().getCanvas());
            getClient().getRsClient().setRenderMode(2);
        }
    }

    public Client getClient() {
        return Preconditions.checkNotNull(clientEnviroment, "Load the RS client before referencing it.").getGameEngine();
    }

    public MouseMiddleMan getMouseMiddleMan() {
        return mouseMiddleMan;
    }

    public KeyboardMiddleMan getKeyboardMiddleMan() {
        return keyboardMiddleMan;
    }
}
