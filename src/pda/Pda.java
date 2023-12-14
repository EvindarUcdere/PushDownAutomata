package pda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Pda {

    private enum State {
        Q0, Q1, Q2, Q3
    }
    static String satir;

    public static void main(String[] args) {
        dosyaOlustur("pdayıTAnırMi.txt");
        // Başlangıç durumu
        State currentState = State.Q0;
        //yıgının temsil eden veri yapısı
        Stack<Character> stack = new Stack<>();
        //giriş kelmesini her bir sembolünü kontrol et 
        try (BufferedReader reader = new BufferedReader(new FileReader("pdayıTAnırMi.txt"))) {
            System.out.println("Dosyadan okunan veriler:");
            while ((satir = reader.readLine()) != null) {
                if (satir != null) {
                    System.out.println(satir);
                    for (int i = 0; i <= satir.length(); i++) {
                        if (currentState == State.Q0) {
                            if (satir.charAt(i) == 'a') {
                                stack.push('a');
                                currentState = State.Q1;
                            } else {
                                System.out.println("kelime kabul edilmedi");
                                System.exit(0);
                            }
                        } else if (currentState == State.Q1) {
                            if (satir.charAt(i) == 'a') {
                                stack.push('a');
                                // 'a' geldiği sürece Q1 durumunda kalmaya devam et
                            } else if (satir.charAt(i) == 'b') {
                                // 'b' sembolü geldiyse yığından bir 'a' çıkar ve Q2 durumuna geç
                                stack.pop();
                                currentState = State.Q2;
                            } else {
                                System.out.println(satir+"  kelime kabul edilmedi");
                                System.exit(0);
                            }
                         }else if (currentState == State.Q2) {
                            // Q2 durumunda 'a' geldiyse yığından 'a' çıkar
                            if (satir.charAt(i) == 'b') {
                                stack.pop();
                                currentState = State.Q2;
                            }else if (satir.charAt(i) == 'c') {
                                // 'c' sembolü geldiyse Q3 durumuna geç
                                currentState = State.Q3;
                                if (stack.isEmpty()) {
                                    System.out.println(satir+"    kelime GEÇERLİ");
                                    break;
                                }
                            } else {
                                System.out.println(satir+   "kelime kabul edilmedi");
                                System.exit(0);
                            }
                        } else if (currentState == State.Q3) {
                            // eğer sembol 'c' değilse kelimeyi reddet
                              if(satir.charAt(i)=='c'){
                                  currentState=State.Q3;
                              }
                              else{
                                 break;
                              }    

                        }

                    }
                    if (currentState == State.Q3 && stack.isEmpty()) {
                        // Q3 durumunda ve yığıın boşsa kelimeyi kabul et
                        System.out.println(satir+ " kelime kabul edidi");
                    } else {
                        System.out.println(satir + "kelime kabul edilmedi!!!");
                        break;
                    }

                } else {
                    System.out.println("Dosya okuma hatası: satir null");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Dosya oluşturma

    private static void dosyaOlustur(String dosyaAdi) {
        try {
            File dosya = new File(dosyaAdi);
            if (dosya.createNewFile()) {
                System.out.println("Dosya oluşturuldu: " + dosyaAdi);
            } else {
                System.out.println("Dosya zaten mevcut.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Dosyadan veri okuma
    private static void dosyadanVeriOku(String dosyaAdi) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {

            System.out.println("Dosyadan okunan veriler:");
            while ((satir = reader.readLine()) != null) {
                if (satir != null) {
                    System.out.println(satir);
                } else {
                    System.out.println("Dosya okuma hatası: satir null");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
