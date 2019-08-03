package com.shadab.java.core.challenges;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'dynamicArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    // Write your code here

        // Write your code here
        int lastAnswer=0;
         List<Integer> result  = new ArrayList<>();
         List<Integer> list[];
            list = new LinkedList[n];
            for(int x=0; x<n; x++){ 
                list[x]= new LinkedList(); 
                             }
            
            for(int x=0; x<queries.size(); x++){
                
                int queryType=queries.get(x).get(0);
                int xElement = queries.get(x).get(1);
                int yElement = queries.get(x).get(2);
                if(queryType==1){
                    int index = ((xElement^lastAnswer)%n);
                    list[index].add(yElement);
                  }
                else if(queryType==2){
                    int index = ((xElement^lastAnswer)%n);
                    int newElement = yElement%list[index].size();
                    lastAnswer = list[index].get(newElement);
                    result.add(lastAnswer);
                     
                }
                
            }
    
         
        
        
        return result;

        
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.dynamicArray(n, queries);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
