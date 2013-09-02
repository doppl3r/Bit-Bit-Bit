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
            if (!particles.get(i).isVisible()) particles.remove(i);
        }
    }
    public void addClusterAt(double x, double y, int amount){
        for (int i = 0; i < amount; i++){
            particles.add(new Particle(x,y));
        }
    }
    public void addClusterAt(double x, double y, int r, int g, int b, int amount){
        for (int i = 0; i < amount; i++){
            particles.add(new Particle(x,y));
            particles.get(particles.size()-1).setRGB(r,g,b);
        }
    }

    public class Particle {
        //color
        private int r;
        private int g;
        private int b;
        private int size;
        //position
        private double x;
        private double y;
        private double speedX; //negative will increment in the opposite direction
        private double speedY;
        //opacity properties
        private double alpha;
        private double alphaRate;

        public Particle(double x, double y){
            r = g = b = 255; //white
            this.x=x;
            this.y=y;
            speedX = (Math.random()*10)-5;
            speedY = (Math.random()*10)-5;
            alpha = 255;
            alphaRate = 5;
            size = 4;
        }
        public void draw(Graphics2D g){
            g.setColor(new Color(r,this.g,b,(int)alpha));
            g.fillRect((int)(x-(size/2)),(int)(y-(size/2)),size,size);
        }
        public void update(){
            //update transparency
            if (alpha - alphaRate > 0) alpha -= alphaRate;
            else alpha = 0;
            //update positioning
            x += speedX;
            y += speedY;
        }
        public void setRGB(int r, int g, int b){ this.r=r; this.g=g; this.b=b; }
        public boolean isVisible(){ return alpha > 0; }
    }
}
