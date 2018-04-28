(ns euler-clojure.problem53)

(def fac
  (memoize
    (fn [n] (apply * (range 2N (bigint (inc n)))))))

(defn ncr
  [n r]
  (/ (fac n) (* (fac r) (fac (- n r)))))

(defn solve
  [n-lim]
  (count
    (filter #(> % 1000000)
            (for [n (range 1 (inc n-lim))
                  r (range 1 n)]
              (ncr n r)))))
