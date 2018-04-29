(ns euler-clojure.problem55)

(defn palindrome?
  [n]
  (let [n-str (seq (str n))]
    (= n-str (reverse n-str))))

(defn reverse-n
  [n]
  (reduce (fn [acc x] (+ x (* 10N acc))) 0 (map #(Character/getNumericValue %) (reverse (str n)))))

(defn lychrel-iter
  [n]
  (+ n (reverse-n n)))

(defn lychrel?
  ([n] (lychrel? (lychrel-iter n) 1))
  ([n iters]
   (or (>= iters 50) (and (not (palindrome? n)) (recur (lychrel-iter n) (inc iters))))))

(defn solve
  []
  (count (filter lychrel? (range 1 10001))))
