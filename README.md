Application available here: https://rest-homework-plotnikowski.herokuapp.com/

Temat
Celem zadania jest napisanie prostej aplikacji webowej
rozszerzającej funkcjonalność  wybranego otwartego 
serwisu udostępniającego REST API.
  
Stworzyć macie Państwo serwis, który:

- odbierze zapytanie od klienta (formularz na stronie),

- odpyta serwis publiczny (lub kilka serwisów),

- dokona odróbki otrzymanych danych (np.: wyciągnięcie średniej, znalezienie ekstremów, porównanie wartości z różnych serwisów itp.),

- wygeneruje i wyśle odpowiedź do klienta (strona z wynikami).

Na przykład: 

- klient podaje miasto i okres czasu ('daty od/do' lub 'ostatnie X dni'),
- serwer odpytuje serwis pogodowy o temperatury w poszczególne dni, oblicza średnią i znajduje ekstrema i wysyła do klienta wszystkie wartości 
 (tzn. prostą stronę z tymi danymi). Albo odpytuje kilka serwisów i podaje różnice w prognozach. 
 
Serwer wykonać musi co najmniej 5 zapytań.

Wybranie serwisu i funkcjonalności pozostawiam Państwa wyobraźni, zainteresowaniom i rozsądkowi. 

Listę różnych publicznych API można znaleźć przykładowo na https://public-apis.xyz.

Wymagania
Klient (przeglądarka) ma wysyłać żądanie na podstawie danych z formularza (statyczny HTML) i otrzymać odpowiedź w formie prostej, 
wygenerowanej przez serwer strony www.
 
Proszę użyć czystego HTML, bez stylizacji, bez dodatkowych bibliotek front-endowych.
 
Nie musi być piękne, ma działać.

Odpowiedź dla klienta musi być generowana przez serwer na podstawie: 

1) żądań REST do publicznych serwisów i 
2) obróbki uzyskanych odpowiedzi.

Serwer ma być uruchomiony na własnym serwerze aplikacyjnym (lub analogicznej technologii), działającym poza IDE.