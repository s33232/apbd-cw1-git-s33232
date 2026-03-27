import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PrepareDataset prep = new PrepareDataset();
        PrepareDataset.DatasetSplit dataset = prep.trainTestSplit("iris.csv");

        System.out.println("Rozmiar zbioru treningowego: " + dataset.train.size());
        System.out.println("Rozmiar zbioru testowego: " + dataset.test.size());

        Perceptron perceptron = new Perceptron(2, 0.05);

        List<Double> epochsList = new ArrayList<>();
        List<Double> accuraciesList = new ArrayList<>();
        int maxEpochs = 50;

        for (int epoch = 1; epoch <= maxEpochs; epoch++) {
            perceptron.train(dataset.train);

            List<Integer> realClasses = new ArrayList<>();
            List<Integer> predictedClasses = new ArrayList<>();
            for (Observation obs : dataset.test) {
                realClasses.add(obs.label);
                predictedClasses.add(perceptron.predict(obs.inputs));
            }
            double accuracy = EvaluationMetrics.measureAccuracy(realClasses, predictedClasses);

            epochsList.add((double) epoch);
            accuraciesList.add(accuracy);

            if (epoch % 5 == 0 || epoch == 1) {
                System.out.println("Epoka " + epoch + " | Dokładność na zbiorze testowym: " + accuracy);
            }
        }

        visualizeAccuracy(epochsList, accuraciesList);
        visualizeDecisionBoundary(perceptron, dataset.test);

        runTerminalInterface(perceptron);
    }

    private static void visualizeAccuracy(List<Double> epochs, List<Double> accuracies) {
        XYChart chart = new XYChartBuilder().width(800).height(600)
                .title("Dokładność vs Epoki").xAxisTitle("Liczba epok").yAxisTitle("Dokładność (Accuracy)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.addSeries("Dokładność testowa", epochs, accuracies);
        new SwingWrapper<>(chart).displayChart();
    }

    private static void visualizeDecisionBoundary(Perceptron p, List<Observation> testData) {
        XYChart chart = new XYChartBuilder().width(800).height(600)
                .title("Hiperpłaszczyzna decyzyjna na tle zbioru testowego").xAxisTitle("Petal Length (Długość płatka)").yAxisTitle("Petal Width (Szerokość płatka)").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);

        List<Double> setosaX = new ArrayList<>(), setosaY = new ArrayList<>();
        List<Double> versiX = new ArrayList<>(), versiY = new ArrayList<>();

        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;

        for (Observation obs : testData) {
            double x = obs.inputs[0];
            double y = obs.inputs[1];
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;

            if (obs.label == 1) {
                setosaX.add(x); setosaY.add(y);
            } else {
                versiX.add(x); versiY.add(y);
            }
        }

        if (!setosaX.isEmpty()) chart.addSeries("Setosa (1)", setosaX, setosaY);
        if (!versiX.isEmpty()) chart.addSeries("Versicolor (0)", versiX, versiY);

        if (p.weights[1] != 0) {
            List<Double> lineX = List.of(minX - 0.5, maxX + 0.5);
            List<Double> lineY = List.of(
                    (p.threshold - p.weights[0] * lineX.get(0)) / p.weights[1],
                    (p.threshold - p.weights[0] * lineX.get(1)) / p.weights[1]
            );
            XYSeries lineSeries = chart.addSeries("Hiperpłaszczyzna decyzyjna", lineX, lineY);
            lineSeries.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
            lineSeries.setMarker(org.knowm.xchart.style.markers.SeriesMarkers.NONE); // Rysujemy jako ciągłą linię bez kropek
        }

        new SwingWrapper<>(chart).displayChart();
    }

    private static void runTerminalInterface(Perceptron p) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Interfejs Predykcji ---");
        System.out.println("Wprowadź wartości atrybutów: Długość płatka i Szerokość płatka (oddzielone spacją).");
        System.out.println("Wpisz 'q' aby zakończyć działanie programu.");

        while (true) {
            System.out.print("\nTwoje wejście > ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Zamykanie programu...");
                break;
            }

            String[] parts = input.trim().split("\\s+");
            if (parts.length != 2) {
                System.out.println("Błąd: Proszę wprowadzić dokładnie dwie liczby, np: 1.5 0.2");
                continue;
            }

            try {
                double f1 = Double.parseDouble(parts[0].replace(",", "."));
                double f2 = Double.parseDouble(parts[1].replace(",", "."));

                int result = p.predict(new double[]{f1, f2});
                String className = (result == 1) ? "Iris-setosa" : "Iris-versicolor";

                System.out.println("Wynik predykcji: Etykieta [" + result + "] -> Klasa: " + className);
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Nieprawidłowy format liczb.");
            }
        }
        scanner.close();
        System.exit(0);
    }
}