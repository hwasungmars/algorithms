(ns info.technicallycorrect.algorithms.quick-sort)

(defn quicksort
  "Quicksort implmentation without in memory updates."
  [array]
  (if (< (count array) 2)
    array
    (let [pivot (rand-nth array)
          {X true Y false} (group-by #(< % pivot) array)]
      (concat (quicksort X) (quicksort Y)))))
