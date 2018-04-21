(ns euler-clojure.problem43
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn list->num
  [xs]
  (reduce (fn [acc x] (+ (* 10 acc) x)) 0 xs))

(defn sub-string-div?
  [xs]
  (let [nums [2 3 5 7 11 13 17]
        sub-xs (map #(list->num (take 3 (drop % xs))) (range 1 8))]
    (every? identity (map #(== 0 (mod %1 %2)) sub-xs nums))))

(defn solve
  []
  (apply + (map list->num (filter sub-string-div? (permutations [9 8 7 6 5 4 3 2 1 0])))))
