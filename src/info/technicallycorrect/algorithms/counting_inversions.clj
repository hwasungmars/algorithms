(ns info.technicallycorrect.algorithms.counting-inversions)

(defn- merge-impl
  "The merge routine with inversion counting."
  [[first-array first-count] [second-array second-count]]
  (loop [X first-array
         Y second-array
         merged []
         inversions 0]
    (if (or (empty? X) (empty? Y))
      [(concat merged X Y) (+ first-count second-count inversions)]
      (let [[x & xrest] X
            [y & yrest] Y]
        (if (<= x y)
          (recur xrest Y (conj merged x) inversions)
          (recur X yrest (conj merged y) (+ inversions (count X))))))))

(defn- count-impl
  "Implementation of the count-inversion using merge sort."
  [[array current-count]]
  (if (> current-count 0)
    [array current-count]
    (if (< (count array) 2)
      [array 0]
      (let [[X Y] (split-at (/ (count array) 2) array)]
        (merge-impl (count-impl [X -1]) (count-impl [Y -1]))))))

(defn count-inversion
  "Count the inversion of a given integer array.

  We use merge sort underneath thus the running time is O(n log n).
  We use -1 to signal that the count hasn't been done.
  "
  [x]
  (let [[_ inversions] (count-impl [x -1])]
    inversions))
