
public class DiffEquation {

	private final double x0 = 0;
	private final double x1 = 1;
	private final double h = 1.0/30;
	
	private final double t0 = 0;
	private final double t1 = 1;
	private final double tau = 1.0/900;
	
	private final double a = 0.00006;
	private final double b = 0.00004;
	
	private final double A = 1.0;
	private final double B = 2.8;

	private double sgm = tau / (h * h);
	
	public int getTPointsNumber() {
		return (int) Math.ceil((t1 - t0) / tau) + 1;
	}

	public int getHPointsNumber() {
		return (int) Math.ceil((x1 - x0) / h) + 1;
	}
	
	public double calcExactSolution(double x, double t) {
		return Math.pow((Math.pow(x, 2)/(B-8*a*t) + A*Math.pow(B-8*a*t, -1.0/4) - (b*(B-8*a*t))/(15*a)), 3.0/2);
	}

	public double calcInitialCondition(double x) {
		return Math.pow((Math.pow(x, 2)/B + A*Math.pow(B, -1.0/4) - (b*B)/(15*a)), 3.0/2);
	}

	public double calcLeftBorder(double t) {
		return Math.pow((A*Math.pow(B-8*a*t, -1.0/4) - (b*(B-8*a*t))/(15*a)), 3.0/2);
	}

	public double calcRightBorder(double t) {
		return Math.pow((1.0/(B-8*a*t) + A*Math.pow(B-8*a*t, -1.0/4) - (b*(B-8*a*t))/(15*a)), 3.0/2);
	}
	
	public double calcApproximateSolution(double wLeft, double wCentral , double wRight) {
		return (wCentral + a*sgm*((1/6)*Math.pow(wCentral, -1.0/3)*Math.pow(wRight - wLeft, 2) + Math.pow(wCentral, 2.0/3)*(wRight - 2*wCentral + wLeft)) + b*tau*Math.pow(wCentral, 1.0/3));
	}
	
	public void printMatrix(double[][] matrix) {
		System.out.print("{");
		for (int i = 0; i < matrix.length; ++i) {
			System.out.print("{");
			for (int j = 0; j < matrix[i].length; ++j) {
				//System.out.print(String.format("%.6f\t", matrix[i][j]));
				System.out.print(matrix[i][j]);
				if (j != matrix[i].length-1) System.out.print(", ");
			}
			if (i != matrix.length-1) {
				System.out.print("},");
			} else System.out.print("}");
			System.out.println();
		}
		System.out.print("}");
	}
	
	public double getX0() {
		return x0;
	}

	public double getX1() {
		return x1;
	}

	public double getT0() {
		return t0;
	}

	public double getT1() {
		return t1;
	}
	
	public double getH() {
		return h;
	}

	public double getTau() {
		return tau;
	}
}
