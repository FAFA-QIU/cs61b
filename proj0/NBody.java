public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readLine();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        int index = 0;
        in.readDouble();
        while (index < num) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[index++] = new Planet(xP, yP, xV, yV, mass, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        double time = 0;
        while (time <= T) {
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            int index = 0;
            for (Planet p : planets) {
                xForces[index] = p.calcNetForceExertedByX(planets);
                yForces[index++] = p.calcNetForceExertedByY(planets);
            }
            index = 0;
            for (Planet p : planets) {
                p.update(dt, xForces[index], yForces[index]);
                index++;
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
