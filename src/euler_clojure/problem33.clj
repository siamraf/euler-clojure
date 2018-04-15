(ns euler-clojure.problem33)

(defn curious-simplify?
  [[n1 n2 :as n] [d1 d2 :as d]]
  (when (and (not= 1 (count (set d))) (not= 1 (count (set n))) (= 1 (count (clojure.set/difference (set n) (set d)))))
    (let [actual (/ (+ (* 10 n1) n2) (+ (* 10 d1) d2))
          n' (first (clojure.set/difference (set n) (set d)))
          d' (first (clojure.set/difference (set d) (set n)))
          simple (when (and n' d' (not= 0 d') (not (and (zero? n2) (zero? d2)))) (/ n' d'))]
      (= actual simple))))

(defn get-curious
  []
  (filter #(apply curious-simplify? %)
    (for [a (range 10 99)
          b (range (inc a) 99)]
      (let [as (map #(Character/getNumericValue %) (str a))
            bs (map #(Character/getNumericValue %) (str b))]
        [as bs]))))

(defn solve
  []
  (apply * (map (fn [[[n1 n2] [d1 d2]]] (/ (+ (* 10 n1) n2) (+ (* 10 d1) d2))) (get-curious))))
