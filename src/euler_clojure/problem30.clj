(ns euler-clojure.problem30)

(defn is-power-of-digits
  [power n]
  (= n (apply + (map #(int (Math/pow (Character/getNumericValue %) power)) (str n)))))

(defn solve
  [n]
  (filter (partial is-power-of-digits n) (map (partial + 10) (range))))
