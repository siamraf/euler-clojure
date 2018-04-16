(ns euler-clojure.problem36)

(defn to-binary
  [n]
  (case n
    0 []
    (conj (to-binary (quot n 2)) (rem n 2))))

(defn palindrome?
  [xs]
  (= (seq xs) (reverse (seq xs))))

(defn double-base-palindrome?
  [n]
  (let [xs (map #(Character/getNumericValue %) (str n))
        bs (to-binary n)]
    (every? palindrome? [xs bs])))

(defn solve
  []
  (apply + (filter double-base-palindrome? (range 1000000))))
