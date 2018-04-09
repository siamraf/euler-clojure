(ns euler-clojure.problem24
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn solve
  []
  (nth (permutations (range 10)) 999999))
