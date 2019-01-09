public class Planet {

		public static final double G = 6.67e-11;
		public double xxPos;
		public double yyPos;
		public double xxVel;
		public double yyVel;
		public double mass;
		public String imgFileName;

	public Planet (double xP, double yP, double xV,
				   double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet (Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/** Calculates the distance between two planets
	*/
	public double calcDistance (Planet p) {
		double distance;
		distance = Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)
							+ (yyPos - p.yyPos)*(yyPos -p.yyPos));
		return distance;
	}

	/** Calculates the force exerted on this planet by the given planet
	*/

	public double calcForceExertedBy (Planet p) {
		double force;
		double distance = this.calcDistance(p);
		
		force = G * this.mass * p.mass / (distance * distance);
		return force;
	}

	/** calculates the force exerted by X axixs
	*/
	public double calcForceExertedByX (Planet p) {
		double forceByX;
		double distance = calcDistance(p);
		double force = this.calcForceExertedBy(p);
		forceByX = force * (p.xxPos - xxPos) / distance;
		return forceByX; 
	}

	/** calculates the force exerted by Y axixs
	*/

	public double calcForceExertedByY (Planet p) {
		double forceByY;
		double distance = calcDistance(p);
		double force = this.calcForceExertedBy(p);
		forceByY = force * (p.yyPos - yyPos) / distance;
		return forceByY; 
	}

	/** calculates the net X, Y force exerted by all planets in the given array
	*/
	public double calcNetForceExertedByX (Planet[] allPlanets) {
		
	}
}