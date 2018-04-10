(ns euler-clojure.problem21
  (:require [clojure.math.combinatorics :refer [combinations]]))

(defn is-prime
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (nil? (seq (filter #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6))))))))

(defn prime-factors
  [n]
  (cond
    (= 0 (mod n 2)) (conj (prime-factors (quot n 2)) 2)
    :else (loop [i 3 n' n factors []]
      (cond
        (= 0 (mod n' i)) (recur i (quot n' i) (conj factors i))
        (>= i (int (Math/sqrt n'))) (cond-> factors (is-prime n') (conj n'))
        :else (recur (+ i 2) n' factors)))))

(defn proper-divisors
  [n]
  (let [pfs (prime-factors n)]
    (cons 1 (map #(apply * %) (mapcat #(combinations pfs %) (range 1 (count pfs)))))))

(defn solve
  []
  (let [ds (into {} (map #(vector % (apply + (proper-divisors %))) (range 1 10000)))]
    (apply + (filter #(and (contains? ds (ds %)) (not= % (ds %)) (= % (ds (ds %)))) (keys ds)))))
