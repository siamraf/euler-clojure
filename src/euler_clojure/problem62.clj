(ns euler-clojure.problem62
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn cube?
  [n]
  (= n (apply * (repeat 3 (int (Math/cbrt n))))))

(defn num->list
  [n]
  (map #(Character/getNumericValue %) (str n)))

(defn list->num
  [xs]
  (reduce (fn [acc x] (+ (* 10 acc) x)) 0 xs))

(defn cube-perms
  [n]
  (filter cube? (map list->num (filter #(not= 0 (first %)) (permutations (num->list n))))))

(defn solve
  [limit]
  (let [cubes (filter #(= limit (count %)) (map (comp #(cube-perms (apply * (repeat 3 %))) inc) (range)))]
    (first cubes)))
