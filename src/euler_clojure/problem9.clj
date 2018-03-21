(ns euler-clojure.problem9)

(defn is-pythag
  [a b c]
  (= (+ (* a a) (* b b)) (* c c)))

(defn solve
  []
  (for [c (range 3 1001)
        b (range 2 c)
        a (range 1 b)
        :when (and (= 1000 (+ a b c)) (is-pythag a b c))]
    (* a b c)))
