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
}