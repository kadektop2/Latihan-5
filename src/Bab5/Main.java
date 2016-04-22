package Bab5;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Tamu> listTamu = new ArrayList<>();
    static ArrayList<Pesan> pesanTamu = new ArrayList<>();
    static Scanner scn;
    static int loggedID = 0;

    public static void main(String[] args) {
        scn = new Scanner(System.in);
        while (true) {
            if (loggedID > 0) {
                System.out.print("Selamat datang di buku tamu!\nAnda login sebagai : " + listTamu.get(loggedID - 1).getNama() + "\n1. Isi Buku Tamu\n2. Lihat Buku Tamu\n3. Logout\n4. Kelua\nMasukkan pilihan : ");
                int pil = scn.nextInt();
                scn.nextLine(); //cast enter

                switch (pil) {
                    case 1:
                        Main.isiPesan();
                        break;
                    case 2:
                        Main.showPesan();
                        break;
                    case 3:
                        Main.logoutTamu();
                        break;
                    case 4:
                        Main.exit();
                        break;
                    default:
                        System.out.println("Maaf menu tidak ditemukan.");
                        break;
                }
            } else {
                System.out.print("Selamat datang di buku tamu!\n1. Daftar\n2. Login\n3. Lihat Buku Tamu\n4. Logout\n5. Exit\nMasukkan pilihan : ");
                int pil = scn.nextInt();
                scn.nextLine(); //cast enter

                switch (pil) {
                    case 1:
                        Main.addTamu();
                        break;
                    case 2:
                        Main.loginTamu();
                        break;
                    case 3:
                        Main.showPesan();
                        break;
                    case 4:
                        Main.logoutTamu();
                        break;
                    case 5:
                        Main.exit();
                        break;
                    default:
                        System.out.println("Maaf menu tidak ditemukan.");
                        break;
                }
            }
        }
    }

    private static void addTamu() {
        Tamu tamu = new Tamu();
        //set id tamu, get arraylist size and increment 1
        tamu.setId(listTamu.size() + 1);
        //set nama
        System.out.print("Masukkan nama : ");
        tamu.setNama(scn.nextLine());
        System.out.print("Masukkan email : ");
        tamu.setEmail(scn.nextLine());
        System.out.print("Apakah data yang anda masukkan sudah benar ? (Y/N) : ");
        if (scn.next().equalsIgnoreCase("Y")) {
            listTamu.add(tamu);
            System.out.println("\nPendaftaran berhasil!\nID User : " + tamu.getId() + "\nNAMA : " + tamu.getNama() + "\nEMAIL : " + tamu.getEmail() + "\n");
        }
    }

    private static void loginTamu() {
        Tamu tamus = new Tamu();
        System.out.print("Masukkan id user : ");
        tamus.setId(scn.nextInt());
        scn.nextLine();
        System.out.print("Masukkan email : ");
        tamus.setEmail(scn.nextLine());
        boolean found = false;
        for (Tamu list : listTamu) {
            if (list.getId() == tamus.getId() && list.getEmail().equalsIgnoreCase(tamus.getEmail())) {
                System.out.println("Login Berhasil!");
                loggedID = tamus.getId();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("User tidak ditemukan");
        }
    }

    private static void isiPesan() {
        Pesan pesan = new Pesan();
        System.out.print("Kepada : ");
        pesan.setKepada(scn.nextLine());
        System.out.print("Isi Pesan : ");
        pesan.setPesan(scn.nextLine());
        pesan.setId_tamu(loggedID - 1);
        System.out.print("Apakah data yang anda masukkan sudah benar ? (Y/N) : ");
        if (scn.next().equalsIgnoreCase("Y")) {
            pesanTamu.add(pesan);
            System.out.println("Pesan berhasil di tambahkan!");
        }
    }

    private static void showPesan() {
        if (loggedID > 0) {
            System.out.println("Anda telah login dengan akun : " + listTamu.get(loggedID - 1).getNama() + " list pesan akan difilter berdasarkan id anda.\n Jika anda ingin melihat pesan dari tamu lain silahkan logout.");
            for (Pesan pesans : pesanTamu) {
                if (pesans.getId_tamu() == (loggedID - 1)) {
                    System.out.println("===========================");
                    System.out.println("Kepada : " + pesans.getKepada());
                    System.out.println("Dari : " + listTamu.get(pesans.getId_tamu()).getNama());
                    System.out.println("Isi Pesan : " + pesans.getPesan());
                    System.out.println("===========================");
                }
            }
        } else {
            for (Pesan pesans : pesanTamu) {
                System.out.println("===========================");
                System.out.println("Kepada : " + pesans.getKepada());
                System.out.println("Dari : " + listTamu.get(pesans.getId_tamu()).getNama());
                System.out.println("Isi Pesan : " + pesans.getPesan());
                System.out.println("===========================");
            }
        }
    }

    private static void logoutTamu() {
        loggedID = 0;
        System.out.println("Anda telah logout.");
    }
    
    private static void exit(){
        System.exit(0);
    }
}