(ns euler-clojure.problem41
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn prime?
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (nil? (seq (filter #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6))))))))

(defn list->num
  [xs]
  (reduce (fn [acc x] (+ (* 10 acc) x)) 0 xs))

(defn solve
  []
  (first (filter prime? (map list->num (permutations [7 6 5 4 3 2 1])))))
