package fr.troisil.info.java.funcprog;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class App {
    public static void main(String[] args) {
        // Chemin du fichier personne.csv dans le répertoire des ressources
        String cheminFichier = "src/main/resources/personne.csv";

        try {
            List<Personne> personnes = Files.lines(Paths.get(cheminFichier))
                    .skip(1)
                    .map(line -> {
                        String[] elements = line.split(",");
                        int age = Integer.parseInt(elements[1].trim());
                        return new Personne(elements[0].trim(), age);
                    })
                    .collect(Collectors.toList());

            // 8.2
         /*   List<Personne> personnesTrie = personnes.stream()
                    .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                    .toList();

            //8.3
         Comparator<Personne> comparateurParAge = Comparator.comparing(Personne::getAge);
            List<Personne> personnesTrie = personnes.stream()
                    .sorted(comparateurParAge)
                    .collect(Collectors.toList());
            */
            List<Personne> personnesTrie = personnes.stream()
                    .sorted(Comparator.comparing(Personne::getAge, Comparator.reverseOrder())
                            .thenComparing(Comparator.comparing(Personne::getPrenom)))
                    .collect(Collectors.toList());

            personnesTrie.forEach(System.out::println);


        } catch (IOException e) {
            System.err.println("Erreur : Le fichier n'a pas pu être trouvé ou lu.");
            e.printStackTrace();
        }
    }
}
