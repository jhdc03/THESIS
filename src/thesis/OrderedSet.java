/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;

/**
 *
 * @author harve
 */
abstract class OrderedSet {
    abstract void insert(Comparable x);
    abstract Comparable  removeFirst();
    abstract int size();
    abstract Comparable remove(Comparable x);
}
    