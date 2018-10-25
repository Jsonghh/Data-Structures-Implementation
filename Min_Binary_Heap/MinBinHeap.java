package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }
  
  public void swap(EntryPair[] array, int curIdx, int parIdx) {
	  EntryPair tmp = array[curIdx];
	  array[curIdx] = array[parIdx];
	  array[parIdx] = tmp;
  }

@Override
public void insert(EntryPair entry) {
	array[++size] = entry;
	int index = size;
	while (index > 1 && array[index].getPriority() < array[index / 2].getPriority()) {
		swap(array, index, index / 2);
		index /= 2;
	}
	
}

@Override
public void delMin() {
	array[1] = array[size];
	int index = 1;
	while (index * 2 < size) {
		int k = 2 * index;
		if (array[k].getPriority() > array[k + 1].getPriority()) {
			k++;
		}
		if (array[index].getPriority() < array[k].getPriority()) {
			break;
		}
		swap(array, index, k);
		index = k;
	}
	array[size] = null;
	size--;
	
}

@Override
public EntryPair getMin() {
	return array[1];
}

@Override
public int size() {
	return size;
}

@Override
public void build(EntryPair[] entries) {
	if (entries == null || entries.length == 0) {
		return;
	}
	if (entries.length == 1) {
		size = entries.length;
		return;
	}
	size = entries.length;
	int i = 0;
	for (EntryPair entry : entries) {
		array[++i] = entry;
	}
	int m = size / 2;
	while (m >= 1) { // outer loop: iterate from last leaf to root
		int index = m;
		while (index * 2 < size) { //inner loop: iterate from current parent to leaf
			int k = 2 * index;
			if (array[k].getPriority() > array[k + 1].getPriority()) {
				k++;
			}
			if (array[index].getPriority() < array[k].getPriority()) {
				break;
			}
			swap(array, index, k);
			index = k;
		}
		m--;
	}
}
}