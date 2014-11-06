(ns info.technicallycorrect.algorithms.randomised-selection)

(defn randomised-selection
  "Select the nth order element in linear time."
  [n array]
  (let [pivot (rand-nth array)
        {smaller-equal true larger false} (group-by #(<= % pivot) array)]
    (cond (= (count smaller-equal) n) pivot
          (> (count smaller-equal) n) (randomised-selection n smaller-equal)
          :else (randomised-selection (- n (count smaller-equal)) larger))))
