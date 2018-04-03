(ns euler-clojure.problem15)

(def recurse
  (memoize
    (fn [x y n]
      (cond
        (= n y x) 1
        (= n y) 1
        (= n x) 1
        :else (+ (recurse (inc x) y n) (recurse x (inc y) n))))))

(defn solve
  [n]
  (recurse 0 0 n))
