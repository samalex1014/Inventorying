/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorying;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import java.util.ArrayList;

/**
 *
 * @author sam
 */
public class Inventorying {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList inventory = readMachines();
        
        checkSurplusAssets(inventory);
        
        checkSurplusSerial(inventory);
        
        checkSEPSerial(inventory);
        
        checkSEPMac(inventory);
        
        checkSEPHosts(inventory);
        
        int unverified = 0;
        
        for(int i = 0; i < inventory.size(); i++) {
            Computer curr = (Computer) inventory.get(i);
            
            if(!curr.verified()) {
                unverified++;
            }
        }
        
        int surplussed = 0;
        
        for(int i = 0; i < inventory.size(); i++) {
            Computer curr = (Computer) inventory.get(i);
            
            if(curr.surplussed()) {
                surplussed++;
            }
        }
        
        int active = 0;
        
        for(int i = 0; i < inventory.size(); i++) {
            Computer curr = (Computer) inventory.get(i);
            
            if(curr.active()) {
                active++;
            }
        }

        System.out.print("Inventory = " + inventory.size() + "\n");
        System.out.println("Active: " + active);
        System.out.println("Surplussed: " + surplussed);
        System.out.println("Unverified: " + unverified);
        
        printActive(inventory);
        printSurplus(inventory);
        printUnverified(inventory);
    }
    
    public static void printActive(ArrayList inv) {
        SimpleWriter write = new SimpleWriter1L("F:\\output\\active.csv");
        
        for(int i = 0; i < inv.size(); i++) {
            Computer curr = (Computer) inv.get(i);
            
            if(curr.active()) {
                write.println(curr.printInfo());
            }
        }
        
        write.close();
    }
    
    public static void printSurplus(ArrayList inv) {
        SimpleWriter write = new SimpleWriter1L("F:\\output\\surplus.csv");
        
        for(int i = 0; i < inv.size(); i++) {
            Computer curr = (Computer) inv.get(i);
            
            if(curr.surplussed()) {
                write.println(curr.printInfo());
            }
        }
        
        write.close();
    }
    
    public static void printUnverified(ArrayList inv) {
        SimpleWriter write = new SimpleWriter1L("F:\\output\\unverified.csv");
        
        for(int i = 0; i < inv.size(); i++) {
            Computer curr = (Computer) inv.get(i);
            
            if(!curr.verified()) {
                write.println(curr.printInfo());
            }
        }
        
        write.close();
    }

    public static ArrayList readMachines() {
        ArrayList<Computer> computers = new ArrayList();
        SimpleReader read = new SimpleReader1L("F:\\data\\computers.csv");
        int linesRead = 0;

        while (!read.atEOS()) {
            String machine = read.nextLine();

            linesRead++;

            String res[] = machine.split(",");
            
            if(res.length == 5) {
                computers.add(new Computer(res[0], res[1], res[2], res[3], res[4]));
            } else if (res.length == 4) {
                computers.add(new Computer(res[0], res[1], res[2], res[3]));
            } else if (res.length == 3) {
                computers.add(new Computer(res[0], res[1], res[2]));
            } else if (res.length == 2) {
                computers.add(new Computer(res[0], res[1]));
            }
        }

        System.out.print("Read " + linesRead + " lines\n");
        return computers;
    }

    public static void checkSurplusAssets(ArrayList comps) {
        SimpleReader read = new SimpleReader1L("F:\\data\\SurplusAssets.csv");

        while (!read.atEOS()) {
            String currAsset = read.nextLine();
            for (int i = 0; i < comps.size(); i++) {
                Computer curr = (Computer) comps.get(i);

                if (!curr.verified()) {

                    curr.checkAsset(currAsset, true, false);
                }
            }
        }
    }

    public static void checkSurplusSerial(ArrayList comps) {
        SimpleReader read = new SimpleReader1L("F:\\data\\SurplusSerials.csv");

        while (!read.atEOS()) {
            String currSerial = read.nextLine();

            for (int i = 0; i < comps.size(); i++) {
                Computer curr = (Computer) comps.get(i);
                if (!curr.verified()) {
                    curr.checkSerial(currSerial, true, false);
                }
            }
        }
    }

    public static void checkSEPSerial(ArrayList comps) {
        SimpleReader read = new SimpleReader1L("F:\\data\\SEPSerials.csv");

        while (!read.atEOS()) {
            String currSerial = read.nextLine();
            for (int i = 0; i < comps.size(); i++) {
                Computer curr = (Computer) comps.get(i);
                if (!curr.verified()) {
                    curr.checkSerial(currSerial, false, true);
                }
            }
        }
    }

    public static void checkSEPMac(ArrayList comps) {
        SimpleReader read = new SimpleReader1L("F:\\data\\SEPMacs.csv");

        while (!read.atEOS()) {
            String currMac = read.nextLine();
            for (int i = 0; i < comps.size(); i++) {
                Computer curr = (Computer) comps.get(i);
                if (!curr.verified()) {
                    curr.checkSerial(currMac, false, true);
                }
            }
        }
    }

    public static void checkSEPHosts(ArrayList comps) {
        SimpleReader read = new SimpleReader1L("F:\\data\\SEPHosts.csv");

        while (!read.atEOS()) {
            String currHost = read.nextLine();
            for (int i = 0; i < comps.size(); i++) {
                Computer curr = (Computer) comps.get(i);
                if (!curr.verified()) {
                    curr.checkSerial(currHost, false, true);
                }
            }
        }
    }
}
