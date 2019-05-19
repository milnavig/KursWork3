
public class Main {

	public static void main(String[] args) {
		
		DiffEquation diff = new DiffEquation();
		
		ExactSolution exactSolve = new ExactSolution(diff);
		double[][] exactResult = exactSolve.solve();
		
		System.out.println("\nПоследовательное решение явным методом:");
		SerialSolution serialSolve = new SerialSolution(diff);
		
		long startTimeSerial = System.nanoTime();
		double[][] serialResult = serialSolve.solve();
		long endTimeSerial = System.nanoTime();
		
		long executeTimeSerial = endTimeSerial - startTimeSerial;
		
		System.out.println("Результат работы явного метода:");
		diff.printMatrix(serialResult);
		
		System.out.println("\nВремя выполнения последовательного решения: " + executeTimeSerial + " нс");
		
		//System.out.println();
		System.out.println("\nПогрешности:");

		printResultError(diff, serialResult, exactResult);
		
		System.out.println();
		System.out.println("\nПараллельное решение явным методом:");
		
		ParallelSolution parallelSolve = new ParallelSolution(diff);
		
		long startTimeParallel = System.nanoTime();
		double[][] parallelResult = parallelSolve.solve();
		long endTimeParallel = System.nanoTime();
		long executeTimeParallel = endTimeParallel - startTimeParallel;
		
		diff.printMatrix(parallelResult);
		
		System.out.println("\nВремя выполнения параллельного решения: " + executeTimeParallel + " нс");
		
		System.out.println("\nПогрешности:");
		
		printResultError(diff, parallelResult, exactResult);
	}

	private static void printResultError(DiffEquation diff, double[][] DifferenceSchemeSolve, double[][] trueMatrix) {
		MathError err = new MathError(diff, DifferenceSchemeSolve, trueMatrix);
		System.out.println("Средняя абсолютная погрешность: " + err.mediumAbsoluteError());
		System.out.println("Максимальная абсолютная погрешность: " + err.maxAbsoluteError());
		System.out.println("Средняя относительная погрешность: " + err.mediumRelativeError());
		System.out.println("Максимальная относительная погрешность: " + err.maxRelativeError());
	}

}
