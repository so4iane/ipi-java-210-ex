package com.ipiecoles.java.java210;

import java.awt.*;
import java.util.Scanner;

public class Sudoku {
    public static final String FIN_SAISIE = "FIN";
    public boolean resolu = false;
    public short sudokuAResoudre[][];

    public short[][] getSudokuAResoudre() {
        return sudokuAResoudre;
    }

    ;

    public void setSudokuAResoudre(short[][] tab) {
        sudokuAResoudre = tab;
    }


    /**
     * Constructeur par défaut c'est une methode d'initialisation de la class, ici Sudoku va devenir un type
     */
    public Sudoku() {
        sudokuAResoudre = new short[9][9];    /*new pour initialiser et rien pour déclarer*/
    }

    public static boolean ligneSaisieEstCoherente(String ligneSaisie) {
        if (ligneSaisie == null || ligneSaisie.trim().equals(""))/*le trim permet d'enlever tous les espaces*/ {
            System.out.println("Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces");
            return false;

        } else if (ligneSaisie.length() != 3)/*ligne ne faisaint pas 3 caractères*/ {
            System.out.println("Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères");
            return false;
        } else if (!ligneSaisie.substring(0, 1).matches("[0-8]") /*premier caractere/chiffre saisie () entre 1 et 8*/
                || !ligneSaisie.substring(1, 2).matches("[0-8]")
                || !ligneSaisie.substring(2, 3).matches("[1-9]")) {
            System.out.println("L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9");
            return false;
        }
        return true;
    }


    /**
     * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour initialiser un sudoku à résoudre.
     * Les coordonnées prennent la forme XYZ avec X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
     * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée après chaque saisie.
     * Lorsqu'il a terminé sa saisie, il entre la chaîne FIN. La fonction remplit au fur et à mesure un tableau de String
     * comportant les coordonnées des chiffres saisis.
     * <p>
     * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant la méthode ligneSaisieEstCoherente
     * En cas de mauvaise saisie, la saisie ne doit pas être prise en compte et l'utilisateur doit pouvoir saisie une nouvelle ligne
     * La fonction doit également gérer le cas où l'utilisateur ne rentre rien mais appuye sur Entrée
     *
     * @return Un tableau comportant les coordonnées des chiffres présents dans le sudoku à résoudre
     */
    public static String[] demandeCoordonneesSudoku() {
        String[] tableauCoordonnees = new String[81];/*On cree un tableau à une dimension, de max 81 donnees*/
        int i = 0;
        String ligneSaisie;/*déclarer en dehors des {} du do, sinon on ne pourra pas l'appeler dans le while*/
        Scanner saisie = new Scanner(System.in);
        do {
            System.out.println("Saisissez les coordonnées et le chiffre voulu.");

            ligneSaisie = saisie.nextLine();
            if (ligneSaisieEstCoherente(ligneSaisie)) {
                tableauCoordonnees[i] = ligneSaisie;/*rentre la valeur saisie dans la case correspondante*/
                i = i + 1;
            }/*i=i+1 correspond à i++*/ else {
                System.out.println("Les coordonnées du chiffre et/ou sa valeur ne sont pas cohérente. Merci de réessayer.");
            }
        }
        while (!ligneSaisie.equals("FIN") && i < 81);
        saisie.close();
        return tableauCoordonnees;
    }

    /**
     * La méthode prend un tableau de coordonnées de chiffre sous la forme XYZ avec X correspondant
     * à l'abscisse, Y l'ordonnée et Z la valeur et remplit le tableau sudokuAResoudre avec les bonnes valeurs
     * au bon endroit. Ex 012, première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle est
     * rencontrée dans le tableau, on arrête le traitement
     * <p>
     * Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string)
     *
     * @param tableauCoordonnees
     */
    public void remplitSudokuATrous(String[] tableauCoordonnees) {
        /*Définir ligne colonne et valeur
        Parcourir le tableauCoordonnees
        Pour chacune de ces valeurs extraire ligne colonne et valeur
        mettre la valeur au bon endroit dans SudokuAResoudre
        sudokuAResoudre[ligne][colonne] = valeur;
         */
        int i=0;
        int ligne;
        int colonne;
        short valeur;
        while (i<tableauCoordonnees.length && tableauCoordonnees[i]!=null)
         {
            ligne = stringToInt( tableauCoordonnees[i].substring(0 , 1));
            colonne = stringToInt(tableauCoordonnees[i].substring(1 , 2));
            valeur = stringToShort(tableauCoordonnees[i].substring(2 ,3));
            sudokuAResoudre[ligne][colonne] = valeur;
            i++;
        }



    }
    private short stringToShort(String s) {
        return Short.parseShort(s);
    }
    private int stringToInt(String s) {
        return Integer.parseInt(s);
    }

    /**
     * Cette méthode affiche un sudoku de manière formatée sur la console.
     * Cela doit ressembler exactement à :
     * -----------------------
     * |   8   | 4   2 |   6   |
     * |   3 4 |       | 9 1   |
     * | 9 6   |       |   8 4 |
     * -----------------------
     * |       | 2 1 6 |       |
     * |       |       |       |
     * |       | 3 5 7 |       |
     * -----------------------
     * | 8 4   |       |   7 5 |
     * |   2 6 |       | 1 3   |
     * |   9   | 7   1 |   4   |
     * -----------------------
     *
     * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu ou non).
     *               Ce tableau fait 9 par 9 et contient des chiffres de 0 à 9, 0 correspondant à une valeur
     *               non trouvée (dans ce cas, le programme affiche un blanc à la place de 0
     */
    public void ecrireSudoku(short[][] sudoku) {

    }

    /**
     * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
     * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes :
     * <p>
     * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
     * 2 : Si le valeur est déjà dans la colone, le chiffre n'est pas autorisé
     * 3 : Si la valeur est est déjà dans la boite, le chiffre n'est pas autorisé
     *
     * @param abscisse
     * @param ordonnee
     * @param chiffre
     * @param sudoku
     * @return
     */
    public static boolean estAutorise(int abscisse, int ordonnee, short chiffre, short[][] sudoku) {
        return true;
    }

    public boolean resoudre(int abscisse, int ordonnee, short[][] sudoku) {
        return true;
    }
}