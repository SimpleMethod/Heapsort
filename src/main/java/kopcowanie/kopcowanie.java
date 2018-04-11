package kopcowanie;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/**
 * Klasa implementująca algorytm sortowania przez kopcowanie.kopcowanie oraz metod umożliwiających używanie algorytmu.
 */
public class kopcowanie {
    /**
     * Prywatna zmienna statyczna do przechowywania tablicy.
     */
    private static int[] TABLICA;
    /**
     * Prywatna zmienna statyczna, kopia zmiennej TABLICA służąca do badania złożoności.
     */
    private static int ELEMENT_TABLICY;
    /**
     * Prywatna zmienna statyczna służy do przypisania lewego elementu tablicy do dalszego porównania.
     */
    private static int LEWY_ELEMENT;
    /**
     * Prywatna zmienna statyczna służy do przypisania prawego elementu tablicy do dalszego porównania.
     */
    private static int PRAWY_ELEMENT;
    /**
     * Prywatna zmienna statyczna służy do przypisania największego elementu tablicy do dalszego porównania.
     */
    private static int ELEMENT_NAJWIEKSZY;
    /**
     * Prywatna zmienna statyczna służąca do zapobiegnięcia uruchomieniu algorytmu kopcowania lub wyświetlenia tablicy bez wcześniejszego wygenerowania danych.
     */
    private static int FLAGAWL;

    // Metody służce do implementacji algorytmu kopcowania.

    /**
     * Metoda służaca do budowania kopca.
     *
     * @param ROZMIAR           Rozmiar tablicy.
     * @param TYMCZASOWATABLICA Kopia tablicy do kopca.
     */
    private static void BdowanieKopca(int ROZMIAR, int[] TYMCZASOWATABLICA) {
        ELEMENT_TABLICY = ROZMIAR;
        for (int i = ROZMIAR / 2; i >= 0; i--) {
            System.out.println("Nstąpiła wymiana!");
            Kopiec(TYMCZASOWATABLICA, i);
        }
    }

    /**
     * Metoda służaca do porównywania i znalezienia największego elementu.
     *
     * @param TABLICA_KOPCA         Kopia tablicy do kopca.
     * @param ELEMENT_DO_POROWNANIA Porównywany element.
     */
    private static void Kopiec(int[] TABLICA_KOPCA, int ELEMENT_DO_POROWNANIA) {
        LEWY_ELEMENT = 2 * ELEMENT_DO_POROWNANIA;
        PRAWY_ELEMENT = 2 * ELEMENT_DO_POROWNANIA + 1;
        if (LEWY_ELEMENT <= ELEMENT_TABLICY && TABLICA_KOPCA[LEWY_ELEMENT] > TABLICA_KOPCA[ELEMENT_DO_POROWNANIA])
            ELEMENT_NAJWIEKSZY = LEWY_ELEMENT;
        else
            ELEMENT_NAJWIEKSZY = ELEMENT_DO_POROWNANIA;

        if (PRAWY_ELEMENT <= ELEMENT_TABLICY && TABLICA_KOPCA[PRAWY_ELEMENT] > TABLICA_KOPCA[ELEMENT_NAJWIEKSZY])
            ELEMENT_NAJWIEKSZY = PRAWY_ELEMENT;

        if (ELEMENT_NAJWIEKSZY != ELEMENT_DO_POROWNANIA) {
            Swap(ELEMENT_DO_POROWNANIA, ELEMENT_NAJWIEKSZY);
            Kopiec(TABLICA_KOPCA, ELEMENT_NAJWIEKSZY);
        }
    }

    /**
     * Metoda służąca do zamiany miejscami elementów tablicy.
     *
     * @param A Pierwszy element do wymiany.
     * @param B Drugi element do wymiany.
     */
    private static void Swap(int A, int B) {
        int temp = TABLICA[A];
        TABLICA[A] = TABLICA[B];
        TABLICA[B] = temp;
    }

    /**
     * Główna metoda sortująca.
     */
    public static void Kopcowanie() {
        BdowanieKopca(TABLICA.length - 1, TABLICA);
        for (int i = ELEMENT_TABLICY; i > 0; i--) {
            Swap(0, i);
            ELEMENT_TABLICY--;
            Kopiec(TABLICA, 0);
        }
    }

    // Metody służce do obsługi algorytmu kopcowania.

    /**
     * Prywatna metoda służąca do generowania liczb pseudolosowych.
     *
     * @param ILOSC_ELEMENTOW  Ilość elementów do wygenerowania.
     * @param POCZATEK_ZAKRESU Minimalny dozwolony element.
     * @param KONIEC_ZAKRESU   Maksymalny dozwolony element.
     * @return Zwraca wygenerowaną tablicę z liczbami.
     */
    private static int[] GenerowanieLiczbLosowych(int ILOSC_ELEMENTOW, int POCZATEK_ZAKRESU, int KONIEC_ZAKRESU) {
        TABLICA = new int[ILOSC_ELEMENTOW];
        TABLICA = new Random().ints(POCZATEK_ZAKRESU, KONIEC_ZAKRESU).limit(ILOSC_ELEMENTOW).toArray();
        FLAGAWL = 1;
        return TABLICA;
    }

    /**
     * Metoda służaca do wyświetlenia tablicy.
     *
     * @param TABLICA Tablica do wyświetlenia.
     */
    public static void WyswietlanieTablicy(int[] TABLICA) {
        if (FLAGAWL != 0) {
            System.out.println(Arrays.toString(TABLICA));
        } else {
            System.err.println("Przed wyświetleniem tabeli należy wygenerować lub wprowadzić dane!");
        }

    }

    /**
     * Metoda obsługująca wygenerowanie liczb.
     *
     * @throws IOException           Wyjątek przy nieprawidłowym wprowadzaniu danych.
     * @throws NumberFormatException Wyjątek przy wprowadzaniu danych o innym typie niż liczbowym.
     */
    public static void ObslugaGeneratora() throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int temp = 0, temp1 = 0, temp2 = 0;

        try {
            System.out.println("Proszę podać ilość elementów do wygenerowania: ");
            temp = Integer.parseInt(br.readLine());

        } catch (NumberFormatException nfe) {
            System.err.println("Należy używać tylko cyfr!");
            return;
        }

        try {
            System.out.println("Proszę podać minimalną wartość liczby możliwej do wygenerowania: ");
            temp1 = Integer.parseInt(br.readLine());

        } catch (NumberFormatException nfe) {
            System.err.println("Należy używać tylko cyfr!");
            return;
        }

        try {
            System.out.println("Proszę podać maksymalną wartość liczby możliwej do wygenerowania: ");
            temp2 = Integer.parseInt(br.readLine());

        } catch (NumberFormatException nfe) {
            System.err.println("Należy używać tylko cyfr!");
            return;
        }

        if (temp < 1) {
            System.err.println("Liczba elementów nie może być ujemna, równa zero lub równa jeden!");
            throw new NumberFormatException();
        } else {
            System.out.println("Ilosc: " + temp + " element minimalny: " + temp1 + " element maksymalny: " + temp2);
            if (temp > 1) {
                TABLICA = GenerowanieLiczbLosowych(temp, temp1, temp2);
            }

        }
    }

    /**
     * Metoda implementująca proste menu do obsługi algorytmu.
     *
     * @throws IOException Wyjątek przy nieprawidłowym wprowadzaniu danych.
     */
    public static void Menu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Program kopcowanie.kopcowanie 1.0 \n MENU: \n 1. Wygenerowanie tablicy liczb. \n 2. Wyświetlanie tablicy. \n 3.Uruchomienie funkcji sortującej. \n 4.Wyjście z programu");

        while (true) {
            System.out.print("Proszę podać numer opcji do wykonania: ");
            try {
                int i = Integer.parseInt(br.readLine());

                switch (i) {
                    case 1:
                        ObslugaGeneratora();
                        break;
                    case 2:
                        WyswietlanieTablicy(TABLICA);
                        break;
                    case 3:
                        if (FLAGAWL != 0) {
                            Kopcowanie();
                        }
                        break;
                    case 4:
                        System.out.println("Dziękuję za skorzystanie z programu!");
                        System.exit(0);
                        break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Należy używać tylko cyfr!");
                return;
            }

        }

    }

    public static void main(String[] args) throws IOException {
        Menu();
    }
}
