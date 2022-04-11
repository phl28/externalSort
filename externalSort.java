import java.io.File;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;
import java.io.FileWriter;
import java.util.Random;

// Below lines marked with !!!!!! are lines that I believe have to be corrected as they were originally c++ code

 
// A min heap node
class MinHeapNode
{
    int element; // The element to be stored
     
     // index of the array from
     // which the element is taken
    int i;
     
    // index of the next element
    // to be picked from array
    int j;
 
    public MinHeapNode(int element, int i, int j)
    {
        this.element = element;
        this.i = i;
        this.j = j;
    }
};
 
// A class for Min Heap
class MinHeap
{
    MinHeapNode[] harr; // Array of elements in heap
    int heap_size; // Current number of elements in min heap
 
    // Constructor: Builds a heap from
    // a given array a[] of given size
    public MinHeap(MinHeapNode a[], int size)
    {
        heap_size = size;
        harr = a;
        int i = (heap_size - 1)/2;
        while (i >= 0)
        {
            MinHeapify(i);
            i--;
        }
    }
 
    // A recursive method to heapify a subtree
    // with the root at given index This method
    // assumes that the subtrees are already heapified
    void MinHeapify(int i)
    {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap_size && harr[l].element < harr[i].element)
            smallest = l;
        if (r < heap_size && harr[r].element < harr[smallest].element)
            smallest = r;
        if (smallest != i)
        {
            swap(harr, i, smallest);
            MinHeapify(smallest);
        }
    }
 
    // to get index of left child of node at index i
    int left(int i) { return (2*i + 1); }
 
    // to get index of right child of node at index i
    int right(int i) { return (2*i + 2); }
 
    // to get the root
    MinHeapNode getMin()
    {
        if(heap_size <= 0)
        {
            System.out.println("Heap underflow");
            return null;
        }
        return harr[0];
    }
 
    // to replace root with new node
    // "root" and heapify() new root
    void replaceMin(MinHeapNode root) {
        harr[0] = root;
        MinHeapify(0);
    }
 
    // A utility function to swap two min heap nodes
    void swap(MinHeapNode[] arr, int i, int j) {
        MinHeapNode temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 

};



public class externalSort {
    
    public static void main (String[] args) {
        // code below is wrong as it is copied directly from a c++ code
        // !!!!!!
        // No. of Partitions of input file.
        int num_ways = 10;
     
        // The size of each partition
        int run_size = 1000;
     
        String input_file = "input.txt";
        String output_file = "output.txt";
     
        File in = writeFile(input_file);
     
        Random rand = new Random()
        int upperbound = 1000000;
        
        // generate input
        for (int i = 0; i < num_ways * run_size; i++)
            int randInt = rand.nextInt(upperbound);
            in.write(randomInt);
     
        in.close();
     
        externalSort(input_file, output_file, num_ways,
                     run_size);
     
        return 0;
    }
    
    // perform mergeSort
    public static void mergeSort(int[] inputArray) {
        
        int inputLength = inputArray.length;
        
        if (inputLength < 2) {
            return ;
        }
        
        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];
        
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = inputArray[i];
        }
        
        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i - midIndex] = inputArray[i];
        }
        
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        
        merge(inputArray, leftHalf, rightHalf);
        
    }
    
    public static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            } else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }
        while (i < leftSize) {
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }
        while (j < rightSize) {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    File readFile(String fileName)
    {
        try {
            File fp = new File(fileName);
            Scanner reader = new Scanner(fp);
            return reader;
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred while opening the file.");
            e.printStackTrace();
        }

    }
    
    File writeFile(String fileName)
    {
        try {
            FileWriter writer = new FileWriter(filename);
            return writer;
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    
    
    // Merges k sorted files. Names of files are assumed
    // to be 1, 2, 3, ... k
    static void mergeFiles(String[] output_file, int n, int k)
    {
        File[] in = new File[k];
        for (int i = 0; i < k; i++) {
            String fileName = new String[2];
     
            // convert i to string
            // !!!!!!
            snprintf(fileName, sizeof(fileName),
                     "%d", i);
     
            // Open output files in read mode.
            in[i] = readFile(fileName);
        }
     
        // FINAL OUTPUT FILE
        File out = writeFile(output_file);
     
        // Create a min heap with k heap
        // nodes. Every heap node
        // has first element of scratch
        // output file
        MinHeapNode[] harr = new MinHeapNode[k];
        int i;
        for (i = 0; i < k; i++) {
            // break if no output file is empty and
            // index i will be no. of input files
            //!!!!!!
            if (fscanf(in[i], "%d ", &harr[i].element) != 1)
                break;
     
            // Index of scratch output file
            harr[i].i = i;
        }
        
        // Create the heap
        MinHeap hp = new MinHeap(harr, i);
     
        int count = 0;
     
        // Now one by one get the
        // minimum element from min
        // heap and replace it with
        // next element.
        // run till all filled input
        // files reach EOF
        while (count != i) {
            // Get the minimum element
            // and store it in output file
            MinHeapNode root = hp.getMin();
            // !!!
            fprintf(out, "%d ", root.element);
     
            // Find the next element that
            // will replace current
            // root of heap. The next element
            // belongs to same
            // input file as the current min element.
            // !!!!
            if (fscanf(in[root.i], "%d ",
                       &root.element)
                != 1) {
                root.element = Integer.MAX_VALUE;
                count++;
            }
     
            // Replace root with next
            // element of input file
            hp.replaceMin(root);
        }
     
        // close input and output files
        for (int i = 0; i < k; i++)
            int[i].close();
     
        out.close();
    }
    
    // Using a merge-sort algorithm,
    // create the initial runs
    // and divide them evenly among
    // the output files
    static void createInitialRuns(
        String[] input_file, int run_size,
        int num_ways)
    {
        // For big input file
        File in = readFile(input_file);
     
        // output scratch files
        File[] out = new File[num_ways];
        String[] fileName = new String[2];
        for (int i = 0; i < num_ways; i++) {
            // convert i to string
            // !!!!!!
            snprintf(fileName, fileName.SIZE,
                     "%d", i);
     
            // Open output files in write mode.
            out[i] = writeFile(fileName);
        }
     
        // allocate a dynamic array large enough
        // to accommodate runs of size run_size
        int[] arr = (int*)malloc(
            run_size * Integer.Bytes);
     
        boolean more_input = true;
        int next_output_file = 0;
     
        int i;
        while (more_input) {
            // write run_size elements
            // into arr from input file
            for (i = 0; i < run_size; i++) {
                // !!!!!
                if (fscanf(in, "%d ", &arr[i]) != 1) {
                    more_input = false;
                    break;
                }
            }
     
            // sort array using merge sort
            mergeSort(arr);
     
            // write the records to the
            // appropriate scratch output file
            // can't assume that the loop
            // runs to run_size
            // since the last run's length
            // may be less than run_size
            for (int j = 0; j < i; j++)
                //!!!!!
                fprintf(out[next_output_file],
                        "%d ", arr[j]);
     
            next_output_file++;
        }
     
        // close input and output files
        for (int i = 0; i < num_ways; i++)
            out[i].close();
     
        in.close();
    }
    
    // For sorting data stored on disk
    public static void externalSort(
        String[] input_file, String[] output_file,
        int num_ways, int run_size)
    {
        // read the input file,
        // create the initial runs,
        // and assign the runs to
        // the scratch output files
        createInitialRuns(input_file,
                          run_size, num_ways);
     
        // Merge the runs using
        // the K-way merging
        mergeFiles(output_file, run_size, num_ways);
    }
}


