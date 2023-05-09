package com.company;

import com.company.source.Entry;
import com.company.source.SortedPositionalMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SortedPositionalMap<Integer, String> map = new SortedPositionalMap<>();
        map.put(10, "Value 10");
        map.put(20, "Value 20");
        map.put(20, "Value 20");
        map.put(30, "Value 30");
        map.put(15,"Value 15");

        // Test with existing key
        Entry<Integer, String> entry1 = map.higherEntry(3);
        System.out.println(entry1.getKey() + " - " + entry1.getValue());

        Entry<Integer, String> entry2 = map.higherEntry(3);
        System.out.println(entry2.getKey() + " - " + entry2.getValue());
        Entry<Integer, String> entry3 = map.higherEntry(10);
        System.out.println(entry3.getKey() + " - " + entry3.getValue());
        Entry<Integer, String> entry4 = map.higherEntry(11);
        System.out.println(entry3.getKey() + " - " + entry3.getValue());
//        System.out.println(map.entrySet());
        System.out.println(map.firstEntry());
        System.out.println(map.lastEntry());
        System.out.println(map.entrySet());

        System.out.println(map.subMap(15,30));
        System.out.println(map.subMap(10,30));
//        System.out.println(map.subMap(7,10));

        map.remove(10);
        map.remove(15);
        map.remove(20);
        map.remove(30);
        // Add entries to the map
        map.put(10, "Value 10");
        map.put(20, "Value 20");
        map.put(30, "Value 30");
        map.put(40, "Value 40");
        map.put(50, "Value 50");

        // Test ceilingEntry
        System.out.println("Ceiling Entry for key 15: " + map.ceilingEntry(15));
        System.out.println("Ceiling Entry for key 25: " + map.ceilingEntry(25));
        System.out.println("Ceiling Entry for key 35: " + map.ceilingEntry(35));
        System.out.println("Ceiling Entry for key 45: " + map.ceilingEntry(45));

        // Test floorEntry
        System.out.println("Floor Entry for key 15: " + map.floorEntry(10));
        System.out.println("Floor Entry for key 25: " + map.floorEntry(25));
        System.out.println("Floor Entry for key 35: " + map.floorEntry(35));
        System.out.println("Floor Entry for key 40: " + map.floorEntry(40));

        // Test lowerEntry
//        System.out.println("Lower Entry for key 10: " + map.lowerEntry(10));
        System.out.println("Lower Entry for key 25: " + map.lowerEntry(25));
        System.out.println("Lower Entry for key 35: " + map.lowerEntry(35));
        System.out.println("Lower Entry for key 40: " + map.lowerEntry(40));

        // Test higherEntry
        System.out.println("Higher Entry for key 15: " + map.higherEntry(15));
        System.out.println("Higher Entry for key 25: " + map.higherEntry(25));
        System.out.println("Higher Entry for key 35: " + map.higherEntry(35));
        System.out.println("Higher Entry for key 45: " + map.higherEntry(45));

    }
}
