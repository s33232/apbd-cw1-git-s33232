import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrepareDataset {

    public static class DatasetSplit {
        public List<Observation> train = new ArrayList<>();
        public List<Observation> test = new ArrayList<>();
    }

    public DatasetSplit trainTestSplit(String filepath) {
        List<Observation> setosaList = new ArrayList<>();
        List<Observation> versicolorList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            br.readLine(); // Pominięcie nagłówka, jeśli istnieje w pliku
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] values = line.split(",");

                // W standardowym pliku iris.csv indeksy to: 0:sepal.len, 1:sepal.wid, 2:petal.len, 3:petal.wid, 4:variety
                // Używamy indeksów 2 i 3 do dwuwymiarowej wizualizacji, ponieważ dają najlepszą liniową separowalność
                double f1 = Double.parseDouble(values[2]);
                double f2 = Double.parseDouble(values[3]);
                String variety = values[4].trim().replaceAll("\"", "");

                if (variety.equalsIgnoreCase("Setosa") || variety.equalsIgnoreCase("Iris-setosa")) {
                    setosaList.add(new Observation(new double[]{f1, f2}, 1)); // setosa = 1
                } else if (variety.equalsIgnoreCase("Versicolor") || variety.equalsIgnoreCase("Iris-versicolor")) {
                    versicolorList.add(new Observation(new double[]{f1, f2}, 0)); // versicolor = 0
                }
            }
        } catch (Exception e) {
            System.err.println("Błąd podczas czytania pliku: " + e.getMessage());
        }

        Collections.shuffle(setosaList);
        Collections.shuffle(versicolorList);

        DatasetSplit split = new DatasetSplit();

        int setosaTrainSize = (int) Math.round(setosaList.size() * 0.7);
        int versicolorTrainSize = (int) Math.round(versicolorList.size() * 0.7);

        split.train.addAll(setosaList.subList(0, setosaTrainSize));
        split.test.addAll(setosaList.subList(setosaTrainSize, setosaList.size()));

        split.train.addAll(versicolorList.subList(0, versicolorTrainSize));
        split.test.addAll(versicolorList.subList(versicolorTrainSize, versicolorList.size()));

        Collections.shuffle(split.train);
        Collections.shuffle(split.test);

        return split;
    }
}