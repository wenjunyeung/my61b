public class NBody {
	public static double readRadius (String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double R = in.readDouble();
		return R;
	}

	public static Planet[] readPlanets (String fileName) {
		In in = new In (fileName);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] allPlanets = new Planet[N];

		for (int i=0; i<N; i++){
			allPlanets[i] = new Planet (in.readDouble(), in.readDouble(), 
										in.readDouble(), in.readDouble(),
										in.readDouble(), in.readString());
		}
		return allPlanets;
	}

	public static void main (String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		
		StdDraw.setScale(-radius, radius);
		Planet[] allPlanets = NBody.readPlanets(filename);
		/* Drawing the background 
		
		StdDraw.picture(0, 0, "images/starfield.jpg");
        
		/* Drawing all of the planets 
		
		for (Planet p : allPlanets) {
			p.draw();
		}
		*/

		StdDraw.enableDoubleBuffering();
		double t = 0;
		int N = allPlanets.length;
		double[] xForces = new double[N];
		double[] yForces = new double[N]; 
		while(t < T ){

			for (int i=0; i<N; i++) {
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			} 

			for (int i=0; i<N; i++) {
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Planet p : allPlanets) {
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
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