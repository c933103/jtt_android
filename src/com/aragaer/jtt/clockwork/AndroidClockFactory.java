package com.aragaer.jtt.clockwork;
// vim: et ts=4 sts=4 sw=4

import android.content.Context;

import com.aragaer.jtt.astronomy.DayIntervalCalculator;
import com.aragaer.jtt.astronomy.SscCalculator;
import com.aragaer.jtt.location.AndroidLocationProvider;
import com.aragaer.jtt.location.LocationProvider;


public class AndroidClockFactory implements ComponentFactory {

    private final Astrolabe astrolabe;
    private final Chime chime;

    public AndroidClockFactory(Context context) {
        astrolabe = new Astrolabe(null, null, 1);
        chime = new Chime(null);
    }

    public static Clock createFromContext(Context context) {
        LocationProvider provider = new AndroidLocationProvider(context);
        DayIntervalCalculator calculator = new SscCalculator();
        Astrolabe astrolabe = new Astrolabe(calculator, provider, 1);
        Metronome metronome = new AndroidMetronome(context);
        Chime chime = new Chime(context);
        return new Clock(astrolabe, chime, metronome);
    }

    public Astrolabe getAstrolabe() {
        return astrolabe;
    }

    public Chime getChime() {
        return chime;
    }
}