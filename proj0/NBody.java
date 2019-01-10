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

		/* Drawing the background */
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		/* Drawing all of the planets */
		Planet[] allPlanets = NBody.readPlanets(filename);
		for (Planet p : allPlanets) {
			p.draw();
		}
	}
}