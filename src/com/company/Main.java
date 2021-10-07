package com.company;

/**The banker's algorithm is a resource allocation and deadlock avoidance algorithm
 * that tests for safety by simulating the allocation for predetermined maximum possible amounts of all resources,
 *  then makes an “s-state” check to test for possible activities, before deciding whether allocation should be allowed to continue ...
 * @author Felix Ogbonnaya
 * @since 2021-04-20
 */
public class Main {
    //Initialize the Allocation matrix P1 - P5
    private static int [][] alloc_d = {{ 0,0,1,2 }, { 1,0,0,0 } ,{ 1,3,5,4 } ,{0,6,3,2 } ,{ 0,0,1,4 }};
    //Initialize the Max Matrix P1 - P5
    private static int [][] max_d = {{ 0,0,1,2 }, { 1,7,5,0 }, { 2,3,5,6 }, { 0,6,5,2 }, { 0,6,5,6 }};
    //These are available resources
    private static int [] avail_e = { 1, 5, 2, 0};

    private static int [][] need;
    //Initialize the number of process
    private static int process = alloc_d.length;
    //Initialize the number of resources
    private static int resources = avail_e.length;

    public static void main(String[] args) {

        String result = safety_algorithm().toString();
        System.out.println("Safe Sequence:  " + result);

    }

    public static StringBuilder safety_algorithm(){
        int [] finish = new int[process];
        //Iterates through the values in the Array Finish and initializes each to 0
        for (int i : finish){
            finish[i] = 0;
        }
        calculate_need();
        int is_satisfied = 0;
        StringBuilder sb = new StringBuilder("[ ");

        //Loop through until the jobs are satisfied
        while (is_satisfied < process){
            for (int i = 0; i < process; i++) {
                if(finish[i] == 0 ){

                    for (int j = 0; j< resources; j++){
                        if(need[i][j] > avail_e[j]){
                            break;
                        }
                        if(j == resources - 1){
                            for (int k = 0; k < resources; k++) {
                                avail_e[k] += alloc_d[i][k];
                            }
                            sb.append("P" + i + " ");
                            is_satisfied += 1;
                            finish[i] = 1;
                        }
                    }
                }
            }
        }
        sb.append("]");
        return sb;

    }
    private static void calculate_need(){
        need = new int[process][resources];

        for(int i = 0; i<process; i++){
            for(int j = 0; j<resources; j++){
                need[i][j] = max_d[i][j] - alloc_d[i][j];
            }

        }
    }
}
