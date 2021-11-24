import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HousieTicketGenerationApp {

    public static int[][] ticketMatrix = new int[3][9];

    static List<Integer> ticketNumbers = new ArrayList<>();

    static List<Integer> ticketNumbers2 = new ArrayList<>();

    static int row0Counter = 0, row1Counter = 0, row2Counter = 0, columnIndex = 0;

    static List<Integer> allNums = new ArrayList<>();

    public static void main(String[] args) {

        List<Integer> c1 = new ArrayList<>();
        fillTheList(c1,1,10);

        List<Integer> c2 = new ArrayList<>();
        fillTheList(c2,11,20);

        List<Integer> c3 = new ArrayList<>();
        fillTheList(c3,21,30);

        List<Integer> c4 = new ArrayList<>();
        fillTheList(c4,31,40);

        List<Integer> c5 = new ArrayList<>();
        fillTheList(c5,41,50);

        List<Integer> c6 = new ArrayList<>();
        fillTheList(c6,51,60);

        List<Integer> c7 = new ArrayList<>();
        fillTheList(c7,61,70);

        List<Integer> c8 = new ArrayList<>();
        fillTheList(c8,71,80);

        List<Integer> c9 = new ArrayList<>();
        fillTheList(c9,81,90);

        List<List<Integer>> numberRangeLists = new ArrayList<>();
        numberRangeLists.add(c1);
        numberRangeLists.add(c2);
        numberRangeLists.add(c3);
        numberRangeLists.add(c4);
        numberRangeLists.add(c5);
        numberRangeLists.add(c6);
        numberRangeLists.add(c7);
        numberRangeLists.add(c8);
        numberRangeLists.add(c9);



        fillTheList(allNums, 1, 90);

        for (int i=0; i<9; i++){
            int random = generateRandomNumberFromList(numberRangeLists.get(i));
            allNums.remove(Integer.valueOf(random));
            ticketNumbers.add(random);

        }


        List<Integer> rowNumbers = new ArrayList<>();
        rowNumbers.add(0);
        rowNumbers.add(1);
        rowNumbers.add(2);


        List<List<Integer>> rowIndexesForAllColumns = new ArrayList<>();


       while(rowIndexesForAllColumns.size() < 9) {

           List<Integer> randomRowIndexes = new ArrayList<>();
            int random = generateRandomRowIndexes(rowNumbers);
               if(random == 0 && row0Counter<5) {
                   row0Counter++;
                   randomRowIndexes.add(random);
                   rowIndexesForAllColumns.add(randomRowIndexes);
                   ticketMatrix[random][columnIndex] = ticketNumbers.get(columnIndex);
                   columnIndex++;
               }
               else if(random == 1 && row1Counter<5) {
                   row1Counter++;
                   randomRowIndexes.add(random);
                   rowIndexesForAllColumns.add(randomRowIndexes);
                   ticketMatrix[random][columnIndex] = ticketNumbers.get(columnIndex);
                   columnIndex++;
               }
               else if(random == 2 && row2Counter<5) {
                   row2Counter++;
                   randomRowIndexes.add(random);
                   rowIndexesForAllColumns.add(randomRowIndexes);
                   ticketMatrix[random][columnIndex] = ticketNumbers.get(columnIndex);
                   columnIndex++;
               }
        }


        for(int i=1; i<=6; i++){
            int random = generateRandomNumberFromList(allNums);
            checkTheRange(random, rowIndexesForAllColumns, rowNumbers);
            ticketNumbers2.add(random);
        }

        Collections.sort(ticketNumbers2);


        Iterator itr = rowIndexesForAllColumns.iterator();

        int columnIndexInTheList = 0, ticket2Counter=0;

        while(itr.hasNext()){
            try {
                List<Integer> columnsList = (List<Integer>) itr.next();
                int size = columnsList.size();
                if (size != 1) {
                    int row_index = columnsList.get(1);
                    ticketMatrix[row_index][columnIndexInTheList] = ticketNumbers2.get(ticket2Counter);
                    ticket2Counter++;
                }
                columnIndexInTheList++;
            }
            catch(ArrayIndexOutOfBoundsException ex){
                System.out.println(ex.getMessage());
            }
        }


        printMatrix(ticketMatrix);


    }

    private static void printMatrix(int[][] ticketMatrix) {
        System.out.println("-----------------------------------------------------------");
        for(int r=0; r<3; r++){
            for(int c=0; c<9; c++){
                System.out.print(ticketMatrix[r][c] + "  |  ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
    }


    private static int generateRandomRowIndexes(List<Integer> rowNumbers) {

            int random = generateRandomNumberFromList(rowNumbers);

            return random;

    }

    private static void generateRandomRowSecondIndexes(List<List<Integer>> rowIndexesForAllColumns, List<Integer> rowNumbers, int columnNo) {
        int rowNumberAlreadyFilled = rowIndexesForAllColumns.get(columnNo).get(0);
        List<Integer> rowNumbersCopied = new ArrayList<>();
        rowNumbersCopied.addAll(rowNumbers);
        rowNumbersCopied.remove(Integer.valueOf(rowNumberAlreadyFilled));

        int random = generateRandomRowIndexes(rowNumbersCopied);
        if(random == 0) {
            if(row0Counter<5) {
                row0Counter++;
                List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                getRandomRowIndexes.add(random);
            }
            else{
                if(rowNumberAlreadyFilled !=1){
                    row1Counter++;
                    List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                    getRandomRowIndexes.add(1);
                }
                else{
                    row2Counter++;
                    List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                    getRandomRowIndexes.add(2);
                }
            }
        }
        else if(random == 1) {
            if(row1Counter<5) {
                row1Counter++;
                List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                getRandomRowIndexes.add(random);
            }
            else{
                if(rowNumberAlreadyFilled !=0){
                    row0Counter++;
                    List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                    getRandomRowIndexes.add(0);
                }
                else{
                    row2Counter++;
                    List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                    getRandomRowIndexes.add(2);
                }
            }

        }
        else if(random == 2) {
            if(row2Counter<5) {
                row2Counter++;
                List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                getRandomRowIndexes.add(random);
            }
            else{
                if(rowNumberAlreadyFilled !=0){
                    row0Counter++;
                    List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                    getRandomRowIndexes.add(0);
                }
                else{
                    row1Counter++;
                    List getRandomRowIndexes = rowIndexesForAllColumns.get(columnNo);
                    getRandomRowIndexes.add(1);
                }
            }

        }

    }

    private static void checkTheRange(int randomNum, List<List<Integer>> rowIndexesForAllColumns, List<Integer> rowNumbers) {
        if(randomNum>=1 && randomNum<=10){
            removeTheNumbersFromTheList(1,10);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 0);
        }
        else if(randomNum>=11 && randomNum<=20){
            removeTheNumbersFromTheList(11,20);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 1);
        }
        else if(randomNum>=21 && randomNum<=30){
            removeTheNumbersFromTheList(21,30);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 2);
        }
        else if(randomNum>=31 && randomNum<=40){
            removeTheNumbersFromTheList(31,40);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 3);
        }
        else if(randomNum>=41 && randomNum<=50){
            removeTheNumbersFromTheList(41,50);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 4);
        }
        else if(randomNum>=51 && randomNum<=60){
            removeTheNumbersFromTheList(51,60);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 5);
        }
        else if(randomNum>=61 && randomNum<=70){
            removeTheNumbersFromTheList(61,70);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 6);
        }
        else if(randomNum>=71 && randomNum<=80){
            removeTheNumbersFromTheList(71,80);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 7);
        }
        else {
            removeTheNumbersFromTheList(81,90);
            generateRandomRowSecondIndexes(rowIndexesForAllColumns, rowNumbers, 8);
        }

    }



    private static void removeTheNumbersFromTheList(int start, int end) {
        for(int i=start; i<=end; i++){
            if(allNums.contains(i)){
                allNums.remove(Integer.valueOf(i));
            }
        }
    }


    private static void fillTheList(List<Integer> c1, int start, int end) {
        for(int i=start; i<=end; i++){
            c1.add(i);
        }
    }

    private static int generateRandomNumberFromList(List<Integer> list) {

        int first = 0;
        int last = list.size() - 1;

        int r = (int) (Math.random()*(last - first + 1) + first);


        return list.get(r);
    }


}
