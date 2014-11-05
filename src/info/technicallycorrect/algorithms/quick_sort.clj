(ns info.technicallycorrect.algorithms.quick-sort)

(defn quicksort
  "Quicksort implmentation without in memory updates."
  [array]
  (if (< (count array) 2)
    array
    (let [pivot (nth array (rand-int (count array)))
          {X true Y false} (group-by #(< % pivot) array)]
      (concat (quicksort X) (quicksort Y)))))
