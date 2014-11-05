(ns info.technicallycorrect.algorithms.quick-sort-test
  (:require [clojure.test :refer :all]
            [info.technicallycorrect.algorithms.quick-sort :refer :all]))

(deftest quicksort-base-case
  (testing "Quicksort should be able to do the base cases."
    (is (= [] (quicksort [])))
    (is (= [1] (quicksort [1])))))

(deftest quicksort-simple-case
  (testing "Quicksort should be able to sort simple cases."
    (is (= [1 2] (quicksort [2 1])))
    (is (= [1 2 3 4 5] (quicksort [5 1 4 2 3])))))

(deftest quicksort-load-test
  (testing "Quicksort should be able to sort large arrays."
    (let [large-array (range 100000)]
      (is (= large-array (quicksort (shuffle large-array)))))))
