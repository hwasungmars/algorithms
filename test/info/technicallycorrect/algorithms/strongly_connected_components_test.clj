(ns info.technicallycorrect.algorithms.strongly-connected-components-test
  (:require [clojure.test :refer :all]
            [info.technicallycorrect.algorithms.strongly-connected-components :refer :all]))

(deftest reverse-graph-test
  (testing "base case with two nodes."
    (let [graph {:a [:b]}
          expected {:b [:a]}]
      (is (= expected (reverse-graph graph)))))
  (testing "a cycling dependency."
    (let [graph {:a [:b] :b [:c] :c [:a]}
          expected {:b [:a] :c [:b] :a [:c]}]
      (is (= expected (reverse-graph graph))))))

(deftest dfs-pop-test
  (testing "base case of acyclic graph"
    (let [graph {:a [:b]}
          expected [:b :a]]
      (is (= expected (dfs-pop graph :a)))))
  (testing "simple tree"
    (let [graph {:a [:b :c]}
          expected [:b :c :a]]
      (is (= expected (dfs-pop graph :a)))))
  (testing "base case of a cyclic graph"
    (let [graph {:a [:b] :b [:a]}
          expected [:b :a]]
      (is (= expected (dfs-pop graph :a)))))
  (testing "a rectangle acyclic graph"
    (let [graph {:a [:b :c] :b [:d] :c [:d]}
          expected [:d :b :c :a]]
      (is (= expected (dfs-pop graph :a)))))
  (testing "four node cylic graph"
    (let [graph {:a [:b :c :d] :b [:c] :c [:a :d]}
          expected [:d :c :b :a]]
      (is (= expected (dfs-pop graph :a)))))
  )
