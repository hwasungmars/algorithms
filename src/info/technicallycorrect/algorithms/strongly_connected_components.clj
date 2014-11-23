(ns info.technicallycorrect.algorithms.strongly-connected-components)

(defn- reverser
  "Given a kv of a graph, generate the reversal of the kv."
  [k v]
  (loop [result {}
         [vfirst & vrest] v]
    (if (nil? vfirst) result
      (recur (assoc result vfirst [k]) vrest))))

(defn reverse-graph
  "Reverse a adjacent representation of a graph."
  [adj-graph]
  (let [reversed-comp (map #(apply reverser %) adj-graph)]
    (apply merge-with concat reversed-comp)))

(defn dfs-pop-impl
  "dfs-pop implementation.

  This implementation is rather dense because we want to cover cases with cyclic graphs.
  Input: adjacent graph representation, node to start from, known nodes as a set
  Output: [known-nodes popped-nodes] where popped-nodes are the newly popped ones in that path.
  known-nodes act as a global state thus passed along whenever it is updated.
  "
  [adj-graph node known]
  (if (.contains known node) [known []]
    (let [children (get adj-graph node)]
      (loop [[child & child-rest] children
             seen (conj known node)
             result []]
        (if (nil? child) [seen (concat result [node])]
          (let [[updated-seen newly-popped] (dfs-pop-impl adj-graph child seen)]
            (recur child-rest
                   updated-seen
                   (concat result newly-popped))))))))

(defn dfs-pop
  "Return the order of the nodes being popped from dfs search."
  [adj-graph node]
  (let [[known popped] (dfs-pop-impl adj-graph node #{})]
    popped))
