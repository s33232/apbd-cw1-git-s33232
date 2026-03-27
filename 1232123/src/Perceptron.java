import java.util.List;
import java.util.Random;

public class Perceptron {
    public int dimension;
    public double[] weights;
    public double threshold;
    public double alpha;
    public double beta;

    public Perceptron(int dimension, double alpha) {
        this.dimension = dimension;
        this.alpha = alpha;
        this.weights = new double[dimension];
        Random rand = new Random();

        for (int i = 0; i < dimension; i++) {
            this.weights[i] = rand.nextDouble() - 0.5;
        }
        this.threshold = rand.nextDouble() - 0.5;
    }

    public int predict(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < dimension; i++) {
            sum += inputs[i] * weights[i];
        }
        return sum >= threshold ? 1 : 0;
    }

    public void train(List<Observation> trainingData) {
        for (Observation obs : trainingData) {
            int prediction = predict(obs.inputs);
            int error = obs.label - prediction;

            if (error != 0) {
                for (int i = 0; i < dimension; i++) {
                    weights[i] += alpha * error * obs.inputs[i];
                }

                threshold -= alpha * error;
            }
        }
    }
}