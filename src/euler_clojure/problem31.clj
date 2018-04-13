(ns euler-clojure.problem31)

(def find-ways
  (memoize
    (fn [target total current-coin]
      (apply + (cond
        (= total target) [1]
        (> total target) []
        :else (case current-coin
          1 (map #(find-ways target (+ % total) %) [1 2 5 10 20 50 100 200])
          2 (map #(find-ways target (+ % total) %) [2 5 10 20 50 100 200])
          5 (map #(find-ways target (+ % total) %) [5 10 20 50 100 200])
          10 (map #(find-ways target (+ % total) %) [10 20 50 100 200])
          20 (map #(find-ways target (+ % total) %) [20 50 100 200])
          50 (map #(find-ways target (+ % total) %) [50 100 200])
          100 (map #(find-ways target (+ % total) %) [100 200])
          200 (map #(find-ways target (+ % total) %) [200])))))))

(defn solve
  [target]
  (find-ways target 0 1))
