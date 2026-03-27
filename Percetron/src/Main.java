import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Подготовка обучающих данных
        List<String> texts = Arrays.asList(
                "This is a sample english text to train the neural network. The more text we have, the better.",
                "To jest przykładowy polski tekst do treningu sieci neuronowej. Im więcej tekstu, tym lepiej.",
                "Este es un texto de ejemplo en español para entrenar la red neuronal. Cuanto más texto, mejor."
        );
        List<String> labels = Arrays.asList("English", "Polish", "Spanish");
        List<String> uniqueLanguages = Arrays.asList("English", "Polish", "Spanish");

        List<double[]> trainingData = new ArrayList<>();
        for (String text : texts) {
            trainingData.add(TextVectorizer.textToVector(text));
        }

        // 2. Инициализация и обучение сети
        SingleLayerNeuralNetwork mpp = new SingleLayerNeuralNetwork(uniqueLanguages, 0.1, 0.1);

        System.out.println("Обучение сети...");
        for (int epoch = 0; epoch < 500; epoch++) {
            mpp.trainLayer(trainingData, labels);
        }
        System.out.println("Обучение завершено!");

        // 3. Запуск графического интерфейса
        // Запускаем Swing в специальном потоке для безопасности (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            LanguageClassifierGUI gui = new LanguageClassifierGUI(mpp);
            gui.setLocationRelativeTo(null); // Центрируем окно на экране
            gui.setVisible(true);            // Показываем окно
        });
    }
}