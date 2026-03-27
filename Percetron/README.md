# Projekt 2: Klasyfikacja Perceptronem (Iris Dataset)

## 📝 Opis projektu
Implementacja klasyfikatora opartego na modelu **Perceptronu** i algorytmie **Delta**. Program został stworzony w ramach kursu NAI (Metody Programowania Współbieżnego / Sztuczna Inteligencja). 

Głównym celem jest binarna klasyfikacja gatunku *Iris-setosa* względem pozostałych dwóch gatunków (*Iris-versicolor*, *Iris-virginica*).

## 🚀 Funkcjonalności
* **Dynamiczne wczytywanie danych**: Program obsługuje dowolną liczbę atrybutów numerycznych (niezależnie od tego, czy jest ich 4, czy 10).
* **Trening modelu**: Implementacja reguły delty do aktualizacji wag i progu (threshold).
* **Testowanie**: Automatyczne obliczanie dokładności (Accuracy) na zbiorze testowym.
* **Tryb interaktywny**: Możliwość ręcznego wpisywania wektorów atrybutów w konsoli w celu natychmiastowej klasyfikacji.
* **Zero bibliotek ML**: Całość logiki (matematyka, pętle, wczytywanie) zaimplementowana od zera w czystej Javie.

## 🛠 Matematyka Perceptronu
Decyzja klasyfikatora opiera się na wzorze:
$$y = \begin{cases} 1 & \text{jeżeli } \sum_{i=1}^{n} (x_i \cdot w_i) \geq \theta \\ 0 & \text{jeżeli } \sum_{i=1}^{n} (x_i \cdot w_i) < \theta \end{cases}$$

Reguła aktualizacji wag (Delta):
$$\Delta w = \alpha \cdot (target - output) \cdot x$$

## 📂 Struktura plików
* `src/` - Kod źródłowy (klasy Perceptron, Observation, DataList, Main).
* `iris_training.txt` - Dane do nauki modelu.
* `iris_test.txt` - Dane do sprawdzenia skuteczności.

## 💻 Jak uruchomić
1. Skompiluj projekt w dowolnym IDE (np. IntelliJ IDEA).
2. Uruchom klasę `Main`.
3. Podaj wartość parametru $k$ (jeśli dotyczy) lub prędkość uczenia $\alpha$ (learning rate).
4. Po zakończeniu testów, wpisz własne parametry kwiatu lub wpisz `exit`, aby wyjść.

## 📊 Wyniki
Model osiąga wysoką dokładność (często 100%) dla gatunku *Iris-setosa*, ponieważ jest on liniowo separowalny od pozostałych klas.
