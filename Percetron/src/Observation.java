import java.util.Arrays;

public class Observation {
    private final double[] features;
    private final int label; // setosa=1, versicolor=0

    public Observation(double[] features, int label) {
        this.features = features;
        this.label = label;
    }

    public double[] getFeatures() {
        return features;
    }

    public int getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Observation{label=" + label + ", features=" + Arrays.toString(features) + "}";
    }
}
