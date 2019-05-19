
public class MathError {
	
	private DiffEquation diff;
	private double[][] DifferenceSchemeSolve;
	private double[][] trueMatrix;
	
	public MathError(DiffEquation diff, double[][] DifferenceSchemeSolve, double[][] trueMatrix) {
		this.diff = diff;
		this.DifferenceSchemeSolve = DifferenceSchemeSolve;
		this.trueMatrix = trueMatrix;
	}
	
	public double mediumAbsoluteError() {
		double[][] errorMatrix = new double[diff.getTPointsNumber()][diff.getHPointsNumber()];
		double error = 0.0;
		
		for (int i = 0; i < diff.getTPointsNumber(); i++) {
			for (int j = 0; j < diff.getHPointsNumber(); j++) {
				errorMatrix[i][j] = Math.abs(DifferenceSchemeSolve[i][j] - trueMatrix[i][j]);
				error += errorMatrix[i][j];
			}
		}
		error = error / (diff.getTPointsNumber() * diff.getHPointsNumber());
		return error;
	}
	
	public double maxAbsoluteError() {
		double[][] errorMatrix = new double[diff.getTPointsNumber()][diff.getHPointsNumber()];
		double error = 0.0;
		
		for (int i = 0; i < diff.getTPointsNumber(); i++) {
			for (int j = 0; j < diff.getHPointsNumber(); j++) {
				errorMatrix[i][j] = Math.abs(DifferenceSchemeSolve[i][j] - trueMatrix[i][j]);
				if(i == 0 && j == 0) { error = errorMatrix[i][j];}
				if (error < errorMatrix[i][j]) {
					error = errorMatrix[i][j];
				}
			}
		}
		return error;
	}
	
	public double mediumRelativeError() {
		double[][] errorMatrix = new double[diff.getTPointsNumber()][diff.getHPointsNumber()];
		double error = 0.0;
		
		for (int i = 0; i < diff.getTPointsNumber(); i++) {
			for (int j = 0; j < diff.getHPointsNumber(); j++) {
				errorMatrix[i][j] = 100 * (Math.abs(DifferenceSchemeSolve[i][j] - trueMatrix[i][j])) / trueMatrix[i][j];
				error += errorMatrix[i][j];
			}
		}
		error = error / (diff.getTPointsNumber() * diff.getHPointsNumber());
		return error;
	}
	
	public double maxRelativeError() {
		double[][] errorMatrix = new double[diff.getTPointsNumber()][diff.getHPointsNumber()];
		double error = 0.0;
		
		for (int i = 0; i < diff.getTPointsNumber(); i++) {
			for (int j = 0; j < diff.getHPointsNumber(); j++) {
				errorMatrix[i][j] = 100 * (Math.abs(DifferenceSchemeSolve[i][j] - trueMatrix[i][j])) / trueMatrix[i][j];
				if(i == 0 && j == 0) { error = errorMatrix[i][j];}
				if (error < errorMatrix[i][j]) {
					error = errorMatrix[i][j];
				}
			}
		}
		return error;
	}

}
