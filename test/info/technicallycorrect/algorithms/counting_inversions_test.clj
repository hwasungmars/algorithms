(ns info.technicallycorrect.algorithms.counting-inversions-test
  (:require [clojure.test :refer :all]
            [info.technicallycorrect.algorithms.counting-inversions :refer :all]))

(deftest base-cases
  (testing "Empty sequence and single element sequence should return 0."
    (is (= 0 (count-inversion [])))
    (is (= 0 (count-inversion [1])))))

(deftest simple-cases
  (testing "Basic tuple test."
    (is (= 0 (count-inversion [1 2])))
    (is (= 1 (count-inversion [2 1])))))

(deftest more-cases
  (testing "Inversion should count 3 and 5 elements."
    (is (= 1 (count-inversion [1 3 2])))
    (is (= 5 (count-inversion [2 3 5 4 1])))))

(deftest stress-test
  (testing "Inversion should be able to deal with large inverted arrays."
    (let [large-number 10000
          ;; Maximum inversion is large-number choose 2
          expected (/ (* large-number (- large-number 1)) 2)]
      (is (= expected (count-inversion (range large-number 0 -1)))))))
