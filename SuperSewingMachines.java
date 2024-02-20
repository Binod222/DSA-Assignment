public class SuperSewingMachines {
    int dresses[];
    final int noOfSewingMachines;
    final int totalDressCount, idealDressCount;
    final boolean isEquilizable;

    // Constructor
    SuperSewingMachines(int[] dresses) {
        this.dresses = dresses;
        noOfSewingMachines = dresses.length;
        int[] totalCountSolvable = checkEquilizabililty();
        isEquilizable = totalCountSolvable[1] == 1;
        totalDressCount = totalCountSolvable[0];
        idealDressCount = totalDressCount / noOfSewingMachines;
    }

    // Check if dresses can be equally distributed
    int[] checkEquilizabililty() {
        int totalDresses = 0;
        for (int sewingMachine = 0; sewingMachine < noOfSewingMachines; sewingMachine++) {
            totalDresses += dresses[sewingMachine];
        }
        return new int[]{totalDresses, totalDresses % noOfSewingMachines};
    }

    // Returns the sewing machine with the least and most dresses
    int[] minDressMaxDress() {
        int min = 0, max = 0;
        for (int sewingMachine = 1; sewingMachine < noOfSewingMachines; sewingMachine++) {
            if (dresses[sewingMachine] < dresses[min]) min = sewingMachine;
            if (dresses[sewingMachine] > dresses[max]) max = sewingMachine;
        }
        return new int[]{min, max};
    }

    // Check if all machines have ideal dress count
    boolean ifEqualized() {
        for (int sewingMachine = 1; sewingMachine < noOfSewingMachines; sewingMachine++) {
            if (dresses[sewingMachine] != idealDressCount) return false;
        }
        return true;
    }

    // Equalize dress distribution among machines
    int equalize() {
        if (!isEquilizable) return -1; // If not equilizable, return -1
        int moveCounter = 0;
        while (!ifEqualized()) {
            int[] minMax = minDressMaxDress();
            if (minMax[1] - minMax[0] > 0) {
                // Move dresses from machine with more dresses to machine with fewer dresses
                dresses[minMax[1]]--;
                dresses[minMax[0]]++;
            } else if (minMax[1] - minMax[0] < 0) {
                // Move dresses from machine with fewer dresses to machine with more dresses
                dresses[minMax[1]]++;
                dresses[minMax[0]]--;
            } else {
                // If dresses are already equally distributed, exit loop
                break;
            }
            moveCounter++;
        }
        return moveCounter;
    }

    // Print the dress count on each machine
    void printer() {
        System.out.println();
        for (int sewingMachine = 0; sewingMachine < noOfSewingMachines; sewingMachine++) {
            System.out.print(dresses[sewingMachine] + "\t");
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        SuperSewingMachines sp = new SuperSewingMachines(new int[]{2, 1, 3, 0, 2});
        System.out.println("Number of sewing machines: " + sp.noOfSewingMachines);
        System.out.println("Minimum moves required: " + sp.equalize());
        System.out.println("Dresses after equalization:");
        sp.printer();
    }
}
