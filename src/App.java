import java.util.Random;
import java.util.Arrays;

public class App {

    public static int MostProfitable(int[] values){
        //this is a holder for the maxProfit on the left half of the array
        int maxLeft = 0;
        //this is a holder for the maxProfit on the right half of the array
        int maxRight = 0;
        //this is for the 3rd case where the most profit bought on the left half and sold on the right half
        int lastCase = 0;

        if(values.length <= 1){
            return 0;
        }
        else{

            //creates a copy of the left side of the array
            int[] leftHalf = Arrays.copyOfRange(values, 0, values.length/2);
            //creates a copy of the right side of the array
            int[] rightHalf = Arrays.copyOfRange(values, values.length/2, values.length);

            //recursive calls on the left and right halves
            maxLeft = MostProfitable(leftHalf);
            maxRight = MostProfitable(rightHalf);
            
            int min = Integer.MAX_VALUE;

            for(int i=0; i < leftHalf.length; i++){
                if(leftHalf[i] < min)
                    min = leftHalf[i];
            }
            int max = Integer.MIN_VALUE;
            for(int i=0; i < rightHalf.length; i++){
                if(rightHalf[i] > max)
                    max = rightHalf[i];
            }

            lastCase = max - min;

            return Math.max(lastCase, Math.max(maxLeft, maxRight));
        }
    }

    public static int MostProfitableOneArray(int[] values, int startIndex, int endIndex, int currentLength){
        //this is a holder for the maxProfit on the left half of the array
        int maxLeft = 0;
        //this is a holder for the maxProfit on the right half of the array
        int maxRight = 0;
        //this is for the 3rd case where the most profit bought on the left half and sold on the right half
        int lastCase = 0;

        if(currentLength <= 1){
            return 0;
        }
        else{
            //recurses on the left side of the array
            maxLeft = MostProfitableOneArray(values, startIndex, currentLength/2, (currentLength/2 - startIndex));
            //recurses on the right side of the array
            maxRight = MostProfitableOneArray(values, currentLength/2, endIndex, (currentLength/2 - startIndex));
            
            int min = Integer.MAX_VALUE;

            
            for(int i=startIndex; i < endIndex/2; i++){
                if(values[i] < min)
                    min = values[i];
            }

            int max = Integer.MIN_VALUE;
            for(int i=endIndex/2; i < endIndex; i++){
                if(values[i] > max)
                    max = values[i];
            }

            lastCase = max - min;

            //System.out.println(Math.max(lastCase, Math.max(maxLeft, maxRight)));
            return Math.max(lastCase, Math.max(maxLeft, maxRight));
        }
    }


    public static void main(String[] args) throws Exception {
        for(int p=482; p<488; p++){
            Random r = new Random(p);
            int size = 10000000;
            int vals[] = new int[size];
            for(int i=0; i<size; i++){
                vals[i] = r.nextInt(Integer.MAX_VALUE);
            }
            //you have your array called vals, solve the problem here
            int profit = MostProfitable(vals);
            System.out.println(p-481 + ": " + profit);
            int profit1 = MostProfitableOneArray(vals, 0, vals.length, vals.length);
            System.out.println(p-481 + ": " + profit1);
        }
    }
}
