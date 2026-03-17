# APBD Ćwiczenia 1
1. Kiedy Git wykona fast-forward, a kiedy powstaje merge commit?
Fast-forward dzieje się wtedy, gdy główna gałąź nie zmieniła się od czasu stworzenia naszej roboczej gałęzi. Git po prostu przesuwa wskaźnik do przodu i historia leci w jednej linii. Z kolei merge commit powstaje, kiedy pracowaliśmy na bocznej gałęzi, ale w międzyczasie ktoś lub my sami dodaliśmy też nowe rzeczy bezpośrednio do głównej gałęzi. Historie się rozeszły, więc Git musi stworzyć zupełnie nowy commit, żeby połączyć te dwie ścieżki w jedną.

2. Czym w praktyce różni się merge od rebase?
Merge po prostu łączy dwie gałęzie i zostawia ślad w historii w postaci nowego commita łączącego. Pokazuje dokładnie, jak przebiegała praca, ale przy wielu gałęziach robi się z tego niezła pajęczyna. Rebase działa inaczej. Bierze nasze nowe commity z bocznej gałęzi i sztucznie dokleja je na samej górze głównej gałęzi. Dzięki temu historia wygląda tak, jakbyśmy wszystko pisali w jednej prostej linii po kolei, bez żadnych rozgałęzień.

3. W jaki sposób został rozwiązany konflikt w Twoim repozytorium?
Konflikt w pliku Main.java rozwiązałem ręcznie. Git zatrzymał łączenie i dodał do kodu swoje specjalne znaczniki, żeby pokazać mi dwie różne wersje tego samego fragmentu. Po prostu usunąłem te techniczne dopiski Gita i zostawiłem tę wersję tekstu, która najbardziej mi pasowała z nowej gałęzi. Na koniec zapisałem plik, dodałem go do zmian i zrobiłem normalny commit zamykający sprawę.
