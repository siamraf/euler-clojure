(ns euler-clojure.problem32
  (:require [clojure.math.combinatorics :refer [permutations]]) )

(def digits (set "123456789"))

(defn- is-pandigital
  [a b c]
  (let [nums (str a b c)]
    (and (= digits (set nums)) (= (count digits) (count nums)))))

(defn is-prime
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (nil? (seq (filter #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6))))))))

(def prime-factors
  (memoize (fn [n]
  (cond
    (= 0 (mod n 2)) (conj (prime-factors (quot n 2)) 2)
    :else (loop [i 3 n' n factors []]
      (cond
        (= 0 (mod n' i)) (recur i (quot n' i) (conj factors i))
        (>= i (int (Math/sqrt n'))) (cond-> factors (is-prime n') (conj n'))
        :else (recur (+ i 2) n' factors)))))))

(defn proper-divisors
  [n]
  (let [pfs (prime-factors n)]
    (cons 1 (map #(apply * %) (mapcat #(combinations pfs %) (range 1 (count pfs)))))))

(defn two-factors
  [n]
  (let [divs (set (proper-divisors n))]
    (set (keep #(when (contains? divs (quot n %)) [(min % (quot n %)) (max % (quot n %)) n]) divs))))

(defn solve
  []
  (apply + (set (map last (filter #(apply is-pandigital %) (mapcat two-factors (range 1 99999)))))))
