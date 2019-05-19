
public class ExactSolution {

	private int TPointsNumber;
	private int HPointsNumber;
	
	private double x0;
	private double t0;
	
	private double h;
	private double tau;
	
	private DiffEquation diff;

	public ExactSolution(DiffEquation diff) {
		this.diff = diff;
		
		this.TPointsNumber = diff.getTPointsNumber();
		this.HPointsNumber = diff.getHPointsNumber();
		this.x0 = diff.getX0();
		this.t0 = diff.getT0();
		this.h = diff.getH();
		this.tau = diff.getTau();
	}

	public double[][] solve() {
		double trueMatrix[][] = new double[TPointsNumber][HPointsNumber];
		
		double t = t0;
		for (int i = 0; i < TPointsNumber; i++) {
			double x = x0;
			for (int j = 0; j < HPointsNumber; j++) {
				trueMatrix[i][j] = diff.calcExactSolution(x, t);
				x += h;
			}
			t += tau;
		}
		
		System.out.println("\nТочный результат:");
		diff.printMatrix(trueMatrix);

		return trueMatrix;
	}
}
