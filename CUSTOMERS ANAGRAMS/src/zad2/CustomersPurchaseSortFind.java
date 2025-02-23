/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CustomersPurchaseSortFind {

    private List<Purchase> purchaseList;


    public CustomersPurchaseSortFind() {
        purchaseList = new ArrayList<Purchase>();
    }



    public void readFile(String filePath){

        try {

            Scanner sc = new Scanner(new File(filePath));

            while (sc.hasNext()) {
                String[] customerInfo = sc.nextLine().split(";");
                Purchase purchase = new Purchase(
                        customerInfo[0], customerInfo[1], customerInfo[2],
                        Double.parseDouble(customerInfo[3]), Double.parseDouble(customerInfo[4])
                );
                this.purchaseList.add(purchase);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void showSortedBy(String sortOrder) {
        System.out.println(sortOrder);
        if(sortOrder.equals("Nazwiska")){
            this.purchaseList.stream()
                    .sorted(Comparator.comparing(Purchase::getName).thenComparing(Purchase::getId))
                    .forEach(System.out::println);
        } else {
            this.purchaseList.stream()
                    .sorted(Comparator.comparing(Purchase::getOverallCost).reversed().thenComparing(Purchase::getId))
                    .forEach(e -> System.out.println(e + " (koszt: " + e.getOverallCost() + ")"));
        }

        System.out.println();
    }


    public void showPurchaseFor(String idInput) {
        System.out.println("Klient " + idInput);
        for(Purchase purchase : purchaseList){
            if(idInput.equals(purchase.getId())){
                System.out.println(purchase);
            }
        }
        System.out.println();
    }


}
