/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static volatile boolean endProccess = false;


  public static void main(String[] args) {

      List<Towar> towary = new ArrayList<Towar>();

      Lock lock = new ReentrantLock();

        Thread A = new Thread(() -> {

            try {
                Scanner scanner = new Scanner(new File("../Towary.txt"));

                int counter = 1;

                    while (scanner.hasNext()) {
                        int idTowaru = scanner.nextInt();
                        double wagaTowaru = scanner.nextDouble();

                        lock.lock();
                        try {
                            towary.add(new Towar(idTowaru, wagaTowaru));
                        } finally {
                            lock.unlock();
                        }

                        if(counter % 200 == 0)
                            System.out.println("utworzono " + counter + " obiektów");

                        counter++;
                    }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                endProccess = true;
            }

        });


        Thread B = new Thread(() -> {
            double lacznaWaga = 0;
            int counter = 1;

            while (!endProccess || !towary.isEmpty()) {

                Towar tmpTowar = null;

                lock.lock();
                try {
                    if (!towary.isEmpty())
                        tmpTowar = towary.remove(0);
                } finally {
                    lock.unlock();
                }

                if (tmpTowar != null) {
                    lacznaWaga += tmpTowar.getWaga();
                    counter++;

                    if(counter % 100 == 0)
                        System.out.println("policzono wage " + counter + " towarów");
                }
            }

            System.out.println(lacznaWaga);

        });


         A.start();
         B.start();

         try {
             A.join();
             B.join();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

  }
}
