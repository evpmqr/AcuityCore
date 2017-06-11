package maddev.dev.maddev;

import com.acuity.api.script.impl.AcuityScript;

/**
 * Created by maddev on 6/11/17.
 */
public class Main extends AcuityScript {

    @Override
    public void loop() {
        System.out.println("Hallo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
