import java.io.PrintWriter;
import java.io.IOException;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Collections;


class RoomAllocation {

	static final int START_TIME = 1;
	static final int END_TIME = 2;

	static class Reader 
	{ 
		final private int BUFFER_SIZE = 1 << 16; 
		private DataInputStream din; 
		private byte[] buffer; 
		private int bufferPointer, bytesRead; 
  
		public Reader() 
		{ 
			din = new DataInputStream(System.in); 
			buffer = new byte[BUFFER_SIZE]; 
			bufferPointer = bytesRead = 0; 
		}
  
		public int nextInt() throws IOException 
		{ 
			int ret = 0; 
			byte c = read(); 
			while (c <= ' ') 
				c = read(); 
			boolean neg = (c == '-'); 
			if (neg) 
				c = read(); 
			do
			{ 
				ret = ret * 10 + c - '0'; 
			}  while ((c = read()) >= '0' && c <= '9'); 
  
			if (neg) 
				return -ret; 
			return ret; 
		}
  
		private void fillBuffer() throws IOException 
		{ 
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
			if (bytesRead == -1) 
				buffer[0] = -1; 
		} 
  
		private byte read() throws IOException 
		{ 
			if (bufferPointer == bytesRead) 
				fillBuffer(); 
			return buffer[bufferPointer++]; 
		} 
  
		public void close() throws IOException 
		{ 
			if (din == null) 
				return; 
			din.close(); 
		} 
	}

	static class Customer implements Comparable<RoomAllocation.Customer> {
		private int startTime;
		private int endTime;
		private int originalIndex;

		public Customer(int startTime, int endTime, int originalIndex) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.originalIndex = originalIndex;
		}

		public int getStartTime() {
			return this.startTime;
		}
		public int getEndTime() {
			return this.endTime;
		}
		public int getOriginalIndex() {
			return this.originalIndex;
		}

		public int hashCode() {
			return this.startTime * 31 + this.endTime * 37 + this.originalIndex * 17;
		}

		public boolean equals(RoomAllocation.Customer customer) {
			if (customer == null) {
				return false;
			}
			return this.startTime == customer.startTime && this.endTime == customer.endTime && this.originalIndex == customer.originalIndex;
		}

		public int compareTo(RoomAllocation.Customer customer) {
			if (this.startTime != customer.startTime) {
				return this.startTime - customer.startTime;
			} else {
				if (this.endTime != customer.endTime) {
					return this.endTime - customer.endTime;
				} else {
					return this.originalIndex - customer.originalIndex;
				}
			}
		}
	}

	static class CustomerListItem implements Comparable<CustomerListItem> {
		private int time;
		private int timeType;
		private RoomAllocation.Customer customer;

		public CustomerListItem(int time, int timeType, RoomAllocation.Customer customer) {
			this.time = time;
			this.timeType = timeType;
			this.customer = customer;
		}

		public int compareTo(RoomAllocation.CustomerListItem item) {
			if (this.time != item.time) {
				return this.time - item.time;
			} else if (this.timeType != item.timeType) {
				return this.timeType - item.timeType;
			} else {
				return this.customer.compareTo(item.customer);
			}
		}

		public int getTime() {
			return this.time;
		}

		public int getTimeType() {
			return this.timeType;
		}

		public RoomAllocation.Customer getCustomer() {
			return this.customer;
		}
	} 

    public static RoomAllocation.CustomerListItem[] bucketSort(RoomAllocation.CustomerListItem[] array, int bucketCount) {
        //if (bucketCount <= 0) throw new IllegalArgumentException("Invalid bucket count");
        if (array.length <= 1) return array; //trivially sorted

        int high = array[0].time;
        int low = array[0].time;
        for (int i = 1; i < array.length; i++) { //find the range of input elements
            if (array[i].time > high) {
            	high = array[i].time;
            }
            if (array[i].time < low) {
            	low = array[i].time;
            }
        }
        double interval = ((double)(high - low + 1))/bucketCount; //range of one bucket

        ArrayList<RoomAllocation.CustomerListItem> buckets[] = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) { //initialize buckets
            buckets[i] = new ArrayList();
        }

        for (int i = 0; i < array.length; i++) { //partition the input array
            buckets[(int)((array[i].time - low)/interval)].add(array[i]);
        }

        int pointer = 0;
        for (int i = 0; i < buckets.length; i++) {
            Collections.sort(buckets[i]); //mergeSort
            for (int j = 0; j < buckets[i].size(); j++) { //merge the buckets
                array[pointer] = buckets[i].get(j);
                pointer++;
            }
        }
        return array;
    }

	public static void main(String[] args) throws IOException {
		RoomAllocation.Reader input = new RoomAllocation.Reader();
		int n = input.nextInt();
		RoomAllocation.CustomerListItem[] customerItems = new RoomAllocation.CustomerListItem[2 * n];
		for (int i = 0; i < n; i++) {
			int startTime = input.nextInt();
			int endTime = input.nextInt();
			RoomAllocation.Customer customer = new RoomAllocation.Customer(startTime, endTime, i);
			RoomAllocation.CustomerListItem startItem = new RoomAllocation.CustomerListItem(startTime, RoomAllocation.START_TIME, customer);
			RoomAllocation.CustomerListItem endItem = new RoomAllocation.CustomerListItem(endTime, RoomAllocation.END_TIME, customer);
			customerItems[i] = startItem;
			customerItems[2 * n - 1 - i] = endItem;
		}

		customerItems = bucketSort(customerItems, n > 100 ? n / 100 : 1);

		int numberOfRooms = 0;
		int maxNumberOfRooms = 0;

		HashMap<RoomAllocation.Customer, Integer> occupiedRooms = new HashMap<RoomAllocation.Customer, Integer>();
		ArrayList<Integer> freeRooms = new ArrayList<Integer>();
		int[] roomAllocation = new int[n];

		for (int i = 0; i < customerItems.length; i++) {
			RoomAllocation.CustomerListItem item = customerItems[i];
			if (item.getTimeType() == RoomAllocation.START_TIME) {
				numberOfRooms++;
				if (numberOfRooms > maxNumberOfRooms) {
					maxNumberOfRooms = numberOfRooms;
				}
				int roomNumber = maxNumberOfRooms;
				if (freeRooms.size() > 0) {
					roomNumber = freeRooms.get(freeRooms.size() - 1);
					freeRooms.remove(freeRooms.size() - 1);
				}
				occupiedRooms.put(item.getCustomer(), roomNumber);
				roomAllocation[item.getCustomer().getOriginalIndex()] = roomNumber;
			} else {
				numberOfRooms--;
				freeRooms.add(occupiedRooms.get(item.getCustomer()));
				occupiedRooms.remove(item.getCustomer());
			}
		}

		PrintWriter output = new PrintWriter(System.out);
		output.println(maxNumberOfRooms);
		for (int i = 0; i < n; i++) {
			output.print(roomAllocation[i]);
			if (i < n - 1) {
				output.print(" ");
			}
		}
		output.println();

		output.flush();

	}
}