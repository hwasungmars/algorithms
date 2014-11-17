(ns info.technicallycorrect.algorithms.random-contraction)

(defn- random-edge
  "Given a adjacent representation of a graph, return a random edge by its vertexes."
  [adj-rep]
  (let [v (rand-nth (keys adj-rep))
        w (rand-nth (get adj-rep v))]
    [v w]))

(defn- contract
  "Given an adjacent representation and two vertexes v w, merge the neighbours into w and remove v."
  [adj-rep v w]
  (let [v-neighbours (get adj-rep v)
        w-neighbours (get adj-rep w)
        merged-neighbours (concat v-neighbours w-neighbours)]
    (-> adj-rep (dissoc v) (assoc w merged-neighbours))))

(defn- rename-values
  "Given an adjecent representation and two vertexes v w, rename v to w."
  [adj-rep v w]
  (let [renamer (fn [new-adj [k value]]
                  (assoc new-adj k (replace {v w} value)))]
    (reduce renamer {} adj-rep)))

(defn contract-edge
  "Given an adjacent representation and two vertexes, contract the two."
  [adj-rep v w]
  (let [contracted-vertex (contract adj-rep v w)]
    (rename-values contracted-vertex v w)))

(defn remove-self-loops
  "Given an adjecent representation, remove the loops."
  [adj-rep]
  (let [reducer (fn [new-adj [k v]]
                  (assoc new-adj k (remove #{k} v)))]
    (reduce reducer {} adj-rep)))

(defn- min-cut-one-step
  "One step of the min cut algorithm."
  [adj-rep]
  (let [[v w] (random-edge adj-rep)
        contracted (contract-edge adj-rep v w)]
    (remove-self-loops contracted)))

(defn min-cut
  "Use random contraction to find the minimu cut of an adjacent graph representation.

  This algorithm is a randomised algorithm and may not return the correct value on a single run.
  However, if you run this serveral times and take the min value of the output, it is very likely it
  is correct.
  "
  [adj-rep]
  (loop [graph adj-rep]
    (let [nb-nodes (count (keys graph))]
      (if (= 2 nb-nodes)
        (let [first-neighbours (count (val (first graph)))
              last-neighbours (count (val (last graph)))]
          ;; If the following assertion fails, the graph hasn't contracted in a symmetric way.
          (assert (= first-neighbours last-neighbours))
          first-neighbours)
        (recur (min-cut-one-step graph))))))
