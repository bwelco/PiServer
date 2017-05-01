package com.bwelco.piserver.socket;

import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.Pwm;

import java.io.IOException;

/**
 * Created by bwelco on 2017/5/1.
 */

public class DoorController {

    private final static String PWM0 = "PWM0";
    private static DoorController instance;
    private Pwm pwmController;

    private DoorController() {
        PeripheralManagerService manager = new PeripheralManagerService();
        try {
            pwmController = manager.openPwm(PWM0);
            pwmController.setPwmFrequencyHz(50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // degree from -90 - 90
    public void setDegree(int degree) {
        try {
            pwmController.setEnabled(false);
            pwmController.setPwmDutyCycle(getDutyCycle(degree));
            pwmController.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public float getDutyCycle(int degree) {
        int length = 10;
        int startDegree = -90;
        int len = degree - startDegree;
        return (((float) length * ((float) len / 180f)) + 2.5f);
    }

    public static DoorController getInstance() {
        if (null == instance) {
            synchronized (DoorController.class) {
                if (null == instance) {
                    instance = new DoorController();
                }
            }
        }
        return instance;
    }

    public void reset() {
        setDegree(0);
    }

    public void close() {
        if (pwmController != null) {
            try {
                pwmController.close();
                pwmController = null;
            } catch (IOException e) {

            }
        }
    }
}
