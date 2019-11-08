import Models.*;

import java.util.ArrayList;

public class Program {
    public static void main(String args[]) {
        DomesticCat domesticCat1 = new DomesticCat("Licorice", "Bombay", "Everett");
        DomesticCat domesticCat2 = new DomesticCat("Lollipop", "Norwegian Forest", "Gideon");
        DomesticCat domesticCat3 = new DomesticCat("Cotton Candy", "Birman", "Judah");

        WildCat wildCat1 = new WildCat("Biscuit", "Scottish Fold");
        WildCat wildCat2 = new WildCat("Muffin", "Ragamuffin");
        WildCat wildCat3 = new WildCat("Custard", "Russian Blue");

        ArrayList<Cat> catsList = new ArrayList();
        catsList.add(domesticCat1);
        catsList.add(domesticCat2);
        catsList.add(domesticCat3);
        catsList.add(wildCat1);
        catsList.add(wildCat2);
        catsList.add(wildCat3);

        System.out.println("\t\uD83D\uDC3E CATS LIST \uD83D\uDC3E\n");
        for (int i = 0; i < catsList.size(); i++) {
            System.out.println("\uD83D\uDC3E " + catsList.get(i).getName() + " (" + catsList.get(i).getBreed() + ") playing status: ");
            catsList.get(i).play(i % 2 == 0);
        }

        System.out.print("\nHey, " + domesticCat2.getName() + "! \nResponse: ");
        domesticCat2.layOnSofa();

        System.out.print("\nKitty-kitty, " + wildCat3.getName() + "! \nResponse: ");
        wildCat3.hatePeople();

        System.out.print("\nWhy are you so happy, " + domesticCat1.getName() + "? \nResponse: ");
        domesticCat1.spoilShoes();
    }
}