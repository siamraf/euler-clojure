(ns euler-clojure.problem44)

(defn pent
  [n]
  (/ (* n (- (* 3 n) 1)) 2))

(defn solves?
  [p1 p2 pents]
  (assert (< (+ p1 p2) (pent 10000000)))
  (and (pents (+ p1 p2)) (pents (Math/abs (- p2 p1)))))

(defn get-gap
  [[gap-size n]]
  (- (pent (+ gap-size n)) (pent n)))

(defn solve
  []
  (let [pents (set (map pent (range 10000000)))]
      (loop [n-gaps {1 1}]
        (let [[gap-size n] (apply min-key get-gap n-gaps)]
          (if (= gap-size (count n-gaps))
            (recur (assoc n-gaps (inc gap-size) 1))
            (if (solves? (pent n) (pent (+ gap-size n)) pents)
              [n (+ gap-size n)]
              (recur (assoc n-gaps gap-size (inc n)))))))))
