(ns euler-clojure.problem62
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn perm-key
  [x]
  (frequencies (str x)))

(defn cubes
  []
  (map (comp #(apply * (repeat 3 %)) inc) (range)))

(defn solve
  [n]
  (loop [[c & cs] (cubes)
        perms {}]
    (let [pkey (perm-key c)]
      (if (= (dec n) (count (perms pkey)))
        (apply min (conj (perms pkey) c))
        (recur cs (assoc perms pkey (conj (perms pkey) c)))))))
