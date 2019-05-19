
public class Main {

	public static void main(String[] args) {
		
		DiffEquation diff = new DiffEquation();
		
		ExactSolution exactSolve = new ExactSolution(diff);
		double[][] exactResult = exactSolve.solve();
		
		System.out.println("\n���������������� ������� ����� �������:");
		SerialSolution serialSolve = new SerialSolution(diff);
		
		long startTimeSerial = System.nanoTime();
		double[][] serialResult = serialSolve.solve();
		long endTimeSerial = System.nanoTime();
		
		long executeTimeSerial = endTimeSerial - startTimeSerial;
		
		System.out.println("��������� ������ ������ ������:");
		diff.printMatrix(serialResult);
		
		System.out.println("\n����� ���������� ����������������� �������: " + executeTimeSerial + " ��");
		
		//System.out.println();
		System.out.println("\n�����������:");

		printResultError(diff, serialResult, exactResult);
		
		System.out.println();
		System.out.println("\n������������ ������� ����� �������:");
		
		ParallelSolution parallelSolve = new ParallelSolution(diff);
		
		long startTimeParallel = System.nanoTime();
		double[][] parallelResult = parallelSolve.solve();
		long endTimeParallel = System.nanoTime();
		long executeTimeParallel = endTimeParallel - startTimeParallel;
		
		diff.printMatrix(parallelResult);
		
		System.out.println("\n����� ���������� ������������� �������: " + executeTimeParallel + " ��");
		
		System.out.println("\n�����������:");
		
		printResultError(diff, parallelResult, exactResult);
	}

	private static void printResultError(DiffEquation diff, double[][] DifferenceSchemeSolve, double[][] trueMatrix) {
		MathError err = new MathError(diff, DifferenceSchemeSolve, trueMatrix);
		System.out.println("������� ���������� �����������: " + err.mediumAbsoluteError());
		System.out.println("������������ ���������� �����������: " + err.maxAbsoluteError());
		System.out.println("������� ������������� �����������: " + err.mediumRelativeError());
		System.out.println("������������ ������������� �����������: " + err.maxRelativeError());
	}

}
