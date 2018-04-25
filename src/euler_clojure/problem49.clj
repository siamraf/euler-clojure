(ns euler-clojure.problem49
  (:require [clojure.math.combinatorics :refer [combinations]]))

(defn perm-key
  [n]
  (frequencies (str n)))

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

(defn solve
  [n]
  (let [nprimes (drop-while #(< (count (str %)) n) (sieve (apply * (repeat n 10))))
        perms (vals (group-by perm-key nprimes))
        triplets (mapcat #(combinations % 3) (map sort (filter #(>= (count %) 3) perms)))]
    (prn (sort (apply concat triplets)))
    (filter (fn [[a b c]] (= (- b a) (- c b))) triplets)))
