package com.company.source;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import com.company.source.Entry;
import com.company.source.AbstractMap.MapEntry;

public class SortedPositionalMap<K,V> extends AbstractSortedMap<K,V> {
    private LinkedPositionalList<MapEntry<K,V>> posTable = new LinkedPositionalList<>();
    private Comparator<K> comparator;
    public SortedPositionalMap(){super();}
    public SortedPositionalMap(Comparator<K> comp){super(comp);}

    private Position<AbstractMap.MapEntry<K,V>> findPosition(K key) throws IllegalArgumentException
    {
        for (Position<AbstractMap.MapEntry<K,V>> pos:posTable.positions()) {
            if(compare(key,pos.getElement().getKey())==0)
                {
                return pos;
                }
            }return null;
    }

    private Iterable<Entry<K,V>> snapshot(K f,K l) throws IllegalArgumentException {
        LinkedPositionalList<Entry<K, V>> buffer = new LinkedPositionalList<>();
        if (posTable.isEmpty()) {
            return null;
        }
        Position<MapEntry<K, V>> firstPos = findPosition(f);
        Position<MapEntry<K, V>> lastPos = findPosition(l);
        if (firstPos != null && lastPos != null) {
            Position<MapEntry<K, V>> cursor = firstPos;
            while (cursor != null) {
                buffer.addLast(cursor.getElement());
                cursor=posTable.after(cursor);
            }
            return buffer;
        } throw new IllegalArgumentException("start or finish key is not found");
    }
    public int size()   //returns the size of the map
    {
        return posTable.size();
    }

    @Override
    public V get(K key) {
        checkKey(key);
        Position<AbstractMap.MapEntry<K,V>> needed = findPosition(key);
        if (needed.equals(null)){
            return null;
        }
        return needed.getElement().getValue();
    }

    @Override
    public V put(K key, V value) {
        checkKey(key);
        Position<MapEntry<K, V>> cursor = findPosition(key);
        if (cursor == null) {
            MapEntry<K, V> newEntry = new MapEntry<>(key, value);
            Position<MapEntry<K, V>> insertionPosition = posTable.first();
            while (insertionPosition != null && compare(key, insertionPosition.getElement().getKey()) >= 0) {
                insertionPosition = posTable.after(insertionPosition);
            }
            if (insertionPosition == null) {
                posTable.addLast(newEntry);
            } else {
                posTable.addBefore(insertionPosition, newEntry);
            }
            return null;
        } else {
            V oldVal = cursor.getElement().getValue();
            cursor.getElement().setValue(value);
            return oldVal;
        }
    }

    @Override
    public V remove(K key) {
        checkKey(key);
        Position<AbstractMap.MapEntry<K,V>> cursor = findPosition(key);
        if (cursor == null){
            return null;
        }
        V removableValue = cursor.getElement().getValue();
        posTable.remove(cursor);
        return removableValue;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return snapshot(firstEntry().getKey(),lastEntry().getKey());
    }




    @Override
    public Entry<K, V> firstEntry() {
        Position<MapEntry<K,V>> f = posTable.first();
        return f.getElement();}

    @Override
    public Entry<K, V> lastEntry() {
       Position<MapEntry<K,V>> l = posTable.last();
       return l.getElement();
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        if (isEmpty()){
            return null;
        }
        if (key==null){throw new IllegalArgumentException("Key cannot be null");}
        Position<MapEntry<K,V>> cursor = findPosition(key);
        if (cursor == null){//case when key does not exist
            Position<MapEntry<K,V>> e = posTable.first();
            while (compare(key,e.getElement().getKey())>0 && posTable.after(e)!=null){
                e = posTable.after(e);
            }
            if (compare(key,e.getElement())<=0) {
                return e.getElement();
            }if (posTable.after(e)!=null){
                return posTable.after(e).getElement();
            }throw new IllegalArgumentException("selected key does not have ceiling entry");
        }return cursor.getElement();
    }

    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        if (isEmpty()){
            return null;
        }
        if (key==null){throw new IllegalArgumentException("Key cannot be null");}
        Position<MapEntry<K,V>> cursor = findPosition(key);
        if (cursor == null){//case when key does not exist
            Position<MapEntry<K,V>> e = posTable.first();
            while (compare(key,e.getElement().getKey())>0 && posTable.after(e)!=null){
                e = posTable.after(e);
            }
            if (compare(key,e.getElement())>=0) {
                return e.getElement();
            }if (posTable.before(e)!=null){
                return posTable.before(e).getElement();
            }throw new IllegalArgumentException("selected key does not have floor entry");
        }return cursor.getElement(); //case when it is equal to it
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        if (isEmpty()){
            return null;
        }
        if (key==null){throw new IllegalArgumentException("Key cannot be null");}
            Position<MapEntry<K,V>> e = posTable.first();
            while (compare(key,e.getElement().getKey())>0 && posTable.after(e)!=null){
                e = posTable.after(e);
            }
            if (compare(key,e.getElement())>0) {
                return e.getElement();
            }if (posTable.before(e)!=null){
                return posTable.before(e).getElement();
            }throw new IllegalArgumentException("selected key does not have a lower entry");
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        if (isEmpty()){
            return null;
        }
        if (key==null){throw new IllegalArgumentException("Key cannot be null");}
            Position<MapEntry<K,V>> e = posTable.first();
            while (compare(key,e.getElement().getKey())>0 && posTable.after(e)!=null){
                e = posTable.after(e);
            }
            if (compare(key,e.getElement())<0) {
                return e.getElement();
            }if (posTable.after(e)!=null){
                return posTable.after(e).getElement();
            }throw new IllegalArgumentException("selected key does not have higher entry");
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        if(findPosition(fromKey)!=null && findPosition(toKey)!=null){
        return snapshot(fromKey,toKey);}
        else throw new IllegalArgumentException("First/Last key is not found in subMap method");
    }


}
