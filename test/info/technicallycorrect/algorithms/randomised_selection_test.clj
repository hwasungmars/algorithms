(ns info.technicallycorrect.algorithms.randomised-selection-test
  (:require [clojure.test :refer :all]
            [info.technicallycorrect.algorithms.randomised-selection :refer :all]))

(deftest randomised-selection-base-case
  (testing "Randomised selection base case with one element."
    (is (= 2 (randomised-selection 1 [2])))))

(deftest randomised-selection-simple-case
  (testing "Randomised selection simple cases with small number of elements."
    (is (= 3 (randomised-selection 2 [2 3 4 5])))
    (is (= 2 (randomised-selection 3 (shuffle (range 5)))))))

(deftest randomised-selection-stress-test
  (testing "Randomised selection with large number of elements."
    (let [large-number 10000]
      (is (= 2 (randomised-selection 3 (shuffle (range large-number))))))))
