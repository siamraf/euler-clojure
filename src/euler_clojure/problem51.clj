(ns euler-clojure.problem51
  (:require [clojure.math.combinatorics :refer [combinations]]))

(defn sieve
  [n]
  (let [xs (vec (range 2 (inc n)))
        ys (range 2 (inc (int (Math/sqrt n))))]
    (remove nil?
      (reduce
        (fn [acc y]
          (if (acc (- y 2))
            (reduce
              (fn [acc' y'] (assoc acc' (- y' 2) nil))
              acc
              (range (* y y) (inc n) y))
            acc))
        xs
        ys))))

(defn remove-occurences
  [p xs]
  (apply str (map second (filter #(not ((set xs) (first %))) (zipmap (range) (str p))))))

(defn occurences
  [p x]
  (map first (filter #(= x (second %)) (zipmap (range) (str p)))))

(defn family-keys
  [p]
  (mapcat #(let [os (occurences p (first %))
              os (mapcat (partial combinations os) (range 2 (inc (count os))))]
          (map (fn [x] (vector (remove-occurences p x) x)) os)) (filter #(>= (second %) 2) (frequencies (str p)))))

(defn solve
  [family-size limit]
  (let [families (group-by first (mapcat #(zipmap (family-keys %) (repeatedly (constantly %))) (sieve limit)))]
    (filter #(= (count (second %)) family-size) families)))
