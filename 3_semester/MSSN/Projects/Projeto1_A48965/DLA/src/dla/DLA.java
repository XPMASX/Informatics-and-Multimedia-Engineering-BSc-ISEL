package dla;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;

import java.util.ArrayList;
import java.util.List;

public class DLA implements IProcessingApp {

    private List<Walker> walkers;
    private int num_Walkers = 20;
    private int num_Steps = 150;

    @Override
    public void setup(PApplet p)
    {
        walkers = new ArrayList<Walker>();

        Walker w = new Walker(p,new PVector(p.width/2,p.height/2));
        walkers.add(w);


        for (int i = 0;i<num_Walkers;i++) {
            w = new Walker(p);
            walkers.add(w);
        }
    }

    @Override
    public void draw(PApplet p, float dt)
    {
        p.background(190);

        for (int i = 0; i<num_Steps;i++){
            for (Walker w : walkers) {
                if (w.getState() == Walker.State.WANDER) {
                    w.wander(p);
                    w.updateState(p, walkers);
                }
            }
        }

        for (Walker w : walkers) {
            w.display(p);
        }

        while (Walker.num_wanders!=num_Walkers)
            walkers.add(new Walker(p));

        System.out.println("Stopped = " + Walker.num_stopped + "Wanders = " + Walker.num_wanders);
    }

    @Override
    public void mousePressed(PApplet p)
    {

    }

}
