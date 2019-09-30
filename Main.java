package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    static boolean aanZet = false; // If false: wit is aan zet, true: zwart aan zet.
    static boolean schaakStukSyntax; // test of schaak stuk wel mogelijk is.
    static final String[][] table = new String[9][9]; //geeft de tabel weer waar het schaakbord op is.
    static Scanner scan = new Scanner(System.in);
    static String naarPlekStuk; //vakje waartoe er gezet kan worden
    static String zetStuk; //het stuk waarmee je zet
    static String niks = "empty"; // een leeg vakje
    static int firstCharNumber; //eerste karakter van de input die je gaat zetten in nummervorm
    static int secondCharNumber; //tweede karakter van de input die je gaat zetten in nummervorm
    static int firstCharNumberZet; //eerste karakter van de input waartoe je gaat zetten
    static int secondCharNumberZet; //tweede karakter van de input waartoe je gaat zetten
    static char firstChar;  //het eerste karakter van de input die je gaat zetten

    public static void main(String[] args) { //initialisatie method. Deze wordt 1 keer afgespeeld

        String blackRook = "B-Rook";    //alle pionnen.
        String blackKnight = "B-Knight";
        String blackBishop = "B-Bishop";
        String blackQueen = "B-Queen";
        String blackKing = "B-King";
        String blackPawn = "B-Pawn";

        String whiteRook = "W-Rook";
        String whiteKnight = "W-Knight";
        String whiteBishop = "W-Bishop";
        String whiteQueen = "W-Queen";
        String whiteKing = "W-King";
        String whitePawn = "W-Pawn";

        table[0] = new String[]{"O", "1", "2", "3", "4", "5", "6", "7", "8"}; //definieren van de starttabel
        table[1] = new String[]{"A", blackRook, blackKnight, blackBishop, blackQueen, blackKing, blackBishop, blackKnight, blackRook};
        table[2] = new String[]{"B", blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn, blackPawn};
        table[3] = new String[]{"C", niks, niks, niks, niks, niks, niks, niks, niks};
        table[4] = new String[]{"D", niks, niks, niks, niks, niks, niks, niks, niks};
        table[5] = new String[]{"E", niks, niks, niks, niks, niks, niks, niks, niks};
        table[6] = new String[]{"F", niks, niks, niks, niks, niks, niks, niks, niks};
        table[7] = new String[]{"G", whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn, whitePawn};
        table[8] = new String[]{"H", whiteRook, whiteKnight, whiteBishop, whiteQueen, whiteKing, whiteBishop, whiteKnight, whiteRook};
        for (final Object[] row : table) { //print schaakbord
            System.out.format("%15s%15s%15s%15s%15s%15s%15s%15s%15s\n", row);
        }
        beurt(); //gaat naar beurt
    }

    static void beurt() { //hoofddeel van de code
        if (aanZet == true) { //kijkt wie er aan de beurt is
            System.out.println("Zwart is aan de beurt");
            System.out.println("Selecteer het vakje van het stuk dat u wil zetten = ");
        } else {
            System.out.println("Wit is aan de beurt");
            System.out.println("Selecteer het vakje van het stuk dat u wil zetten = ");
        }

        String schaakStuk = scan.nextLine(); //leest schaakstuk in waarmee er gezet kan worden
        int schaakStukLengte = schaakStuk.length();
        schaakStukSyntax = false; //zet syntax op false totdat is vastgesteld dat deze correct is.
        while (schaakStukSyntax == false) {
            while (schaakStukLengte != 2) {  //test of de ingevoerde schaakstuk wel 2 karakters heeft.
                System.out.println("Deze syntax is incorrect, kies opnieuw = ");
                schaakStuk = scan.nextLine();
                schaakStukLengte = schaakStuk.length();
            }
            firstChar = schaakStuk.charAt(0);
            int secondChar = schaakStuk.charAt(1)+1;
            if (firstChar < 'i' && firstChar > 'a' || firstChar == 'a') { //test of de input "schaakstuk" wel op het bord staat.
                if (secondChar < 57 && secondChar > 48) {
                    schaakStukSyntax = true;
                    char firstCharCheck = 'a';
                    firstCharNumber = 1;
                    while (firstChar != firstCharCheck) {
                        firstCharCheck++;
                        firstCharNumber++;
                    }
                    secondCharNumber = secondChar - 49;
                    zetStuk = table[firstCharNumber][secondCharNumber];
                    if (!zetStuk.contains("B-") && aanZet == true) {
                        schaakStukSyntax = false;
                    }
                    if (!zetStuk.contains("W-") && aanZet == false) {
                        schaakStukSyntax = false;
                    }
                    if (schaakStukSyntax == true) {
                        System.out.println("Deze syntax is correct, u zet nu met het volgende stuk:"); //hier is gedetermineerd of het "schaakstuk" wel op het bord staat.
                        System.out.println(zetStuk);
                        System.out.println("Wilt u met dit stuk zetten (ja/nee)?"); //confirmatie. Als hij hiervoorbij is wiped hij het stuk van het bord
                        String confirmatie = scan.nextLine();
                        if (confirmatie.equals("nee") || confirmatie.equals("Nee")) {
                            schaakStukSyntax = false;
                        }
                        table[firstCharNumber][secondCharNumber] = niks; //wiping van het gekozen stuk van het bord.
                        if (schaakStukSyntax == true) {
                            naarPlekCheck(); //hier gaat hij naar naarplekcheck()
                        }
                    }
                }
            }
            if (schaakStukSyntax != true) {
                System.out.println("Deze syntax is incorrect, kies opnieuw = ");
                schaakStuk = scan.nextLine();
            }
        }
    }

    public static void naarPlekCheck() { //leest in naar welke plek je wilt zetten en of dit correct is
        schaakStukSyntax = false;
        while (schaakStukSyntax == false) {
            System.out.println("Naar welke plek wilt u deze zetten?");
            String naarPlek = scan.next();
            int schaakStukLengte = naarPlek.length();
            while (schaakStukLengte != 2) {
                System.out.println("Deze syntax is incorrect, kies opnieuw = ");
                naarPlek = scan.nextLine();
                schaakStukLengte = naarPlek.length();
            }
            char firstChar = naarPlek.charAt(0);
            int secondChar = naarPlek.charAt(1)+1;
            if (firstChar < 'i' && firstChar > 'a' || firstChar == 'a') {
                if (secondChar < 57 && secondChar > 48) {
                    schaakStukSyntax = true;
                    char firstCharCheck = 'a';
                    firstCharNumberZet = 1;
                    while (firstChar != firstCharCheck) {
                        firstCharCheck++;
                        firstCharNumberZet++;
                    }
                    secondCharNumberZet = secondChar - 49;
                    naarPlekStuk = table[firstCharNumberZet][secondCharNumberZet]; // hier is gedetermineerd of de syntax wel op het bord staat
                    if (zetStuk.contains("Rook")) { //hier checkt hij voor de respectievelijke stukken of de ingevoerde zet wel mogelijk is.
                        rookCheck();
                    }
                    else if (zetStuk.contains("Pawn")){
                        pawnCheck();
                    }
                    else if (zetStuk.contains("Knight")){
                        knightCheck();
                    }
                    else if (zetStuk.contains("Bishop")){
                        bishopCheck();
                    }
                    else if (zetStuk.contains("King")){
                        kingCheck();
                    }
                    else if (zetStuk.contains("Queen")){
                        queenCheck();
                    }
                    if (schaakStukSyntax == true) {
                        naarPlekMogelijk(firstCharNumberZet, secondCharNumberZet); //als zet mogelijk is gaat hij naar naarplekmogelijk()
                    }
                } else {
                    System.out.println("deze syntax is incorrect, kies een nieuwe plek.");
                    schaakStukSyntax = false;
                }
            } else {
                System.out.println("Deze syntax is incorrect, kies een nieuwe plek.");
                schaakStukSyntax = false;
            }
        }
    }

    public static void naarPlekMogelijk(int firstCharNumber, int secondCharNumber) { //kijkt of het spel is afgelopen en anders switcht hij degene die aan de beurt is
        if (table[firstCharNumber][secondCharNumber].contains("King")){
            if (aanZet){
                System.out.println("Zwart heeft gewonnen!");
                System.exit(0);
            }
            else{
                System.out.println("Wit heeft gewonnen!");
                System.exit(0);
            }
        }
        table[firstCharNumber][secondCharNumber] = zetStuk;
        if (aanZet) {
            aanZet = false;
        } else {
            aanZet = true;
        }
        scan.nextLine();

        for (final Object[] row : table) {
            System.out.format("%15s%15s%15s%15s%15s%15s%15s%15s%15s\n", row);
        }
        beurt();
    }

    public static void rookCheck() { //kijkt of de rook (de toren) wel naar de plek mag die is ingevuld
        //System.out.println(table[(firstCharNumber-1)][secondCharNumber]);
        /*if (!(table[(firstCharNumber-1)][secondCharNumber].equals(niks))&&!(table[(firstCharNumber+1)][secondCharNumber].equals(niks))
                &&!(table[(firstCharNumber)][secondCharNumber-1].equals(niks))&&!(table[(firstCharNumber+1)][secondCharNumber].equals(niks))){
            System.out.println("Dit stuk heeft geen zetten, u wordt teruggeleid.");
            beurt();
            rookCheck();
        } */
        if (firstCharNumber == firstCharNumberZet || secondCharNumber == secondCharNumberZet) {
            if (secondCharNumber == secondCharNumberZet) {
                if (firstCharNumber > firstCharNumberZet) {
                    for (int i = firstCharNumber; i > firstCharNumberZet; i--) {
                        if (!table[i][secondCharNumber].equals("empty")) {
                            schaakStukSyntax = false;
                            return;
                        }
                    }
                } else if (firstCharNumber < firstCharNumberZet) {
                    for (int i = firstCharNumber; i < firstCharNumberZet; i++) {
                        if (!table[i][secondCharNumber].equals("empty")) {
                            schaakStukSyntax = false;
                            return;
                        }
                    }
                }
                else if (firstCharNumber == firstCharNumberZet && secondCharNumber == secondCharNumberZet) {
                    System.out.println("Hij staat al op dit vakje, kies opnieuw:");
                    schaakStukSyntax = false;
                    return;
                }
            }
            else if (firstCharNumberZet == firstCharNumber){
                if (secondCharNumber > secondCharNumberZet) {
                    for (int i = secondCharNumber; i > secondCharNumberZet; i--) {
                        if (!table[firstCharNumber][i].equals("empty")) {
                            schaakStukSyntax = false;
                            return;
                        }
                    }
                }
                else if (secondCharNumber < secondCharNumberZet) {
                    for (int i = secondCharNumber; i < secondCharNumberZet; i++) {
                        if (!table[firstCharNumber][i].equals("empty")) {
                            schaakStukSyntax = false;
                            return;
                        }
                    }
                }
                else if (firstCharNumber == firstCharNumberZet && secondCharNumber == secondCharNumberZet) {
                    System.out.println("Hij staat al op dit vakje, kies opnieuw:");
                    schaakStukSyntax = false;
                    return;
                }
            }

                if (aanZet == true) {
                    if (table[firstCharNumberZet][secondCharNumberZet].contains("B")) {
                        schaakStukSyntax = false;
                        return;
                    } else {
                        schaakStukSyntax = true;
                        return;
                    }
                }
                if (aanZet == false) {
                    if (table[firstCharNumberZet][secondCharNumberZet].contains("W")) {
                        schaakStukSyntax = false;
                        return;
                    } else {
                        schaakStukSyntax = true;
                        return;
                    }
                }

        }
        else{
            schaakStukSyntax = false;
            return;
        }
    }
    public static void pawnCheck(){ //kijkt of de Pawn (de pion) wel naar de plek mag die is ingevuld
        if (firstChar == 'b' || firstChar == 'g'){
            if ((!(firstCharNumber == firstCharNumberZet+2) && !(firstCharNumber == firstCharNumberZet-2)) || (secondCharNumberZet != secondCharNumber)){
                if (((firstCharNumber == firstCharNumberZet+2) || (firstCharNumber == firstCharNumberZet-2)) && (!table[firstCharNumberZet][secondCharNumberZet].equals(niks))) {
                    if (aanZet == true){
                        if (table[firstCharNumberZet][secondCharNumberZet].contains("B")){
                            schaakStukSyntax = false;
                        }
                        else{
                            schaakStukSyntax = true;
                        }
                    }
                    if (aanZet == false){
                        if (table[firstCharNumberZet][secondCharNumberZet].contains("W")){
                            schaakStukSyntax = false;
                        }
                        else{
                            schaakStukSyntax = true;
                        }
                    }
                }
            }
        }
        //System.out.println(table[firstCharNumberZet][secondCharNumberZet]);
        else if ((!(firstCharNumber == firstCharNumberZet+1) && !(firstCharNumber == firstCharNumberZet-1)) || (secondCharNumberZet != secondCharNumber)){
            if (((firstCharNumber == firstCharNumberZet+1) || (firstCharNumber == firstCharNumberZet-1)) && (!table[firstCharNumberZet][secondCharNumberZet].equals(niks))) {
                    if (aanZet == true){
                        if (table[firstCharNumberZet][secondCharNumberZet].contains("B")){
                            schaakStukSyntax = false;
                        }
                        else{
                            schaakStukSyntax = true;
                        }
                    }
                    if (aanZet == false){
                        if (table[firstCharNumberZet][secondCharNumberZet].contains("W")){
                            schaakStukSyntax = false;
                        }
                        else{
                            schaakStukSyntax = true;
                        }
                    }
            }
            else{
                schaakStukSyntax = false;
            }
        }
        else{
            if (table[firstCharNumberZet][secondCharNumberZet].contains("B") || table[firstCharNumberZet][secondCharNumberZet].contains("W")) {
                schaakStukSyntax = false;
            }
            else{
                schaakStukSyntax = true;
            }
        }
    }
    public static void knightCheck(){ //kijkt of de Knight (het paard) wel naar de plek mag die is ingevuld
        if ((firstCharNumber == firstCharNumberZet+2 && (secondCharNumber == secondCharNumberZet +1 || secondCharNumber == secondCharNumberZet-1))
                ||(firstCharNumber==firstCharNumberZet+1 && (secondCharNumber == secondCharNumberZet+2||secondCharNumber==secondCharNumberZet-2))
                ||(firstCharNumber==firstCharNumberZet-1 && (secondCharNumber == secondCharNumberZet+2||secondCharNumber==secondCharNumberZet-2))
                ||(firstCharNumber==firstCharNumberZet-2 && (secondCharNumber == secondCharNumberZet-1||secondCharNumber==secondCharNumberZet+1))
        ){
            //System.out.println("test");
            if (aanZet == true){
                if (table[firstCharNumberZet][secondCharNumberZet].contains("B")){
                    schaakStukSyntax = false;
                }
                else{
                    schaakStukSyntax = true;
                }
            }
            if (aanZet == false){
                if (table[firstCharNumberZet][secondCharNumberZet].contains("W")){
                    schaakStukSyntax = false;
                }
                else{
                    schaakStukSyntax = true;
                }
            }
        }
        else{
            schaakStukSyntax = false;
        }
    }
    public static void bishopCheck(){ //kijkt of de bishop (de loper) wel naar de plek mag die is ingevuld
        if (Math.abs(firstCharNumberZet-firstCharNumber) == Math.abs(secondCharNumberZet-secondCharNumber)){
            for (int i = 1; i < Math.abs(firstCharNumberZet-firstCharNumber); i++) {
                if(firstCharNumber > firstCharNumberZet && secondCharNumber > secondCharNumberZet) {
                    if (!(table[firstCharNumber-i][secondCharNumber-i].equals(niks))) {
                        schaakStukSyntax = false;
                        return;
                    }
                }
                else if(firstCharNumber > firstCharNumberZet && secondCharNumber < secondCharNumberZet) {
                    if (!(table[firstCharNumber-i][secondCharNumber+i].equals(niks))) {
                        schaakStukSyntax = false;
                        return;
                    }
                }
                else if(firstCharNumber < firstCharNumberZet && secondCharNumber < secondCharNumberZet) {
                    if (!(table[firstCharNumber+i][secondCharNumber+i].equals(niks))) {
                        schaakStukSyntax = false;
                        return;
                    }
                }
                else if(firstCharNumber < firstCharNumberZet && secondCharNumber > secondCharNumberZet) {
                    if (!(table[firstCharNumber+i][secondCharNumber-i].equals(niks))) {
                        schaakStukSyntax = false;
                        return;
                    }
                }
               else if(firstCharNumberZet == firstCharNumber){
                   schaakStukSyntax = false;
                   System.out.println("Hier staat hij al op, kies opnieuw.");
               }
            }
            if (aanZet == true){
                if (table[firstCharNumberZet][secondCharNumberZet].contains("B")){
                    schaakStukSyntax = false;
                }
                else{
                    schaakStukSyntax = true;
                }
            }
            if (aanZet == false){
                if (table[firstCharNumberZet][secondCharNumberZet].contains("W")){
                    schaakStukSyntax = false;
                }
                else{
                    schaakStukSyntax = true;
                }
            }
        }
        else{
            schaakStukSyntax = false;
        }
    }
    public static void kingCheck(){ //kijkt of de king (de koning) wel naar de plek mag die is ingevuld
        if ((firstCharNumber == firstCharNumberZet-1 ) || (firstCharNumber == firstCharNumberZet+1) || (secondCharNumber == secondCharNumberZet-1) || (secondCharNumber==secondCharNumberZet+1)){
            if (aanZet == true){
                if (table[firstCharNumberZet][secondCharNumberZet].contains("B")){
                    schaakStukSyntax = false;
                }
                else{
                    schaakStukSyntax = true;
                }
            }
            if (aanZet == false){
                if (table[firstCharNumberZet][secondCharNumberZet].contains("W")){
                    schaakStukSyntax = false;
                }
                else{
                    schaakStukSyntax = true;
                }
            }
        }
        else{
            schaakStukSyntax = false;
        }
    }
    public static void queenCheck(){ //kijkt of de queen (de koningin) wel naar de plek mag die is ingevuld. Dit doet het door te kijken of de toren, of de loper deze zet kan uitvoeren.
        rookCheck();
        if (schaakStukSyntax == true){
            return;
        }
        bishopCheck();
    }
}
