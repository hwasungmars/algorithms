(ns info.technicallycorrect.algorithms.merge-sort)

(defn mrg
  "Merge the two arrays."
  [first-array second-array]
  (loop [X first-array
         Y second-array
         R []]
    (if (or (empty? X) (empty? Y))
      (concat R X Y)
      (let [[x & xrest] X
            [y & yrest] Y]
        (if (<= x y)
          (recur xrest Y (conj R x))
          (recur X yrest (conj R y)))))))

(defn mergesort
  "Merge sort an array."
  [input]
  (if (< (count input) 2)
    input
    (let [[x y] (split-at (/ (count input) 2) input)]
      (mrg (mergesort x) (mergesort y)))))
