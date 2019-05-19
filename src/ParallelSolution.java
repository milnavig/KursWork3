
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ParallelSolution {

	private int TPointsNumber;
	private int HPointsNumber;
	private double x0;
	private double t0;
	
	private double h;
	private double tau;
	
	private DiffEquation diff;

	public ParallelSolution(DiffEquation diff) {
		this.diff = diff;
		
		this.TPointsNumber = diff.getTPointsNumber();
		this.HPointsNumber = diff.getHPointsNumber();
		
		this.x0 = diff.getX0();
		this.t0 = diff.getT0();
		
		this.h = diff.getH();
		this.tau = diff.getTau();
	}

	public double[][] solve() {
		double x = x0;
		double w[][] = new double[TPointsNumber][HPointsNumber];
		
		/*
		for (int j = 0; j < HPointsNumber; j++, x += h) {
			w[0][j] = diff.calcInitialCondition(x);
		}*/
		
		IntStream.range(0, diff.getHPointsNumber()).parallel().forEach(j -> {
			w[0][j] = diff.calcInitialCondition(j*h);
		});
		
		double t = t0 + tau;

		for (int i = 1; i < TPointsNumber; ++i, t += tau) {
			w[i][0] = diff.calcLeftBorder(t);
			w[i][HPointsNumber - 1] = diff.calcRightBorder(t);
			
			int index = i;
			
			IntStream.range(1, diff.getHPointsNumber()-1).parallel().forEach(j -> {
				w[index][j] = diff.calcApproximateSolution(w[index-1][j-1],w[index-1][j],w[index-1][j+1]);
			});
		}
		return w;
	}
}
