(ns info.technicallycorrect.algorithms.random-contraction-test
  (:require [clojure.test :refer :all]
            [info.technicallycorrect.algorithms.random-contraction :refer :all]))

(deftest contract-edge-test
  (testing "Contract a edge by merging the vertexes and renaming it."
    (let [adj-g {:a [:b :c] :b [:c :a] :c [:a :b]}
          expected {:b [:b :c :c :b] :c [:b :b]}]
      (is (= expected (contract-edge adj-g :a :b))))))

(deftest remove-self-loops-test
  (testing "Remove loops."
    (let [adj-g {:b [:b :c :c :b] :c [:b :b]}
          expected {:b [:c :c] :c [:b :b]}]
      (is (= expected (remove-self-loops adj-g))))))

(deftest min-cut-base-test
  (testing "Min cut on two node graph base case."
    (let [adj-g {:a [:b :b] :b [:a :a]}
          expected 2]
      (is (= expected (min-cut adj-g))))))

(deftest min-cut-triagle-test
  (testing "Min cut on two node graph base case."
    (let [adj-g {:a [:b :c] :b [:c :a] :c [:a :b]}
          expected 2]
      (is (= expected (min-cut adj-g))))))
