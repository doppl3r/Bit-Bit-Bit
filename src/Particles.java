import java.awt.*;
import java.util.LinkedList;

public class Particles {
    private LinkedList<Particle> particles;

    public Particles(){
        particles = new LinkedList<Particle>();
    }
    public void draw(Graphics2D g){
        int size = particles.size();
        for (int i = 0; i < size; i++){
            particles.get(i).draw(g);
        }
    }
    public void update(){
        for (int i = 0; i < particles.size(); i++){
            particles.get(i).update();
        }
    }
    public void addClusterAt(int x, int y, int amount){
        for (int i = 0; i < amount; i++){
            particles.add(new Particle(x,y));
        }
    }

    public class Particle {
        //color
        private int r;
        private int g;
        private int b;
        //position
        private double x;
        private double y;
        private double speedX; //negative will increment in the opposite direction
        private double speedY;
        //opacity properties
        private double alpha;
        private double alphaRate;

        public Particle(double x, double y){
            this.x=x;
            this.y=y;
            speedX = (Math.random()*10)-5;
            speedY = (Math.random()*10)-5;
            alpha = 255;
            alphaRate = 5;
        }
        public void draw(Graphics2D g){

        }
        public void update(){
            if (alpha - alphaRate > 0) alpha -= alphaRate;
            else alpha = 0;
        }
        public boolean isVisible(){ return alpha==0; }
    }
}
