(ns info.technicallycorrect.algorithms.merge-sort-test
  (:require [clojure.test :refer :all]
            [info.technicallycorrect.algorithms.merge-sort :refer :all]))

(deftest mergesort-base-case
  (testing "Mergesort should be able to do the base cases."
    (is (= [] (mergesort [])))
    (is (= [1] (mergesort [1])))))

(deftest mergesort-five-case
  (testing "Mergesort should be able to order 5 elements."
    (is (= [1 2 3 4 5] (mergesort [4 3 2 1 5])))))

(deftest mergesort-stack-overflow
  (testing "Mergesort should not stack overflow easily."
    (let [large-array (range 100000)]
      (is (= large-array (mergesort (shuffle large-array)))))))
