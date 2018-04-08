(ns euler-clojure.problem23
  (:require [clojure.math.combinatorics :refer [combinations]]))

(defn is-prime
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (if (keep #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6)) true false)))))

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

(defn get-abundants
  [limit]
  (filter #(> (apply + (proper-divisors %)) %) (range 12 limit)))

(defn solve
  []
  (let [abundants (get-abundants 28123)
        sums-of-abundants (set (map #(apply + %)
                                    (concat (zipmap abundants abundants)
                                            (combinations abundants 2))))]
    (apply + (remove #(sums-of-abundants %) (range 1 28124)))))
