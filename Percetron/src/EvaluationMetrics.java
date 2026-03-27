public class EvaluationMetrics {

    public static double measureAccuracy(int[] realClasses, int[] predictedClasses) {
        if (realClasses.length == 0) return 0.0;
        if (realClasses.length != predictedClasses.length) {
            throw new IllegalArgumentException("realClasses length must equal predictedClasses length");
        }
        int correct = 0;
        for (int i = 0; i < realClasses.length; i++) {
            if (realClasses[i] == predictedClasses[i]) correct++;
        }
        return (double) correct / realClasses.length;
    }
}