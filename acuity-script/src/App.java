import com.acuity.api.Events;
import com.acuity.api.rs.events.impl.PlayerAnimationChangeEvent;
import com.acuity.api.rs.events.impl.PlayerHealthChangeEvent;
import com.acuity.api.script.impl.AcuityScript;
import com.google.common.eventbus.Subscribe;

public class App extends AcuityScript {

    public App() {
        Events.getRsEventBus().register(this);
    }

    @Override
    public void loop() {

    }

    @Subscribe
    public void onAnimationChange(PlayerAnimationChangeEvent event) {
        if(event.getAnimation() != 836)
            return;
        //On death.
        System.out.println(event.getPlayer().getName() + " has died.");
    }

    @Subscribe
    public void OnHealthChange(PlayerHealthChangeEvent event) {

    }

    @Override
    public void onExit() {
        Events.getRsEventBus().unregister(this);
        System.out.println("Unregistering....");
        super.onExit();
    }
}
