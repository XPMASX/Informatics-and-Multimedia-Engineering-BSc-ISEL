package aa;

import physics.Body;
import processing.core.PVector;

public class Cohesion extends Behaviour{
    public Cohesion(float weight) {
        super(weight);
    }

    @Override
    public PVector getDesiredVelocity(Boid me)
    {
        PVector target = me.getPos().copy();
        for (Body b :
                me.eye.getFarSight()) {
            target.add(b.getPos());
        }
        target.div(me.eye.getFarSight().size()+1);
        return PVector.sub(target, me.getPos());
    }
}
