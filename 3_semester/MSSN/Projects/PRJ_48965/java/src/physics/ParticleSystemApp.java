package physics;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystemApp implements IProcessingApp {

    private List<ParticleSystem> pss;
    private double[] window = {-10, 10, -10,10};
    private float[] viewport = {0, 0, 1, 1};
    private SubPlot plt;
    private float[] velParams = {PApplet.radians(180), PApplet.radians(20), 1, 3};
    private float[] lifetimeParams = {3, 5};
    private float[] radiusParams = {0.1f, 0.3f};
    private float flow = 100;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window,viewport,p.width,p.height);
        pss = new ArrayList<ParticleSystem>();
    }


    @Override
    public void draw(PApplet p, float dt) {
        p.background(255);

        for(ParticleSystem ps : pss)
        {
            ps.applyForce(new PVector(0,0));
        }

        for (ParticleSystem ps: pss)
        {
            ps.move(dt);
            ps.display(p, plt);
        }

        velParams[0] = PApplet.map(p.mouseX, 0, p.width, PApplet.radians(0), PApplet.radians(360));
        for (ParticleSystem ps : pss)
        {
            PSControl psc = ps.getPSControl();
            psc.setVelPArams(velParams);
        }

    }

    @Override
    public void mousePressed(PApplet p) {

        double[] ww = plt.getWorldCoord(p.mouseX, p.mouseY);

        int color = p.color(p.random(255), p.random(255), p.random(255));

        PSControl psc = new PSControl(velParams, lifetimeParams, radiusParams, flow, color);
        ParticleSystem ps = new ParticleSystem(new PVector((float) ww[0], (float) ww[1]),
                new PVector(),1f, 1f, psc);
        pss.add(ps);
    }

    @Override
    public void keyPressed(PApplet p) {

    }

    @Override
    public void mouseReleased(PApplet p) {

    }

    @Override
    public void mouseDragged(PApplet p) {

    }
}
