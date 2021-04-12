package sort;
import java.util.Random;
import java.util.ArrayList;

/**
 * @author: kangkang
 * 2021/4/12
 * 七种排序算法的java实现
 */
public class Solution {
    public void bubbleSort(int[] array) {
        //冒泡排序（稳定排序）
        //每一次比较相邻两个数，将较大的数放在后面，这样两两比较，一共比较n-1次，这样较大的数就不断上升直到最后面，再对前面剩下的数进行相同的操作
        int m = array.length;
        for(int i = 0; i < m-1; i++){
            for(int j = 0; j < m - i - 1; j++) {
                int temp = 0;
                if(array[j] > array[j + 1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        for(int i = 0; i < m; i++){
            System.out.print(array[i] + " ");
        }
    }

    public void insertSort(int[] array) {
        //直接插入排序（稳定排序）
        //分为有序和无序两个部分，对于无序的第一个元素，依次和有序的数组元素进行比较，然后找到合适的位置插入进去，将比该元素大的元素依次后移。
        int sentinel,j = 0;
        for(int i = 1; i < array.length; i++){
            j = i - 1;
            sentinel = array[i];//记录当前位置的元素值，因为当其他元素后移的时候，可能会覆盖掉该元素。
            while (j >=0 && array[j] > sentinel) {
                array[j+1] = array[j];
                j--;
            }
            array[j + 1] = sentinel;//将元素插入到该位置
        }
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

    public void shellSort(int[] array) {
        //希尔排序
        //排序思想，设置一个增量，一般是array.length/2，将数组按照增量拆分，然后对每个小数组进行插入排序，完成之后设置增量 = 增量/2，然后再次对数组进行插入排序，直到增量为1，此时数组基本上都是有序的
        //然后再进行一次插入排序即可。
        //step:步长
        for (int step = array.length / 2; step > 0; step /= 2) {
            //对一个步长区间进行比较 [step,arr.length)
            for (int i = step; i < array.length; i++) {
                int value =array[i];
                int j;

                //对步长区间中具体的元素进行比较
                for (j = i - step; j >= 0 && array[j] > value; j -= step) {
                    //j为左区间的取值，j+step为右区间与左区间的对应值。
                    array[j + step] = array[j];
                }
                //此时step为一个负数，[j + step]为左区间上的初始交换值
                array[j + step] = value;
            }
        }

        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

    public void QuickSort(int[] array, int low, int high) {
        //快速排序
        //每次找一个基准数，把大于基准数的数放到基准数的右边，把小于基准树的数放到基准数的左边，然后再对左右两个数组一次进行相同的操作直到整个数组排序完成。
        int i,j,temp = 0;
        if(low > high) {
            return;
        }
        i = low;//i是左指针，从左向右遍历，找到比基准数大的数
        j = high;//j是右指针，从右向左遍历，找到比基准数小的数
        temp = array[low];//将基准数暂存
        int t = 0;//哨兵
        while ( i < j ) {
            while(i < j && temp <= array[j]) {
                //首先从右边开始遍历，找到比基准小的数，否则j不断减小
                j--;
            }
            while (i < j && temp >= array[i]) {
                //从左边遍历，找到比基准数大的数，否则i不断地增加
                i++;
            }
            if(i < j) {
                //在满足条件的情况下,将基准数放到中间，小数放左边，大数放右边
                t = array[j];
                array[j] = array[i];
                array[i] = t;
            }
        }
        array[low] = array[i];
        array[i] = temp;
        QuickSort(array, low, j - 1);
        QuickSort(array, j + 1, high);
    }

    public void selectSort(int[] array) {
        //直接选择排序（不稳定排序）
        //同样是分成两部分，对于无序的部分来说，每次遍历整个数组，将数组中最小的元素选择出来，然后将其放入到有序的列表当中。
        int flag = 0;//记录最小元素的位置
        int sentinel = 0;
        int sortListIndex = 0;//记录已经排序的列表的位置
        for(int i = 1; i < array.length; i++){
            //一共比较n-1次，当最后一次的时候是不需要比较的
            sentinel = array[sortListIndex];//记录有序列表下一位置的元素大小
            flag = sortListIndex;//每次比较前，将flag赋值为当前的索引，避免出现当前位置就是最小数的情况
            for(int j = sortListIndex; j < array.length; j++) {
                //依次比较
                if(array[j] < sentinel) {
                    sentinel = array[j];
                    flag = j;
                }
            }
            //当一次比较完毕，最小的元素放到当前索引出，将当前索引的元素放到最小的元素的位置
            array[flag] = array[sortListIndex];
            array[sortListIndex] = sentinel;
            sortListIndex++;
        }
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

    public void mergeSort(int[] array) {
        int[] temp = new int[array.length];// 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        mergeSort(array, 0, array.length-1, temp);
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

    public void mergeSort(int[] arr, int left, int right, int []temp) {
        if(left < right) {
            int mid = (left+right) / 2;
            mergeSort(arr, left, mid, temp);// 左边归并排序，使得左子序列有序
            mergeSort(arr, mid+1, right, temp);// 右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp);// 将两个有序子数组合并操作
        }
    }

    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;// 左序列指针
        int j = mid+1;// 右序列指针
        int t = 0;// 临时数组指针
        while (i <= mid && j <= right) {
            if(arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while(i <= mid) {// 将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j <= right) {// 将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        // 将temp中的元素全部拷贝到原数组中
        while(left <= right) {
            arr[left++] = temp[t++];
        }
    }

    public void radixSort(int[] array) {
        ArrayList<ArrayList<Integer>> queue = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            queue.add(new ArrayList<>());// 创建一个基数从0---9 每个数字上都是一个list
        }
        // 找到最大值，并判断最大值是几位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        int time = 0;
        while (max > 0) {
            max /= 10;
            time++;
        }
        for (int i = 0; i < time; i++) {// 循环每一个位数（个位、十位、百位）
            for (int j = 0; j < array.length; j++) {// 循环数组，取每一个值
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue3 = queue.get(x);

                queue3.add(array[j]);
                queue.set(x, queue3);
            }
            int count = 0;
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue4 = queue.get(k);
                    array[count] = queue4.get(0);
                    queue4.remove(0);
                    count++;
                }
            }
        }
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String args[]) {
        Random r = new Random();
        int[] list = new int[10];
        //打印原始数组
        for(int i = 0; i < 10; ++i) {
            list[i] = r.nextInt(100);
        }

        int[] list1 = list.clone();
        int[] list2 = list.clone();
        int[] list3 = list.clone();
        int[] list4 = list.clone();
        int[] list5 = list.clone();
        int[] list6 = list.clone();
        int[] list7 = list.clone();

        System.out.println("---初始数组---");
        for(int x : list){
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.println("---冒泡排序---");
        Solution s = new Solution();
        s.bubbleSort(list1);
        System.out.println();

        System.out.println("---插入排序---");
        s.insertSort(list2);
        System.out.println();

        System.out.println("---选择排序---");
        s.selectSort(list3);
        System.out.println();

        System.out.println("---快速排序---");
        s.QuickSort(list4, 0, list4.length - 1);
        for(int m = 0; m < list4.length; m++){
            System.out.print(list4[m] + " ");
        }
        System.out.println();

        System.out.println("---希尔排序---");
        s.shellSort(list5);
        System.out.println();

        System.out.println("---归并排序---");
        s.mergeSort(list6);
        System.out.println();

        System.out.println("---基数排序---");
        s.radixSort(list7);
        System.out.println();

    }
}
