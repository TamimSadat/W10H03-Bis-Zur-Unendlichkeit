package pgdp.infinite.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class InfiniteNode<T> {

    private final InfiniteTree<T> tree;
    private final InfiniteNode<T> parent;
    private final T value;
    private List<InfiniteNode<T>> childrenNode;
    public InfiniteNode(InfiniteTree<T> tree, T value, InfiniteNode<T> parent) {
        this.parent = parent;
        this.tree = tree;
        this.value = value;
        this.childrenNode = new ArrayList<>();
    }
    public T getValue() {
        return value;
    }
    public InfiniteNode<T> getParent() {
        return parent;
    }
    /**
     * @return Gibt alle bisher berechneten Kinder des Knotens zurück.
     */
    public List<InfiniteNode<T>> getChildren() {
        // TODO: Implementieren.
        return childrenNode;
    }
    /**
     * Berechnet das nächste Kind des Knotens und gibt es zurück.
     * @return das neue Kind oder null, wenn es keine weiteren Kinder gibt.
     */
    private int currentChild = 0;
    public InfiniteNode<T> calculateNextChild() {
        // TODO: Implementieren.
        if (currentChild == 0) {
            childrenNode.clear();
        }
        List<T> children = new ArrayList<>();
        tree.children.apply(value).forEachRemaining(children::add);//Quelle: https://www.baeldung.com/java-convert-iterator-to-list Absatz -> "Using Java 8 Iterator.forEachRemaining"
        if (currentChild >= children.size()) {
            return null;
        }
        InfiniteNode<T> childNode = new InfiniteNode<>(tree, children.get(currentChild++), this);
        childrenNode.add(childNode);
        return childNode;
    }
    /**
     * Berechnet alle Kinder des Knotens.
     */
    public void calculateAllChildren() {
        // TODO: Implementieren.
        childrenNode.clear();
        List<T> allChildrenOfNode = new ArrayList<>();
        tree.children.apply(value).forEachRemaining(allChildrenOfNode::add);
        for (T child : allChildrenOfNode) {
            InfiniteNode<T> childNode = new InfiniteNode<>(tree, child, this);
            childrenNode.add(childNode);
        }
    }
    /**
     * @return true, wenn alle Kinder berechnet wurden, false sonst.
     */
    public boolean isFullyCalculated() {
        // TODO: Implementieren.
        List<T> childrenCalculated = new ArrayList<>();
        tree.children.apply(value).forEachRemaining(childrenCalculated::add);
        return childrenCalculated.size() == childrenNode.size();
    }

    /**
     * Setzt die gesamte Berechnung des Knotens zurück.
     */
    public void resetChildren() {
        // TODO: Implementieren.
        childrenNode.clear();
        currentChild = 0;
    }

}
