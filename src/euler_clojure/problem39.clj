(ns euler-clojure.problem39)

(defn right-angle?
  [a b c]
  (= (+ (* a a) (* b b)) (* c c)))

(defn p-compliant?
  [p a b c]
  (= p (+ a b c)))

(defn get-sols
  [p]
  (for [a (range 1 (quot p 3))
        b (range a p)
        c [(int (Math/sqrt (+ (* a a) (* b b))))]
        :when (and (right-angle? a b c) (p-compliant? p a b c))]
    [a b c]))

(defn solve
  [n]
  (map #(vector % (count (get-sols %))) (range 3 (inc n)))
  )
