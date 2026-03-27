import java.util.List;
import java.util.Random;

public class Perceptron {
    private final int dimensions;
    private final double[] weights;
    private double threshold;
    private final double alpha;
    private final double beta;

    public Perceptron(int dimensions, double alpha, double beta, long seed) {
        if (dimensions <= 0) throw new IllegalArgumentException("dimensions must be > 0");
        this.dimensions = dimensions;
        this.alpha = alpha;
        this.beta = beta;

        this.weights = new double[dimensions];
        Random rnd = new Random(seed);
        for (int i = 0; i < dimensions; i++) {
            weights[i] = -1.0 + (2.0 * rnd.nextDouble());
        }
        threshold = -1.0 + (2.0 * rnd.nextDouble());
    }

    public int getDimensions() {
        return dimensions;
    }

    public double[] getWeights() {
        return weights.clone();
    }

    public double getThreshold() {
        return threshold;
    }

    public double calculateNet(double[] inputs) {
        if (inputs.length != dimensions) {
            throw new IllegalArgumentException("inputs length must be " + dimensions);
        }
        double net = 0.0;
        for (int i = 0; i < dimensions; i++) {
            net += weights[i] * inputs[i];
        }
        return net - threshold;
    }

    public int predict(double[] inputs) {
        return calculateNet(inputs) >= 0.0 ? 1 : 0;
    }

    public int[] predict(List<double[]> inputs) {
        int[] out = new int[inputs.size()];
        for (int i = 0; i < inputs.size(); i++) {
            out[i] = predict(inputs.get(i));
        }
        return out;
    }

    public void train(List<double[]> inputs, int[] labels, int epochs) {
        if (inputs.size() != labels.length) {
            throw new IllegalArgumentException("inputs size must equal labels length");
        }
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < inputs.size(); i++) {
                trainOne(inputs.get(i), labels[i]);
            }
        }
    }

    public void trainOne(double[] inputs, int expectedOutput) {
        int actual = predict(inputs);
        int error = expectedOutput - actual;
        if (error == 0) return;

        for (int i = 0; i < dimensions; i++) {
            weights[i] += error * alpha * inputs[i];
        }
        threshold -= error * beta;
    }
}